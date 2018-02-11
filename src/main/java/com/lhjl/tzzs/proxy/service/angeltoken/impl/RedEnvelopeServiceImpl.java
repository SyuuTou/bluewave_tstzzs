package com.lhjl.tzzs.proxy.service.angeltoken.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.github.pagehelper.PageHelper;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.angeltoken.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.angeltoken.RedEnvelopeService;
import com.lhjl.tzzs.proxy.service.common.SessionKeyService;
import com.lhjl.tzzs.proxy.utils.MD5Util;

import org.apache.ibatis.session.RowBounds;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.*;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicReference;

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

    @Autowired
    private SessionKeyService sessionKeyService;

    @Autowired
    private RedEnvelopeWechatgroupMapper redEnvelopeWechatgroupMapper;

    @Autowired
    private WxMaService wxService;



    private final static Integer obtainIntegralPeriod = 965;


    public Integer getUserId(Integer appId, String token){

        UserToken query = new UserToken();
        query.setToken(token);
        query.setMetaAppId(String.valueOf(appId));

        UserToken userToken = userTokenMapper.selectOne(query);

        if (null == userToken){
            return null;
        }

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


            addUserIntegralsLog(appId, senceKey, userId, obtainIntegral.getIntegral(), obtainIntegralPeriod,true, new BigDecimal(1));



            return new CommonDto<>("Presented","success", 200);

        }



//        return null;
    }

    public void addUserIntegralsLog(Integer appId, String senceKey, Integer userId, BigDecimal obtainIntegral, Integer obtainIntegralPeriod, Boolean flag, BigDecimal plusOrMinus) {
        try {
            BigDecimal obtainIntegralCopy = obtainIntegral;
            if (plusOrMinus.compareTo(new BigDecimal(0))>=0) {
                UserIntegrals addUserIntegrals = new UserIntegrals();
                addUserIntegrals.setEndTime(DateTime.now().withTime(23, 59, 59, 999).withFieldAdded(DurationFieldType.days(), obtainIntegralPeriod).toDate());
                addUserIntegrals.setBeginTime(DateTime.now().withTime(0, 0, 0, 0).toDate());
                addUserIntegrals.setCreateTime(DateTime.now().toDate());
                addUserIntegrals.setSceneKey(senceKey);
                addUserIntegrals.setIntegralNum(obtainIntegralCopy);
                addUserIntegrals.setUserId(userId);
                addUserIntegrals.setAppId(appId);

                userIntegralsMapper.insert(addUserIntegrals);
            }else{
                UserIntegrals query = new UserIntegrals();
                query.setAppId(appId);
                query.setUserId(userId);
                List<UserIntegrals> userIntegralsList = userIntegralsMapper.select(query);
                BigDecimal temp = null;
                for (UserIntegrals userIntegrals : userIntegralsList){
                    if (null != userIntegrals.getConsumeNum()) {
                        temp = userIntegrals.getIntegralNum().add(userIntegrals.getConsumeNum());
                    }else {
                        temp = userIntegrals.getIntegralNum();
                        userIntegrals.setConsumeNum(new BigDecimal(0));
                    }

                    if (temp.compareTo(new BigDecimal(0)) > 0  ){
                        if (temp.compareTo(obtainIntegralCopy) >= 0){
                            userIntegrals.setConsumeNum(userIntegrals.getConsumeNum().add(obtainIntegralCopy.multiply(new BigDecimal(-1))));
                            obtainIntegralCopy = new BigDecimal(0);
                        }else{
                            userIntegrals.setConsumeNum(userIntegrals.getIntegralNum().multiply(new BigDecimal(-1)));
                            obtainIntegralCopy = obtainIntegralCopy.subtract(temp);
                        }
                        userIntegralsMapper.updateByPrimaryKey(userIntegrals);
                    }

                    if (obtainIntegralCopy.compareTo(new BigDecimal(0)) == 0){
                        break;
                    }
                }
            }

            UserIntegralConsume addUserIntegralConsume = new UserIntegralConsume();
            addUserIntegralConsume.setSceneKey(senceKey);
            addUserIntegralConsume.setUserId(userId);
            addUserIntegralConsume.setBeginTime(DateTime.now().withTime(0,0,0,0).toDate());
            addUserIntegralConsume.setEndTime(DateTime.now().withTime(23,59,59,999).withFieldAdded(DurationFieldType.days(),obtainIntegralPeriod).toDate());
            addUserIntegralConsume.setCostNum(obtainIntegral.multiply(plusOrMinus));
            addUserIntegralConsume.setCreateTime(DateTime.now().toDate());
            addUserIntegralConsume.setAppId(appId);

            userIntegralConsumeMapper.insert(addUserIntegralConsume);

            if (flag) {
                RedEnvelopeLimit redEnvelopeLimit = this.getRedEnvelopeLimit(appId, "MAX_LIMIT");

                redEnvelopeLimit.setGrantValue(redEnvelopeLimit.getGrantValue().add(obtainIntegral));

                redEnvelopeLimitMapper.updateByPrimaryKey(redEnvelopeLimit);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CommonDto<String> createRedEnvelope(Integer appId, RedEnvelopeDto redEnvelopeDto) {

        if (null == redEnvelopeDto.getRedEnvelopeType()){
            return new CommonDto<>("","红包类型不可为空",200);
        }else if (redEnvelopeDto.getRedEnvelopeType() == 0) {
            if (redEnvelopeDto.getAmount().compareTo(new BigDecimal(0)) <= 0) {
                return new CommonDto<>("", "红包金额必须大于0", 200);
            }
        }else if (redEnvelopeDto.getRedEnvelopeType() == 1){
            if (redEnvelopeDto.getTotalAmount().compareTo(new BigDecimal(0)) <= 0) {
                return new CommonDto<>("", "红包总金额必须大于0", 200);
            }
        }

        if(redEnvelopeDto.getQuantity()<=0){
            return new CommonDto<>("","红包数量必须大于0",200);
        }

        RedEnvelope redEnvelope = new RedEnvelope();
        redEnvelope.setAmount(redEnvelopeDto.getAmount());
        redEnvelope.setTotalAmount(redEnvelopeDto.getTotalAmount());
        redEnvelope.setAppId(appId);
        redEnvelope.setCreateTime(DateTime.now().toDate());
        redEnvelope.setQuantity(redEnvelopeDto.getQuantity());
        redEnvelope.setStatus(0);
        redEnvelope.setReceiveQuantity(0);
        redEnvelope.setReceiveAmount(new BigDecimal(0));
        redEnvelope.setToken(redEnvelopeDto.getToken());
        redEnvelope.setUnionKey(MD5Util.md5Encode(DateTime.now().millisOfDay().getAsString(),""));
        redEnvelope.setMessage(redEnvelopeDto.getMessage());
        redEnvelope.setRedEnvelopeType(redEnvelopeDto.getRedEnvelopeType());
        redEnvelope.setDescription(redEnvelopeDto.getDescription());

        redEnvelopeMapper.insert(redEnvelope);

        Users users = this.getUserInfo(appId, redEnvelopeDto.getToken());
        if (!redEnvelopeDto.getDescription().equals("INVITATIONED")) {
            this.addUserIntegralsLog(appId, "SEND_ANGEL_TOKEN ", users.getId(), redEnvelope.getTotalAmount(), obtainIntegralPeriod, false, new BigDecimal(-1));
        }
        return new CommonDto<>(redEnvelope.getUnionKey(),"succcess", 200);
    }

    @Override
    public CommonDto<BigDecimal> checkMaxQuanlity(Integer appId) {

        RedEnvelopeLimit redEnvelopeLimit = getRedEnvelopeLimit(appId, "MAX_LIMIT");

        BigDecimal limit = redEnvelopeLimit.getLimitValue().subtract(redEnvelopeLimit.getGrantValue());

        return new CommonDto<>(limit,"success",200);
    }


    public RedEnvelopeLimit getRedEnvelopeLimit(Integer appId, String max_limit) {
        RedEnvelopeLimit queryRedEnvelopeLimit = new RedEnvelopeLimit();
        queryRedEnvelopeLimit.setAppId(appId);
        queryRedEnvelopeLimit.setKey(max_limit);
        return redEnvelopeLimitMapper.selectOne(queryRedEnvelopeLimit);
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

        AtomicReference<BigDecimal> quanlity = new AtomicReference<>(new BigDecimal("0"));

        userIntegralsList.forEach(item -> {
            quanlity.set(quanlity.get().add(item.getIntegralNum()));
        });

        RedEnvelopeLimit redEnvelopeLimit = getRedEnvelopeLimit(appId, "DAY_LIMIT");
        BigDecimal limit = redEnvelopeLimit.getLimitValue().subtract(quanlity.get());


        return new CommonDto<>(limit,"success",200);
    }


    @Override
    public CommonDto<BigDecimal> checkRemainingBalance(Integer appId, String token) {

        //获取用户ID
        Integer userId = this.getUserId(appId, token);

        if (null == userId){
            return new CommonDto<>(new BigDecimal(0), "未找到用户", 200);
        }
        UserIntegralConsume userIntegralConsumeQuery = new UserIntegralConsume();
        userIntegralConsumeQuery.setAppId(appId);
        userIntegralConsumeQuery.setUserId(userId);

        List<UserIntegralConsume> userIntegralConsumes = userIntegralConsumeMapper.select(userIntegralConsumeQuery);
        BigDecimal totalAmount = new BigDecimal("0.00");

        for (UserIntegralConsume userIntegralConsume: userIntegralConsumes){
            totalAmount = totalAmount.add(userIntegralConsume.getCostNum());
        }


        return new CommonDto<>(totalAmount, "success", 200);
    }


    @Transactional
    @Override
    public CommonDto<RedEnvelopeResDto> receiveRedEnvelope(Integer appId, String unionId, String token, String unionKey) {

        CommonDto<RedEnvelopeResDto> result = new CommonDto<>();
        RedEnvelopeResDto redEnvelopeResDto = new RedEnvelopeResDto();

        try {
            RedEnvelope queryRedEnvelope = new RedEnvelope();
            queryRedEnvelope.setUnionKey(unionId);
            RedEnvelope redEnvelope = redEnvelopeMapper.selectOne(queryRedEnvelope);
            Users users = this.getUserInfo(appId,redEnvelope.getToken());
            if (null == users){
                return  new CommonDto<>(null, "用户不存在", 200);
            }
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
            redEnvelopeResDto.setCompanyDuties(users.getCompanyDuties());
            redEnvelopeResDto.setCompanyName(users.getCompanyName());

            redEnvelopeResDto.setDescription(redEnvelope.getDescription());
            redEnvelopeResDto.setQuantity(redEnvelope.getReceiveQuantity());
            redEnvelopeResDto.setTotalQuantity(redEnvelope.getQuantity());
            redEnvelopeResDto.setMessage(redEnvelope.getMessage());
            redEnvelopeResDto.setRedEnvelopeID(redEnvelope.getUnionKey());
            redEnvelopeResDto.setToken(redEnvelope.getToken());
            RedEnvelopeLog queryRedEnvelopeLog = new RedEnvelopeLog();
            queryRedEnvelopeLog.setAppId(appId);
            queryRedEnvelopeLog.setToken(token);
            queryRedEnvelopeLog.setRedEnvelopeId(redEnvelope.getId());

            RedEnvelopeLog checkRedEnvelopeLog = redEnvelopeLogMapper.selectOne(queryRedEnvelopeLog);
            if (null != checkRedEnvelopeLog){
                redEnvelopeResDto.setStatus("Completed");
                redEnvelopeResDto.setMessage(redEnvelope.getMessage());
                redEnvelopeResDto.setAmount(checkRedEnvelopeLog.getAmount());
            }else {
                //尝试3次获取红包
                for (int i = 0; i < 3; i++) {



                    if (redEnvelope.getQuantity() == redEnvelope.getReceiveQuantity()) {

                        if (StringUtil.isEmpty(redEnvelopeResDto.getMessage())) {
                            redEnvelopeResDto.setMessage(redEnvelope.getMessage());
                        }
                        redEnvelopeResDto.setStatus("Finished");
                        break;
                    } else {
                        RedEnvelopeLog redEnvelopeLog = new RedEnvelopeLog();
                        BigDecimal reciveAmount = null;
                        if (redEnvelope.getRedEnvelopeType() == 0) {
                            reciveAmount = redEnvelope.getAmount();
                            redEnvelopeLog.setAmount(reciveAmount);
                            redEnvelope.setReceiveAmount(redEnvelope.getReceiveAmount().add(redEnvelope.getAmount()));
                            redEnvelopeResDto.setAmount(reciveAmount);
                        }else if (redEnvelope.getRedEnvelopeType() == 1) {
                            reciveAmount = randomAmount(redEnvelope.getQuantity(),redEnvelope.getReceiveQuantity(),redEnvelope.getTotalAmount(),redEnvelope.getReceiveAmount(),appId);
                            redEnvelopeLog.setAmount(reciveAmount);
                            redEnvelope.setReceiveAmount(redEnvelope.getReceiveAmount().add(reciveAmount));
                            redEnvelopeResDto.setAmount(reciveAmount);
                        }
                        Example example = new Example(RedEnvelope.class);
                        example.and().andEqualTo("appId", appId).andEqualTo("unionKey", unionId).andEqualTo("receiveQuantity", redEnvelope.getReceiveQuantity());
                        redEnvelope.setReceiveQuantity(redEnvelope.getReceiveQuantity() + 1);
                        redEnvelopeResDto.setQuantity(redEnvelope.getReceiveQuantity());


                        if (redEnvelopeMapper.updateByExample(redEnvelope, example) > 0) {

                            redEnvelopeResDto.setStatus("Received");



                            redEnvelopeLog.setAppId(appId);
                            redEnvelopeLog.setCreateTime(DateTime.now().toDate());
                            redEnvelopeLog.setRedEnvelopeId(redEnvelope.getId());
                            redEnvelopeLog.setToken(token);
                            redEnvelopeLog.setFromToken(redEnvelope.getToken());
                            redEnvelopeLog.setUnionKey(unionId);

                            redEnvelopeLogMapper.insert(redEnvelopeLog);



                            this.addUserIntegralsLog(appId, "GET_ANGEL_TOKEN", users.getId(), reciveAmount, obtainIntegralPeriod, true, new BigDecimal(1));

                            break;
                        }
                    }
                }
            }

//            queryRedEnvelopeLog = new RedEnvelopeLog();
//            queryRedEnvelopeLog.setRedEnvelopeId(redEnvelope.getId());

            Example queryRedEnvelopeLogExample = new Example(RedEnvelopeLog.class);
            queryRedEnvelopeLogExample.and().andEqualTo("redEnvelopeId",redEnvelope.getId());
            queryRedEnvelopeLogExample.setOrderByClause("create_time desc");
            List<RedEnvelopeLog> redEnvelopeLogs = redEnvelopeLogMapper.selectByExample(queryRedEnvelopeLogExample);

            List<RedEnvelopeLogDto> redEnvelopeLogDtos = new ArrayList<>();

            redEnvelopeLogs.forEach(item -> {
                RedEnvelopeLogDto redEnvelopeLogDto = new RedEnvelopeLogDto();
                try {
                    redEnvelopeLogDto.setAmount(item.getAmount());
                    redEnvelopeLogDto.setCreateTime(item.getCreateTime());
                    redEnvelopeLogDto.setToken(item.getToken());
                    Users temp = this.getUserInfo(appId,item.getToken());
                    if (null != temp) {
                        if (StringUtil.isNotEmpty(temp.getHeadpicReal())) {
                            redEnvelopeLogDto.setHeadPic(temp.getHeadpicReal());
                        } else {
                            redEnvelopeLogDto.setHeadPic(temp.getHeadpic());
                        }
                        if (StringUtil.isNotEmpty(temp.getActualName())) {
                            redEnvelopeLogDto.setName(temp.getActualName());
                        } else {
                            UsersWeixin query = new UsersWeixin();
                            query.setUserId(temp.getId());
                            UsersWeixin usersWeixin = this.usersWeixinMapper.selectOne(query);
                            if (null != usersWeixin) {
                                redEnvelopeLogDto.setName(usersWeixin.getNickName());
                            }
                        }
                        redEnvelopeLogDto.setCompanyDuties(temp.getCompanyDuties());
                        redEnvelopeLogDto.setCompanyName(temp.getCompanyName());
                    }
                } catch (Exception e) {
                   this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
                }
                redEnvelopeLogDtos.add(redEnvelopeLogDto);
            });

            redEnvelopeResDto.setRedEnvelopeLogs(redEnvelopeLogDtos);
            result.setData(redEnvelopeResDto);
            result.setMessage("success");
            result.setStatus(200);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            throw e;
        }

        return result;
    }

    @Override
    public CommonDto<Map<String, BigDecimal>> getInvitationedLimit(Integer appId, String token) {

        RedEnvelopeLimit redEnvelopeLimitAmount = this.getRedEnvelopeLimit(appId, "INVITATION_AMOUNT");
        RedEnvelopeLimit redEnvelopeLimitQuanlity = this.getRedEnvelopeLimit(appId, "INVITATION_QUANLITY");
        Map<String, BigDecimal> limitMap = new HashMap<>();
        limitMap.put("invitationAmount",redEnvelopeLimitAmount.getLimitValue());
        limitMap.put("invitationQuanlity",redEnvelopeLimitQuanlity.getLimitValue());



        return new CommonDto<>(limitMap,"success", 200);
    }

    @Transactional
    @Override
    public CommonDto<String> resolveWechatGroupId(Integer appId, String redEnvelopeId, String token, RedEnvelopeGroupDto groupDto) {

        Users record = new Users();
        record.setUuid(token);
        Users users = usersMapper.selectOne(record);


        //sessionkey加前缀
        String redisKeyId = "sessionkey:" + users.getId();
        //取到sessionKey
        String sessionKey = sessionKeyService.getSessionKey(redisKeyId);

        OpenGIdJsonDto openGIdJsonDto =  WxMaGsonBuilder.create().fromJson(WxMaCryptUtils.decrypt(sessionKey, groupDto.getEncryptedData(), groupDto.getIv()),OpenGIdJsonDto.class);

        RedEnvelope recordRedEnvelop = new RedEnvelope();
        recordRedEnvelop.setUnionKey(redEnvelopeId);

        RedEnvelopeWechatgroup redEnvelopeWechatgroupRecord = new RedEnvelopeWechatgroup();
        redEnvelopeWechatgroupRecord.setUnionKey(groupDto.getUnionKey());

        RedEnvelopeWechatgroup redEnvelopeWechatgroup = redEnvelopeWechatgroupMapper.selectOne(redEnvelopeWechatgroupRecord);
        redEnvelopeWechatgroup.setWechatGroupId(openGIdJsonDto.getOpenGId());

        redEnvelopeWechatgroupMapper.updateByPrimaryKey(redEnvelopeWechatgroup);


        return new CommonDto<>("ok","success", 200);
    }

    @Transactional
    @Override
    public CommonDto<String> getRedEnvelopeWechatGroupKey(Integer appId, String redEnvelopeId, String token) {

        RedEnvelope redEnvelopeRecord = new RedEnvelope();
        redEnvelopeRecord.setUnionKey(redEnvelopeId);

        RedEnvelope redEnvelope = redEnvelopeMapper.selectOne(redEnvelopeRecord);

        RedEnvelopeWechatgroup redEnvelopeWechatgroup = new RedEnvelopeWechatgroup();
        redEnvelopeWechatgroup.setRedEnvelopeId(redEnvelope.getId());
        redEnvelopeWechatgroup.setToken(token);
        redEnvelopeWechatgroup.setAppId(appId);
        redEnvelopeWechatgroup.setCreateTime(DateTime.now().toDate());
        String unionKey = MD5Util.md5Encode(DateTime.now().millisOfDay().getAsString(),"");
        redEnvelopeWechatgroup.setUnionKey(unionKey);

        redEnvelopeWechatgroupMapper.insert(redEnvelopeWechatgroup);

        return new CommonDto<>(unionKey,"success", 200);
    }

    @Override
    public CommonDto<List<RedEnvelopeResDto>> queryRedEnvelopeAllByToken(Integer appId, String token, Integer pageNo, Integer pageSize) {

        Set<String> tokens = getRecivedTokens(token);


        Set<String> tempTokens = new ConcurrentSkipListSet<>();
        for (String  tempToken : tokens){
            tempTokens.addAll(getRecivedTokens(tempToken));
        }
        tokens = tempTokens;

        if (null == tokens || tokens.size() <= 0){
            return  new CommonDto<>(null,"success", 200);
        }
        RowBounds rowBounds = new RowBounds((pageNo-1)*pageSize,pageSize);
        Example query = new Example(RedEnvelope.class);
        query.and().andIn("token",tokens);
        query.setOrderByClause("create_time desc");
        List<RedEnvelope> redEnvelopes =   redEnvelopeMapper.selectByExampleAndRowBounds(query,rowBounds);



        List<RedEnvelopeResDto> redEnvelopeResDtos = new ArrayList<>();
        redEnvelopes.forEach(redEnvelope -> {
            RedEnvelopeResDto redEnvelopeResDto = new RedEnvelopeResDto();
            redEnvelopeResDto.setToken(redEnvelope.getToken());
            redEnvelopeResDto.setRedEnvelopeID(redEnvelope.getUnionKey());
            redEnvelopeResDto.setDescription(redEnvelope.getDescription());
            redEnvelopeResDto.setQuantity(redEnvelope.getReceiveQuantity());
            redEnvelopeResDto.setTotalQuantity(redEnvelope.getQuantity());
            redEnvelopeResDto.setMessage(redEnvelope.getMessage());
            redEnvelopeResDto.setCreateTime(redEnvelope.getCreateTime());
            redEnvelopeResDto.setAmount(redEnvelope.getAmount());
            redEnvelopeResDto.setTotalAmount(redEnvelope.getTotalAmount());

            if (redEnvelope.getReceiveQuantity() == redEnvelope.getQuantity()) {
                redEnvelopeResDto.setStatus("Finished");
            }else{
                RedEnvelopeLog redEnvelopeLogQuery = new RedEnvelopeLog();
                redEnvelopeLogQuery.setToken(token);
                redEnvelopeLogQuery.setRedEnvelopeId(redEnvelope.getId());
                if (redEnvelopeLogMapper.selectCount(redEnvelopeLogQuery)==1){
                    redEnvelopeResDto.setStatus("Received");
                }else{
                    redEnvelopeResDto.setStatus("Unreceived");
                }
            }
            Users temp = this.getUserInfo(appId,redEnvelope.getToken());
            if (null != temp) {
                if (StringUtil.isNotEmpty(temp.getHeadpicReal())) {
                    redEnvelopeResDto.setHeadPic(temp.getHeadpicReal());
                } else {
                    redEnvelopeResDto.setHeadPic(temp.getHeadpic());
                }
                if (StringUtil.isNotEmpty(temp.getActualName())) {
                    redEnvelopeResDto.setNeckName(temp.getActualName());
                } else {
                    UsersWeixin queryUsersWeixin = new UsersWeixin();
                    queryUsersWeixin.setUserId(temp.getId());
                    UsersWeixin usersWeixin = this.usersWeixinMapper.selectOne(queryUsersWeixin);
                    if (null != usersWeixin) {
                        redEnvelopeResDto.setNeckName(usersWeixin.getNickName());
                    }
                }
                redEnvelopeResDto.setCompanyDuties(temp.getCompanyDuties());
                redEnvelopeResDto.setCompanyName(temp.getCompanyName());
            }


            redEnvelopeResDtos.add(redEnvelopeResDto);
        });

        return new CommonDto<>(redEnvelopeResDtos, "success", 200);
    }

    public Set<String> getRecivedTokens(String token) {

        RedEnvelopeLog redEnvelopeLogRecord = new RedEnvelopeLog();
        redEnvelopeLogRecord.setFromToken(token);

        List<RedEnvelopeLog> redEnvelopeLogs = redEnvelopeLogMapper.select(redEnvelopeLogRecord);
        Set<String> tokens = new HashSet<>();
        for (RedEnvelopeLog redEnvelopeLog : redEnvelopeLogs){
            tokens.add(redEnvelopeLog.getToken());
        }
        return tokens;
    }

    @Override
    public CommonDto<RedEnvelopeResDto> getRedEnvelopeInfo(Integer appId, String unionId, String token) {

        RedEnvelope redEnvelopeQuery = new RedEnvelope();
        redEnvelopeQuery.setToken(token);
        redEnvelopeQuery.setUnionKey(unionId);
        redEnvelopeQuery.setAppId(appId);

        RedEnvelope redEnvelope = redEnvelopeMapper.selectOne(redEnvelopeQuery);
        RedEnvelopeResDto redEnvelopeResDto = new RedEnvelopeResDto();
        redEnvelopeResDto.setDescription(redEnvelope.getDescription());
        redEnvelopeResDto.setQuantity(redEnvelope.getReceiveQuantity());
        redEnvelopeResDto.setTotalQuantity(redEnvelope.getQuantity());
        redEnvelopeResDto.setMessage(redEnvelope.getMessage());
        redEnvelopeResDto.setCreateTime(redEnvelope.getCreateTime());
        redEnvelopeResDto.setAmount(redEnvelope.getAmount());
        redEnvelopeResDto.setTotalAmount(redEnvelope.getTotalAmount());
        redEnvelopeResDto.setRedEnvelopeID(redEnvelope.getUnionKey());
        redEnvelopeResDto.setToken(redEnvelope.getToken());
        Users temp = this.getUserInfo(appId,redEnvelope.getToken());
        if (null != temp) {
            if (StringUtil.isNotEmpty(temp.getHeadpicReal())) {
                redEnvelopeResDto.setHeadPic(temp.getHeadpicReal());
            } else {
                redEnvelopeResDto.setHeadPic(temp.getHeadpic());
            }
            if (StringUtil.isNotEmpty(temp.getActualName())) {
                redEnvelopeResDto.setNeckName(temp.getActualName());
            } else {
                UsersWeixin queryUsersWeixin = new UsersWeixin();
                queryUsersWeixin.setUserId(temp.getId());
                UsersWeixin usersWeixin = this.usersWeixinMapper.selectOne(queryUsersWeixin);
                if (null != usersWeixin) {
                    redEnvelopeResDto.setNeckName(usersWeixin.getNickName());
                }
            }
            redEnvelopeResDto.setCompanyDuties(temp.getCompanyDuties());
            redEnvelopeResDto.setCompanyName(temp.getCompanyName());
        }


        return new CommonDto<>(redEnvelopeResDto, "success", 200);
    }

    @Override
    public CommonDto<Map<String, Integer>> getStatisticesRedEnvelope() {

        Map<String, Integer> map = new HashMap<>();

        map.put("peoples", 100+redEnvelopeMapper.getRedEnvelopePeopleNums());
        map.put("contacts", 200+redEnvelopeMapper.getRecivedRedEnvelopePeopleNums());
        map.put("sendRedNum", 300+redEnvelopeMapper.getRedEnvelopeNums());

        return new CommonDto<>(map, "success", 200);
    }

    private BigDecimal randomAmount(Integer quantity, Integer receiveQuantity, BigDecimal totalAmount, BigDecimal receiveAmount, Integer appId) {

        RedEnvelopeLimit redEnvelopeLimit = this.getRedEnvelopeLimit(appId, "RED_ENVELOPE_LIMIT");
        if (totalAmount.divide(redEnvelopeLimit.getLimitValue()).compareTo(new BigDecimal(0)) == 0){
            return redEnvelopeLimit.getLimitValue();
        }
            if (quantity - receiveQuantity == 1){
                return totalAmount.subtract(receiveAmount);
            }else {
                return totalAmount.subtract(receiveAmount).divide(new BigDecimal(quantity - receiveQuantity),2).multiply(new BigDecimal(Math.random())).setScale(2,BigDecimal.ROUND_HALF_DOWN);
            }
    }


    public Users getUserInfo(Integer appId,String token){
        UserToken queryUserToken = new UserToken();
        queryUserToken.setToken(token);
        queryUserToken.setMetaAppId(String.valueOf(appId));
        UserToken userToken = userTokenMapper.selectOne(queryUserToken);

        Users users = null;
        if (null != userToken) {
            users = usersMapper.selectByPrimaryKey(userToken.getUserId());
        }

        return users;
    }

    public static void main(String[] args) {
//        System.out.println(DateTime.now().withTime(0,0,0,0).withFieldAdded(DurationFieldType.days(),30).toString("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(DateTime.now().withTime(23,59,59,999).toString("yyyy-MM-dd HH:mm:ss"));
//        Random random = new Random();
//        System.out.println(String.valueOf());
    }
}
