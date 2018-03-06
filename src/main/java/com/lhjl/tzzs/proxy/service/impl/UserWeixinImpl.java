package com.lhjl.tzzs.proxy.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserGetInfoDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.UserWeixinService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserWeixinImpl implements UserWeixinService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserWeixinImpl.class);

    @Resource
    private UsersWeixinMapper usersWeixinMapper;

    @Resource
    private UsersMapper usersMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private MiniappFormidMapper miniappFormidMapper;

    @Autowired
    private MetaFamilyNameMapper familyNameMapper;
    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Transactional
    @Override
    public CommonDto<UserGetInfoDto> setUsersWeixin(WxMaUserInfo userInfo,String userid){
        CommonDto<UserGetInfoDto> result  = new CommonDto<>();
        UserGetInfoDto userGetInfoDto = new UserGetInfoDto();


        Date now = new Date();
        //userId转数字
        int userID = Integer.parseInt(userid);
        UsersWeixin usersWeixin = null;
        if (StringUtils.isNotEmpty(userInfo.getUnionId())) {
            usersWeixin = new UsersWeixin();
            usersWeixin.setUnionId(userInfo.getUnionId());
            List<UsersWeixin> usersWeixins = usersWeixinMapper.select(usersWeixin);
            if (null != usersWeixins&& usersWeixins.size()>0){
                userID = usersWeixins.get(0).getUserId();

                UserToken userToken = new UserToken();
                userToken.setUserId(Integer.parseInt(userid));

                userToken = userTokenMapper.selectOne(userToken);
                userToken.setUserId(userID);
                userTokenMapper.updateByPrimaryKey(userToken);

            }else {
                //创建用户表的实体类
                Users users =new Users();
                users.setId(userID);
                users.setHeadpic(userInfo.getAvatarUrl());

                //更新用户表的内容（头像）
                usersMapper.updateByPrimaryKeySelective(users);
            }
        }

        //创建用户微信表的实体类
        usersWeixin = new UsersWeixin();
        usersWeixin.setCreateTime(now);
        usersWeixin.setOpenid(userInfo.getOpenId());
        usersWeixin.setUserId(userID);
        usersWeixin.setNickName(userInfo.getNickName());
        usersWeixin.setUnionId(userInfo.getUnionId());



        //先判断用户的微信信息是否存在，存在即更新，不存在即新建
        UsersWeixin usersWeixinForSearch = new UsersWeixin();
        usersWeixinForSearch.setUserId(userID);
        usersWeixinForSearch.setOpenid(userInfo.getOpenId());

        List<UsersWeixin> usersWeixinSearchList =usersWeixinMapper.select(usersWeixinForSearch);

        if (usersWeixinSearchList.size() > 0){
            UsersWeixin usersWeixinForId = usersWeixinSearchList.get(0);
            int userWeixinId = usersWeixinForId.getId();
            //补充上用户微信表的id
            usersWeixin.setId(userWeixinId);

            //开始更新用户微信表信息
            usersWeixinMapper.updateByPrimaryKeySelective(usersWeixin);

        }else{
            //没有用户的微信表信息那么就新建

            usersWeixinMapper.insertSelective(usersWeixin);
        }



        //查找一下用户的手机号，密码是否存在
        Users usersForTelephone = new Users();
        usersForTelephone.setId(userID);

        Users usersSearchResult = usersMapper.selectByPrimaryKey(usersForTelephone.getId());
        String phonenumber = usersSearchResult.getPhonenumber();
        String password = usersSearchResult.getPassword();

        if (phonenumber == null){
            userGetInfoDto.setHasphonenumber(false);
        }else{
            userGetInfoDto.setHasphonenumber(true);
        }

        if (password == null){
            userGetInfoDto.setHaspassword(false);
        }else{
            userGetInfoDto.setHaspassword(true);
        }

        userGetInfoDto.setTips(null);
        userGetInfoDto.setSuccess(true);

        result.setMessage("success");
        result.setStatus(200);
        result.setData(userGetInfoDto);


        return result;
    }

    @Override
    public CommonDto<String> checkName(String token) {
        CommonDto<String> result = new CommonDto<>();
        try {
            UserToken query = new UserToken();
            query.setToken(token);
            UserToken userToken = userTokenMapper.selectOne(query);
            UsersWeixin queryWx = new UsersWeixin();
            queryWx.setUserId(userToken.getUserId());
            UsersWeixin usersWeixin = usersWeixinMapper.selectOne(queryWx);
            String nickName = usersWeixin.getNickName();
            Integer startIndex = 0;
            for (int i = 0; i< nickName.length();i++){
                if (nickName.substring(i,i+1).getBytes("UTF-8").length == 3){
                    startIndex = i;
                    break;
                }
            }

            MetaFamilyName familyName = new MetaFamilyName();
            familyName.setFamily(nickName.substring(startIndex,startIndex+1));
            MetaFamilyName metaFamilyName = familyNameMapper.selectOne(familyName);
            if (metaFamilyName==null&&nickName.length()>=startIndex+2){
                familyName.setFamily(nickName.substring(startIndex,startIndex+2));
                metaFamilyName = familyNameMapper.selectOne(familyName);
            }
            if (null == metaFamilyName){
                result.setStatus(200);
                result.setMessage("非真实姓名");
                result.setData("");
                return result;
            }
            if (nickName.length()<=3&&nickName.length()>1){
                result.setStatus(200);
                result.setMessage("类真实姓名");
                result.setData(nickName);
                return result;
            }
            String name = null;
            if (startIndex+3<=nickName.length()) {
                name = nickName.substring(startIndex, startIndex + 3);
            }

            if (startIndex+4<=nickName.length()&&!nickName.substring(startIndex+3,startIndex+4).equals(" ")){
                if (!nickName.substring(startIndex+2,startIndex+3).equals(" ")){
                    result.setStatus(200);
                    result.setMessage("非真实姓名");
                    result.setData("");
                    return result;
                }
            }
            if (name.trim().length()<=3){
                result.setStatus(200);
                result.setMessage("类真实姓名");
                result.setData(name);
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage(),e.fillInStackTrace());
        } catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getLocalizedMessage(), ex.fillInStackTrace());
            throw ex;
        }

        result.setStatus(200);
        result.setMessage("非真实姓名");
        result.setData("");
        return result;

    }

    @Transactional
    @Override
    public CommonDto<String> saveFormId(Map<String, String> body) {

        CommonDto<String> result = new CommonDto<>();
        //获取用户id
        Integer userId = userExistJudgmentService.getUserId(body.get("token"));
        if (userId == -1){
            result.setData(null);
            result.setStatus(50001);
            result.setMessage("用户token非法");

            return result;
        }

        MiniappFormid miniappFormid = new MiniappFormid();
        miniappFormid.setCreateTime(DateTime.now().toDate());
        miniappFormid.setFormId(body.get("formId"));
        miniappFormid.setSceneKey(body.get("sceneKey"));
        miniappFormid.setToken(body.get("token"));
        miniappFormid.setUserId(userId);

        miniappFormidMapper.insert(miniappFormid);

        result.setStatus(200);
        result.setMessage("success");
        result.setData("ok");
        return result;
    }


    public static void main(String[] args) {
        String s = "a您好b好好好";
        System.out.println(s.length());
        System.out.println(s.substring(0,1));
        try {
            System.out.println(s.substring(1,2).getBytes("utf-8").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
