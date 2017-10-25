package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.UserEditService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.common.SmsCommonService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class UserEditImpl implements UserEditService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserEditImpl.class);

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UsersWeixinMapper usersWeixinMapper;

    @Resource
    private InvestorsMapper investorsMapper;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Resource
    private SmsCommonService smsCommonService;

    @Resource
    private FoundersMapper foundersMapper;

    @Resource
    private FoundersEducationMapper foundersEducationMapper;

    @Resource
    private FoundersWorkMapper foundersWorkMapper;

    @Resource
    private EvaluateService evaluateService;

    @Override
    public CommonDto<UserSetPasswordOutputDto> editUserPassword(UserSetPasswordInputDto body,int userId,String token){
        CommonDto<UserSetPasswordOutputDto> result =new CommonDto<>();
        UserSetPasswordOutputDto userSetPasswordOutputDto = new UserSetPasswordOutputDto();


        String verify = body.getVerify();
        String user7realname_cn = body.getUser7realname_cn();
        String password = body.getPassword();




        Users users = new Users();
        users.setActualName(user7realname_cn);
        users.setPhonenumber(verify);
        String passwordForSet = encodePassword(password);
        users.setPassword(passwordForSet);
        users.setId(userId);

        usersMapper.updateByPrimaryKeySelective(users);

        userSetPasswordOutputDto.setSuccess(true);
        userSetPasswordOutputDto.setMessage(null);
        userSetPasswordOutputDto.setHaspassword(true);
        userSetPasswordOutputDto.setToken(token);
        userSetPasswordOutputDto.setYhid(String.valueOf(userId));

        result.setStatus(200);
        result.setData(userSetPasswordOutputDto);
        result.setMessage("success");


        return result;
    }

    //加密算法
    private String encodePassword(String password){


       String password1 =  MD5Util.md5Encode(password,"utf-8");
       String tmpString = password1.substring(2,7);
       String password2 = password1 + tmpString;
       String result = MD5Util.md5Encode(password2,"utf-8");


        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> getUserHeadpic(int userid){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        Users users =new Users();
        users.setId(userid);

        Users usersForHeadpic =usersMapper.selectByPrimaryKey(users.getId());

        String userHeadpic = usersForHeadpic.getHeadpic();
        String userHeadpic_real = usersForHeadpic.getHeadpicReal();
        String userActualName = usersForHeadpic.getActualName();
        String headpic ="";
        String username = "";
        String leixing = "";

        //判断用户的头像，和用户名
        if (userHeadpic_real == null){
          headpic = userHeadpic;
        }else {
          headpic = userHeadpic_real;
        }

        if (userActualName == null){
            UsersWeixin usersWeixin =new UsersWeixin();
            usersWeixin.setUserId(userid);

            List<UsersWeixin> usersWeixins = usersWeixinMapper.select(usersWeixin);
            if (usersWeixins.size() > 0){
                UsersWeixin usersWeixinForUserName = new UsersWeixin();
                usersWeixinForUserName = usersWeixins.get(0);
                username = usersWeixinForUserName.getNickName();
            }else {
                username ="";
            }

        }else {
            username = userActualName;
        }

        //获取投资人类型
        Investors investors =new Investors();
        investors.setUserId(userid);

        List<Investors> investorsList = investorsMapper.select(investors);
        if (investorsList.size() > 0){
            Investors investorsForLeixing = new Investors();
            investorsForLeixing = investorsList.get(0);
            int leixingResult = investorsForLeixing.getInvestorsType();
            switch (leixingResult){
                case 0: leixing = "个人投资人";
                break;
                case 1:leixing = "机构投资人";
                break;
                case 2:leixing = "VIP投资人";
                default:
                    leixing ="";
            }
        }else {
            leixing="";
        }

        String id = String.valueOf(userid);

        //开始整理返回数据
        obj.put("headpic",headpic);
        obj.put("username",username);
        obj.put("id",id);
        obj.put("leixing",leixing);
        obj.put("success",true);

        result.setStatus(200);
        result.setData(obj);
        result.setMessage("success");


        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> updateUserHeadpic(String headpic,String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        if (token == null || "".equals(token) || "undefined".equals(token)){
            obj.put("success",false);
            obj.put("message","缺少必要参数token");

            result.setStatus(50001);
            result.setData(obj);
            result.setMessage("缺少必要参数token");

            return result;
        }

        if (headpic == null || "".equals(headpic) || "undefined".equals(headpic)){
            obj.put("success",false);
            obj.put("message","请上传头像");

            result.setStatus(50001);
            result.setData(obj);
            result.setMessage("请上传头像");

            return result;
        }

        int userid = userExistJudgmentService.getUserId(token);

        Users users = new Users();
        users.setId(userid);
        users.setHeadpicReal(headpic);

        usersMapper.updateByPrimaryKeySelective(users);

        obj.put("success",true);

        result.setMessage("success");
        result.setData(obj);
        result.setStatus(200);


        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> sendSecurityCode(String token,String phoneNum){

        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();
        Map<String,Object> jieguo = new HashMap<>();

        if (token == null || "".equals(token) || "undefined".equals(token)){
            jieguo.put("message","用户token不能为空");
            jieguo.put("status",50001);
            jieguo.put("data",null);

            obj.put("jieguo",jieguo);
            obj.put("success",false);

            result.setMessage("用户token不能为空");
            result.setData(obj);
            result.setStatus(50001);

            return result;
        }

        if (phoneNum == null || "".equals(phoneNum) || "undefined".equals(phoneNum)){
            jieguo.put("message","手机号不能为空");
            jieguo.put("status",50001);
            jieguo.put("data",null);

            obj.put("jieguo",jieguo);
            obj.put("success",false);

            result.setMessage("手机号不能为空");
            result.setData(obj);
            result.setStatus(50001);

            return result;
        }

        int userid = userExistJudgmentService.getUserId(token);
        String userId = String.valueOf(userid);
        if (userid == -1){
            jieguo.put("message","用户token非法");
            jieguo.put("status",50001);
            jieguo.put("data",null);

            obj.put("jieguo",jieguo);
            obj.put("success",false);

            result.setMessage("用户token非法");
            result.setData(obj);
            result.setStatus(50001);

            return result;
        }


        CommonDto<String> securityCode = smsCommonService.sendSMS(userId,phoneNum);

        jieguo.put("message",securityCode.getMessage());
        jieguo.put("status",securityCode.getStatus());
        jieguo.put("data",securityCode.getData());

        obj.put("jieguo",jieguo);
        obj.put("success",true);

        result.setStatus(200);
        result.setData(obj);
        result.setMessage(securityCode.getMessage());

        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> updateUserInfo(UsersInfoInputDto body){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        String user7realname_cn = body.getUser7realname_cn();
        String xiangmubiao7companyful = body.getXiangmubiao7companyful();
        String user7jobtitle = body.getUser7jobtitle();
        String city = body.getCity();
        String user7excessfield = body.getUser7excessfield();
        String industrycl7tradename = body.getIndustrycl7tradename();
        String token = body.getToken();

        if (token == null || "".equals(token) || "undefined".equals(token)){
            obj.put("success",false);
            obj.put("message","缺少必要参数token");

            result.setData(obj);
            result.setStatus(50001);
            result.setMessage("缺少必要参数token");

            return result;
        }

        if (user7realname_cn == null || "".equals(user7realname_cn) || "undefined".equals(user7realname_cn)){
            obj.put("success",false);
            obj.put("message","用户真实姓名不能为空");

            result.setData(obj);
            result.setStatus(50001);
            result.setMessage("用户真实姓名不能为空");

            return result;
        }

        if (xiangmubiao7companyful == null || "".equals(xiangmubiao7companyful) || "undefined".equals(xiangmubiao7companyful)){
            obj.put("success",false);
            obj.put("message","请输入所在公司");

            result.setData(obj);
            result.setStatus(50001);
            result.setMessage("请输入所在公司");

            return result;
        }

        if (user7jobtitle == null || "".equals(user7jobtitle) || "undefined".equals(user7jobtitle)){
            obj.put("success",false);
            obj.put("message","请输入担任职务");

            result.setData(obj);
            result.setStatus(50001);
            result.setMessage("请输入担任职务");

            return result;
        }

        if (city == null || "".equals(city) || "undefined".equals(city)){
            obj.put("success",false);
            obj.put("message","请选择所在城市");

            result.setData(obj);
            result.setStatus(50001);
            result.setMessage("请选择所在城市");

            return result;
        }

        if (user7excessfield == null || "".equals(user7excessfield) || "undefined".equals(user7excessfield)){
            obj.put("success",false);
            obj.put("message","请选择身份类型");

            result.setData(obj);
            result.setStatus(50001);
            result.setMessage("请选择身份类型");

            return result;
        }

        if (industrycl7tradename == null || "".equals(industrycl7tradename) || "undefined".equals(industrycl7tradename)){
            obj.put("success",false);
            obj.put("message","行业领域不能为空");

            result.setData(obj);
            result.setStatus(50001);
            result.setMessage("行业领域不能为空");

            return result;
        }


        //转义一下身份类型
        int shenfenleixing = -1;
        switch (user7excessfield){
            case "投资人":shenfenleixing =0;
            break;
            case "创业者":shenfenleixing = 1;
            break;
            case "产业公司":shenfenleixing =2;
            break;
            case "媒体":shenfenleixing=3;
            break;
            case "政府机构":shenfenleixing =4;
            break;
            case "服务机构":shenfenleixing =5;
            break;
            default:
                break;
        }

        int userid =  userExistJudgmentService.getUserId(token);

        //准备更新用户表数据
        Users users =new Users();
        users.setId(userid);
        users.setActualName(body.getUser7realname_cn());
        users.setCompanyName(body.getXiangmubiao7companyful());
        users.setCompanyDuties(body.getUser7jobtitle());
        users.setCity(body.getCity());
        users.setIdentityType(shenfenleixing);
        users.setIndustry(body.getIndustrycl7tradename());
        users.setDesc(body.getDesc());
        users.setCompanyDesc(body.getUser7excessfield3());
        users.setDemand(body.getUser7excessfield4());
        users.setEmail(body.getEmail());
        users.setWechat(body.getUser7wechatnumb_noana());
        users.setWorkCard(body.getUser7businesscaa_noana());

        //准备新建教育经历，工作经历
        Founders founders = new Founders();
        founders.setUserId(userid);


        List<Founders> foundersList = foundersMapper.select(founders);

        //获取foundersId
        int foundersId = -1;
        if (foundersList.size() > 0){
            Founders foundersForId = foundersList.get(0);

            foundersId = foundersForId.getId();

        }else{
            foundersMapper.insertSelective(founders);

            foundersId = founders.getId();
        }

        //删除原来的工作经历和教育经历
        FoundersEducation foundersEducationForDelete = new FoundersEducation();
        foundersEducationForDelete.setFounderId(foundersId);

        FoundersWork foundersWorkForDelete =new FoundersWork();
        foundersWorkForDelete.setFounderId(foundersId);

        foundersEducationMapper.delete(foundersEducationForDelete);
        foundersWorkMapper.delete(foundersWorkForDelete);


        //初始化教育经历和工作经历
        String educationInsert = body.getUser7educatione_noana();
        if (educationInsert == null || "undefined".equals(educationInsert)){
            educationInsert = "";
        }

        String workInsert = body.getUser7workexperi_noana();
        if (workInsert == null || "undefined".equals(workInsert)){
            workInsert ="";
        }


        String [] education = educationInsert.split(",");
        String [] work = workInsert.split(",");

        List<FoundersEducation> foundersEducationList= new ArrayList<>();

        for(String string : education){
            FoundersEducation foundersEducation = new FoundersEducation();
            foundersEducation.setFounderId(foundersId);
            foundersEducation.setEducationExperience(string);

            foundersEducationList.add(foundersEducation);
        }

        List<FoundersWork> foundersWorkList = new ArrayList<>();

        for(String string : work){
            FoundersWork foundersWork = new FoundersWork();
            foundersWork.setFounderId(foundersId);
            foundersWork.setWorkExperience(string);

            foundersWorkList.add(foundersWork);
        }

        foundersWorkMapper.insertList(foundersWorkList);
        foundersEducationMapper.insertList(foundersEducationList);

        //更新用户表信息

        usersMapper.updateByPrimaryKeySelective(users);


        //开始设置返回值
        obj.put("success",true);

        result.setMessage("success");
        result.setStatus(200);
        result.setData(obj);

        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> getUserInfo(String token){
        CommonDto<Map<String,Object>> result =new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();
        if (token == null || "".equals(token) || "undefined".equals(token)){
            obj.put("success",false);
            obj.put("message","缺少必要参数token");

            result.setMessage("缺少必要参数token");
            result.setStatus(50001);
            result.setData(null);

            return result;
        }

        //获取用户id
        int userid = userExistJudgmentService.getUserId(token);

        // 获取用户信息
        Users users =new Users();
        users.setId(userid);

        Users usersInfo = usersMapper.selectByPrimaryKey(users.getId());

        //获取工作经历教育经历

        List<String> workForAdd = new ArrayList<>();
        List<String> educationForAdd = new ArrayList<>();
        Founders foundersSearch = new Founders();
        foundersSearch.setUserId(userid);

        List<Founders> foundersList = foundersMapper.select(foundersSearch);
        //如果没有founder的信息说明是第一次进来，教育经历，工作经历都为空
        if (foundersList.size() > 0){
            Founders foundersForId = foundersList.get(0);
            int foundersId = foundersForId.getId();

            FoundersWork foundersWork =new FoundersWork();
            FoundersEducation foundersEducation =new FoundersEducation();

            foundersWork.setFounderId(foundersId);
            foundersEducation.setFounderId(foundersId);

            List<FoundersWork> foundersWorkList = foundersWorkMapper.select(foundersWork);
            List<FoundersEducation> foundersEducationList = foundersEducationMapper.select(foundersEducation);

            if (foundersWorkList.size() > 0){
                for(FoundersWork o :foundersWorkList){
                    workForAdd.add(o.getWorkExperience());
                }

            }

            if (foundersEducationList.size() > 0){
                for(FoundersEducation f:foundersEducationList){
                  educationForAdd.add(f.getEducationExperience());
                }
            }
        }


        //获取热门数据
        CommonDto<Map<String,List<LabelList>>> remenshuju = evaluateService.queryHotData();

         //获取热门城市
         Map<String,List<LabelList>> data = remenshuju.getData();
         List<LabelList> cityKey =data.get("cityKey");

         //获取行业领域
         List<LabelList> industryKey =data.get("industryKey");


        //组装数据
         //组装城市数据
         for(LabelList l:cityKey){
            if (usersInfo.getCity().equals(l.getName())){
                l.setChecked(true);
            }
         }

         //组装行业领域信息
         String [] hangye = usersInfo.getIndustry().split(",");

         for(int i=0;i<industryKey.size();i++){
             LabelList labelList = industryKey.get(i);
            for (int j=0;j<hangye.length;j++){
                if (labelList.getName().equals(hangye[j])){
                    labelList.setChecked(true);
                }
            }
         }

         //组装身份类型
         String xianshi,xianshi1,xianshi2,xianshi3,xianshi4,xianshi5 ="";
         int shenfenleixingInt  = usersInfo.getIdentityType();
         switch (shenfenleixingInt){
             case 0:
                 xianshi="active";
                 xianshi1="";
                 xianshi2="";
                 xianshi3="";
                 xianshi4="";
                 xianshi5="";
                 break;
             case 1:
                 xianshi="";
                 xianshi1="active";
                 xianshi2="";
                 xianshi3="";
                 xianshi4="";
                 xianshi5="";
                 break;
             case 2:
                 xianshi="";
                 xianshi1="";
                 xianshi2="active";
                 xianshi3="";
                 xianshi4="";
                 xianshi5="";
                 break;
             case 3:
                 xianshi="";
                 xianshi1="";
                 xianshi2="";
                 xianshi3="active";
                 xianshi4="";
                 xianshi5="";
                 break;
             case 4:
                 xianshi="";
                 xianshi1="";
                 xianshi2="";
                 xianshi3="";
                 xianshi4="active";
                 xianshi5="";
                 break;
             case 5:
                 xianshi="";
                 xianshi1="";
                 xianshi2="";
                 xianshi3="";
                 xianshi4="";
                 xianshi5="active";
                 break;
             default:
                 xianshi="";
                 xianshi1="";
                 xianshi2="";
                 xianshi3="";
                 xianshi4="";
                 xianshi5="active";
         }
         String [] touziren = new String[6];

         touziren[0] = xianshi;
         touziren[1] = xianshi1;
         touziren[2] = xianshi2;
         touziren[3] = xianshi3;
         touziren[4] = xianshi4;
         touziren[5] = xianshi5;




         //开始组装返回数据

        //行业领域加额外字段
        List<Map<String,Object>> newIndustry= new ArrayList<>();
        for (int i=0;i<industryKey.size();i++){
            LabelList labelListForIndustryKey = industryKey.get(i);
            Map<String,Object> industryInside = new HashMap<>();
            industryInside.put("industrycl7tradename",labelListForIndustryKey.getName());
            industryInside.put("industrycl7describe",i);
            industryInside.put("isactive",labelListForIndustryKey.isChecked());
            industryInside.put("industrycl7orderofarr",i+1);

            newIndustry.add(industryInside);
        }


        obj.put("xuexiao",educationForAdd);
        obj.put("shuzu",newIndustry);
        obj.put("gzjl",workForAdd);
        obj.put("_id",userid);
        obj.put("user7realname_cn",usersInfo.getActualName());
        obj.put("desc",usersInfo.getDesc());
        obj.put("user7excessfield2",usersInfo.getCompanyDuties());
        obj.put("user7excessfield3",usersInfo.getCompanyDesc());
        obj.put("user7excessfield4",usersInfo.getDemand());
        obj.put("email",usersInfo.getEmail());
        obj.put("user7wechatnumb_noana",usersInfo.getWechat());
        obj.put("xiangmubiao7companyful",usersInfo.getCompanyName());
        obj.put("user7jobtitle",usersInfo.getCompanyDuties());
        obj.put("touzirenleixin",touziren);
        obj.put("diqu",cityKey);
        obj.put("diquming",usersInfo.getCity());
        obj.put("user7businesscaa_noana",usersInfo.getWorkCard());
        obj.put("success",true);

        result.setData(obj);
        result.setStatus(200);
        result.setMessage("success");


        return result;
    }
}
