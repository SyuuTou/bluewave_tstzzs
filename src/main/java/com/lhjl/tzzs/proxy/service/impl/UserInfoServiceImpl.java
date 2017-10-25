package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.FoundersEducationMapper;
import com.lhjl.tzzs.proxy.mapper.FoundersMapper;
import com.lhjl.tzzs.proxy.mapper.FoundersWorkMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.model.Founders;
import com.lhjl.tzzs.proxy.model.FoundersEducation;
import com.lhjl.tzzs.proxy.model.FoundersWork;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 蓝海巨浪 on 2017/10/25.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private FoundersMapper foundersMapper;
    @Resource
    private FoundersEducationMapper foundersEducationMapper;
    @Resource
    private FoundersWorkMapper foundersWorkMapper;

    /**
     * 获取个人资料
     * @param userId 用户ID
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> newrxsdqyh(int userId) {
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        List<Map<String, Object>> runCommandList = new ArrayList<>();
        List<Map<String, Object>> paramsList = new ArrayList<>();
        Map<String, Object> runCommand = new HashMap<>();
        Map<String, Object> params = new HashMap<>();

        Users users = new Users();
        users.setId(userId);
        users = usersMapper.selectByPrimaryKey(users);
        if(users != null){
            params.put("user7realname_cn", users.getActualName());
            params.put("city", users.getCity());
            params.put("desc", users.getDesc());
            params.put("email", users.getEmail());
            params.put("headpic", users.getWorkCard());
            params.put("user7corporaten", users.getCompanyName());

            Founders founders = new Founders();
            founders.setUserId(userId);
            founders = foundersMapper.selectOne(founders);

            List<String> educations = new ArrayList<>();
            FoundersEducation foundersEducation = new FoundersEducation();
            foundersEducation.setFounderId(founders.getId());
            List<FoundersEducation> educationList = foundersEducationMapper.select(foundersEducation);
            for(FoundersEducation education : educationList){
                educations.add(education.getEducationExperience());
            }
            params.put("user7educatione_noana", educations);

            params.put("user7excessfield", users.getIdentityType());

            List<String> industries = new ArrayList<>();
            if(users.getIndustry() != null){
                String[] industryArray = users.getIndustry().split(",");
                for(String string : industryArray){
                    industries.add(string);
                }
            }
            params.put("user7excessfield1", industries);

            params.put("user7excessfield2", users.getCompanyDuties());
            params.put("user7excessfield3", users.getCompanyDesc());
            params.put("user7excessfield4", users.getDemand());
            params.put("user7wechatnumb_noana", users.getWechat());

            List<String> works = new ArrayList<>();
            FoundersWork foundersWork = new FoundersWork();
            foundersWork.setFounderId(founders.getId());
            List<FoundersWork> workList = foundersWorkMapper.select(foundersWork);
            for(FoundersWork work : workList){
                works.add(work.getWorkExperience());
            }
            params.put("user7workexperi_noana", works);

            paramsList.add(params);

            runCommand.put("command", "updatelv");
            runCommand.put("params", paramsList);

            runCommandList.add(runCommand);

            Map<String, Object> data = new HashMap<>();
            data.put("success", true);
            data.put("runCommand", runCommandList);
            result.setData(data);
            result.setStatus(200);
            result.setMessage("查询个人资料成功");
        }else{
            result.setMessage("用户信息无效");
            result.setStatus(301);
            return result;
        }

        return result;
    }
}
