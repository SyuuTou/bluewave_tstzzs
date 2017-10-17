package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.ActionDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserLevelDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserLevelService;
import org.springframework.stereotype.Service;
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

    /**
     * 查找会员等级信息
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
                example.and().andEqualTo("userId", localUserId).andEqualTo("yn", 0);
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
     * @return
     */
    @Override
    public CommonDto<UserLevelDto> findLevelInfo(ActionDto action) {
        CommonDto<UserLevelDto> result = new CommonDto<UserLevelDto>();
        UserLevelDto userLevelDto = new UserLevelDto();
        Integer localUserId = this.getLocalUserId(action.getUserId());
        if(localUserId == null){
            result.setStatus(203);
            result.setMessage("当前用户信息无效");
            return result;
        }

        //获取当前会员信息
        MetaUserLevel userLevel = new MetaUserLevel();
        userLevel.setId(action.getLevelId());
        userLevel = metaUserLevelMapper.selectOne(userLevel);
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
        example.and().andEqualTo("userId", localUserId).andEqualTo("yn", 0);
        List<UserLevelRelation> records = userLevelRelationMapper.selectByExample(example);
        if(records.size() > 0){
            if(action.getLevelId() == records.get(0).getLevelId()){
                //可升级状态
                userLevelDto.setBelong("1");
            }else if(action.getLevelId() < records.get(0).getLevelId()){
                //不可购买状态
                userLevelDto.setBelong("2");
            }else{
                //可购买状态
                userLevelDto.setBelong("0");
            }

            //计算当前会员实际价格
            Date failTime = records.get(0).getEndTime();
            Date now = new Date();
            //剩余天数(会员余额)
            long days = (failTime.getTime() - now.getTime())/(1000*60*60*24);
            //当前用户会员等级
            int userLevelId = records.get(0).getLevelId();
            if(action.getLevelId() > userLevelId && userLevelId != 1){
                userLevelDto.setActualPrice(userLevel.getAmount().intValue() - (int)days);
                userLevelDto.setDicount((int)days);
            }else{
                userLevelDto.setActualPrice(userLevel.getAmount().intValue());
            }
        }else{
            userLevelDto.setActualPrice(userLevel.getAmount().intValue());
            ///可购买状态
            userLevelDto.setBelong("0");
        }

        userLevelDto.setAmount(userLevel.getAmount().intValue());

        result.setStatus(200);
        result.setMessage("会员信息查询成功");
        result.setData(userLevelDto);
        return result;
    }

    /**
     * 会员升级
     * @param action
     * @return
     */
    @Override
    public CommonDto<String> upLevel(ActionDto action) {
        CommonDto<String> result = new CommonDto<String>();
        Integer localUserId = this.getLocalUserId(action.getUserId());
        if(localUserId == null){
            result.setStatus(201);
            result.setMessage("当前用户信息无效");
            return result;
        }

        //获取当前用户拥有的会员信息
        Example example = new Example(UserLevelRelation.class);
        example.and().andEqualTo("userId", localUserId).andEqualTo("yn", 0);
        List<UserLevelRelation> records = userLevelRelationMapper.selectByExample(example);
        //更新用户会员记录表
        if(records.size() > 0){
            if(action.getLevelId() > records.get(0).getLevelId()){
                //将之前的等级记录失效
                UserLevelRelation old = records.get(0);
                old.setYn(1);
                userLevelRelationMapper.updateByPrimaryKey(old);
            }else if(action.getLevelId() == records.get(0).getLevelId()){
                result.setStatus(202);
                result.setMessage("该用户已是当前等级，无需升级");
                return result;
            }else if(action.getLevelId() < records.get(0).getLevelId()){
                result.setStatus(203);
                result.setMessage("该用户已有更高等级，无需升级");
                return result;
            }
        }else{
            //更新金币记录表
            String userId = action.getUserId();
            String sceneKey = "EWJkiU7Q";
            int userLevel = action.getLevelId();
            MetaObtainIntegral condition = new MetaObtainIntegral();
            condition.setUserLevel(userLevel);
            condition.setSceneKey(sceneKey);
            int amount = metaObtainIntegralMapper.selectOne(condition).getIntegral();
            //TODO 调用金币购买接口或自行添加

        }
        //插入新的等级记录信息
        UserLevelRelation newOne = new UserLevelRelation();
        newOne.setYn(0);
        newOne.setUserId(localUserId);
        newOne.setLevelId(action.getLevelId());
        Date now = new Date();
        newOne.setBeginTime(now);
        newOne.setCreateTime(now);
        //计算失效时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, 1);
        Date end = calendar.getTime();
        newOne.setEndTime(end);
        userLevelRelationMapper.insertSelective(newOne);




        result.setStatus(200);
        result.setMessage("用户会员等级更新成功");
        return result;
    }

    /**
     * 消费金币
     * @param action
     * @return
     */
    @Override
    public CommonDto<String> consume(ActionDto action) {

        return null;
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
}
