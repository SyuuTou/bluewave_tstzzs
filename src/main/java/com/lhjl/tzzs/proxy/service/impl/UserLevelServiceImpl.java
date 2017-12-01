package com.lhjl.tzzs.proxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhjl.tzzs.proxy.dto.ActionDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InterviewInputDto;
import com.lhjl.tzzs.proxy.dto.UserLevelDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;
import com.lhjl.tzzs.proxy.service.UserLevelService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会员等级
 * Created by 蓝海巨浪 on 2017/10/16.
 */
@Service("userLevelService")
public class UserLevelServiceImpl implements UserLevelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLevelService.class);

    @Resource
    private MetaUserLevelMapper metaUserLevelMapper;
    @Resource
    private MetaUserRightsInterestsMapper metaUserRightsInterestsMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private UserLevelRelationMapper userLevelRelationMapper;
    @Resource
    private MetaObtainIntegralMapper metaObtainIntegralMapper;
    @Resource
    private UserIntegralsMapper userIntegralsMapper;
    @Resource
    private UserIntegralConsumeMapper userIntegralConsumeMapper;
    @Resource
    private ProjectsMapper projectsMapper;
    @Resource
    private UserIntegralConsumeDatasMapper userIntegralConsumeDatasMapper;
    @Resource
    private UserSceneMapper userSceneMapper;
    @Resource
    private UserIntegralsService userIntegralsService;
    @Resource
    private UserMoneyRecordMapper userMoneyRecordMapper;

    @Autowired
    private MetaUserPresentedMapper userPresentedMapper;
    @Autowired
    private InvestorsApprovalMapper investorsApprovalMapper;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    //消费场景
    private static final String INDEX = "Ys54fPbz";
    private static final String ASSESS = "cIQwmmX7";
    private static final String PROJECT = "oSUHqJD6";
    private static final String SEND = "PnLUE0m4";
    private static final String INTERVIEW = "7eREKTD7";

    //购买会员等级场景
    private static final String ONE = "nEBlAOV9";
    private static final String TWO = "aMvVjSju";
    private static final String THREE = "y4Ep6YQT";
    private static final String FOUR = "N4VlBBJP";

    /**
     * 查找会员等级信息
     * @param userId 用户ID
     * @return
     */
    @Override
    public CommonDto<List<UserLevelDto>> findUserLevelList(String userId) {
        CommonDto<List<UserLevelDto>> result = new CommonDto<List<UserLevelDto>>();

        Integer localUserId = this.getLocalUserId(userId);
        if(localUserId == null){
            result.setStatus(203);
            result.setMessage("当前用户信息无效");
            return result;
        }

        //获取会员信息
        Example userLevelExample = new Example(MetaUserLevel.class);
        userLevelExample.setOrderByClause("id asc");
        List<MetaUserLevel> userLevels = metaUserLevelMapper.selectByExample(userLevelExample);

        List<UserLevelDto> userLevelDtos = new ArrayList<UserLevelDto>();

        if(userLevels.size() > 0){
            for(MetaUserLevel userLevel : userLevels){
                Example example = new Example(MetaUserRightsInterests.class);
                example.and().andEqualTo("userLevelId", userLevel.getId());
                //获取权益描述
                List<MetaUserRightsInterests> metaUserRightsInterests = metaUserRightsInterestsMapper.selectByExample(example);
                List<String> descs = new ArrayList<String>();
                if(metaUserRightsInterests.size() > 0){
                    for(MetaUserRightsInterests userRightsInterests : metaUserRightsInterests){
                        String desc = userRightsInterests.getName();
                        descs.add(desc);
                    }
                }
                //当前用户是否拥有当前会员的信息
                example = new Example(UserLevelRelation.class);
                example.and().andEqualTo("userId", localUserId).andEqualTo("yn", 1).andEqualTo("status", 1);
                List<UserLevelRelation> records = userLevelRelationMapper.selectByExample(example);

                //组合返回信息
                UserLevelDto userLevelDto = new UserLevelDto();
                userLevelDto.setId(userLevel.getId());
                userLevelDto.setName(userLevel.getName());
                userLevelDto.setAmount(userLevel.getAmount().intValue());
                userLevelDto.setDescs(descs);
                if(records.size() > 0){
                    if(userLevel.getId() == records.get(0).getLevelId()){
                        //置灰显示
                        userLevelDto.setBelong("1");
                    }else{
                        //正常显示
                        userLevelDto.setBelong("0");
                    }
                }else{
                    //正常显示
                    userLevelDto.setBelong("0");
                }

                //获取原价
                MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
                metaObtainIntegral.setSceneKey("EWJkiU7Q");
                metaObtainIntegral.setUserLevel(userLevel.getId());
                metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
                int originalCost = metaObtainIntegral.getIntegral();
                userLevelDto.setOriginalCost(originalCost);

                //获取金币赠送
                int i;
                boolean flag = true;
                String ratio = "";
                String b = metaObtainIntegral.getRatio() + "";
                if(b.indexOf(".")!=-1)
                {
                    i=b.indexOf(".")+1;
                    for (i = 0;i<b.length();i++)
                    {
                        if (b.charAt(i)=='0')
                        {
                            flag=false;
                            break;
                        }
                    }
                }
                if (flag) {
                    ratio = "1:" + metaObtainIntegral.getRatio();
                }
                else {
                    ratio = "1:" + metaObtainIntegral.getRatio().intValue();
                }

                //权限信息
                MetaObtainIntegral send = new MetaObtainIntegral();
                send.setSceneKey("PnLUE0m4");
                send.setUserLevel(userLevel.getId());
                send = metaObtainIntegralMapper.selectOne(send);
                userLevelDto.setSendsNum(send.getDeliverNum());

                MetaObtainIntegral condition = new MetaObtainIntegral();
                condition.setSceneKey("cIQwmmX7");
                condition.setUserLevel(userLevel.getId());
                condition = metaObtainIntegralMapper.selectOne(condition);
                userLevelDto.setConditionNum(condition.getDeliverNum());

                int coinsNum = (int)(userLevel.getAmount().intValue() * metaObtainIntegral.getRatio());
                userLevelDto.setRatio(ratio);
                userLevelDto.setCoinNum(coinsNum);

                userLevelDtos.add(userLevelDto);
            }
        }else{
            result.setStatus(204);
            result.setMessage("未找到会员信息");
            return result;
        }

        result.setStatus(200);
        result.setMessage("会员信息查询成功");
        result.setData(userLevelDtos);

        return result;
    }

    /**
     * 进入会员等级购买页
     * @param userStr 用户ID（字符串）
     * @param levelId 当前页面会员等级
     * @return
     */
    @Override
    public CommonDto<UserLevelDto> findLevelInfo(String userStr, int levelId) {
        CommonDto<UserLevelDto> result = new CommonDto<UserLevelDto>();

        //测试开关
        boolean test = false;

        UserLevelDto userLevelDto = new UserLevelDto();
        Integer localUserId = this.getLocalUserId(userStr);
        if(localUserId == null){
            result.setStatus(203);
            result.setMessage("当前用户信息无效");
            return result;
        }

        //获取当前会员信息
        MetaUserLevel userLevel = new MetaUserLevel();
        userLevel.setId(levelId);
        userLevel = metaUserLevelMapper.selectOne(userLevel);
        if(userLevel == null){
            result.setStatus(203);
            result.setMessage("当前会员信息不存在");
            return result;
        }
        userLevelDto.setId(userLevel.getId());
        userLevelDto.setName(userLevel.getName());

        //获取权益描述
        Example example = new Example(MetaUserRightsInterests.class);
        example.and().andEqualTo("userLevelId", userLevel.getId());
        List<MetaUserRightsInterests> metaUserRightsInterests = metaUserRightsInterestsMapper.selectByExample(example);
        List<String> descs = new ArrayList<String>();
        if(metaUserRightsInterests.size() > 0){
            for(MetaUserRightsInterests userRightsInterests : metaUserRightsInterests){
                String desc = userRightsInterests.getName();
                descs.add(desc);
            }
        }
        userLevelDto.setDescs(descs);

        //获取当前用户拥有的会员信息
        example = new Example(UserLevelRelation.class);
        example.and().andEqualTo("userId", localUserId).andEqualTo("yn", 1).andEqualTo("status", 1);
        List<UserLevelRelation> records = userLevelRelationMapper.selectByExample(example);
        if(records.size() > 0){
            if(levelId == records.get(0).getLevelId()){
                if(levelId == 4){
                    //已是最高级，不可购买状态
                    userLevelDto.setBelong("2");
                }else{
                    //可升级状态
                    userLevelDto.setBelong("1");
                }
            }else if(levelId < records.get(0).getLevelId()){
                //不可购买状态
                userLevelDto.setBelong("2");
            }else{
                //可购买状态
                userLevelDto.setBelong("0");
            }

            if(test){
                userLevelDto.setActualPrice(new BigDecimal("0.01"));
            }else{
                //计算当前会员实际价格
//                Date failTime = records.get(0).getEndTime();
//                Date now = new Date();
//                //剩余天数(会员余额)
//                long days = (failTime.getTime() - now.getTime())/(1000*60*60*24);
                //当前用户会员等级
                int userLevelId = records.get(0).getLevelId();
                MetaUserLevel nowLevel = new MetaUserLevel();
                nowLevel.setId(userLevelId);
                nowLevel = metaUserLevelMapper.selectByPrimaryKey(nowLevel);

//                if(levelId > userLevelId && userLevelId != 1){
//                    int discount = (nowLevel.getAmount().intValue()/365)*(int)days;
//                    BigDecimal discountB = new BigDecimal(discount);
//                    BigDecimal actualPrice = userLevel.getAmount().subtract(discountB);
//                    userLevelDto.setActualPrice(actualPrice);
//                    userLevelDto.setDicount(discount);
//                }else{
                    userLevelDto.setActualPrice(userLevel.getAmount());
//                }
            }

        }else{
            if(test){
                userLevelDto.setActualPrice(new BigDecimal("0.01"));
            }else{
                userLevelDto.setActualPrice(userLevel.getAmount());
            }
            ///可购买状态
            userLevelDto.setBelong("0");
        }

        userLevelDto.setAmount(userLevel.getAmount().intValue());

        String sceneKey = this.getUserLevelKey(levelId);
        userLevelDto.setSceneKey(sceneKey);

        //获取原价
        MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
        metaObtainIntegral.setSceneKey("EWJkiU7Q");
        metaObtainIntegral.setUserLevel(userLevel.getId());
        metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
        int originalCost = metaObtainIntegral.getIntegral();
        userLevelDto.setOriginalCost(originalCost);

        //获取金币赠送
        int i;
        boolean flag = true;
        String ratio = "";
        String b = metaObtainIntegral.getRatio() + "";
        if(b.indexOf(".")!=-1)
        {
            i=b.indexOf(".")+1;
            for (i = 0;i<b.length();i++)
            {
                if (b.charAt(i)=='0')
                {
                    flag=false;
                    break;
                }
            }
        }
        if (flag) {
            ratio = "1:" + metaObtainIntegral.getRatio();
        }
        else {
            ratio = "1:" + metaObtainIntegral.getRatio().intValue();
        }

        //权限信息
        MetaObtainIntegral send = new MetaObtainIntegral();
        send.setSceneKey("PnLUE0m4");
        send.setUserLevel(userLevel.getId());
        send = metaObtainIntegralMapper.selectOne(send);
        userLevelDto.setSendsNum(send.getDeliverNum());

        MetaObtainIntegral condition = new MetaObtainIntegral();
        condition.setSceneKey("cIQwmmX7");
        condition.setUserLevel(userLevel.getId());
        condition = metaObtainIntegralMapper.selectOne(condition);
        userLevelDto.setConditionNum(condition.getDeliverNum());

        int coinsNum = (int)(originalCost * metaObtainIntegral.getRatio());
        userLevelDto.setRatio(ratio);
        userLevelDto.setCoinNum(coinsNum);

        //当可购买时，插入新的记录
        if("0".equals(userLevelDto.getBelong())){
            //插入新的等级记录信息
            UserLevelRelation newOne = new UserLevelRelation();
            newOne.setYn(0);
            newOne.setUserId(localUserId);
            newOne.setLevelId(levelId);
            Date now = new Date();
            newOne.setBeginTime(now);
            newOne.setCreateTime(now);

            //获取失效周期
            MetaUserLevel metaUserLevel = new MetaUserLevel();
            metaUserLevel.setId(levelId);
            metaUserLevel = metaUserLevelMapper.selectOne(metaUserLevel);
            int period = metaUserLevel.getPeriod();

            //计算失效时间
            Calendar calendar = new GregorianCalendar();
                calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, period);
            Date end = calendar.getTime();
            newOne.setEndTime(end);
            newOne.setStatus(0);//未支付状态
            userLevelRelationMapper.insertSelective(newOne);

            //保存当前会员支付金额
            UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
            userMoneyRecord.setCreateTime(new Date());
            BigDecimal jnum = userLevelDto.getActualPrice();
            userMoneyRecord.setMoney(jnum);
            userMoneyRecord.setSceneKey(sceneKey);
            userMoneyRecord.setUserId(localUserId);
            userMoneyRecordMapper.insert(userMoneyRecord);
            userLevelDto.setMoneyId(userMoneyRecord.getId());
        }

        result.setStatus(200);
        result.setMessage("会员信息查询成功");
        result.setData(userLevelDto);
        return result;
    }

    /**
     * 更新会员记录表（支付之后使用）
     * @param userId 用户ID(本系统ID)
     * @param status 支付状态
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> changeLevel(Integer userId, int status) {
        CommonDto<String> result = new CommonDto<String>();
        //支付成功
        if(status == 1){
            //更新会员等级
            UserLevelRelation userLevelRelation = new UserLevelRelation();
            userLevelRelation.setUserId(userId);
            userLevelRelation.setYn(1);
            userLevelRelation.setStatus(1);
            List<UserLevelRelation> records = userLevelRelationMapper.select(userLevelRelation);

            if(records.size() > 0){
                //将之前的等级记录失效
                UserLevelRelation old = records.get(0);
                old.setYn(0);
                userLevelRelationMapper.updateByPrimaryKey(old);
            }

            //更新新的等级记录信息
            Example example = new Example(UserLevelRelation.class);
            example.and().andEqualTo("userId", userId).andEqualTo("yn", 0).andEqualTo("status", 0);
            example.setOrderByClause("create_time desc");
            List<UserLevelRelation> olds = userLevelRelationMapper.selectByExample(example);
            if(olds.size() > 0){
                UserLevelRelation old = olds.get(0);
                old.setYn(1);
                old.setStatus(status);
                userLevelRelationMapper.updateByPrimaryKey(old);
                //更新用户金币
                String sceneKey = this.getUserLevelKey(old.getLevelId());
                this.insertMemberChange(userId, sceneKey);
            }
        }else{
            //更新新的等级记录信息
            Example example = new Example(UserLevelRelation.class);
            example.and().andEqualTo("userId", userId).andEqualTo("yn", 0).andEqualTo("status", 0);
            example.setOrderByClause("create_time desc");
            List<UserLevelRelation> olds = userLevelRelationMapper.selectByExample(example);
            if(olds.size() > 0){
                UserLevelRelation old = olds.get(0);
                old.setYn(0);
                old.setStatus(status);
                userLevelRelationMapper.updateByPrimaryKey(old);
            }
        }

        result.setStatus(200);
        result.setMessage("用户会员等级更新成功");
        return result;
    }

    /**
     * 会员升级(不通过支付使用)
     * @param userStr 用户ID（字符串）
     * @param levelId 要升级的会员等级
     * @param presentedType
     * @return
     */
    @Transactional
    @Override
    public CommonDto<Map<String, Object>> upLevel(String userStr, int levelId, String presentedType) {
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        try {
            Integer localUserId = this.getLocalUserId(userStr);
            if(localUserId == null){
                result.setStatus(201);
                result.setMessage("当前用户信息无效");
                return result;
            }


            MetaUserPresented query = new MetaUserPresented();
            query.setUserLevel(levelId);
            if (null == presentedType) {
//                InvestorsApproval queryIA = new InvestorsApproval();
//                queryIA.setUserid(localUserId);
                Example queryIA = new Example(InvestorsApproval.class);
                queryIA.and().andEqualTo("userid",localUserId);
                queryIA.setOrderByClause("create_time desc");
                PageHelper.startPage(0,1);//分页
                InvestorsApproval investorsApproval = investorsApprovalMapper.selectByExample(queryIA).get(0);
                if (investorsApproval.getInvestorsType() == null){
                    investorsApproval.setInvestorsType(2);
                }
                if (1 == investorsApproval.getInvestorsType()) {
                    query.setIdType("Institutional_Investor");
                }else if (2 == investorsApproval.getInvestorsType()) {
                    query.setIdType("VIP_Investor");
                }else {
                    query.setIdType("organizing_data");
                }
            }else {
                query.setIdType(presentedType);
            }



            MetaUserPresented userPresented = userPresentedMapper.selectOne(query);
            if (null != userPresented){
                levelId = userPresented.getUserLevel();
            }

            //获取当前用户拥有的会员信息
            UserLevelRelation userLevelRelation = new UserLevelRelation();
            userLevelRelation.setUserId(localUserId);
            userLevelRelation.setYn(1);
            userLevelRelation.setStatus(1);
            UserLevelRelation userLevelRelationOld = userLevelRelationMapper.selectOne(userLevelRelation);

            //更新用户会员记录表
            if(null != userLevelRelationOld){
                if(levelId > userLevelRelationOld.getLevelId()){
                    //更新金币记录表
                    String sceneKeyInsert = this.getUserLevelKey(levelId);
                    this.insertMember(localUserId, sceneKeyInsert, levelId);

                    //将之前的等级记录失效
                    userLevelRelationOld.setYn(0);
                    userLevelRelationMapper.updateByPrimaryKey(userLevelRelationOld);
                }else if(levelId == userLevelRelationOld.getLevelId()){
                    result.setStatus(202);
                    result.setMessage("该用户已是当前等级，无需升级");
                    return result;
                }else if(levelId < userLevelRelationOld.getLevelId()){
                    result.setStatus(203);
                    result.setMessage("该用户已有更高等级，无需升级");
                    return result;
                }
            }else{
                //更新金币记录表
                String sceneKeyInsert = this.getUserLevelKey(levelId);
                this.insertMember(localUserId, sceneKeyInsert, levelId);
            }

            Date beginTime = null;
//            if (null == userLevelRelationOld){
                beginTime = DateTime.now().toDate();
//            }else {
//                beginTime = userLevelRelationOld.getEndTime();
//            }
            //插入新的等级记录信息
            UserLevelRelation newOne = new UserLevelRelation();
            newOne.setYn(1);
            newOne.setUserId(localUserId);
            newOne.setLevelId(levelId);
            Date now = new Date();
            newOne.setBeginTime(beginTime);
            newOne.setCreateTime(now);

            //获取失效周期
            MetaUserLevel metaUserLevel = new MetaUserLevel();
            metaUserLevel.setId(levelId);
            metaUserLevel = metaUserLevelMapper.selectOne(metaUserLevel);
            Integer userPeriod = metaUserLevel.getPeriod();

            if (null != userPresented){
                userPeriod = userPresented.getPeriod();
            }


            //计算失效时间
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(DateTime.now().toDate());
            calendar.add(Calendar.DAY_OF_YEAR, userPeriod);
            Date end = calendar.getTime();
            newOne.setEndTime(end);
            newOne.setStatus(1);
            userLevelRelationMapper.insertSelective(newOne);

            result.setStatus(200);
            result.setMessage("用户会员等级更新成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
        return result;
    }


    /**
     * 消费金币提醒
     * @param action 请求对象
     * @return
     */
    @Transactional
    @Override
    public CommonDto<Map<String, Object>> consumeTips(ActionDto action) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        Map<String, Object> data = new HashMap<String,Object>();
        //获取本系统userId
        Integer localUserId = this.getLocalUserId(action.getUserId());
        if(localUserId == null){
            result.setStatus(301);
             result.setMessage("当前用户信息无效");
            return result;
        }
        //查询当前用户会员等级
        UserLevelRelation userLevelRelation = new UserLevelRelation();
        userLevelRelation.setUserId(localUserId);
        userLevelRelation.setYn(1);
        userLevelRelation.setStatus(1);
        userLevelRelation = userLevelRelationMapper.selectOne(userLevelRelation);
        int userLevel = 0;
        if(userLevelRelation != null){
            userLevel = userLevelRelation.getLevelId();

            Example userLevelRelationForTime = new Example(UserLevelRelation.class);
            userLevelRelationForTime.and().andEqualTo("userId",localUserId).andGreaterThan("endTime",DateTime.now().toDate()).andLessThan("beginTime",DateTime.now().toDate());
            userLevelRelationForTime.setOrderByClause("end_time asc");
            List<UserLevelRelation> userLevelRelationList = userLevelRelationMapper.selectByExample(userLevelRelationForTime);
            if (userLevelRelationList.size() > 0){
//                userLevel = userLevelRelationList.get(0).getLevelId();
                Date userLevelEndTime = userLevelRelationList.get(0).getEndTime();
                Date now = new Date();
                if (now.getTime() > userLevelEndTime.getTime()){
                    result.setStatus(202);
                    result.setMessage("查看天使投资指数统计数据和项目列表，仅对VIP投资人开放");
                    result.setData(data);

                    return result;
                }
            }
        }
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<String> sceneKeys = userLevelRelationMapper.findByUserIdLeid(localUserId,dateFormat.format(DateTime.now().toDate()));
        data.put("levelId", userLevel);
        //查询当前用户金币余额
        Integer z =userIntegralsMapper.findIntegralsZ(localUserId);
        Integer x =userIntegralsMapper.findIntegralsX(localUserId);
        int totalCoins = z+x;

        String sceneKey = action.getSceneKey();

        //首页消费与项目
        if(INDEX.equals(sceneKey) || PROJECT.equals(sceneKey)){


            if(!sceneKeys.contains(sceneKey)){
                result.setStatus(202);
                result.setMessage("查看天使投资指数统计数据和项目列表，仅对VIP投资人开放");
                result.setData(data);
                return result;
            }

            if(INDEX.equals(sceneKey)){
                result.setStatus(200);
                result.setMessage("进入首页不再进行收费");
                result.setData(data);
                return result;
            }

            //获取消费金币数量
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(4);
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
            int consumeNum = metaObtainIntegral.getIntegral();

            //查询是否购买过此功能且在有效期内
            UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
            userIntegralConsume.setSceneKey(sceneKey);
            userIntegralConsume.setUserId(localUserId);
            List<UserIntegralConsume> userIntegralsList = userIntegralConsumeMapper.select(userIntegralConsume);
            //有效数据
            List<UserIntegralConsume> valid = new ArrayList<UserIntegralConsume>();
            if(userIntegralsList.size() > 0){
                for(UserIntegralConsume integrals : userIntegralsList){
                    //计算是否失效
                    Date endTime = integrals.getEndTime();
                    Date now = new Date();
                    if(endTime.getTime() > now.getTime()){
                        valid.add(integrals);
                    }
                }
            }

            if(valid.size() > 0){
                result.setStatus(200);
                result.setMessage("该功能已被购买，可直接进入");
                result.setData(data);
                return result;
            }

            //余额足够
            if((totalCoins + consumeNum) >= 0){
                result.setStatus(204);
                result.setMessage("使用查看更多指数统计数据，消费"+(-consumeNum)+"金币，24小时内可重复查看");
                data.put("consumeNum", -consumeNum);
                //判断是否提示
                UserScene userScene = new UserScene();
                userScene.setUserId(localUserId);
                userScene.setSceneKey(sceneKey);
                userScene.setYn(0);
                userScene = userSceneMapper.selectOne(userScene);
                if(userScene == null || userScene.getFlag() == 0){
                    data.put("flag", "0");//提示
                }else{
                    data.put("flag", "1");//不提示
                }
                result.setData(data);
                return result;
            }else{
                result.setStatus(203);
                result.setMessage("查看天使投资指数统计数据和项目列表需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
                data.put("consumeNum", -consumeNum);
                result.setData(data);
                return result;
            }

        }
        //约谈消费
        if(INTERVIEW.equals(sceneKey)){
            //查询项目信息(是否属于50机构)
            Integer type = projectsMapper.findIvestmentTypeById(action.getProjectsId());
            if(type == null){
                type = 0;
            }
            String tishi = "";
            Integer reserveField = 0;
            if (action.getReserveField() == null){
                reserveField=0;
            }else {
                reserveField = action.getReserveField();
            }
            if (reserveField == 1){
                tishi = "约谈指数机构投资项目，仅对VIP投资人开放";
            }else {
                if (type == 1){
                    tishi = "约谈50指数机构所投资项目，仅对VIP投资人开放";
                }else{
                    tishi = "约谈行业指数机构所投资项目，仅对VIP投资人开放";
                }
            }


            if(!sceneKeys.contains(sceneKey)){
                result.setStatus(202);
                result.setMessage(tishi);
                result.setData(data);
                return result;
            }



            data.put("type", type);

            //查询是否购买过此功能
            boolean isBuy = this.isBuy(sceneKey, localUserId, action.getProjectsId() + "");
            if(isBuy){
                result.setStatus(200);
                result.setMessage("该功能已被购买，可直接进入");
                result.setData(data);
                return result;
            }

            //获取消费金币数量
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(4);
            if(type == 1){
                metaObtainIntegral.setProjectsType(1);
            }else{
                metaObtainIntegral.setProjectsType(0);
            }
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
            int consumeNum = metaObtainIntegral.getIntegral();

            if (consumeNum == 0){
                result.setStatus(200);
                result.setMessage("VIP投资人无需消费，可直接进入");
                result.setData(data);
                return result;
            }
            //金币足够
            if(totalCoins + consumeNum >= 0){

                result.setStatus(204);
                if(type == 1){
                    result.setMessage("约谈50指数机构所投资项目，共消费"+(-consumeNum)+"金币，一次性收费后不再计费");
                }else{
                    result.setMessage("约谈行业指数机构所投资项目，共消费"+(-consumeNum)+"金币，一次性收费后不再计费");
                }

                data.put("consumeNum", -consumeNum);
                //判断是否提示
                UserScene userScene = new UserScene();
                userScene.setUserId(localUserId);
                userScene.setSceneKey(sceneKey);
                userScene.setYn(0);
                userScene = userSceneMapper.selectOne(userScene);
                if(userScene == null || userScene.getFlag() == 0){
                    data.put("flag", "0");//提示
                }else{
                    data.put("flag", "1");//不提示
                }
                result.setData(data);
                return result;
            }else{
                result.setStatus(203);
                if(type == 1){
                    result.setMessage("约谈50指数机构所投资项目需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
                }else{
                    result.setMessage("约谈行业指数机构所投资项目需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
                }
                data.put("consumeNum", -consumeNum);
                result.setData(data);
                return result;
            }

        }

        //评估消费
        if(ASSESS.equals(sceneKey)){
            //判断会员级别
            if(userLevel < 1){
                result.setStatus(202);
                result.setMessage("项目评估仅对会员用户开放，完善个人资料后赠送普通会员");
                result.setData(data);
                return result;
            }
            //判断权限
            String roundName = action.getRoundName();
            String industryName = action.getIndustryName();
            String cityName = action.getCityName();
            String educationName =action.getEducationName();
            String workName = action.getWorkName();

            //获取该场景配置信息
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(userLevel);
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);

            //普通会员
            if(userLevel == 1){
                if(industryName != null && !"".equals(industryName) && !"不限".equals(industryName)){
                    result.setStatus(205);
                    result.setMessage("普通会员用户每次只能选择一个评估选项");
                    result.setData(data);
                    return result;
                }
                if(cityName != null && !"".equals(cityName) && !"不限".equals(cityName)){
                    result.setStatus(205);
                    result.setMessage("普通会员用户每次只能选择一个评估选项");
                    result.setData(data);
                    return result;
                }
                if(educationName != null && !"".equals(educationName) && !"不限".equals(educationName)){
                    result.setStatus(205);
                    result.setMessage("普通会员用户每次只能选择一个评估选项");
                    result.setData(data);
                    return result;
                }
                if(workName != null && !"".equals(workName) && !"不限".equals(workName)){
                    result.setStatus(205);
                    result.setMessage("普通会员用户每次只能选择一个评估选项");
                    result.setData(data);
                    return result;
                }
                if(roundName != null && !"".equals(roundName)){
                    //查询是否购买过
                    boolean isBuy = isBuy(sceneKey, localUserId, roundName);
                    if(isBuy){
                        result.setStatus(200);
                        result.setMessage("该功能已被购买，可直接进入");
                        result.setData(data);
                        return result;
                    }
                    int consumeNum = metaObtainIntegral.getIntegral();
                    //金币足够
                    if(totalCoins + consumeNum > 0){
                        result.setStatus(204);
                        result.setMessage("使用项目评估，每个选项扣除"+(-consumeNum)+"金币，共消费"+(-consumeNum)+"金币，24小时内可重复查看该选项");
                        data.put("consumeNum", -consumeNum);
                        //判断是否提示
                        UserScene userScene = new UserScene();
                        userScene.setUserId(localUserId);
                        userScene.setSceneKey(sceneKey);
                        userScene.setYn(0);
                        userScene = userSceneMapper.selectOne(userScene);
                        if(userScene == null || userScene.getFlag() == 0){
                            data.put("flag", "0");//提示
                        }else{
                            data.put("flag", "1");//不提示
                        }
                        result.setData(data);
                        return result;
                    }else{
                        result.setStatus(203);
                        result.setMessage("使用项目评估需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
                        data.put("consumeNum", -consumeNum);
                        result.setData(data);
                        return result;
                    }
                }else{
                    result.setStatus(303);
                    result.setMessage("融资阶段必须选择");
                    return result;
                }
            }
            //高级以上会员
            if(userLevel >= 2){
                //过滤未购买的选项
                List<String> buys = new ArrayList<String>();
                if(roundName != null && !"".equals(roundName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, roundName);
                    if(!isBuy){
                        buys.add(roundName);
                    }
                }else{
                    result.setStatus(303);
                    result.setMessage("融资阶段必须选择");
                    return result;
                }
                if(industryName != null && !"".equals(industryName) && !"不限".equals(industryName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, industryName);
                    if(!isBuy){
                        buys.add(industryName);
                    }
                }
                if(cityName != null && !"".equals(cityName) && !"不限".equals(cityName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, cityName);
                    if(!isBuy){
                        buys.add(cityName);
                    }
                }
                if(educationName != null && !"".equals(educationName) && !"不限".equals(educationName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, educationName);
                    if(!isBuy){
                        buys.add(educationName);
                    }
                }
                if(workName != null && !"".equals(workName) && !"不限".equals(workName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, workName);
                    if(!isBuy){
                        buys.add(workName);
                    }
                }
                //存在需要消费的选项
                if(buys.size() > 0){
                    int consumeNum = metaObtainIntegral.getIntegral()*buys.size();
                    if(totalCoins + consumeNum >= 0){
                        result.setStatus(204);
                        result.setMessage("使用项目评估，每个选项扣除"+(-metaObtainIntegral.getIntegral())+"金币，共消费"+(-consumeNum)+"金币，24小时内可重复查看该选项");
                        data.put("consumeNum", -consumeNum);
                        //判断是否提示
                        UserScene userScene = new UserScene();
                        userScene.setUserId(localUserId);
                        userScene.setSceneKey(sceneKey);
                        userScene.setYn(0);
                        userScene = userSceneMapper.selectOne(userScene);
                        if(userScene == null || userScene.getFlag() == 0){
                            data.put("flag", "0");//提示
                        }else{
                            data.put("flag", "1");//不提示
                        }
                        result.setData(data);
                        return result;
                    }else{
                        result.setStatus(203);
                        result.setMessage("使用项目评估需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
                        data.put("consumeNum", -consumeNum);
                        result.setData(data);
                        return result;
                    }
                }else{
                    result.setStatus(200);
                    result.setMessage("该功能已被购买，可直接进入");
                    result.setData(data);
                    return result;
                }
            }
        }

        //投递消费
        if(SEND.equals(sceneKey)){
            //判断会员级别
            if(userLevel < 1){
                result.setStatus(202);
                result.setMessage("投递项目仅对会员用户开放，完善个人资料后赠送普通会员");
                result.setData(data);
                return result;
            }

            //获取机构信息
            String[] ids = action.getInvestmentIds().split(",");

            if(ids.length < 1){
                result.setStatus(302);
                result.setMessage("未选择机构，无法进行消费计算");
                return result;
            }

            //获取该场景配置信息
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(userLevel);
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);

            //过滤已购买机构
            List<String> buys = new ArrayList<String>();
            for(int i=0; i<ids.length;i++){
                String investmentId = ids[i];
                //判断是否购买过
                boolean isBuy = isBuy(sceneKey, localUserId, investmentId);
                if(!isBuy){
                    buys.add(investmentId);
                }
            }

            //存在需要消费的机构
            if(buys.size() > 0){
                //校验投递机构个数
                int curNum = buys.size();//当前投递机构数

                //获取已经投递机构数（有效的）
                Example example = new Example(UserIntegralConsumeDatas.class);
                example.and().andEqualTo("userId",localUserId).andEqualTo("sceneKey",sceneKey);
                List<UserIntegralConsumeDatas> userIntegralConsumeDatasList = userIntegralConsumeDatasMapper.selectByExample(example);
                int dayNum = 0;
                for(UserIntegralConsumeDatas consumeDatas : userIntegralConsumeDatasList){
                    UserIntegralConsume consume = new UserIntegralConsume();
                    consume.setId(consumeDatas.getUserIntegralConsumeId());
                    consume = userIntegralConsumeMapper.selectOne(consume);
                    Date endTime = consume.getEndTime();
                    if(endTime != null){
                        Date now = new Date();
                        if(endTime.getTime() > now.getTime()){
                            dayNum ++;
                        }
                    }
                }
                if((curNum + dayNum) > metaObtainIntegral.getDeliverNum()){
                    result.setStatus(205);
                    switch (userLevel){
                        case 1:
                            result.setMessage("普通会员只能选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
                            data.put("button", "0");
                            break;
                        case 2:
                            result.setMessage("高级会员只能选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
                            data.put("button", "0");
                            break;
                        case 3:
                            result.setMessage("VIP会员每日可选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
                            data.put("button", "0");
                            break;
                        case 4:
                            result.setMessage("VIP投资人每日可选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
                            data.put("button", "1");
                            break;
                    }
                    result.setData(data);
                    return result;
                }

                int consumeNum = metaObtainIntegral.getIntegral()*buys.size();
                //金币足够
                if(totalCoins + consumeNum >= 0){
                    result.setStatus(204);
                    result.setMessage("使用投递项目，投递1个机构扣除"+(-metaObtainIntegral.getIntegral())+"金币，共消费"+(-consumeNum)+"金币，24小时内可重复提交给该机构");
                    data.put("consumeNum", -consumeNum);
                    //判断是否提示
                    UserScene userScene = new UserScene();
                    userScene.setUserId(localUserId);
                    userScene.setSceneKey(sceneKey);
                    userScene.setYn(0);
                    userScene = userSceneMapper.selectOne(userScene);
                    if(userScene == null || userScene.getFlag() == 0){
                        data.put("flag", "0");//提示
                    }else{
                        data.put("flag", "1");//不提示
                    }
                    result.setData(data);
                    return result;
                }else{
                    result.setStatus(203);
                    result.setMessage("使用投递项目，需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
                    data.put("consumeNum", -consumeNum);
                    result.setData(data);
                    return result;
                }
            }else{
                result.setStatus(200);
                result.setMessage("该功能已被购买，可直接进入");
                result.setData(data);
                return result;
            }
        }

        return result;
    }





//    /**
//     * 消费金币提醒
//     * @param action 请求对象
//     * @return
//     */
//    @Transactional
//    @Override
//    public CommonDto<Map<String, Object>> consumeTips(ActionDto action) {
//        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
//        Map<String, Object> data = new HashMap<String,Object>();
//        //获取本系统userId
//        Integer localUserId = this.getLocalUserId(action.getUserId());
//        if(localUserId == null){
//            result.setStatus(301);
//            result.setMessage("当前用户信息无效");
//            return result;
//        }
//        //查询当前用户会员等级
//        UserLevelRelation userLevelRelation = new UserLevelRelation();
//        userLevelRelation.setUserId(localUserId);
//        userLevelRelation.setYn(1);
//        userLevelRelation.setStatus(1);
//        userLevelRelation = userLevelRelationMapper.selectOne(userLevelRelation);
//        int userLevel = 0;
//        if(userLevelRelation != null){
//            userLevel = userLevelRelation.getLevelId();
//
//            Example userLevelRelationForTime = new Example(UserLevelRelation.class);
//            userLevelRelationForTime.and().andEqualTo("userId",localUserId).andEqualTo("levelId",userLevel);
//            userLevelRelationForTime.setOrderByClause("end_time asc");
//            List<UserLevelRelation> userLevelRelationList = userLevelRelationMapper.selectByExample(userLevelRelationForTime);
//
//            if (userLevelRelationList.size() > 0){
//                Date userLevelEndTime = userLevelRelationList.get(0).getEndTime();
//                Date now = new Date();
//                if (now.getTime() > userLevelEndTime.getTime()){
//                    result.setStatus(202);
//                    result.setMessage("查看天使投资指数统计数据和项目列表，仅对VIP投资人开放");
//                    result.setData(data);
//
//                    return result;
//                }
//            }
//        }
//        data.put("levelId", userLevel);
//        //查询当前用户金币余额
//        Integer z =userIntegralsMapper.findIntegralsZ(localUserId);
//        Integer x =userIntegralsMapper.findIntegralsX(localUserId);
//        int totalCoins = z+x;
//
//        String sceneKey = action.getSceneKey();
//
//        //首页消费与项目
//        if(INDEX.equals(sceneKey) || PROJECT.equals(sceneKey)){
//
//
//            if(userLevel != 4 && userLevel != 1){
//                result.setStatus(202);
//                result.setMessage("查看天使投资指数统计数据和项目列表，仅对VIP投资人开放");
//                result.setData(data);
//                return result;
//            }
//
//            if(INDEX.equals(sceneKey)){
//                result.setStatus(200);
//                result.setMessage("进入首页不再进行收费");
//                result.setData(data);
//                return result;
//            }
//
//            //获取消费金币数量
//            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
//            metaObtainIntegral.setSceneKey(sceneKey);
//            metaObtainIntegral.setUserLevel(4);
//            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
//            int consumeNum = metaObtainIntegral.getIntegral();
//
//            //查询是否购买过此功能且在有效期内
//            UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
//            userIntegralConsume.setSceneKey(sceneKey);
//            userIntegralConsume.setUserId(localUserId);
//            List<UserIntegralConsume> userIntegralsList = userIntegralConsumeMapper.select(userIntegralConsume);
//            //有效数据
//            List<UserIntegralConsume> valid = new ArrayList<UserIntegralConsume>();
//            if(userIntegralsList.size() > 0){
//                for(UserIntegralConsume integrals : userIntegralsList){
//                    //计算是否失效
//                    Date endTime = integrals.getEndTime();
//                    Date now = new Date();
//                    if(endTime.getTime() > now.getTime()){
//                        valid.add(integrals);
//                    }
//                }
//            }
//
//            if(valid.size() > 0){
//                result.setStatus(200);
//                result.setMessage("该功能已被购买，可直接进入");
//                result.setData(data);
//                return result;
//            }
//
//            //余额足够
//            if((totalCoins + consumeNum) >= 0){
//                result.setStatus(204);
//                result.setMessage("使用查看更多指数统计数据，消费"+(-consumeNum)+"金币，24小时内可重复查看");
//                data.put("consumeNum", -consumeNum);
//                //判断是否提示
//                UserScene userScene = new UserScene();
//                userScene.setUserId(localUserId);
//                userScene.setSceneKey(sceneKey);
//                userScene.setYn(0);
//                userScene = userSceneMapper.selectOne(userScene);
//                if(userScene == null || userScene.getFlag() == 0){
//                    data.put("flag", "0");//提示
//                }else{
//                    data.put("flag", "1");//不提示
//                }
//                result.setData(data);
//                return result;
//            }else{
//                result.setStatus(203);
//                result.setMessage("查看天使投资指数统计数据和项目列表需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
//                data.put("consumeNum", -consumeNum);
//                result.setData(data);
//                return result;
//            }
//
//        }
//        //约谈消费
//        if(INTERVIEW.equals(sceneKey)){
//            //查询项目信息(是否属于50机构)
//            Integer type = projectsMapper.findIvestmentTypeById(action.getProjectsId());
//            if(type == null){
//                type = 0;
//            }
//            String tishi = "";
//            if (type == 1){
//                tishi = "约谈50指数机构所投资项目，仅对VIP投资人开放";
//            }else{
//                tishi = "约谈行业指数机构所投资项目，仅对VIP投资人开放";
//            }
//
////            if(userLevel < 4){
////                result.setStatus(202);
////                result.setMessage(tishi);
////                result.setData(data);
////                return result;
////            }
//
//
//
//            data.put("type", type);
//
//            //查询是否购买过此功能
//            boolean isBuy = this.isBuy(sceneKey, localUserId, action.getProjectsId() + "");
//            if(isBuy){
//                result.setStatus(200);
//                result.setMessage("该功能已被购买，可直接进入");
//                result.setData(data);
//                return result;
//            }
//
//            //获取消费金币数量
//            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
//            metaObtainIntegral.setSceneKey(sceneKey);
//            metaObtainIntegral.setUserLevel(4);
//            if(type == 1){
//                metaObtainIntegral.setProjectsType(1);
//            }else{
//                metaObtainIntegral.setProjectsType(0);
//            }
//            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
//            int consumeNum = metaObtainIntegral.getIntegral();
//
//            if (consumeNum == 0){
//                result.setStatus(200);
//                result.setMessage("VIP投资人无需消费，可直接进入");
//                result.setData(data);
//                return result;
//            }
//            //金币足够
//            if(totalCoins + consumeNum >= 0){
//
//                result.setStatus(204);
//                if(type == 1){
//                    result.setMessage("约谈50指数机构所投资项目，共消费"+(-consumeNum)+"金币，一次性收费后不再计费");
//                }else{
//                    result.setMessage("约谈非50指数机构所投资项目，共消费"+(-consumeNum)+"金币，一次性收费后不再计费");
//                }
//
//                data.put("consumeNum", -consumeNum);
//                //判断是否提示
//                UserScene userScene = new UserScene();
//                userScene.setUserId(localUserId);
//                userScene.setSceneKey(sceneKey);
//                userScene.setYn(0);
//                userScene = userSceneMapper.selectOne(userScene);
//                if(userScene == null || userScene.getFlag() == 0){
//                    data.put("flag", "0");//提示
//                }else{
//                    data.put("flag", "1");//不提示
//                }
//                result.setData(data);
//                return result;
//            }else{
//                result.setStatus(203);
//                if(type == 1){
//                    result.setMessage("约谈50指数机构所投资项目需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
//                }else{
//                    result.setMessage("约谈非50指数机构所投资项目需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
//                }
//                data.put("consumeNum", -consumeNum);
//                result.setData(data);
//                return result;
//            }
//
//        }
//
//        //评估消费
//        if(ASSESS.equals(sceneKey)){
//            //判断会员级别
//            if(userLevel < 1){
//                result.setStatus(202);
//                result.setMessage("项目评估仅对会员用户开放，完善个人资料后赠送普通会员");
//                result.setData(data);
//                return result;
//            }
//            //判断权限
//            String roundName = action.getRoundName();
//            String industryName = action.getIndustryName();
//            String cityName = action.getCityName();
//            String educationName =action.getEducationName();
//            String workName = action.getWorkName();
//
//            //获取该场景配置信息
//            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
//            metaObtainIntegral.setSceneKey(sceneKey);
//            metaObtainIntegral.setUserLevel(userLevel);
//            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
//
//            //普通会员
//            if(userLevel == 1){
//                if(industryName != null && !"".equals(industryName) && !"不限".equals(industryName)){
//                    result.setStatus(205);
//                    result.setMessage("普通会员用户每次只能选择一个评估选项");
//                    result.setData(data);
//                    return result;
//                }
//                if(cityName != null && !"".equals(cityName) && !"不限".equals(cityName)){
//                    result.setStatus(205);
//                    result.setMessage("普通会员用户每次只能选择一个评估选项");
//                    result.setData(data);
//                    return result;
//                }
//                if(educationName != null && !"".equals(educationName) && !"不限".equals(educationName)){
//                    result.setStatus(205);
//                    result.setMessage("普通会员用户每次只能选择一个评估选项");
//                    result.setData(data);
//                    return result;
//                }
//                if(workName != null && !"".equals(workName) && !"不限".equals(workName)){
//                    result.setStatus(205);
//                    result.setMessage("普通会员用户每次只能选择一个评估选项");
//                    result.setData(data);
//                    return result;
//                }
//                if(roundName != null && !"".equals(roundName)){
//                    //查询是否购买过
//                    boolean isBuy = isBuy(sceneKey, localUserId, roundName);
//                    if(isBuy){
//                        result.setStatus(200);
//                        result.setMessage("该功能已被购买，可直接进入");
//                        result.setData(data);
//                        return result;
//                    }
//                    int consumeNum = metaObtainIntegral.getIntegral();
//                    //金币足够
//                    if(totalCoins + consumeNum > 0){
//                        result.setStatus(204);
//                        result.setMessage("使用项目评估，每个选项扣除"+(-consumeNum)+"金币，共消费"+(-consumeNum)+"金币，24小时内可重复查看该选项");
//                        data.put("consumeNum", -consumeNum);
//                        //判断是否提示
//                        UserScene userScene = new UserScene();
//                        userScene.setUserId(localUserId);
//                        userScene.setSceneKey(sceneKey);
//                        userScene.setYn(0);
//                        userScene = userSceneMapper.selectOne(userScene);
//                        if(userScene == null || userScene.getFlag() == 0){
//                            data.put("flag", "0");//提示
//                        }else{
//                            data.put("flag", "1");//不提示
//                        }
//                        result.setData(data);
//                        return result;
//                    }else{
//                        result.setStatus(203);
//                        result.setMessage("使用项目评估需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
//                        data.put("consumeNum", -consumeNum);
//                        result.setData(data);
//                        return result;
//                    }
//                }else{
//                    result.setStatus(303);
//                    result.setMessage("融资阶段必须选择");
//                    return result;
//                }
//            }
//            //高级以上会员
//            if(userLevel >= 2){
//                //过滤未购买的选项
//                List<String> buys = new ArrayList<String>();
//                if(roundName != null && !"".equals(roundName)){
//                    boolean isBuy = isBuy(sceneKey, localUserId, roundName);
//                    if(!isBuy){
//                        buys.add(roundName);
//                    }
//                }else{
//                    result.setStatus(303);
//                    result.setMessage("融资阶段必须选择");
//                    return result;
//                }
//                if(industryName != null && !"".equals(industryName) && !"不限".equals(industryName)){
//                    boolean isBuy = isBuy(sceneKey, localUserId, industryName);
//                    if(!isBuy){
//                        buys.add(industryName);
//                    }
//                }
//                if(cityName != null && !"".equals(cityName) && !"不限".equals(cityName)){
//                    boolean isBuy = isBuy(sceneKey, localUserId, cityName);
//                    if(!isBuy){
//                        buys.add(cityName);
//                    }
//                }
//                if(educationName != null && !"".equals(educationName) && !"不限".equals(educationName)){
//                    boolean isBuy = isBuy(sceneKey, localUserId, educationName);
//                    if(!isBuy){
//                        buys.add(educationName);
//                    }
//                }
//                if(workName != null && !"".equals(workName) && !"不限".equals(workName)){
//                    boolean isBuy = isBuy(sceneKey, localUserId, workName);
//                    if(!isBuy){
//                        buys.add(workName);
//                    }
//                }
//                //存在需要消费的选项
//                if(buys.size() > 0){
//                    int consumeNum = metaObtainIntegral.getIntegral()*buys.size();
//                    if(totalCoins + consumeNum >= 0){
//                        result.setStatus(204);
//                        result.setMessage("使用项目评估，每个选项扣除"+(-metaObtainIntegral.getIntegral())+"金币，共消费"+(-consumeNum)+"金币，24小时内可重复查看该选项");
//                        data.put("consumeNum", -consumeNum);
//                        //判断是否提示
//                        UserScene userScene = new UserScene();
//                        userScene.setUserId(localUserId);
//                        userScene.setSceneKey(sceneKey);
//                        userScene.setYn(0);
//                        userScene = userSceneMapper.selectOne(userScene);
//                        if(userScene == null || userScene.getFlag() == 0){
//                            data.put("flag", "0");//提示
//                        }else{
//                            data.put("flag", "1");//不提示
//                        }
//                        result.setData(data);
//                        return result;
//                    }else{
//                        result.setStatus(203);
//                        result.setMessage("使用项目评估需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
//                        data.put("consumeNum", -consumeNum);
//                        result.setData(data);
//                        return result;
//                    }
//                }else{
//                    result.setStatus(200);
//                    result.setMessage("该功能已被购买，可直接进入");
//                    result.setData(data);
//                    return result;
//                }
//            }
//        }
//
//        //投递消费
//        if(SEND.equals(sceneKey)){
//            //判断会员级别
//            if(userLevel < 1){
//                result.setStatus(202);
//                result.setMessage("投递项目仅对会员用户开放，完善个人资料后赠送普通会员");
//                result.setData(data);
//                return result;
//            }
//
//            //获取机构信息
//            String[] ids = action.getInvestmentIds().split(",");
//
//            if(ids.length < 1){
//                result.setStatus(302);
//                result.setMessage("未选择机构，无法进行消费计算");
//                return result;
//            }
//
//            //获取该场景配置信息
//            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
//            metaObtainIntegral.setSceneKey(sceneKey);
//            metaObtainIntegral.setUserLevel(userLevel);
//            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
//
//            //过滤已购买机构
//            List<String> buys = new ArrayList<String>();
//            for(int i=0; i<ids.length;i++){
//                String investmentId = ids[i];
//                //判断是否购买过
//                boolean isBuy = isBuy(sceneKey, localUserId, investmentId);
//                if(!isBuy){
//                    buys.add(investmentId);
//                }
//            }
//
//            //存在需要消费的机构
//            if(buys.size() > 0){
//                //校验投递机构个数
//                int curNum = buys.size();//当前投递机构数
//
//                //获取已经投递机构数（有效的）
//                Example example = new Example(UserIntegralConsumeDatas.class);
//                example.and().andEqualTo("userId",localUserId).andEqualTo("sceneKey",sceneKey);
//                List<UserIntegralConsumeDatas> userIntegralConsumeDatasList = userIntegralConsumeDatasMapper.selectByExample(example);
//                int dayNum = 0;
//                for(UserIntegralConsumeDatas consumeDatas : userIntegralConsumeDatasList){
//                    UserIntegralConsume consume = new UserIntegralConsume();
//                    consume.setId(consumeDatas.getUserIntegralConsumeId());
//                    consume = userIntegralConsumeMapper.selectOne(consume);
//                    Date endTime = consume.getEndTime();
//                    if(endTime != null){
//                        Date now = new Date();
//                        if(endTime.getTime() > now.getTime()){
//                            dayNum ++;
//                        }
//                    }
//                }
//                if((curNum + dayNum) > metaObtainIntegral.getDeliverNum()){
//                    result.setStatus(205);
//                    switch (userLevel){
//                        case 1:
//                            result.setMessage("普通会员只能选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
//                            data.put("button", "0");
//                            break;
//                        case 2:
//                            result.setMessage("高级会员只能选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
//                            data.put("button", "0");
//                            break;
//                        case 3:
//                            result.setMessage("VIP会员每日可选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
//                            data.put("button", "0");
//                            break;
//                        case 4:
//                            result.setMessage("VIP投资人每日可选择"+metaObtainIntegral.getDeliverNum()+"个机构进行提交，当前已投递"+dayNum+"个");
//                            data.put("button", "1");
//                            break;
//                    }
//                    result.setData(data);
//                    return result;
//                }
//
//                int consumeNum = metaObtainIntegral.getIntegral()*buys.size();
//                //金币足够
//                if(totalCoins + consumeNum >= 0){
//                    result.setStatus(204);
//                    result.setMessage("使用投递项目，投递1个机构扣除"+(-metaObtainIntegral.getIntegral())+"金币，共消费"+(-consumeNum)+"金币，24小时内可重复提交给该机构");
//                    data.put("consumeNum", -consumeNum);
//                    //判断是否提示
//                    UserScene userScene = new UserScene();
//                    userScene.setUserId(localUserId);
//                    userScene.setSceneKey(sceneKey);
//                    userScene.setYn(0);
//                    userScene = userSceneMapper.selectOne(userScene);
//                    if(userScene == null || userScene.getFlag() == 0){
//                        data.put("flag", "0");//提示
//                    }else{
//                        data.put("flag", "1");//不提示
//                    }
//                    result.setData(data);
//                    return result;
//                }else{
//                    result.setStatus(203);
//                    result.setMessage("使用投递项目，需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
//                    data.put("consumeNum", -consumeNum);
//                    result.setData(data);
//                    return result;
//                }
//            }else{
//                result.setStatus(200);
//                result.setMessage("该功能已被购买，可直接进入");
//                result.setData(data);
//                return result;
//            }
//        }
//
//        return result;
//    }

    /**
     * 信息流里查看约谈的提示接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> interviewTips(InterviewInputDto body){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Date now = new Date();
        String token = "";
        if (body.getToken() != null){
            token = body.getToken();
        }

        if ("".equals(token) || "undefined".equals(token)){
            result.setStatus(502);
            result.setMessage("用户token不能为空");
            result.setData(null);

            return result;
        }

        String eventName = "";
        if (body.getEventName() != null){
            eventName = body.getEventName();
        }

        if ("".equals(eventName) || "undefined".equals(eventName)){
            result.setStatus(502);
            result.setMessage("事件流类型不能为空");
            result.setData(null);

            return result;
        }

        String scence = "";
        if (body.getScence() != null){
            scence = body.getScence();
        }

        if ("".equals(scence) || "undefined".equals(scence)){
            result.setStatus(502);
            result.setMessage("场景key不能为空");
            result.setData(null);

            return result;
        }

        //获取用户的id
        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setStatus(502);
            result.setMessage("用户token不存在，请检查");
            result.setData(null);

            return result;
        }

        //获取当前场景的消费金额
        Integer costNum = getSenceCost(scence,1);

        //先查一下用户是否已经消费过
        Example uicExample = new Example(UserIntegralConsume.class);
        uicExample.and().andEqualTo("sceneKey",eventName).andEqualTo("userId",userId);
        uicExample.setOrderByClause("end_time desc");

        //获取用户是否设置不再提示
        Map<String,Object> data = new HashMap<>();
        UserScene userScene = new UserScene();
        userScene.setUserId(userId);
        userScene.setSceneKey(scence);
        userScene.setYn(0);
        userScene = userSceneMapper.selectOne(userScene);
        if(userScene == null || userScene.getFlag() == 0){
            data.put("flag", "0");//提示
        }else{
            data.put("flag", "1");//不提示
        }

        List<UserIntegralConsume> userIntegralConsume = userIntegralConsumeMapper.selectByExample(uicExample);
        if (userIntegralConsume.size() > 0 ){
            if (userIntegralConsume.get(0).getEndTime().getTime() > now.getTime()){
                result.setMessage("用户已经购买查看当前约谈内容权限，可直接查看");
                result.setStatus(200);
                result.setData(null);
            }else {

                //判断用户账户余额是否充足
                if (jugeCoinsIsEnough(userId,costNum)){
                    result.setStatus(204);
                    result.setMessage("查看约谈内容，共消费"+(-costNum)+"金币，一次性收费后不再计费");
                    result.setData(data);
                }else {
                    result.setStatus(203);
                    result.setMessage("查看约谈内容，需要"+(-costNum)+"金币，您的金币已不足，快去充值吧");
                }
            }
        }else {
            //判断用户账户余额是否充足
            if (jugeCoinsIsEnough(userId,costNum)){
                result.setStatus(204);
                result.setMessage("查看约谈内容，共消费"+(-costNum)+"金币，一次性收费后不再计费");
                result.setData(data);
            }else {
                result.setStatus(203);
                result.setMessage("查看约谈内容，需要"+(-costNum)+"金币，您的金币已不足，快去充值吧");
            }
        }


        return result;
    }

    /**
     * 判断金币是否充足接口
     * @param userId 用户id
     * @param costNum 花费数量
     * @return
     */
    private boolean jugeCoinsIsEnough(Integer userId,Integer costNum){
        boolean jieguo = false;
        //查询当前用户金币余额
        Map<String, Object> u = userIntegralsMapper.findIntegralsU(userId);
        Integer z = Integer.valueOf(String.valueOf(u.get("xnum")));
        Integer x = Integer.valueOf(String.valueOf(u.get("znum")));
        int totalCoins = z+x;

        if ((-costNum) < totalCoins){
            jieguo = true;
        }

        return jieguo;
    }

    /**
     * 获取场景价格的接口
     * @param sence 场景key
     * @param userlevel 用户等级
     * @return
     */
    private Integer getSenceCost(String sence,Integer userlevel){
        Integer result = 0;

        Example moiExample = new Example(MetaObtainIntegral.class);
        moiExample.and().andEqualTo("sceneKey",sence).andEqualTo("userLevel");

       List<MetaObtainIntegral> metaObtainIntegralList = metaObtainIntegralMapper.selectByExample(moiExample);
       if (metaObtainIntegralList.size() > 0){
           result = metaObtainIntegralList.get(0).getIntegral();
       }


        return result;
    }

    /**
     * 信息流里查看约谈的消费接口
     * @param body
     * @return
     */
    @Transactional
    @Override
    public CommonDto<Map<String,Object>> interviewCost(InterviewInputDto body){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        String token = "";
        if (body.getToken() != null){
            token = body.getToken();
        }

        if ("".equals(token) || "undefined".equals(token)){
            result.setStatus(502);
            result.setMessage("用户token不能为空");
            result.setData(null);

            return result;
        }

        String eventName = "";
        if (body.getEventName() != null){
            eventName = body.getEventName();
        }

        if ("".equals(eventName) || "undefined".equals(eventName)){
            result.setStatus(502);
            result.setMessage("事件流类型不能为空");
            result.setData(null);

            return result;
        }

        String scence = "";
        if (body.getScence() != null){
            scence = body.getScence();
        }

        if ("".equals(scence) || "undefined".equals(scence)){
            result.setStatus(502);
            result.setMessage("场景key不能为空");
            result.setData(null);

            return result;
        }

        //获取用id
        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setStatus(502);
            result.setMessage("用户token已经不存在，请检查");
            result.setData(null);

            return result;
        }

        Example moiExample = new Example(MetaObtainIntegral.class);
        moiExample.and().andEqualTo("sceneKey",scence).andEqualTo("userLevel",1);
        List<MetaObtainIntegral> metaObtainIntegralList = metaObtainIntegralMapper.selectByExample(moiExample);

        //获取需要消费金币
        Integer costNum = 0;
        //获取周期
        Integer period = 0;
        if (metaObtainIntegralList.size() > 0){
            costNum = metaObtainIntegralList.get(0).getIntegral();
            period = metaObtainIntegralList.get(0).getPeriod();
        }else {
            result.setData(null);
            result.setMessage("场景类型有误，没有找到对应场景定价信息");
            result.setStatus(502);

            return result;
        }

        Date now = new Date();

        //计算失效时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, period);
        Date end = calendar.getTime();

        //插入消费表
        UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
        userIntegralConsume.setUserId(userId);
        userIntegralConsume.setSceneKey(scence);
        userIntegralConsume.setCostNum(costNum);
        userIntegralConsume.setBeginTime(now);
        userIntegralConsume.setCreateTime(now);
        userIntegralConsume.setEndTime(end);
        userIntegralConsumeMapper.insert(userIntegralConsume);

        //更新金币记录表
        changeIntegrals(userId, -costNum);

        Integer consumeId = userIntegralConsume.getId();
        //插入交易记录明细表
        UserIntegralConsumeDatas newUserIntegralConsumeDatas = new UserIntegralConsumeDatas();
        newUserIntegralConsumeDatas.setUserId(userId);
        newUserIntegralConsumeDatas.setSceneKey(scence);
        newUserIntegralConsumeDatas.setDatasId(body.getEventName());
        newUserIntegralConsumeDatas.setConsumeDate(new Date());
        newUserIntegralConsumeDatas.setUserIntegralConsumeId(consumeId);
        userIntegralConsumeDatasMapper.insert(newUserIntegralConsumeDatas);

        result.setData(null);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }
    /**
     * 消费金币
     * @param action 请求对象
     * @return
     */
    @Transactional
    @Override
    public CommonDto<Map<String, Object>> consume(ActionDto action) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        Map<String, Object> data = new HashMap<String,Object>();

        //获取本系统userId
        Integer localUserId = this.getLocalUserId(action.getUserId());
        if(localUserId == null){
            result.setStatus(301);
            result.setMessage("当前用户信息无效");
            return result;
        }

        //查询当前用户会员等级
        UserLevelRelation userLevelRelation = new UserLevelRelation();
        userLevelRelation.setUserId(localUserId);
        userLevelRelation.setYn(1);
        userLevelRelation.setStatus(1);
        userLevelRelation = userLevelRelationMapper.selectOne(userLevelRelation);
        int userLevel = 0;
        if(userLevelRelation != null){
            userLevel = userLevelRelation.getLevelId();
        }

        String sceneKey = action.getSceneKey();
        //首页消费与项目
        if(PROJECT.equals(sceneKey)){

            //获取消费金币数量
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(4);
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
            int consumeNum = metaObtainIntegral.getIntegral();

            Date now = new Date();
            //计算失效时间
            int period = metaObtainIntegral.getPeriod();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, period);
            Date end = calendar.getTime();

            //插入消费表
            UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
            userIntegralConsume.setUserId(localUserId);
            userIntegralConsume.setSceneKey(sceneKey);
            userIntegralConsume.setCostNum(consumeNum);
            userIntegralConsume.setBeginTime(now);
            userIntegralConsume.setCreateTime(now);
            userIntegralConsume.setEndTime(end);
            userIntegralConsumeMapper.insert(userIntegralConsume);

            //更新金币记录表
            this.changeIntegrals(localUserId, -consumeNum);

            result.setStatus(200);
            result.setMessage("金币消费成功");
            result.setData(data);
            return result;

        }
        //约谈消费
        if(INTERVIEW.equals(sceneKey)){

            //查询项目信息(是否属于50机构)
            Integer type = projectsMapper.findIvestmentTypeById(action.getProjectsId());

            //获取消费金币数量
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(4);
            if(type == 1){
                metaObtainIntegral.setProjectsType(1);
            }else{
                metaObtainIntegral.setProjectsType(0);
            }
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
            int consumeNum = metaObtainIntegral.getIntegral();

            Date now = new Date();

            //计算失效时间
            Date end = userLevelRelation.getEndTime();

            //插入消费表
            UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
            userIntegralConsume.setUserId(localUserId);
            userIntegralConsume.setSceneKey(sceneKey);
            userIntegralConsume.setCostNum(consumeNum);
            userIntegralConsume.setBeginTime(now);
            userIntegralConsume.setCreateTime(now);
            userIntegralConsume.setEndTime(end);
            userIntegralConsumeMapper.insert(userIntegralConsume);
            int consumeId = userIntegralConsume.getId();

            //更新金币记录表
            this.changeIntegrals(localUserId, -consumeNum);

            //插入交易记录明细表
            UserIntegralConsumeDatas newUserIntegralConsumeDatas = new UserIntegralConsumeDatas();
            newUserIntegralConsumeDatas.setUserId(localUserId);
            newUserIntegralConsumeDatas.setSceneKey(sceneKey);
            newUserIntegralConsumeDatas.setDatasId(action.getProjectsId()+"");
            newUserIntegralConsumeDatas.setConsumeDate(new Date());
            newUserIntegralConsumeDatas.setUserIntegralConsumeId(consumeId);
            userIntegralConsumeDatasMapper.insert(newUserIntegralConsumeDatas);

            result.setStatus(200);
            result.setMessage("金币消费成功");
            result.setData(data);
            return result;

        }

        //评估消费
        if(ASSESS.equals(sceneKey)){

            //判断权限
            String roundName = action.getRoundName();
            String industryName = action.getIndustryName();
            String cityName = action.getCityName();
            String educationName =action.getEducationName();
            String workName = action.getWorkName();

            //获取该场景配置信息
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(userLevel);
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);

            //普通会员
            if(userLevel == 1){
                int consumeNum = metaObtainIntegral.getIntegral();
                //金币足够
                Date now = new Date();
                //计算失效时间
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(now);
                calendar.add(Calendar.DAY_OF_YEAR, metaObtainIntegral.getPeriod());
                Date end = calendar.getTime();
                //插入消费表
                UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
                userIntegralConsume.setUserId(localUserId);
                userIntegralConsume.setSceneKey(sceneKey);
                userIntegralConsume.setCostNum(consumeNum);
                userIntegralConsume.setBeginTime(now);
                userIntegralConsume.setCreateTime(now);
                userIntegralConsume.setEndTime(end);
                userIntegralConsumeMapper.insert(userIntegralConsume);
                int consumeId = userIntegralConsume.getId();

                //更新金币记录表
                this.changeIntegrals(localUserId, -consumeNum);

                //插入交易记录明细表
                UserIntegralConsumeDatas newUserIntegralConsumeDatas = new UserIntegralConsumeDatas();
                newUserIntegralConsumeDatas.setUserId(localUserId);
                newUserIntegralConsumeDatas.setSceneKey(sceneKey);
                newUserIntegralConsumeDatas.setDatasId(roundName);
                newUserIntegralConsumeDatas.setConsumeDate(new Date());
                newUserIntegralConsumeDatas.setUserIntegralConsumeId(consumeId);
                userIntegralConsumeDatasMapper.insert(newUserIntegralConsumeDatas);

                result.setStatus(200);
                result.setMessage("金币消费成功");
                result.setData(data);
                return result;
            }
            //高级以上会员
            if(userLevel >= 2){
                //过滤未购买的选项
                List<String> buys = new ArrayList<String>();
                if(roundName != null && !"".equals(roundName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, roundName);
                    if(!isBuy){
                        buys.add(roundName);
                    }
                }
                if(industryName != null && !"".equals(industryName) && !"不限".equals(industryName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, industryName);
                    if(!isBuy){
                        buys.add(industryName);
                    }
                }
                if(cityName != null && !"".equals(cityName) && !"不限".equals(cityName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, cityName);
                    if(!isBuy){
                        buys.add(cityName);
                    }
                }
                if(educationName != null && !"".equals(educationName) && !"不限".equals(educationName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, educationName);
                    if(!isBuy){
                        buys.add(educationName);
                    }
                }
                if(workName != null && !"".equals(workName) && !"不限".equals(workName)){
                    boolean isBuy = isBuy(sceneKey, localUserId, workName);
                    if(!isBuy){
                        buys.add(workName);
                    }
                }
                //存在需要消费的选项
                int consumeNum = metaObtainIntegral.getIntegral()*buys.size();
                Date now = new Date();
                //计算失效时间
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(now);
                calendar.add(Calendar.DAY_OF_YEAR, metaObtainIntegral.getPeriod());
                Date end = calendar.getTime();
                //插入消费表
                UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
                userIntegralConsume.setUserId(localUserId);
                userIntegralConsume.setSceneKey(sceneKey);
                userIntegralConsume.setCostNum(consumeNum);
                userIntegralConsume.setBeginTime(now);
                userIntegralConsume.setCreateTime(now);
                userIntegralConsume.setEndTime(end);
                userIntegralConsumeMapper.insert(userIntegralConsume);
                int consumeId = userIntegralConsume.getId();

                //更新金币记录表
                this.changeIntegrals(localUserId, -consumeNum);

                //插入交易记录明细表
                for(String string : buys){
                    UserIntegralConsumeDatas newUserIntegralConsumeDatas = new UserIntegralConsumeDatas();
                    newUserIntegralConsumeDatas.setUserId(localUserId);
                    newUserIntegralConsumeDatas.setSceneKey(sceneKey);
                    newUserIntegralConsumeDatas.setDatasId(string);
                    newUserIntegralConsumeDatas.setConsumeDate(new Date());
                    newUserIntegralConsumeDatas.setUserIntegralConsumeId(consumeId);
                    userIntegralConsumeDatasMapper.insert(newUserIntegralConsumeDatas);
                }

                result.setStatus(200);
                result.setMessage("金币消费成功");
                result.setData(data);
                return result;
            }
        }

        //投递消费
        if(SEND.equals(sceneKey)){
            //获取机构信息
            String[] ids = action.getInvestmentIds().split(",");

            //获取该场景配置信息
            MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
            metaObtainIntegral.setSceneKey(sceneKey);
            metaObtainIntegral.setUserLevel(userLevel);
            metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);

            //过滤已购买机构
            List<String> buys = new ArrayList<String>();
            for(int i=0; i<ids.length;i++){
                String investmentId = ids[i];
                //判断是否购买过
                boolean isBuy = isBuy(sceneKey, localUserId, investmentId);
                if(!isBuy){
                    buys.add(investmentId);
                }
            }

            int consumeNum = metaObtainIntegral.getIntegral()*buys.size();
            Date now = new Date();
            //计算失效时间
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, metaObtainIntegral.getPeriod());
            Date end = calendar.getTime();
            //插入消费表
            UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
            userIntegralConsume.setUserId(localUserId);
            userIntegralConsume.setSceneKey(sceneKey);
            userIntegralConsume.setCostNum(consumeNum);
            userIntegralConsume.setBeginTime(now);
            userIntegralConsume.setCreateTime(now);
            userIntegralConsume.setEndTime(end);
            userIntegralConsumeMapper.insert(userIntegralConsume);
            int consumeId = userIntegralConsume.getId();

            //更新金币记录表
            this.changeIntegrals(localUserId, -consumeNum);

            //插入交易记录明细表
            for(String string : buys){
                UserIntegralConsumeDatas newUserIntegralConsumeDatas = new UserIntegralConsumeDatas();
                newUserIntegralConsumeDatas.setUserId(localUserId);
                newUserIntegralConsumeDatas.setSceneKey(sceneKey);
                newUserIntegralConsumeDatas.setDatasId(string);
                newUserIntegralConsumeDatas.setConsumeDate(new Date());
                newUserIntegralConsumeDatas.setUserIntegralConsumeId(consumeId);
                userIntegralConsumeDatasMapper.insert(newUserIntegralConsumeDatas);
            }

            result.setStatus(200);
            result.setMessage("金币消费成功");
            result.setData(data);
            return result;
        }

        return result;
    }

    /**
     * 更新金币记录表
     * @param userId 用户ID
     * @param consumeNum 此次消费金币数量
     */
    private void changeIntegrals(int userId, int consumeNum){
        Example condition = new Example(UserIntegrals.class);
        condition.and().andEqualTo("userId", userId).andGreaterThan("endTime", new Date()).andGreaterThan("integralNum", 0);
        condition.setOrderByClause("end_time asc");
        List<UserIntegrals> integrals = userIntegralsMapper.selectByExample(condition);
        for(int i=0; i<integrals.size(); i++){
            //当前金币已消费数量
            int res = integrals.get(i).getIntegralNum() + integrals.get(i).getConsumeNum();
            if(res >= consumeNum){
                integrals.get(i).setConsumeNum(integrals.get(i).getConsumeNum()-consumeNum);
                integrals.get(i).setConsumeTime(new Date());
                userIntegralsMapper.updateByPrimaryKey(integrals.get(i));
                consumeNum = 0;
            }else if(res != 0){
                integrals.get(i).setConsumeNum(-integrals.get(i).getIntegralNum());
                integrals.get(i).setConsumeTime(new Date());
                userIntegralsMapper.updateByPrimaryKey(integrals.get(i));
                consumeNum = consumeNum - res;
            }
            if(consumeNum == 0){
                break;
            }
        }
    }

    /**
     * 用户取消消费提示
     * @param userId 用户ID
     * @param sceneKey 场景KEY
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> cancel(String userId, String sceneKey) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        //获取本系统userId
        Integer localUserId = this.getLocalUserId(userId);
        if(localUserId == null){
            result.setStatus(301);
            result.setMessage("当前用户信息无效");
            return result;
        }

        UserScene userScene = new UserScene();
        userScene.setUserId(localUserId);
        userScene.setSceneKey(sceneKey);
        userScene.setYn(0);
        userScene = userSceneMapper.selectOne(userScene);
        if(userScene != null){
            //更新取消标识
            userScene.setFlag(1);
            userSceneMapper.updateByPrimaryKey(userScene);
        }else{
            //插入取消操作
            UserScene newUserScene = new UserScene();
            newUserScene.setUserId(localUserId);
            newUserScene.setSceneKey(sceneKey);
            newUserScene.setFlag(1);
            newUserScene.setYn(0);
            newUserScene.setCreateTime(new Date());
            userSceneMapper.insert(newUserScene);
        }

        result.setStatus(200);
        result.setMessage("当前场景的消费提示已取消");
        return result;
    }

    /**
     * 机构投递/项目约谈/评估 使用，判断当前功能是否购买过且还在有效期
     * @param sceneKey  场景key
     * @param userId 用户ID
     * @param datasId 评估选项/机构ID/项目ID
     * @return
     */
    private boolean isBuy(String sceneKey, int userId, String datasId){
        boolean isBuy = false;
        UserIntegralConsumeDatas userIntegralConsumeDatas = new UserIntegralConsumeDatas();
        userIntegralConsumeDatas.setSceneKey(sceneKey);
        userIntegralConsumeDatas.setUserId(userId);
        userIntegralConsumeDatas.setDatasId(datasId);
        List<UserIntegralConsumeDatas> userIntegralConsumeDatasList = userIntegralConsumeDatasMapper.select(userIntegralConsumeDatas);
        //记录有效数据
        List<UserIntegralConsumeDatas>  validData = new ArrayList<UserIntegralConsumeDatas>();
        if(userIntegralConsumeDatasList.size() > 0){
            for(UserIntegralConsumeDatas consumeDatas : userIntegralConsumeDatasList){
                //计算是否失效
                UserIntegralConsume userIntegralConsume = new UserIntegralConsume();
                userIntegralConsume.setId(consumeDatas.getUserIntegralConsumeId());
                userIntegralConsume = userIntegralConsumeMapper.selectOne(userIntegralConsume);
                Date endTime = userIntegralConsume.getEndTime();
                if(endTime != null){
                    Date now = new Date();
                    if(endTime.getTime() > now.getTime()){
                        validData.add(consumeDatas);
                    }
                }else{
                    validData.add(consumeDatas);
                }
            }
        }
        //存在有效数据及已购买该功能
        if(validData.size() > 0){
            isBuy = true;
        }
        return isBuy;
    }

    /**
     * 获取本地用户ID
     * @param userId 用户ID
     * @return
     */
    private Integer getLocalUserId(String userId){
        //获取本系统用户ID
        Example userExample = new Example(Users.class);
        userExample.and().andEqualTo("uuid", userId);
        List<Users> users = usersMapper.selectByExample(userExample);
        Integer localUserId = null;
        if(users.size() > 0){
            localUserId = users.get(0).getId();
        }
        return localUserId;
    }

    /**
     * 获取当前会员等级对应的购买场景key
     * @param userLevelId
     * @return
     */
    private String getUserLevelKey(int userLevelId){
        String sceneKey = null;
        switch(userLevelId){
            case 1:
                sceneKey = ONE;
                break;
            case 2:
                sceneKey = TWO;
                break;
            case 3:
                sceneKey = THREE;
                break;
            case 4:
                sceneKey = FOUR;
                break;
        }
        return sceneKey;
    }

    /**
     * 购买会员的金币记录表
     */
    private CommonDto<String> insertMember(Integer userId,String sKey,Integer leId) {
        CommonDto<String> result = new CommonDto<String>();
        Map<String,Integer> map =new HashMap<String,Integer>();
        if(userId !=null){
            //当前会员状态总的金币
            Integer leId1 =usersMapper.findByUserid(userId);
            if(leId1 != null){

                //Integer dnum1 = userIntegralsMapper.findByQnum(leId1);
            	MetaUserLevel userLevel11 =new MetaUserLevel();
            	userLevel11.setId(leId1);
            	MetaUserLevel m2 = metaUserLevelMapper.selectByPrimaryKey(userLevel11);
                BigDecimal qj2=m2.getAmount();
                Integer dnum1=qj2.intValue();
                Float bei1 =usersMapper.findByBei(leId1);
                Integer hnum1 =(int)(dnum1*(1+bei1));
                //购买或升级买的金币
                Float bei2 =usersMapper.findByBei(leId);
                //Integer qj = userIntegralsMapper.findByQnum(leId);
                MetaUserLevel userLevel1 =new MetaUserLevel();
                userLevel1.setId(leId);
            	MetaUserLevel m1 = metaUserLevelMapper.selectByPrimaryKey(userLevel1);
                BigDecimal qj1=m1.getAmount();
                Integer qj=qj1.intValue();
                Integer hnum2 =(int)(qj*(1+bei2));
                Integer hnum =hnum2;
                //userIntegrals2.setSceneKey(sKey);
                //Integer snum =(int)(body.getQj()*bei);
                UserIntegrals userIntegrals =new UserIntegrals();
                userIntegrals.setUserId(userId);
                userIntegrals.setSceneKey(sKey);
                userIntegrals.setIntegralNum(hnum);
                userIntegrals.setCreateTime(new Date());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
                metaObtainIntegral.setSceneKey(sKey);
                metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
                calendar.add(Calendar.DAY_OF_YEAR,metaObtainIntegral.getPeriod());
                Date end= calendar.getTime();
                userIntegrals.setEndTime(end);
                userIntegrals.setBeginTime((new Date()));
                userIntegrals.setConsumeNum(0);
                userIntegralsMapper.insert(userIntegrals);
                //总表插入
                UserIntegralConsume userIntegrals3=new UserIntegralConsume();
                userIntegrals3.setUserId(userId);
                userIntegrals3.setSceneKey(sKey);
                userIntegrals3.setCostNum(hnum);
                //if(jb>=100){
                userIntegrals3.setCreateTime(new Date());
                Calendar calendar3 = new GregorianCalendar();
                calendar3.setTime(new Date());

                //获取该场景配置信息
                MetaObtainIntegral metaObtainIntegral3 = new MetaObtainIntegral();
                metaObtainIntegral3.setSceneKey(sKey);
                metaObtainIntegral3 = metaObtainIntegralMapper.selectOne(metaObtainIntegral3);
                calendar3.add(Calendar.DAY_OF_YEAR,metaObtainIntegral3.getPeriod());
                Date end3 = calendar3.getTime();
                userIntegrals3.setEndTime(end3);
                userIntegrals3.setBeginTime((new Date()));
                userIntegralConsumeMapper.insert(userIntegrals3);
            }else{
                leId1=1;
                Integer hnum1 = 0;
                //购买或升级买的金币
                Float bei2 =usersMapper.findByBei(leId);
                //Integer qj = userIntegralsMapper.findByQnum(leId);
                MetaUserLevel userLevel1 =new MetaUserLevel();
                userLevel1.setId(leId);
            	MetaUserLevel m1 = metaUserLevelMapper.selectByPrimaryKey(userLevel1);
                BigDecimal qj1=m1.getAmount();
                Integer qj=qj1.intValue();
                Integer hnum2 =(int)(qj*(1+bei2));
                Integer hnum =hnum2;
                //userIntegrals2.setSceneKey(sKey);
                //Integer snum =(int)(body.getQj()*bei);
                UserIntegrals userIntegrals =new UserIntegrals();
                userIntegrals.setUserId(userId);
                userIntegrals.setSceneKey(sKey);
                userIntegrals.setIntegralNum(hnum);
                userIntegrals.setCreateTime(new Date());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
                metaObtainIntegral.setSceneKey(sKey);
                metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
                calendar.add(Calendar.DAY_OF_YEAR,metaObtainIntegral.getPeriod());
                Date end= calendar.getTime();
                userIntegrals.setEndTime(end);
                userIntegrals.setBeginTime((new Date()));
                userIntegrals.setConsumeNum(0);
                userIntegralsMapper.insert(userIntegrals);
                UserIntegralConsume userIntegrals3=new UserIntegralConsume();
                userIntegrals3.setUserId(userId);
                userIntegrals3.setSceneKey(sKey);
                userIntegrals3.setCostNum(hnum);
                //if(jb>=100){
                userIntegrals3.setCreateTime(new Date());
                Calendar calendar3 = new GregorianCalendar();
                calendar3.setTime(new Date());

                //获取该场景配置信息
                MetaObtainIntegral metaObtainIntegral3 = new MetaObtainIntegral();
                metaObtainIntegral3.setSceneKey(sKey);
                metaObtainIntegral3 = metaObtainIntegralMapper.selectOne(metaObtainIntegral3);
                calendar3.add(Calendar.DAY_OF_YEAR,metaObtainIntegral3.getPeriod());
                Date end3 = calendar3.getTime();
                userIntegrals3.setEndTime(end3);
                userIntegrals3.setBeginTime((new Date()));
                userIntegralConsumeMapper.insert(userIntegrals3);

            }
        }
        return result;
    }

    /**
     * 购买会员的金币记录表
     */
    private CommonDto<String> insertMemberChange(Integer userId,String sKey) {
        CommonDto<String> result = new CommonDto<String>();
        Map<String,Integer> map =new HashMap<String,Integer>();
        if(userId !=null){
            //当前会员状态总的金币
            Integer leId1 =usersMapper.findByUserid(userId);
//            if(leId1 != null){
//                //购买或购买升级的
//                //Integer dnum1 = userIntegralsMapper.findByQnum(leId1);
//            	MetaUserLevel userLevel1 =new MetaUserLevel();
//                userLevel1.setId(leId1);
//            	MetaUserLevel m1 = metaUserLevelMapper.selectByPrimaryKey(userLevel1);
//                BigDecimal qj1=m1.getAmount();
//                Integer dnum1=qj1.intValue();
//                Float bei1 =usersMapper.findByBei(leId1);
//                Integer hnum1 =(int)(dnum1*(1+bei1));
//                //购买或升级前买的金币
//                Integer leId = null;
////                        userLevelRelationMapper.findByUserIdLeid(userId);
//
//                Integer hnum = null;
//                if(leId != null){
//                    Float bei2 =usersMapper.findByBei(leId);
//                    //Integer qj = userIntegralsMapper.findByQnum(leId);
//                    MetaUserLevel userLevel =new MetaUserLevel();
//                    userLevel.setId(leId);
//                	MetaUserLevel m = metaUserLevelMapper.selectByPrimaryKey(userLevel);
//                    BigDecimal qj2=m.getAmount();
//                    Integer qj=qj2.intValue();
//                    Integer hnum2 =(int)(qj*(1+bei2));
//                    hnum =hnum1;
//                }else{
//                    hnum = hnum1;
//                }
//
//                //userIntegrals2.setSceneKey(sKey);
//                //Integer snum =(int)(body.getQj()*bei);
//                UserIntegrals userIntegrals =new UserIntegrals();
//                userIntegrals.setConsumeNum(0);
//                userIntegrals.setUserId(userId);
//                userIntegrals.setSceneKey(sKey);
//                userIntegrals.setIntegralNum(hnum);
//                userIntegrals.setCreateTime(new Date());
//                Calendar calendar = new GregorianCalendar();
//                calendar.setTime(new Date());
//                MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
//                metaObtainIntegral.setSceneKey(sKey);
//                metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
//                calendar.add(Calendar.DAY_OF_YEAR,metaObtainIntegral.getPeriod());
//                Date end= calendar.getTime();
//                userIntegrals.setEndTime(end);
//                userIntegrals.setBeginTime((new Date()));
//                userIntegralsMapper.insert(userIntegrals);
//
//                UserIntegralConsume userIntegrals3=new UserIntegralConsume();
//                userIntegrals3.setUserId(userId);
//                userIntegrals3.setSceneKey(sKey);
//                userIntegrals3.setCostNum(hnum);
//                //if(jb>=100){
//                userIntegrals3.setCreateTime(new Date());
//                Calendar calendar3 = new GregorianCalendar();
//                calendar.setTime(new Date());
//
//                //获取该场景配置信息
//                MetaObtainIntegral metaObtainIntegral3 = new MetaObtainIntegral();
//                metaObtainIntegral3.setSceneKey(sKey);
//                metaObtainIntegral3 = metaObtainIntegralMapper.selectOne(metaObtainIntegral3);
//                calendar3.add(Calendar.DAY_OF_YEAR,metaObtainIntegral3.getPeriod());
//                Date end3 = calendar3.getTime();
//                userIntegrals3.setEndTime(end3);
//                userIntegrals3.setBeginTime((new Date()));
//                userIntegralConsumeMapper.insert(userIntegrals3);
//                //购买金币赠送的记录
//            }else{
                leId1=usersMapper.findByUserid(userId);
                Integer hnum1 = 0;
                //购买或升级买的金币
                Float bei2 =usersMapper.findByBei(leId1);
                //Integer qj = userIntegralsMapper.findByQnum(leId1);
                MetaUserLevel userLevel =new MetaUserLevel();
                userLevel.setId(leId1);
            	MetaUserLevel m = metaUserLevelMapper.selectByPrimaryKey(userLevel);
                BigDecimal qj2=m.getAmount();
                Integer qj=qj2.intValue();
                Integer hnum2 =(int)(qj*(1+bei2));
                Integer hnum =hnum2;
                //userIntegrals2.setSceneKey(sKey);
                //Integer snum =(int)(body.getQj()*bei);
                UserIntegrals userIntegrals =new UserIntegrals();
                userIntegrals.setConsumeNum(0);
                userIntegrals.setUserId(userId);
                userIntegrals.setSceneKey(sKey);
                userIntegrals.setIntegralNum(hnum);
                userIntegrals.setCreateTime(new Date());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
                metaObtainIntegral.setSceneKey(sKey);
                metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
                calendar.add(Calendar.DAY_OF_YEAR,metaObtainIntegral.getPeriod());
                Date end= calendar.getTime();
                userIntegrals.setEndTime(end);
                userIntegrals.setEndTime(end);
                userIntegrals.setBeginTime((new Date()));
                userIntegralsMapper.insert(userIntegrals);
                //插入总表
                UserIntegralConsume userIntegrals3=new UserIntegralConsume();
                userIntegrals3.setUserId(userId);
                userIntegrals3.setSceneKey(sKey);
                userIntegrals3.setCostNum(hnum);
                //if(jb>=100){
                userIntegrals3.setCreateTime(new Date());
                Calendar calendar3 = new GregorianCalendar();
                calendar3.setTime(new Date());

                //获取该场景配置信息
                MetaObtainIntegral metaObtainIntegral3 = new MetaObtainIntegral();
                metaObtainIntegral3.setSceneKey(sKey);
                metaObtainIntegral3 = metaObtainIntegralMapper.selectOne(metaObtainIntegral3);
                calendar3.add(Calendar.DAY_OF_YEAR,metaObtainIntegral3.getPeriod());
                Date end3 = calendar3.getTime();
                userIntegrals3.setEndTime(end3);
                userIntegrals3.setBeginTime((new Date()));
                userIntegralConsumeMapper.insert(userIntegrals3);

//            }
        }
        return result;
    }

    /**
     * 获取当前获取当前用户有效等级，有多个显示等级最高的那个
     * @param token
     * @return
     */
    public CommonDto<Map<String,Object>> getUserLevel(String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        Date now = new Date();

        //先获取到用户id
        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setMessage("用户token无效");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        Example userLevelExample = new Example(UserLevelRelation.class);
        userLevelExample.and().andEqualTo("userId",userId).andEqualTo("yn",1).andEqualTo("status",1).andGreaterThan("endTime",now);
        userLevelExample.setOrderByClause("level_id desc");

        List<UserLevelRelation> userLevelRelationList = userLevelRelationMapper.selectByExample(userLevelExample);
        if (userLevelRelationList.size() > 0){
            if (userLevelRelationList.get(0).getLevelId() !=1 && userLevelRelationList.get(0).getLevelId() !=4){
                Example userlevelExamplea = new Example(UserLevelRelation.class);
                //处理一下普通会员还没到期，然后就买高级会员的情况
                userlevelExamplea.and().andEqualTo("userId",userId).andEqualTo("levelId",1).andEqualTo("yn",1).andEqualTo("status",1).andGreaterThan("endTime",now);

                List<UserLevelRelation> userLevelRelationList1 = userLevelRelationMapper.selectByExample(userlevelExamplea);
                if (userLevelRelationList1.size() > 0){
                    //有没过期的1级会员，先返回一级会员
                    obj.put("userLevel",userLevelRelationList1.get(0).getLevelId());
                    obj.put("isEffective",true);
                }else {
                    //没有没过期的1级会员，显示当前会员
                    obj.put("userLevel",userLevelRelationList.get(0).getLevelId());
                    obj.put("isEffective",true);
                }
            }else {
                //显示一级或，四级会员
                obj.put("userLevel",userLevelRelationList.get(0).getLevelId());
                obj.put("isEffective",true);
            }

        }else {
            obj.put("userLevel",null);
            obj.put("isEffective",false);
        }

        result.setData(obj);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }
}
