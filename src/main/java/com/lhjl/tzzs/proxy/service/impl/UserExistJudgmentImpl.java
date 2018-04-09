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
import com.lhjl.tzzs.proxy.service.bluewave.BlueUserInfoService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
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

    @Resource
    private BlueUserInfoService blueUserInfoService;

    //判断用户是否注册过，没有即注册。
    @Transactional
    @Override
    public CommonDto<UserExsitJudgmentDto> userExistJudgment(String oppenId, Integer appid, String unionid){
        CommonDto<UserExsitJudgmentDto> result =new CommonDto<UserExsitJudgmentDto>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();
        List<UsersWeixin> usersWeixins = null;
        UsersWeixin usersWeixin = new UsersWeixin();
        usersWeixin.setOpenid(oppenId);

        usersWeixins = usersWeixinMapper.select(usersWeixin);

        if (usersWeixins.size()>0){
            usersWeixin = usersWeixins.get(0);
            if (StringUtils.isNotEmpty(unionid)){
                usersWeixin.setUnionId(unionid);
                usersWeixinMapper.updateByPrimaryKey(usersWeixin);
            }
            //token存在的情况，返回token,返回用户手机号是否存在
            UsersWeixin usersWeixinForId = usersWeixins.get(0);
            int userId = usersWeixinForId.getUserId();

            log.info("用户微信号已存在，对应的用户id为：");
            log.info("userId={}",userId);

            //去获取token
            UserToken userToken = new UserToken();
            userToken.setUserId(userId);
            userToken.setMetaAppId(String.valueOf(appid));

            List<UserToken> userTokens = userTokenMapper.select(userToken);

            //  log.info("你在这里呀！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
            if (handlerUserInfo(oppenId, appid, unionid, result, userExsitJudgmentDto, usersWeixinForId, userId, userTokens))
                return result;


//        }
//
//
//
//        log.info("进入用户登录注册实现方法中");
//
//        if (usersWeixins.size() > 0){


        }else{
            if (null != unionid ) {
            usersWeixin = new UsersWeixin();
            usersWeixin.setUnionId(unionid);

            usersWeixins = usersWeixinMapper.select(usersWeixin);
            if (usersWeixins.size()>0) {
                usersWeixin = usersWeixins.get(0);
                usersWeixin.setOpenid(oppenId);
                usersWeixin.setId(null);
                usersWeixin.setMetaAppId(appid);
                usersWeixinMapper.insert(usersWeixin);

                //token存在的情况，返回token,返回用户手机号是否存在
                UsersWeixin usersWeixinForId = usersWeixins.get(0);
                int userId = usersWeixinForId.getUserId();

                log.info("用户微信号已存在，对应的用户id为：");
                log.info("userId={}", userId);

                //去获取token
                UserToken userToken = new UserToken();
                userToken.setUserId(userId);
                userToken.setMetaAppId(String.valueOf(appid));

                List<UserToken> userTokens = userTokenMapper.select(userToken);

                //  log.info("你在这里呀！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
                if (handlerUserInfo(oppenId, appid, unionid, result, userExsitJudgmentDto, usersWeixinForId, userId, userTokens))
                    return result;
            }

            }else {

                //token不存在的情况，创建用户返回手机号信息
                String token = encode();

                Date now = new Date();
                log.info("当前用户的微信号不存在，创建用户，及相关表");

                //创建用户
                Users users = new Users();
                users.setCreateTime(now);
                String uuid = token;
                users.setUuid(uuid);
                users.setMetaAppId(appid);
                users.setUnionid(unionid);
                usersMapper.insertSelective(users);

                Integer userid = users.getId();
                //创建用户token
                UserToken userToken = new UserToken();
                userToken.setUserId(userid);
                userToken.setToken(token);
                userToken.setRegisterTime(now);
                userToken.setMetaAppId(String.valueOf(appid));

                userTokenMapper.insert(userToken);

                //创建用户微信信息表
                UsersWeixin usersWeixinForInsert = new UsersWeixin();
                usersWeixinForInsert.setOpenid(oppenId);
                usersWeixinForInsert.setUserId(userid);
                usersWeixinForInsert.setCreateTime(now);
                usersWeixinForInsert.setMetaAppId(appid);
                usersWeixinForInsert.setUnionId(unionid);
                usersWeixinMapper.insertSelective(usersWeixinForInsert);


                //数据插入成功，返回结果

                userExsitJudgmentDto.setHaspassword(0);
                userExsitJudgmentDto.setHasphonenumber(0);
                userExsitJudgmentDto.setHasHeadpic(0);
                userExsitJudgmentDto.setSuccess(true);
                userExsitJudgmentDto.setToken(token);
                userExsitJudgmentDto.setYhid(userid);

                result.setMessage("success");
                result.setStatus(200);
                result.setData(userExsitJudgmentDto);
            }
        }


        return result;

    }

    public boolean handlerUserInfo(String oppenId, Integer appid, String unionid, CommonDto<UserExsitJudgmentDto> result, UserExsitJudgmentDto userExsitJudgmentDto, UsersWeixin usersWeixinForId, int userId, List<UserToken> userTokens) {
        if (userTokens.size() > 0){
            //获取到token
            UserToken userTokenForToken = userTokens.get(0);
            String token = userTokenForToken.getToken();

            log.info("获取到用户token,token为：");
            log.info(token);

            //检查用户的手机号，密码
            Users users =new Users();
            users.setId(userId);

            Users getUser =usersMapper.selectByPrimaryKey(users.getId());
            if (getUser == null){

                log.info("获取用户信息出现异常,此时的用户id为：");
                log.info("userId={}",userId);

                result.setData(null);
                result.setMessage("获取用户信息出现异常");
                result.setStatus(401);

                return true;
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

            //获取用户是否有头像
            Integer headpicYn = blueUserInfoService.checkUserHeadpic(token,appid).getData();
            if (headpicYn != 1){
                userExsitJudgmentDto.setHasHeadpic(0);
            }else {
                userExsitJudgmentDto.setHasHeadpic(1);
            }

            userExsitJudgmentDto.setYhid(userId);
            userExsitJudgmentDto.setToken(token);
            userExsitJudgmentDto.setSuccess(true);

            result.setData(userExsitJudgmentDto);
            result.setStatus(200);
            result.setMessage("success");

        }else{

            log.info("当前用户的token信息不存在，请检查,当前用户的oppenId为：");
            log.info(oppenId);
            log.info("token不存在的话，删掉微信信息，重新创建用户");

            Integer userweixinId = usersWeixinForId.getId();

            //删掉微信表的信息
            usersWeixinMapper.deleteByPrimaryKey(userweixinId);

            //创建用户返回手机号信息
            String token = encode();

            Date now = new Date();
            log.info("创建用户，及相关表");

            //创建用户
            Users users =new Users();
            users.setCreateTime(now);
            String uuid = token;
            users.setUuid(uuid);
            users.setMetaAppId(appid);

            usersMapper.insertSelective(users);

            Integer userid = users.getId();
            //创建用户token
            UserToken userToken1 = new UserToken();
            userToken1.setUserId(userid);
            userToken1.setToken(token);
            userToken1.setRegisterTime(now);
            userToken1.setMetaAppId(String.valueOf(appid));

            userTokenMapper.insert(userToken1);

            //创建用户微信信息表
            UsersWeixin usersWeixinForInsert1 = new UsersWeixin();
            usersWeixinForInsert1.setOpenid(oppenId);
            usersWeixinForInsert1.setUserId(userid);
            usersWeixinForInsert1.setCreateTime(now);
            usersWeixinForInsert1.setUnionId(unionid);
            usersWeixinForInsert1.setMetaAppId(appid);

            usersWeixinMapper.insertSelective(usersWeixinForInsert1);


            //数据插入成功，返回结果

            userExsitJudgmentDto.setHaspassword(0);
            userExsitJudgmentDto.setHasphonenumber(0);
            userExsitJudgmentDto.setHasHeadpic(0);
            userExsitJudgmentDto.setSuccess(true);
            userExsitJudgmentDto.setToken(token);
            userExsitJudgmentDto.setYhid(userid);

            result.setMessage("success");
            result.setStatus(200);
            result.setData(userExsitJudgmentDto);

        }
        return false;
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

        if (token == null || "".equals(token)){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("token不能为空");

            return result;
        }
        UserYnDto userYnDto =new UserYnDto();

        UserToken userToken = new UserToken();
        userToken.setToken(token);

        List<UserToken> userTokens = userTokenMapper.select(userToken);

        if (userTokens.size() > 0){
            int userId = userTokens.get(0).getUserId();

            Users usersForTelephone = usersMapper.selectByPrimaryKey(userId);
            
            if(usersForTelephone!=null) {
            	if (usersForTelephone.getPhonenumber() == null){
                    userYnDto.setPhonenumberyn(0);
                }else{
                    userYnDto.setPhonenumberyn(1);
                }

                if (usersForTelephone.getPassword() == null){
                    userYnDto.setPasswordyn(0);
                }else {
                    userYnDto.setPasswordyn(1);
                }

                userYnDto.setSuccess(true);
            }

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
        if (token == null || "".equals(token)){
            return result;
        }
        try {
            UserToken userToken = new UserToken();
            userToken.setToken(token);

            UserToken userTokens = userTokenMapper.selectOne(userToken);
            //log.info("原来是你这里呀啊～～～～～～～～～～～！！！！！！！！{}",userToken);
            result = userTokens.getUserId();

        } catch (Exception e) {
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }
}

