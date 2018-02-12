package com.lhjl.tzzs.proxy.service.bluewave.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import com.lhjl.tzzs.proxy.service.common.SessionKeyService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private WxMaService wxService;

    @Autowired
    private UsersWeixinLtsMapper usersWeixinLtsMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersTokenLtsMapper usersTokenLtsMapper;

    @Resource
    private SessionKeyService sessionKeyService;

    @Resource
    private UserTokenMapper userTokenMapper;
    /**
     * 用户登录注册方法
     * @param openId
     * @param unionid
     * @return
     */
    @Transactional
    @Override
    public CommonDto<UserExsitJudgmentDto> userRegister(String openId, String unionid, Integer appid) {
        CommonDto<UserExsitJudgmentDto> result =new CommonDto<UserExsitJudgmentDto>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();

        if (unionid == null){
            result.setMessage("unionid不能为空，请检查");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (openId == null){
            result.setMessage("openId不能为空，请检查");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (appid == null){
            result.setMessage("appid不能为空，请检查");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        UsersWeixinLts usersWeixinLts = new UsersWeixinLts();
        usersWeixinLts.setOpenid(openId);
        usersWeixinLts.setMetaAppId(appid);

        List<UsersWeixinLts> usersWeixinLtss = usersWeixinLtsMapper.select(usersWeixinLts);

        log.info("进入用户登录注册实现方法中");

        if (usersWeixinLtss.size() > 0){
            //oppenid存在的情况，返回token
            //先查找用户表信息
            if (usersWeixinLtss.get(0).getUserId() == null){
                //当前记录的用户id没有，删掉微信表里的信息重新创建
                usersWeixinLtsMapper.deleteByPrimaryKey(usersWeixinLtss.get(0).getId());

                result = creatUserInfo(openId, unionid, appid);
            }else {
                Users usersInfo = usersMapper.selectByPrimaryKey(usersWeixinLtss.get(0).getUserId());
                if (usersInfo == null){
                    //微信表里存的用户id不存在，删除微信表信息，重新创建用户表信息
                    result = creatUserInfo(openId, unionid, appid);
                }else {
                    //用户信息可以取到
                    //查询token
                    UsersTokenLts usersTokenLtsForSearch = new UsersTokenLts();
                    usersTokenLtsForSearch.setUserId(usersInfo.getId());
                    usersTokenLtsForSearch.setMetaAppId(appid);

                    List<UsersTokenLts> usersTokenLtsList = usersTokenLtsMapper.select(usersTokenLtsForSearch);
                    if (usersTokenLtsList.size() > 0){
                        //当前应用对应用户token已经存在，直接返回token
                        userExsitJudgmentDto.setHaspassword(0);
                        userExsitJudgmentDto.setHasphonenumber(0);
                        userExsitJudgmentDto.setSuccess(true);
                        userExsitJudgmentDto.setToken(usersTokenLtsList.get(0).getToken());
                        userExsitJudgmentDto.setYhid(usersInfo.getId());

                        result.setData(userExsitJudgmentDto);
                        result.setStatus(200);
                        result.setMessage("success");
                    }else {
                        //当前应用对应的token不存在,创建token
                        String token = encode();
                        Date now = new Date();

                        UsersTokenLts usersTokenLtsForInsert = new UsersTokenLts();
                        usersTokenLtsForInsert.setMetaAppId(appid);
                        usersTokenLtsForInsert.setUserId(usersInfo.getId());
                        usersTokenLtsForInsert.setToken(token);
                        usersTokenLtsForInsert.setRegisterTime(now);

                        usersTokenLtsMapper.insertSelective(usersTokenLtsForInsert);

                        userExsitJudgmentDto.setHaspassword(0);
                        userExsitJudgmentDto.setHasphonenumber(0);
                        userExsitJudgmentDto.setSuccess(true);
                        userExsitJudgmentDto.setToken(token);
                        userExsitJudgmentDto.setYhid(usersInfo.getId());

                        result.setData(userExsitJudgmentDto);
                        result.setStatus(200);
                        result.setMessage("success");

                    }
                }
            }
        }else{
            //oppenid不存在的情况，创建用户返回手机号信息
            result = creatUserInfo(openId,unionid,appid);
        }

        return result;

    }

    /**
     * 创建用户的方法
     * @param openId
     * @param unionid
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<UserExsitJudgmentDto> creatUserInfo(String openId, String unionid,Integer appid){
        CommonDto<UserExsitJudgmentDto> result = new CommonDto<>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();

        String token = encode();

        Date now = new Date();
        log.info("当前应用的用户微信号不存在，开始创建用户，及相关表");

        //创建用户之前先判断union是否存在，不存在创建，存在就直接拿就行
        Users usersForSearchUnionid = new Users();
        usersForSearchUnionid.setUnionid(unionid);

        Integer userid = null;
        List<Users> usersSearchResult = usersMapper.select(usersForSearchUnionid);
        if (usersSearchResult.size() > 0){
            userid = usersSearchResult.get(0).getId();
        }else {
            //创建用户
            Users users =new Users();
            users.setCreateTime(now);
            String uuid = token;
            users.setUuid(uuid);
            users.setUnionid(unionid);
            users.setMetaAppId(appid);

            usersMapper.insertSelective(users);

            userid = users.getId();
        }
        //先判断用户当前应用的token是否存在
        UsersTokenLts usersTokenLtsForSearch = new UsersTokenLts();
        usersTokenLtsForSearch.setUserId(userid);
        usersTokenLtsForSearch.setMetaAppId(appid);

        List<UsersTokenLts> usersTokenLtsResult = usersTokenLtsMapper.select(usersTokenLtsForSearch);
        if (usersTokenLtsResult.size() > 0){
            token = usersTokenLtsResult.get(0).getToken();
        }else {
            //创建用户token
            UsersTokenLts usersTokenLts = new UsersTokenLts();
            usersTokenLts.setUserId(userid);
            usersTokenLts.setRegisterTime(now);
            usersTokenLts.setToken(token);
            usersTokenLts.setMetaAppId(appid);

            usersTokenLtsMapper.insertSelective(usersTokenLts);
        }

        //创建用户微信信息表
        UsersWeixinLts usersWeixinLtsForInsert = new UsersWeixinLts();
        usersWeixinLtsForInsert.setMetaAppId(appid);
        usersWeixinLtsForInsert.setOpenid(openId);
        usersWeixinLtsForInsert.setCreateTime(now);
        usersWeixinLtsForInsert.setUserId(userid);

        usersWeixinLtsMapper.insertSelective(usersWeixinLtsForInsert);

        //数据插入成功，返回结果
        userExsitJudgmentDto.setHaspassword(0);
        userExsitJudgmentDto.setHasphonenumber(0);
        userExsitJudgmentDto.setSuccess(true);
        userExsitJudgmentDto.setToken(token);
        userExsitJudgmentDto.setYhid(userid);

        result.setMessage("success");
        result.setStatus(200);
        result.setData(userExsitJudgmentDto);

        return result;
    }

    /**
     * 生成token的方法
     * @return
     */
    private String encode(){

        Date now =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssSSSS");
        String sdfs = sdf.format(now);

        String sjs = String.valueOf(Math.random()*10000);

        String stringForToken = sdfs+sjs;


        String token = MD5Util.md5Encode(stringForToken,"utf-8");

        return token;


    }

    /**
     * 解析用户code信息
     * @param code
     * @param appid
     * @return
     */
    @Override
    public CommonDto<UserExsitJudgmentDto> loginComplex(String code,Integer appid)   {
        CommonDto<UserExsitJudgmentDto> result = new CommonDto<>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();
        if (StringUtils.isBlank(code)) {
            userExsitJudgmentDto.setToken(null);
            userExsitJudgmentDto.setSuccess(false);

            log.info("用户code为空");

            result.setMessage("empty jscode");
            result.setStatus(401);
            result.setData(userExsitJudgmentDto);
            return result;
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            log.info("登录注册场景,SessionKey：{},Openid：{},Expiresin：{},unionid:{}", session.getSessionKey(), session.getOpenid(), session.getExpiresin().toString(), session.getUnionid());

            String openId = session.getOpenid();
            String sessionKey = session.getSessionKey();

            //存sessionkey用的秘钥
            String secretKey = encode();
            //给sessionKey加上前缀
            String cacheKeyId = "sessionkey:" + secretKey;

            boolean jieguo = sessionKeyService.setSessionKey(sessionKey, cacheKeyId);

            userExsitJudgmentDto.setSuccess(true);
            userExsitJudgmentDto.setToken(secretKey);

            result.setData(userExsitJudgmentDto);
            result.setMessage("success");
            result.setStatus(200);

            if (!jieguo) {
                userExsitJudgmentDto.setSuccess(false);
                userExsitJudgmentDto.setToken(null);

                log.info("缓存sessionkey出错");

                result.setData(userExsitJudgmentDto);
                result.setStatus(501);
                result.setMessage("缓存sessionkey出错");
                return result;
            }
        }catch (WxErrorException we){
            we.printStackTrace();
        }catch (Exception e){
            throw e;
        }

        return result;
    }

    /**
     * 解析用户信息的接口
     * @param appid
     * @param body
     * @return
     */
    @Override
    public CommonDto<String> analysisUserInfo(Integer appid, Map<String, Object> body) {
        CommonDto<String> result = new CommonDto<>();

        log.info("解析用户信息场景secretKey:{},signature:{},rawData:{},encryptedData:{},iv:{}",body.get("secretKey"),body.get("signature"),body.get("rawData"),body.get("encryptedData"),body.get("iv"));

        String secretKey = (String) body.get("secretKey");
        String signature = (String) body.get("signature");
        String rawData = (String) body.get("rawData");
        String encryptedData = (String) body.get("encryptedData");
        String iv = (String) body.get("iv");

        //sessionkey加前缀
        String redisKeyId = "sessionkey:" + secretKey;
        log.info(redisKeyId);
        //取到sessionKey
        String sessionKey = sessionKeyService.getSessionKey(redisKeyId);

        log.info("解析用户信息场景获取的sessionKey为：{}",sessionKey);

        if ("".equals(sessionKey) || sessionKey == null){

            log.info("没有找到当前用户的sessionKey信息,无法完成解码");

            result.setData(null);
            result.setStatus(502);
            result.setMessage("没有找到当前用户的sessionKey信息,无法完成解码");

            return result;
        }

        // 用户信息校验
        //rawData,signature有前端传入
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {

            log.info("user check failed");

            result.setMessage("user check failed");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        //开始解密用户信息
        WxMaUserInfo userInfo = null;
        try {
            userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            throw e;
        }
        log.info("解析的用户信息为：{}",userInfo);
        //先注册用户
        CommonDto<UserExsitJudgmentDto> registerResult = userRegister(userInfo.getOpenId(),userInfo.getUnionId(),appid);

        Integer userId = registerResult.getData().getYhid();

        //更新用户信息
        result = updateUserWechatInfo(appid,userId,userInfo);

        result.setData(registerResult.getData().getToken());

        return result;
    }

    /**
     * 根据token换取用户id的方法
     * @param token
     * @return
     */
    @Override
    public Integer getUserIdByToken(String token,Integer appid) {
        Integer result  = -1;
        if (token == null || "".equals(token) || "undefined".equals(token)){
            return result;
        }

//        if (appid != 1){
//            UsersTokenLts usersTokenLts = new UsersTokenLts();
//            usersTokenLts.setToken(token);
//
//            List<UsersTokenLts> usersTokenLtsList = usersTokenLtsMapper.select(usersTokenLts);
//            if (usersTokenLtsList.size() > 0){
//                result = usersTokenLtsList.get(0).getUserId();
//            }
//        }else {
            UserToken userToken = new UserToken();
            userToken.setToken(token);
            userToken.setMetaAppId(String.valueOf(appid));

            List<UserToken> userTokenList = userTokenMapper.select(userToken);
            if (userTokenList.size() > 0){
                result = userTokenList.get(0).getUserId();
            }
//        }

        return result;
    }

    /**
     * 检查用户token是否有效方法
     * @param token
     * @return
     */
    @Override
    public CommonDto<String> checkUserToken(String token,Integer appid) {

        CommonDto<String> result  = new CommonDto<>();
        if ("".equals(token) || token == null){
            result.setStatus(502);
            result.setMessage("token不能为空");
            result.setData(null);
            return result;
        }

        if (appid == null){
            result.setMessage("appid不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }
        Integer userId = -1;
        if (appid != 1){
            UsersTokenLts usersTokenLts = new UsersTokenLts();
            usersTokenLts.setToken(token);
            usersTokenLts.setMetaAppId(appid);

            List<UsersTokenLts> usersTokenLtsList = usersTokenLtsMapper.select(usersTokenLts);
            if (usersTokenLtsList.size() < 1){
                result.setData(null);
                result.setMessage("用户token不存在");
                result.setStatus(502);

                return result;
            }

           userId = getUserIdByToken(token,appid);
        }else {
            UserToken userToken = new UserToken();
            userToken.setToken(token);

            List<UserToken> userTokenList = userTokenMapper.select(userToken);
            if (userTokenList.size() < 1){
                result.setData(null);
                result.setMessage("用户token不存在");
                result.setStatus(502);

                return result;

            }
            userId = getUserIdByToken(token,userId);
        }



        if (userId == -1){
              result.setData(null);
              result.setStatus(502);
              result.setMessage("用户token无效");

              return result;
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(null);
        return result;
    }

    @Override
    public CommonDto<Boolean> checkUserSessionKey(Integer appid, String sessionkey,String token) {
        CommonDto<Boolean> result = new CommonDto<>();
        if (StringUtils.isAnyBlank(sessionkey,token)){
            result.setMessage("用户token，或用户sessionkey不能为空");
            result.setStatus(502);
            result.setData(false);

            return result;
        }

        if (appid == null){
            result.setStatus(502);
            result.setData(false);
            result.setMessage("appid不能为空");

            return result;
        }

        Integer userId = getUserIdByToken(token,appid);
        if (userId == -1){
            result.setMessage("用户token无效，请检查");
            result.setStatus(502);
            result.setData(false);

            return result;
        }

        String redisKeyId = "sessionkey:" + userId;
        String sessionKey = sessionKeyService.getSessionKey(redisKeyId);
        if (sessionKey == null){
            result.setData(false);
            result.setStatus(502);
            result.setMessage("用户sessionkey已经失效");

            return result;
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(true);

        return result;
    }

    /**
     * 保存用户微信信息方法
     * @param appid
     * @param userId
     * @param userInfo
     * @return
     */
    @Transactional
    public CommonDto<String> updateUserWechatInfo(Integer appid,Integer userId,WxMaUserInfo userInfo){
        CommonDto<String> result  = new CommonDto<>();
        Date now = new Date();

        //先查询用户的微信信息是否存在
        UsersWeixinLts usersWeixinLts = new UsersWeixinLts();
        usersWeixinLts.setMetaAppId(appid);
        usersWeixinLts.setUserId(userId);

        List<UsersWeixinLts> usersWeixinLtsList = usersWeixinLtsMapper.select(usersWeixinLts);

        if (usersWeixinLtsList.size() > 0){
            UsersWeixinLts usersWeixinLtsForUpdate = usersWeixinLtsList.get(0);
            usersWeixinLtsForUpdate.setNickName(userInfo.getNickName());
            usersWeixinLtsForUpdate.setUnionId(userInfo.getUnionId());

            usersWeixinLtsMapper.updateByPrimaryKeySelective(usersWeixinLtsForUpdate);
        }else {
            UsersWeixinLts usersWeixinLtsForInsert = new UsersWeixinLts();
            usersWeixinLtsForInsert.setNickName(userInfo.getNickName());
            usersWeixinLtsForInsert.setOpenid(userInfo.getOpenId());
            if (userInfo.getUnionId() == null){
                userInfo.setUnionId("");
            }
            usersWeixinLtsForInsert.setUnionId(userInfo.getUnionId());
            usersWeixinLtsForInsert.setMetaAppId(appid);
            usersWeixinLtsForInsert.setUserId(userId);
            usersWeixinLtsForInsert.setCreateTime(now);

            usersWeixinLtsMapper.insertSelective(usersWeixinLtsForInsert);
        }

        //存储头像
        Users users = new Users();
        users.setId(userId);
        users.setHeadpic(userInfo.getAvatarUrl());
        users.setUnionid(userInfo.getUnionId());

        usersMapper.updateByPrimaryKeySelective(users);

        result.setData(null);
        result.setStatus(200);
        result.setMessage("sucess");

        return result;
    }
}
