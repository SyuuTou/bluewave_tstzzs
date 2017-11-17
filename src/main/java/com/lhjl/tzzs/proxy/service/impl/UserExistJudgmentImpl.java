package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import com.lhjl.tzzs.proxy.dto.UserYnDto;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.mapper.UsersWeixinMapper;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.model.UsersWeixin;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class UserExistJudgmentImpl implements UserExistJudgmentService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserExistJudgmentImpl.class);

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UsersWeixinMapper usersWeixinMapper;

    @Resource
    private UserTokenMapper userTokenMapper;

    //判断用户是否注册过，没有即注册。
    @Transactional
    @Override
    public CommonDto<UserExsitJudgmentDto> userExistJudgment(String oppenId){
        CommonDto<UserExsitJudgmentDto> result =new CommonDto<UserExsitJudgmentDto>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();

        UsersWeixin usersWeixin = new UsersWeixin();
        usersWeixin.setOpenid(oppenId);
        List<UsersWeixin> usersWeixins = usersWeixinMapper.select(usersWeixin);

        if (usersWeixins.size() > 0){
            //token存在的情况，返回token,返回用户手机号是否存在
            UsersWeixin usersWeixinForId = usersWeixins.get(0);
            int userId = usersWeixinForId.getUserId();

            //去获取token
            UserToken userToken = new UserToken();
            userToken.setUserId(userId);

            List<UserToken> userTokens = userTokenMapper.select(userToken);
            if (userTokens.size() > 0){
                //获取到token
                UserToken userTokenForToken = userTokens.get(0);
                String token = userTokenForToken.getToken();

                //检查用户的手机号，密码
                Users users =new Users();
                users.setId(userId);

                Users getUser =usersMapper.selectByPrimaryKey(users.getId());
                if (getUser == null){

                    log.info("获取用户信息出现异常");


                    result.setData(null);
                    result.setMessage("获取用户信息出现异常");
                    result.setStatus(401);

                    return result;
                }
                String phonenumber = getUser.getPhonenumber();
                String password = getUser.getPassword();

                if(phonenumber == null){
                    userExsitJudgmentDto.setHasphonenumber(0);
                }else{
                    userExsitJudgmentDto.setHasphonenumber(1);
                }

                if (password == null){
                    userExsitJudgmentDto.setHaspassword(0);
                }else {
                    userExsitJudgmentDto.setHaspassword(1);
                }
                userExsitJudgmentDto.setYhid(userId);
                userExsitJudgmentDto.setToken(token);
                userExsitJudgmentDto.setSuccess(true);

                result.setData(userExsitJudgmentDto);
                result.setStatus(200);
                result.setMessage("success");

            }else{
               userExsitJudgmentDto.setSuccess(false);
               userExsitJudgmentDto.setToken(null);

               log.info("当前输入token非法，请检查");

               result.setMessage("当前输入token非法，请检查");
               result.setStatus(401);
               result.setData(userExsitJudgmentDto);
            }


        }else{
            //token不存在的情况，创建用户返回手机号信息
            String token = encode();

            Date now = new Date();

            //创建用户
            Users users =new Users();
            users.setCreateTime(now);
            String uuid = token;
            users.setUuid(uuid);

            usersMapper.insertSelective(users);

            Integer userid = users.getId();
            //创建用户token
            UserToken userToken = new UserToken();
            userToken.setUserId(userid);
            userToken.setToken(token);
            userToken.setRegisterTime(now);

            userTokenMapper.insert(userToken);

            //创建用户微信信息表
            UsersWeixin usersWeixinForInsert = new UsersWeixin();
            usersWeixinForInsert.setOpenid(oppenId);
            usersWeixinForInsert.setUserId(userid);
            usersWeixinForInsert.setCreateTime(now);

            usersWeixinMapper.insertSelective(usersWeixinForInsert);


            //数据插入成功，返回结果

            userExsitJudgmentDto.setHaspassword(0);
            userExsitJudgmentDto.setHasphonenumber(0);
            userExsitJudgmentDto.setSuccess(true);
            userExsitJudgmentDto.setToken(token);
            userExsitJudgmentDto.setYhid(userid);

            result.setMessage("success");
            result.setStatus(200);
            result.setData(userExsitJudgmentDto);

        }


        return result;

    }

    //token生成方法
    private String encode(){

       Date now =new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssSSSS");
       String sdfs = sdf.format(now);

       String sjs = String.valueOf(Math.random()*10000);

       String stringForToken = sdfs+sjs;


       String token = MD5Util.md5Encode(stringForToken,"utf-8");

       return token;


    }


    //根据token判断用户手机号，密码是否存在
    @Override
    public CommonDto<UserYnDto> userYn(String token){
        CommonDto<UserYnDto> result =new CommonDto<>();

        UserYnDto userYnDto =new UserYnDto();

        UserToken userToken = new UserToken();
        userToken.setToken(token);

        List<UserToken> userTokens = userTokenMapper.select(userToken);

        if (userTokens.size() > 0){
            UserToken userTokenForId = userTokens.get(0);
            int userId = userTokenForId.getUserId();

            Users users =new Users();
            users.setId(userId);

            Users usersForTelephone = usersMapper.selectByPrimaryKey(users.getId());
            String phonenumber = usersForTelephone.getPhonenumber();
            String password = usersForTelephone.getPassword();

            if (phonenumber == null){
                userYnDto.setPhonenumberyn(0);
            }else{
                userYnDto.setPhonenumberyn(1);
            }

            if (password == null){
                userYnDto.setPasswordyn(0);
            }else {
                userYnDto.setPasswordyn(1);
            }

            userYnDto.setSuccess(true);

            result.setMessage("success");
            result.setStatus(200);
            result.setData(userYnDto);

        }else{
            userYnDto.setSuccess(false);

            result.setData(userYnDto);
            result.setStatus(401);
            result.setMessage("token非法，请检查token");
        }


        return result;
    }

    @Override
    public int getUserId(String token){
        int result = -1;
        try {
            UserToken userToken = new UserToken();
            userToken.setToken(token);

            List<UserToken> userTokens = userTokenMapper.select(userToken);

            if (userTokens.size() > 0){
                UserToken userTokenForId = userTokens.get(0);
                result = userTokenForId.getUserId();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }
}

