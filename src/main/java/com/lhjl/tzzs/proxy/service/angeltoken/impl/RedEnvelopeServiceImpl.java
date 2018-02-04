package com.lhjl.tzzs.proxy.service.angeltoken.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeLogDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.RedEnvelopeResDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.UserEditService;
import com.lhjl.tzzs.proxy.service.angeltoken.RedEnvelopeService;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service("redEnvelopeService")
public class RedEnvelopeServiceImpl extends GenericService implements RedEnvelopeService {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private RedEnvelopeMapper redEnvelopeMapper;

    @Autowired
    private UserIntegralsMapper userIntegralsMapper;

    @Autowired
    private MetaObtainIntegralMapper metaObtainIntegralMapper;

    @Autowired
    private UserIntegralConsumeMapper userIntegralConsumeMapper;

    @Autowired
    private RedEnvelopeLimitMapper redEnvelopeLimitMapper;

    @Autowired
    private RedEnvelopeLogMapper redEnvelopeLogMapper;


    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersWeixinMapper usersWeixinMapper;

    private final static Integer obtainIntegralPeriod = 965;


    public Integer getUserId(Integer appId, String token){

        UserToken query = new UserToken();
        query.setToken(token);
        query.setMetaAppId(String.valueOf(appId));

        UserToken userToken = userTokenMapper.selectOne(query);

        return userToken.getUserId();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CommonDto<String> checkAndAddAngelToken(Integer appId, String token, String senceKey) {

        //日期
//        DateTime.now().dayOfMonth()

        //获取用户ID
        Integer userId = this.getUserId(appId, token);

        //根据当前日期判断用户是否已赠送令牌
        String beginTime = DateTime.now().withTime(0,0,0,0).toString("yyyy-MM-dd HH:mm:ss");
        String endTime = DateTime.now().withTime(23,59,59,999).toString("yyyy-MM-dd HH:mm:ss");

        Example userIntegralsQuery = new Example(UserIntegrals.class);
        userIntegralsQuery.and().andBetween("createTime",beginTime,endTime).andEqualTo("userId",userId).andEqualTo("appId", appId).andEqualTo("sceneKey",senceKey);


        CommonDto<BigDecimal> limitQuanlity = this.checkMaxQuanlity(appId);
        if (limitQuanlity.getData().compareTo(new BigDecimal(0))< 0){
            return new CommonDto<>("MaxLimited","success", 200);
        }

        CommonDto<BigDecimal> receiveQuantity = this.checkReceiveQuantity(appId, token);
        if (receiveQuantity.getData().compareTo(new BigDecimal(0))<= 0){
            return new CommonDto<>("DayLimited","success", 200);
        }

        List<UserIntegrals> userIntegralsList = userIntegralsMapper.selectByExample(userIntegralsQuery);
        if (null != userIntegralsList && userIntegralsList.size()>0){
            return new CommonDto<>("Completed","success", 200);
        }else{
            MetaObtainIntegral queryObtainIntegral = new MetaObtainIntegral();
            queryObtainIntegral.setSceneKey(senceKey);
            queryObtainIntegral.setUserLevel(0); // 0 代表所有的会员级别

            MetaObtainIntegral obtainIntegral = metaObtainIntegralMapper.selectOne(queryObtainIntegral);

            addUserIntegralsLog(appId, senceKey, userId, obtainIntegral.getIntegral(), obtainIntegralPeriod);



            return new CommonDto<>("Presented","success", 200);

        }



//        return null;
    }

    public void addUserIntegralsLog(Integer appId, String senceKey, Integer userId, BigDecimal obtainIntegral, Integer obtainIntegralPeriod) {
        UserIntegrals addUserIntegrals = new UserIntegrals();
        addUserIntegrals.setEndTime(DateTime.now().withTime(23,59,59,999).withFieldAdded(DurationFieldType.days(),obtainIntegralPeriod).toDate());
        addUserIntegrals.setBeginTime(DateTime.now().withTime(0,0,0,0).toDate());
        addUserIntegrals.setCreateTime(DateTime.now().toDate());
        addUserIntegrals.setSceneKey(senceKey);
        addUserIntegrals.setIntegralNum(obtainIntegral);
        addUserIntegrals.setUserId(userId);
        addUserIntegrals.setAppId(appId);

        userIntegralsMapper.insert(addUserIntegrals);

        UserIntegralConsume addUserIntegralConsume = new UserIntegralConsume();
        addUserIntegralConsume.setSceneKey(senceKey);
        addUserIntegralConsume.setUserId(userId);
        addUserIntegralConsume.setBeginTime(DateTime.now().withTime(0,0,0,0).toDate());
        addUserIntegralConsume.setEndTime(DateTime.now().withTime(23,59,59,999).withFieldAdded(DurationFieldType.days(),obtainIntegralPeriod).toDate());
        addUserIntegralConsume.setCostNum(obtainIntegral);
        addUserIntegralConsume.setCreateTime(DateTime.now().toDate());
        addUserIntegralConsume.setAppId(appId);

        userIntegralConsumeMapper.insert(addUserIntegralConsume);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CommonDto<Long> createRedEnvelope(Integer appId, RedEnvelopeDto redEnvelopeDto) {


        RedEnvelope redEnvelope = new RedEnvelope();
        redEnvelope.setAmount(redEnvelopeDto.getAmount());
        redEnvelope.setTotalAmount(redEnvelopeDto.getTotalAmount());
        redEnvelope.setAppId(appId);
        redEnvelope.setCreateTime(DateTime.now().toDate());
        redEnvelope.setQuantity(redEnvelopeDto.getQuantity());
        redEnvelope.setStatus(0);
        redEnvelope.setToken(redEnvelopeDto.getToken());

        redEnvelopeMapper.insert(redEnvelope);

        Users users = this.getUserInfo(appId, redEnvelopeDto.getToken());

        this.addUserIntegralsLog(appId,"SEND_ANGEL_TOKEN ",users.getId(),redEnvelope.getTotalAmount(),obtainIntegralPeriod);

        return new CommonDto<>(redEnvelope.getId(),"succcess", 200);
    }

    @Override
    public CommonDto<BigDecimal> checkMaxQuanlity(Integer appId) {

        RedEnvelopeLimit queryRedEnvelopeLimit = new RedEnvelopeLimit();
        queryRedEnvelopeLimit.setAppId(appId);
        queryRedEnvelopeLimit.setKey("MAX_LIMIT");
        RedEnvelopeLimit redEnvelopeLimit = redEnvelopeLimitMapper.selectOne(queryRedEnvelopeLimit);

        BigDecimal limit = redEnvelopeLimit.getLimitValue().subtract(redEnvelopeLimit.getGrantValue());

        return new CommonDto<>(limit,"success",200);
    }

    @Override
    public CommonDto<BigDecimal> checkReceiveQuantity(Integer appId, String token) {

        //根据当前日期判断用户是否已赠送令牌
        String beginTime = DateTime.now().withTime(0,0,0,0).toString("yyyy-MM-dd HH:mm:ss");
        String endTime = DateTime.now().withTime(23,59,59,999).toString("yyyy-MM-dd HH:mm:ss");

        //获取用户ID
        Integer userId = this.getUserId(appId, token);

        Example userIntegralsQuery = new Example(UserIntegrals.class);
        userIntegralsQuery.and().andBetween("createTime",beginTime,endTime).andEqualTo("userId",userId).andEqualTo("appId", appId);

        List<UserIntegrals> userIntegralsList = userIntegralsMapper.selectByExample(userIntegralsQuery);

        BigDecimal quanlity = new BigDecimal("0");

        userIntegralsList.forEach(item -> {
            quanlity.add(item.getIntegralNum());
        });

        RedEnvelopeLimit queryRedEnvelopeLimit = new RedEnvelopeLimit();
        queryRedEnvelopeLimit.setAppId(appId);
        queryRedEnvelopeLimit.setKey("DAY_LIMIT");
        RedEnvelopeLimit redEnvelopeLimit = redEnvelopeLimitMapper.selectOne(queryRedEnvelopeLimit);
        BigDecimal limit = redEnvelopeLimit.getLimitValue().subtract(quanlity);


        return new CommonDto<>(limit,"success",200);
    }


    @Override
    public CommonDto<BigDecimal> checkRemainingBalance(Integer appId, String token) {

        //获取用户ID
        Integer userId = this.getUserId(appId, token);

        UserIntegralConsume userIntegralConsumeQuery = new UserIntegralConsume();
        userIntegralConsumeQuery.setAppId(appId);
        userIntegralConsumeQuery.setUserId(userId);

        List<UserIntegralConsume> userIntegralConsumes = userIntegralConsumeMapper.select(userIntegralConsumeQuery);
        BigDecimal totalAmount = new BigDecimal(0);

        userIntegralConsumes.forEach(item -> {
            totalAmount.add(item.getCostNum());
        });


        return new CommonDto<>(totalAmount, "success", 200);
    }


    @Override
    public CommonDto<RedEnvelopeResDto> receiveRedEnvelope(Integer appId, Long redEnvelopeId, String token) {

        CommonDto<RedEnvelopeResDto> result = new CommonDto<>();
        RedEnvelopeResDto redEnvelopeResDto = new RedEnvelopeResDto();

        Users users = this.getUserInfo(appId,token);
        if (StringUtil.isNotEmpty(users.getHeadpicReal())) {
            redEnvelopeResDto.setHeadPic(users.getHeadpicReal());
        }else{
            redEnvelopeResDto.setHeadPic(users.getHeadpic());
        }
        if (StringUtil.isNotEmpty(users.getActualName())) {
            redEnvelopeResDto.setNeckName(users.getActualName());
        }else {
            UsersWeixin queryUserWeixin = new UsersWeixin();
            queryUserWeixin.setUserId(users.getId());

            UsersWeixin usersWeixin = usersWeixinMapper.selectOne(queryUserWeixin);
            if (null != usersWeixin) {
                redEnvelopeResDto.setNeckName(usersWeixin.getNickName());
            }
        }

        //尝试3次获取红包
        for (int i =0 ;i < 3; i++) {
            RedEnvelope redEnvelope = redEnvelopeMapper.selectByPrimaryKey(redEnvelopeId);

            redEnvelopeResDto.setAmount(redEnvelope.getAmount());
            redEnvelopeResDto.setQuantity(redEnvelope.getReceiveQuantity());
            redEnvelopeResDto.setTotalQuantity(redEnvelope.getQuantity());

            if (redEnvelope.getQuantity() == redEnvelope.getReceiveQuantity()){

                if (StringUtil.isEmpty(redEnvelopeResDto.getMessage())){
                    redEnvelopeResDto.setMessage(redEnvelope.getMessage());
                }
                redEnvelopeResDto.setStatus("Finished");
                break;
            }else {
                Example example = new Example(RedEnvelope.class);
                example.and().andEqualTo("appId", appId).andEqualTo("id", redEnvelopeId).andEqualTo("receiveQuantity", redEnvelope.getReceiveQuantity());

                redEnvelope.setReceiveQuantity(redEnvelope.getReceiveQuantity()+1);
                if (redEnvelopeMapper.updateByExample(redEnvelope, example) > 0) {

                    redEnvelopeResDto.setStatus("Received");

                    RedEnvelopeLog redEnvelopeLog = new RedEnvelopeLog();
                    redEnvelopeLog.setAmount(redEnvelope.getAmount());
                    redEnvelopeLog.setAppId(appId);
                    redEnvelopeLog.setCreateTime(DateTime.now().toDate());
                    redEnvelopeLog.setRedEnvelopeId(redEnvelopeId);
                    redEnvelopeLog.setToken(token);
                    redEnvelopeLogMapper.insert(redEnvelopeLog);

                    this.addUserIntegralsLog(appId,"GET_ANGEL_TOKEN",users.getId(),redEnvelope.getAmount(),obtainIntegralPeriod);

                    break;
                }
            }
        }

        RedEnvelopeLog queryRedEnvelope = new RedEnvelopeLog();
        queryRedEnvelope.setRedEnvelopeId(redEnvelopeId);
        List<RedEnvelopeLog> redEnvelopeLogs = redEnvelopeLogMapper.select(queryRedEnvelope);

        List<RedEnvelopeLogDto> redEnvelopeLogDtos = new ArrayList<>();

        redEnvelopeLogs.forEach(item -> {
            RedEnvelopeLogDto redEnvelopeLogDto = new RedEnvelopeLogDto();
            redEnvelopeLogDto.setAmount(item.getAmount());
            redEnvelopeLogDto.setCreateTime(item.getCreateTime());
            redEnvelopeLogDto.setToken(item.getToken());
            Users temp = this.getUserInfo(appId,item.getToken());
            if (StringUtil.isNotEmpty(temp.getHeadpicReal())) {
                redEnvelopeLogDto.setHeadPic(temp.getHeadpicReal());
            }else{
                redEnvelopeLogDto.setHeadPic(temp.getHeadpic());
            }
            if (StringUtil.isNotEmpty(temp.getActualName())) {
                redEnvelopeLogDto.setName(temp.getActualName());
            }else {
                UsersWeixin usersWeixin = this.usersWeixinMapper.selectByPrimaryKey(temp.getId());
                redEnvelopeLogDto.setName(usersWeixin.getNickName());
            }
            redEnvelopeLogDtos.add(redEnvelopeLogDto);
        });

        redEnvelopeResDto.setRedEnvelopeLogs(redEnvelopeLogDtos);
        result.setData(redEnvelopeResDto);

        return result;
    }


    public Users getUserInfo(Integer appId,String token){
        UserToken queryUserToken = new UserToken();
        queryUserToken.setToken(token);
        queryUserToken.setMetaAppId(String.valueOf(appId));
        UserToken userToken = userTokenMapper.selectOne(queryUserToken);

        Users users = usersMapper.selectByPrimaryKey(userToken.getUserId());

        return users;
    }

    public static void main(String[] args) {
        System.out.println(DateTime.now().withTime(0,0,0,0).withFieldAdded(DurationFieldType.days(),30).toString("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(DateTime.now().withTime(23,59,59,999).toString("yyyy-MM-dd HH:mm:ss"));
    }
}
