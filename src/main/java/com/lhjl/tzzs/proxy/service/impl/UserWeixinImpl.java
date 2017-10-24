package com.lhjl.tzzs.proxy.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserGetInfoDto;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.mapper.UsersWeixinMapper;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.model.UsersWeixin;
import com.lhjl.tzzs.proxy.service.UserWeixinService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserWeixinImpl implements UserWeixinService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserWeixinImpl.class);

    @Resource
    private UsersWeixinMapper usersWeixinMapper;

    @Resource
    private UsersMapper usersMapper;

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
        users.setUuid(userid);
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

}
