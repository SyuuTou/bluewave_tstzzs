package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.ActionDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserLevelDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;
import com.lhjl.tzzs.proxy.service.UserLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 会员等级
 * Created by 蓝海巨浪 on 2017/10/16.
 */
@Service
public class UserLevelServiceImpl implements UserLevelService {

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
        boolean test = true;

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
                Date failTime = records.get(0).getEndTime();
                Date now = new Date();
                //剩余天数(会员余额)
                long days = (failTime.getTime() - now.getTime())/(1000*60*60*24);
                //当前用户会员等级
                int userLevelId = records.get(0).getLevelId();
                MetaUserLevel nowLevel = new MetaUserLevel();
                nowLevel.setId(userLevelId);
                nowLevel = metaUserLevelMapper.selectByPrimaryKey(nowLevel);

                if(levelId > userLevelId && userLevelId != 1){
                    int discount = (nowLevel.getAmount().intValue()/365)*(int)days;
                    BigDecimal discountB = new BigDecimal(discount);
                    BigDecimal actualPrice = userLevel.getAmount().subtract(discountB);
                    userLevelDto.setActualPrice(actualPrice);
                    userLevelDto.setDicount(discount);
                }else{
                    userLevelDto.setActualPrice(userLevel.getAmount());
                }
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
     * @return
     */
    @Transactional
    @Override
    public CommonDto<Map<String, Object>> upLevel(String userStr, int levelId) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        Integer localUserId = this.getLocalUserId(userStr);
        if(localUserId == null){
            result.setStatus(201);
            result.setMessage("当前用户信息无效");
            return result;
        }

        //获取当前用户拥有的会员信息
        UserLevelRelation userLevelRelation = new UserLevelRelation();
        userLevelRelation.setUserId(localUserId);
        userLevelRelation.setYn(1);
        userLevelRelation.setStatus(1);
        List<UserLevelRelation> records = userLevelRelationMapper.select(userLevelRelation);

        //更新用户会员记录表
        if(records.size() > 0){
            if(levelId > records.get(0).getLevelId()){
                //更新金币记录表
                String sceneKeyInsert = this.getUserLevelKey(levelId);
                this.insertMember(localUserId, sceneKeyInsert, levelId);

                //将之前的等级记录失效
                UserLevelRelation old = records.get(0);
                old.setYn(0);
                userLevelRelationMapper.updateByPrimaryKey(old);
            }else if(levelId == records.get(0).getLevelId()){
                result.setStatus(202);
                result.setMessage("该用户已是当前等级，无需升级");
                return result;
            }else if(levelId < records.get(0).getLevelId()){
                result.setStatus(203);
                result.setMessage("该用户已有更高等级，无需升级");
                return result;
            }
        }else{
            //更新金币记录表
            String sceneKeyInsert = this.getUserLevelKey(levelId);
            this.insertMember(localUserId, sceneKeyInsert, levelId);
        }

        //插入新的等级记录信息
        UserLevelRelation newOne = new UserLevelRelation();
        newOne.setYn(1);
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
        newOne.setStatus(1);
        userLevelRelationMapper.insertSelective(newOne);

        result.setStatus(200);
        result.setMessage("用户会员等级更新成功");
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
        }
        data.put("levelId", userLevel);
        //查询当前用户金币余额
        Integer z =userIntegralsMapper.findIntegralsZ(localUserId);
        Integer x =userIntegralsMapper.findIntegralsX(localUserId);
        int totalCoins = z+x;

        String sceneKey = action.getSceneKey();

        //首页消费与项目
        if(INDEX.equals(sceneKey) || PROJECT.equals(sceneKey)){
            if(userLevel < 4){
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
            UserIntegrals userIntegrals = new UserIntegrals();
            userIntegrals.setSceneKey(sceneKey);
            userIntegrals.setUserId(localUserId);
            List<UserIntegrals> userIntegralsList = userIntegralsMapper.select(userIntegrals);
            //有效数据
            List<UserIntegrals> valid = new ArrayList<UserIntegrals>();
            if(userIntegralsList.size() > 0){
                for(UserIntegrals integrals : userIntegralsList){
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
            if(userLevel < 4){
                result.setStatus(202);
                result.setMessage("约谈项目，仅对VIP投资人开放");
                result.setData(data);
                return result;
            }

            //查询项目信息(是否属于50机构)
            Integer type = projectsMapper.findIvestmentTypeById(action.getProjectsId());
            if(type == null){
                result.setStatus(301);
                result.setMessage("该项目无效");
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

            //金币足够
            if(totalCoins + consumeNum >= 0){

                result.setStatus(204);
                if(type == 1){
                    result.setMessage("约谈50项目，共消费"+(-consumeNum)+"金币，一次性收费后不再计费");
                }else{
                    result.setMessage("约谈非50项目，共消费"+(-consumeNum)+"金币，一次性收费后不再计费");
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
                    result.setMessage("约谈50项目需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
                }else{
                    result.setMessage("约谈非50项目需要"+(-consumeNum)+"金币，您的金币已不足，快去充值吧");
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

            //插入交易记录表
            UserIntegrals newUserIntegrals = new UserIntegrals();
            newUserIntegrals.setSceneKey(sceneKey);
            newUserIntegrals.setUserId(localUserId);
            newUserIntegrals.setIntegralNum(consumeNum);
            newUserIntegrals.setBeginTime(now);
            newUserIntegrals.setCreateTime(now);
            newUserIntegrals.setEndTime(end);
            userIntegralsMapper.insert(newUserIntegrals);

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

            //插入交易记录表
            UserIntegrals newUserIntegrals = new UserIntegrals();
            newUserIntegrals.setSceneKey(sceneKey);
            newUserIntegrals.setUserId(localUserId);
            newUserIntegrals.setIntegralNum(consumeNum);
            newUserIntegrals.setBeginTime(now);
            newUserIntegrals.setCreateTime(now);
            newUserIntegrals.setEndTime(end);
            userIntegralsMapper.insert(newUserIntegrals);

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

                //插入交易记录表
                UserIntegrals newUserIntegrals = new UserIntegrals();
                newUserIntegrals.setSceneKey(sceneKey);
                newUserIntegrals.setUserId(localUserId);
                newUserIntegrals.setIntegralNum(consumeNum);
                newUserIntegrals.setBeginTime(now);
                newUserIntegrals.setCreateTime(now);
                newUserIntegrals.setEndTime(end);
                userIntegralsMapper.insert(newUserIntegrals);

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

                //插入交易记录表
                UserIntegrals newUserIntegrals = new UserIntegrals();
                newUserIntegrals.setSceneKey(sceneKey);
                newUserIntegrals.setUserId(localUserId);
                newUserIntegrals.setIntegralNum(consumeNum);
                newUserIntegrals.setBeginTime(now);
                newUserIntegrals.setCreateTime(now);
                newUserIntegrals.setEndTime(end);
                userIntegralsMapper.insert(newUserIntegrals);

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

            //插入交易记录表
            UserIntegrals newUserIntegrals = new UserIntegrals();
            newUserIntegrals.setSceneKey(sceneKey);
            newUserIntegrals.setUserId(localUserId);
            newUserIntegrals.setIntegralNum(consumeNum);
            newUserIntegrals.setBeginTime(now);
            newUserIntegrals.setCreateTime(now);
            newUserIntegrals.setEndTime(end);
            userIntegralsMapper.insert(newUserIntegrals);

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

                Integer dnum1 = userIntegralsMapper.findByQnum(leId1);
                Float bei1 =usersMapper.findByBei(leId1);
                Integer hnum1 =(int)(dnum1*(1+bei1));
                //购买或升级买的金币
                Float bei2 =usersMapper.findByBei(leId);
                Integer qj = userIntegralsMapper.findByQnum(leId);
                Integer hnum2 =(int)(qj*(1+bei2));
                Integer hnum =hnum2-hnum1;
                //userIntegrals2.setSceneKey(sKey);
                //Integer snum =(int)(body.getQj()*bei);
                UserIntegrals userIntegrals =new UserIntegrals();
                userIntegrals.setUserId(userId);
                userIntegrals.setSceneKey(sKey);
                userIntegrals.setIntegralNum(hnum);
                userIntegrals.setCreateTime(new Date());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                calendar.add(Calendar.YEAR, 1);
                Date end = calendar.getTime();
                userIntegrals.setEndTime(end);
                userIntegrals.setBeginTime((new Date()));
                userIntegralsMapper.insert(userIntegrals);
            }else{
                leId1=1;
                Integer hnum1 = 0;
                //购买或升级买的金币
                Float bei2 =usersMapper.findByBei(leId);
                Integer qj = userIntegralsMapper.findByQnum(leId);
                Integer hnum2 =(int)(qj*(1+bei2));
                Integer hnum =hnum2-hnum1;
                //userIntegrals2.setSceneKey(sKey);
                //Integer snum =(int)(body.getQj()*bei);
                UserIntegrals userIntegrals =new UserIntegrals();
                userIntegrals.setUserId(userId);
                userIntegrals.setSceneKey(sKey);
                userIntegrals.setIntegralNum(hnum);
                userIntegrals.setCreateTime(new Date());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                calendar.add(Calendar.YEAR, 1);
                Date end = calendar.getTime();
                userIntegrals.setEndTime(end);
                userIntegrals.setBeginTime((new Date()));
                userIntegralsMapper.insert(userIntegrals);

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
               if(leId1 != null){
            	 //购买或购买升级的
                Integer dnum1 = userIntegralsMapper.findByQnum(leId1);
                Float bei1 =usersMapper.findByBei(leId1);
                Integer hnum1 =(int)(dnum1*(1+bei1));
                //购买或升级前买的金币
                Integer leId = userLevelRelationMapper.findByUserIdLeid(userId);

                Integer hnum = null;
                if(leId != null){
                    Float bei2 =usersMapper.findByBei(leId);
                    Integer qj = userIntegralsMapper.findByQnum(leId);
                    Integer hnum2 =(int)(qj*(1+bei2));
                    hnum =hnum1-hnum2;
                }else{
                    hnum = hnum1;
                }

                //userIntegrals2.setSceneKey(sKey);
                //Integer snum =(int)(body.getQj()*bei);
                UserIntegrals userIntegrals =new UserIntegrals();
                userIntegrals.setUserId(userId);
                userIntegrals.setSceneKey(sKey);
                userIntegrals.setIntegralNum(hnum);
                userIntegrals.setCreateTime(new Date());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                calendar.add(Calendar.YEAR, 1);
                Date end = calendar.getTime();
                userIntegrals.setEndTime(end);
                userIntegrals.setBeginTime((new Date()));
                userIntegralsMapper.insert(userIntegrals);
            }else{
                leId1=usersMapper.findByUserid(userId);
                Integer hnum1 = 0;
                //购买或升级买的金币
                Float bei2 =usersMapper.findByBei(leId1);
                Integer qj = userIntegralsMapper.findByQnum(leId1);
                Integer hnum2 =(int)(qj*(1+bei2));
                Integer hnum =hnum2-hnum1;
                //userIntegrals2.setSceneKey(sKey);
                //Integer snum =(int)(body.getQj()*bei);
                UserIntegrals userIntegrals =new UserIntegrals();
                userIntegrals.setUserId(userId);
                userIntegrals.setSceneKey(sKey);
                userIntegrals.setIntegralNum(hnum);
                userIntegrals.setCreateTime(new Date());
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());
                calendar.add(Calendar.YEAR, 1);
                Date end = calendar.getTime();
                userIntegrals.setEndTime(end);
                userIntegrals.setBeginTime((new Date()));
                userIntegralsMapper.insert(userIntegrals);

            }
        }
        return result;
    }
}
