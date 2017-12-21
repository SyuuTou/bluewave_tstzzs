package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.UserHeadPicOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.mapper.UsersTokenLtsMapper;
import com.lhjl.tzzs.proxy.mapper.UsersWeixinLtsMapper;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.model.UsersWeixinLts;
import com.lhjl.tzzs.proxy.service.bluewave.BlueUserInfoService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlueUserInfoServiceImpl implements BlueUserInfoService{
    @Resource
    private UserLoginService userLoginService;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersTokenLtsMapper usersTokenLtsMapper;

    @Autowired
    private UsersWeixinLtsMapper usersWeixinLtsMapper;

    @Autowired
    private InvestorsMapper investorsMapper;

    /**
     * 获取个人中心页面的信息接口
     * @param appid
     * @param token
     * @return
     */
    @Override
    public CommonDto<UserHeadPicOutputDto> getUserHeadpic(Integer appid, String token) {
        CommonDto<UserHeadPicOutputDto> result  = new CommonDto<>();
        UserHeadPicOutputDto userHeadPicOutputDto = new UserHeadPicOutputDto();

        if (appid == null){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("appid不能为空");

            return result;
        }

        if ("".equals(token) || token == null){
            result.setMessage("用户token不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        Integer userId = userLoginService.getUserIdByToken(token);
        if (userId == -1){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户token无效，请检查");

            return result;
        }
        Map<String,Object> userHeadpic = getUserHeadpicAndName(appid,token);
        Map<String,Object> userIdentityType = getUserIdentityType(appid,token);

        userHeadPicOutputDto.setUserName(String.valueOf(userHeadpic.get("userName")));
        userHeadPicOutputDto.setHeadpic(String.valueOf(userHeadpic.get("headpic")));
        userHeadPicOutputDto.setCompanyName(String.valueOf(userHeadpic.get("companyName")));
        userHeadPicOutputDto.setCompanyDuties(String.valueOf(userHeadpic.get("companyDuties")));
        userHeadPicOutputDto.setIdentityType(String.valueOf(userIdentityType.get("identityType")));
        userHeadPicOutputDto.setInvestorType(String.valueOf(userIdentityType.get("investorType")));

        result.setMessage("success");
        result.setStatus(200);
        result.setData(userHeadPicOutputDto);

        return result;
    }

    /**
     * 修改用户头像接口
     * @param appid
     * @param body
     * @return
     */
    @Override
    public CommonDto<String> updateUserHeadpic(Integer appid, Map<String, Object> body) {
        CommonDto<String> result  =new CommonDto<>();

        if (appid == null){
            result.setMessage("应用id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.get("token") == null || "".equals("token")){
            result.setMessage("用户token不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }


        if (body.get("headpic") == null || "".equals("headpic")){
            result.setMessage("更新的头像不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        String token = (String) body.get("token");
        String headpic = (String)body.get("headpic");

        Integer userId = userLoginService.getUserIdByToken(token);

        if (userId == -1){
            result.setMessage("用户token非法，请检查");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        Users users = new Users();
        users.setId(userId);
        users.setHeadpicReal(headpic);

        usersMapper.updateByPrimaryKeySelective(users);

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 获取用户头像和姓名的方法
     * @param appid
     * @param token
     * @return
     */
    public Map<String,Object> getUserHeadpicAndName(Integer appid,String token){
        Map<String,Object> obj = new HashMap<>();
        obj.put("headpic","");
        obj.put("userName","");
        obj.put("companyName","");
        obj.put("companyDuties","");

        Integer userId = userLoginService.getUserIdByToken(token);
        Users users = usersMapper.selectByPrimaryKey(userId);

        UsersWeixinLts usersWeixinLtsForSearch = new UsersWeixinLts();
        usersWeixinLtsForSearch.setUserId(userId);
        usersWeixinLtsForSearch.setMetaAppId(appid);
        UsersWeixinLts usersWeixinLts = usersWeixinLtsMapper.selectOne(usersWeixinLtsForSearch);

        //判断应该取用户哪个，名称
        if (users.getActualName() != null && users.getActualName() != ""){
            obj.put("userName",users.getActualName());
        }else {
            if (usersWeixinLts.getNickName() != null && !"".equals(usersWeixinLts.getNickName())){
                obj.put("userName",usersWeixinLts.getNickName());
            }
        }

        if (users.getHeadpicReal() != null && users.getHeadpicReal() != ""){
            obj.put("headpic",users.getHeadpicReal());
        }else {
            if (users.getHeadpic() != null && users.getHeadpic() != ""){
                obj.put("headpic",users.getHeadpic());
            }
        }
        if (users.getCompanyName() != null){
            obj.put("companyName",users.getCompanyName());
        }
        if (users.getCompanyDuties() != null){
            obj.put("companyDuties",users.getCompanyDuties());
        }

        return obj;
    }

    /**
     * 获取用户身份类型，投资人类型方法
     * @param appid
     * @param token
     * @return
     */
    public Map<String,Object> getUserIdentityType(Integer appid,String token){
        Map<String,Object> map = new HashMap<>();
        map.put("investorType","");
        map.put("identityType","");

        Integer userId = userLoginService.getUserIdByToken(token);

        Investors investors = new Investors();
        investors.setUserId(userId);
        investors.setApprovalStatus(1);
        investors.setYn(1);

        Investors investorsForInfo = investorsMapper.selectOne(investors);
        if (investorsForInfo != null){
            if (investorsForInfo.getInvestorsType() != null){
            switch (investorsForInfo.getInvestorsType()){
                case 0:map.put("investorType","个人投资人");
                    break;
                case 1:map.put("investorType","机构投资人");
                    break;
                case 2:map.put("investorType","VIP投资人");
                    break;
            }
            }
        }

        Users users = usersMapper.selectByPrimaryKey(userId);
        if (users != null){
            if (users.getIdentityType() != null) {
                switch (users.getIdentityType()) {
                    case 0:
                        map.put("identityType", "投资人");
                        break;
                    case 1:
                        map.put("identityType", "创业者");
                        break;
                    case 2:
                        map.put("identityType", "产业公司");
                        break;
                    case 3:
                        map.put("identityType", "媒体");
                        break;
                    case 4:
                        map.put("identityType", "政府机构");
                        break;
                    case 5:
                        map.put("identityType", "服务机构");
                        break;
                }
            }
        }

        return map;
    }
}
