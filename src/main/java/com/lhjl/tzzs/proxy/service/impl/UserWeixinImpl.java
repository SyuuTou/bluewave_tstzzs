package com.lhjl.tzzs.proxy.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserGetInfoDto;
import com.lhjl.tzzs.proxy.mapper.MetaFamilyNameMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.mapper.UsersWeixinMapper;
import com.lhjl.tzzs.proxy.model.MetaFamilyName;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.model.UsersWeixin;
import com.lhjl.tzzs.proxy.service.UserWeixinService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

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
    private MetaFamilyNameMapper familyNameMapper;
    @Transactional
    @Override
    public CommonDto<UserGetInfoDto> setUsersWeixin(WxMaUserInfo userInfo,String userid){
        CommonDto<UserGetInfoDto> result  = new CommonDto<>();
        UserGetInfoDto userGetInfoDto = new UserGetInfoDto();


        Date now = new Date();
        //userId转数字
        int userID = Integer.parseInt(userid);

        //创建用户微信表的实体类
        UsersWeixin usersWeixin = new UsersWeixin();
        usersWeixin.setCreateTime(now);
        usersWeixin.setOpenid(userInfo.getOpenId());
        usersWeixin.setUserId(userID);
        usersWeixin.setNickName(userInfo.getNickName());
        usersWeixin.setUnionId(userInfo.getUnionId());

        //创建用户表的实体类
        Users users =new Users();
        users.setId(userID);
        users.setHeadpic(userInfo.getAvatarUrl());

        //先判断用户的微信信息是否存在，存在即更新，不存在即新建
        UsersWeixin usersWeixinForSearch = new UsersWeixin();
        usersWeixinForSearch.setUserId(userID);

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

        //更新用户表的内容（头像）
        usersMapper.updateByPrimaryKeySelective(users);

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
            if (metaFamilyName==null){
                familyName.setFamily(nickName.substring(startIndex,startIndex+2));
                metaFamilyName = familyNameMapper.selectOne(familyName);
            }
            if (null == metaFamilyName){
                result.setStatus(200);
                result.setMessage("非真实姓名");
                result.setData("");
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
            log.error(e.getMessage(),e.fillInStackTrace());
        } catch (Exception ex){
            log.error(ex.getLocalizedMessage(), ex.fillInStackTrace());
            throw ex;
        }

        result.setStatus(200);
        result.setMessage("非真实姓名");
        result.setData("");
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
