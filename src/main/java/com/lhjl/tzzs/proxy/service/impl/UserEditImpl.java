package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordInputDto;
import com.lhjl.tzzs.proxy.dto.UserSetPasswordOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.mapper.UsersWeixinMapper;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.model.UsersWeixin;
import com.lhjl.tzzs.proxy.service.UserEditService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.common.SmsCommonService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        String result = "";

       String password1 =  MD5Util.md5Encode(password,"utf-8");
       String tmpString = password1.substring(2,7);
       String password2 = password1 + tmpString;
       String finalPassword = MD5Util.md5Encode(password2,"utf-8");

       result = finalPassword;

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
}
