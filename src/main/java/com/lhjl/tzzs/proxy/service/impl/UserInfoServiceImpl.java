package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdministratorOutputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private FoundersMapper foundersMapper;
    @Resource
    private FoundersEducationMapper foundersEducationMapper;
    @Resource
    private FoundersWorkMapper foundersWorkMapper;
    @Autowired
    private MiniappFormidMapper miniappFormidMapper;
    @Autowired
    private UserChooseRecordMapper userChooseRecordMapper;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Autowired
    private ActivityApprovalLogMapper activityApprovalLogMapper;
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
        for(Map<String,Object> obj :list){
            //查找用户的最近登录记录
            if (obj.get("ID") != null){
                Example userExample = new Example(UserChooseRecord.class);
                userExample.and().andEqualTo("userId",obj.get("ID"));
                userExample.setOrderByClause("create_time desc");

                List<UserChooseRecord> userChooseRecordList = userChooseRecordMapper.selectByExample(userExample);
                if (userChooseRecordList.size() > 0){
                    String cctime = sdf.format(userChooseRecordList.get(0).getCreateTime());
                    obj.put("cctime",cctime);
                }else {
                    obj.put("cctime","");
                }
            }else {
                obj.put("cctime","");
            }


            obj.put("create_time",String.valueOf(obj.get("create_time")));
            String investorsType = "未认证或未审核投资人";
            if (obj.get("investors_type") == null){
                investorsType = "未认证或未审核投资人";
            }else {
                Integer investorsNum = (Integer)obj.get("investors_type");
                switch (investorsNum){
                    case 0:investorsType = "个人投资人";
                    break;
                    case 1:investorsType = "机构投资人";
                    break;
                    case 2:investorsType="VIP投资人";
                    break;
                    default:investorsType="未认证或未审核投资人";
                }
            }
            obj.put("investors_type",investorsType);
            obj.put("id_type",obj.get("id_type"));
        }




        result.setStatus(200);
        result.setMessage("success");
        result.setData(list);


        return result;
    }

    /**
     * 获取用户可用formid
     * @param userId 用户id
     * @return
     */
    @Override
    public CommonDto<String> getUserFormid(Integer userId){
        CommonDto<String> result =new CommonDto<>();

        List<MiniappFormid> miniappFormidList = miniappFormidMapper.findFormiDesc(userId);
        String formId = "";
        if (miniappFormidList.size() > 0){
            formId = miniappFormidList.get(0).getFormId();
            //获取当前时间3分钟之后的时间
            Date  now = new Date();
            Date nowAfter5 = new Date(now.getTime()+180000);

            //读出来以后锁定formid
            MiniappFormid miniappFormidForUpdate = new MiniappFormid();
            miniappFormidForUpdate.setId(miniappFormidList.get(0).getId());
            miniappFormidForUpdate.setYn(2);
            miniappFormidForUpdate.setRelieveTime(nowAfter5);

            miniappFormidMapper.updateByPrimaryKeySelective(miniappFormidForUpdate);
        }
        if ("".equals(formId)){
            result.setStatus(50001);
            result.setData(formId);
            result.setMessage("formid用完啦！");

            return result;
        }
        result.setMessage("success");
        result.setData(formId);
        result.setStatus(200);


        return result;
    }

    /**
     * 设置formid为失效的接口
     * @param formid
     * @return
     */
    @Override
    public CommonDto<String> setUserFormid(String formid){
        CommonDto<String> result = new CommonDto<>();
        MiniappFormid miniappFormidForSearch = new MiniappFormid();
        miniappFormidForSearch.setFormId(formid);

        //查到formid对应的记录。将该记录改为已使用
        List<MiniappFormid> miniappFormidList = miniappFormidMapper.select(miniappFormidForSearch);
        if (miniappFormidList.size() > 0){
            MiniappFormid miniappFormidForUpdate = new MiniappFormid();
            miniappFormidForUpdate.setId(miniappFormidList.get(0).getId());
            miniappFormidForUpdate.setYn(1);

            miniappFormidMapper.updateByPrimaryKeySelective(miniappFormidForUpdate);
        }else {
            result.setMessage("没有找到该formid对应的记录");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }
        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");


        return result;
    }

    /**
     * 用token换取用户真实姓名，头像，公司名称的接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<ProjectAdministratorOutputDto> getUserComplexInfo(Map<String,String> body){
        CommonDto<ProjectAdministratorOutputDto> result = new CommonDto<>();

        if (body.get("token") == null || "".equals(body.get("token")) || "undefined".equals("token")){
            result.setData(null);
            result.setStatus(50001);
            result.setMessage("用户token不能为空");

            log.info("获取用户复合信息场景");
            log.info("用户token不能为空");

            return result;
        }

        Map<String,String> userSearchResult = usersMapper.findUserComplexInfoOne(body.get("token"));

        if (userSearchResult == null){
            result.setMessage("没有找到：当前token对应的用户信息，请检查");
            result.setStatus(50001);
            result.setData(null);

            log.info("获取用户复合信息场景");
            log.info("没有找到：当前token对应的用户信息，请检查");

            return result;
        }else {
            ProjectAdministratorOutputDto projectAdministratorOutputDto = new ProjectAdministratorOutputDto();
            projectAdministratorOutputDto.setToken(body.get("token"));
            projectAdministratorOutputDto.setRealName(userSearchResult.get("actual_name"));
            projectAdministratorOutputDto.setNickName(userSearchResult.get("nick_name"));
            String headpic = "";
            if (userSearchResult.get("headpic_real") == null){
                if (userSearchResult.get("headpic") == null){
                    headpic ="";
                }else {
                    headpic = userSearchResult.get("headpic");
                }
            }else {
                headpic = userSearchResult.get("headpic_real");
            }
            projectAdministratorOutputDto.setHeadpic(headpic);
            String comanyName = "";
            if (userSearchResult.get("company_name") != null){
                comanyName = userSearchResult.get("company_name");
            }
            projectAdministratorOutputDto.setCompanyName(comanyName);

            String companyDuties = "";
            if (userSearchResult.get("company_duties") != null){
                companyDuties = userSearchResult.get("company_duties");
            }
            projectAdministratorOutputDto.setCompanyDuties(companyDuties);

            result.setData(projectAdministratorOutputDto);
            result.setStatus(200);
            result.setMessage("success");

            return result;
        }

    }

    /**
     * 获取用户是否已经报名接口
     * @param token
     * @return
     */
    @Override
    public CommonDto<String> getUserActivity(String token){
        CommonDto<String> result  = new CommonDto<>();

        if (token == null || "".equals(token) || "undefined".equals(token)){
            result.setData(null);
            result.setMessage("用户token不能为空");
            result.setStatus(502);

            return result;
        }

        //获取用户id
        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setStatus(502);
            result.setMessage("用户token不存在，请检查");
            result.setData(null);

            return result;
        }

        Example aalExample = new Example(ActivityApprovalLog.class);
        aalExample.and().andEqualTo("userId",userId);

        List<ActivityApprovalLog> activityApprovalLogList = activityApprovalLogMapper.selectByExample(aalExample);

        if (activityApprovalLogList.size() > 0){
            result.setStatus(201);
            result.setData("用户已报名");
            result.setMessage("success");
        }else {
            boolean isComplete = judgeUserMessage(userId);
            if (isComplete){
                result.setMessage("success");
                result.setData("用户资料已经完善，可直接去支付");
                result.setStatus(202);

            }else {
                result.setStatus(203);
                result.setData("用户资料未完善需要去完善资料");
                result.setMessage("success");
            }
        }


        return result;
    }

    /**
     * 判断用户的真实名称，公司职位，公司名称，手机号，身份类型是否填写完毕，只要有一个没有填写完毕就返回false
     * @param userId
     * @return
     */
    private boolean judgeUserMessage(Integer userId){
        boolean result = false;

        if (userId == null){
            return result;
        }

        Users users = usersMapper.selectByPrimaryKey(userId);
        if (users != null){
            if (StringUtils.isAnyBlank(users.getActualName(),users.getCompanyName(),users.getCompanyDuties(),users.getPhonenumber())){
                return result;
            }else {
                if (users.getIdentityType() == null){
                    return result;
                }else {
                    result = true;
                }
            }
        }

        return result;
    }

    /**
     * 活动申请页面信息回显接口
     * @param token
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> getUserActivityInfo(String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        if (token == null || "".equals(token) || "undefined".equals(token)){
            result.setMessage("用户token不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        Integer userId= userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setMessage("用户token不存在");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        Users users = usersMapper.selectByPrimaryKey(userId);

        if (users == null ){
            result.setMessage("没有找到用户的信息");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        String realName = "";
        String companyName = "";
        String companyDuties = "";
        String phoneNumber = "";
        String identityType = "";

        if (users.getActualName() != null ){
            realName = users.getActualName();
        }

        if (users.getCompanyDuties() != null ){
            companyDuties = users.getCompanyDuties();
        }

        if (users.getCompanyName() != null ){
            companyName = users.getCompanyName();
        }

        if (users.getIdentityType() != null ){
            Integer it = users.getIdentityType();
            switch (it){
                case 0:identityType= "投资人";
                    break;
                case 1:identityType= "创业者";
                    break;
                case 2:identityType= "产业公司";
                    break;
                case 3:identityType= "媒体";
                    break;
                case 4:identityType= "政府机构";
                    break;
                case 5:identityType= "服务机构";
                    break;
                    default:identityType="";
            }
        }

        if (users.getPhonenumber() != null ){
            phoneNumber = users.getPhonenumber();
        }

        obj.put("realName",realName);
        obj.put("companyName",companyName);
        obj.put("companyDuties",companyDuties);
        obj.put("phoneNumber",phoneNumber);
        obj.put("identityType",identityType);

        result.setStatus(200);
        result.setData(obj);
        result.setMessage("success");

        return result;
    }

}
