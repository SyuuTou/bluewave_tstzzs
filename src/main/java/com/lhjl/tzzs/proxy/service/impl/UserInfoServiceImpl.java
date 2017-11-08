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
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
            params.put("user7businesscaa_noana", users.getWorkCard());
            params.put("headpic", users.getHeadpic());
            params.put("user7corporaten", users.getCompanyName());

            Founders founders = new Founders();
            founders.setUserId(userId);
            founders = foundersMapper.selectOne(founders);

            if(founders != null){
                List<String> educations = new ArrayList<>();
                FoundersEducation foundersEducation = new FoundersEducation();
                foundersEducation.setFounderId(founders.getId());
                List<FoundersEducation> educationList = foundersEducationMapper.select(foundersEducation);
                for(FoundersEducation education : educationList){
                    educations.add(education.getEducationExperience());
                }
                params.put("user7educatione_noana", educations);

                List<String> works = new ArrayList<>();
                FoundersWork foundersWork = new FoundersWork();
                foundersWork.setFounderId(founders.getId());
                List<FoundersWork> workList = foundersWorkMapper.select(foundersWork);
                for(FoundersWork work : workList){
                    works.add(work.getWorkExperience());
                }
                params.put("user7workexperi_noana", works);
            }else{
                params.put("user7educatione_noana", new ArrayList<String>());
                params.put("user7workexperi_noana", new ArrayList<String>());
            }

            Integer identity = users.getIdentityType();
            if(identity != null){
                String identiStr = "";
                switch(identity){
                    case 0:
                        identiStr = "投资人";
                        break;
                    case 1:
                        identiStr = "创业者";
                        break;
                    case 2:
                        identiStr = "产业公司";
                        break;
                    case 3:
                        identiStr = "媒体";
                        break;
                    case 4:
                        identiStr = "研究机构";
                        break;
                }
                params.put("user7excessfield", identiStr);
            }else{
                params.put("user7excessfield", "");
            }


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

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public CommonDto<List<Map<String,Object>>> getUserList(Integer pageNum,Integer pageSize){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();
        List<Map<String,Object>> list= new ArrayList<>();


        if (pageNum == null || pageNum < 0){
            pageNum = 0;
        }

        if (pageSize == null || pageSize < 0){
            pageSize = 10;
        }

        Integer startPage = pageNum*pageSize;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        list = usersMapper.findUserList(startPage,pageSize);
 //       for (Map<String,Object> users:list){
//            Date createTime= users.getCreateTime();
//            String stringDate = sdf.format(createTime);
//            users.setPassword(stringDate);
  //      }
        for(Map<String,Object> obj :list){

            obj.put("create_time",String.valueOf(obj.get("create_time")));

        }




        result.setStatus(200);
        result.setMessage("success");
        result.setData(list);


        return result;
    }
}
