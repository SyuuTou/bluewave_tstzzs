package com.lhjl.tzzs.proxy.controller.weixin;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.UserWeixinService;
import com.lhjl.tzzs.proxy.service.common.SessionKeyService;
import com.lhjl.tzzs.proxy.service.common.SmsCommonService;
import com.lhjl.tzzs.proxy.utils.JsonUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wechat/user")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxService;

    @Resource
    private SessionKeyService sessionKeyService;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Resource
    private UserWeixinService userWeixinService;

    /**
     * 登陆接口
     */

    @GetMapping("login")
    public CommonDto<UserExsitJudgmentDto> login(String code) {
        CommonDto<UserExsitJudgmentDto> result = new CommonDto<>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();
        logger.info("请求进来了");
        if (StringUtils.isBlank(code)) {
            userExsitJudgmentDto.setToken(null);
            userExsitJudgmentDto.setSuccess(false);


            result.setMessage("empty jscode");
            result.setStatus(401);
            result.setData(userExsitJudgmentDto);
            return result;
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            this.logger.info(session.getExpiresin().toString());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            String openId = session.getOpenid();
            String sessionKey = session.getSessionKey();
            result = userExistJudgmentService.userExistJudgment(openId);
            String userid =String.valueOf(result.getData().getYhid());

            boolean jieguo = sessionKeyService.setSessionKey(sessionKey,userid);

            if (!jieguo){
                userExsitJudgmentDto.setSuccess(false);
                userExsitJudgmentDto.setToken(null);

                result.setData(userExsitJudgmentDto);
                result.setStatus(501);
                result.setMessage("缓存sessionkey出错");
                return result;
            }


            return result;
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            userExsitJudgmentDto.setToken(null);
            userExsitJudgmentDto.setSuccess(false);

            result.setMessage("服务器发生错误！");
            result.setStatus(501);
            result.setData(userExsitJudgmentDto);

            return result;
        }
    }


    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */

//    @GetMapping("info")
//    public CommonDto<UserGetInfoDto> info(String token, String signature, String rawData, String encryptedData, String iv) {
      @PostMapping("info")
      public CommonDto<UserGetInfoDto> info(@RequestBody Map<String,String> body) {
        CommonDto<UserGetInfoDto> result = new CommonDto<>();
        UserGetInfoDto userGetInfoDto = new UserGetInfoDto();

        String token = body.get("token");
        String signature = body.get("signature");
        String rawData = body.get("rawData");
        String encryptedData = body.get("encryptedData");
        String iv = body.get("iv");

        //先获取到用户的id
        Integer yhxinxi = userExistJudgmentService.getUserId(token);
        String userid = String.valueOf(yhxinxi);
        if (userid == null || "".equals(userid)){
            userGetInfoDto.setSuccess(false);
            userGetInfoDto.setTips("token非法，请检查token");

            result.setMessage("token非法，请检查token");
            result.setStatus(501);
            result.setData(null);

            return result;
        }

        //取到sessionKey
        String sessionKey = sessionKeyService.getSessionKey(userid);
        if (sessionKey == "" || sessionKey == null){
            userGetInfoDto.setSuccess(false);
            userGetInfoDto.setTips("没有找到当前用户的sessionKey信息,无法完成解码");

            result.setData(null);
            result.setStatus(501);
            result.setMessage("没有找到当前用户的sessionKey信息,无法完成解码");

            return result;
        }

        // 用户信息校验
        //rawData,signature有前端传入
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            userGetInfoDto.setSuccess(false);
            userGetInfoDto.setTips("user check failed");

            result.setMessage("user check failed");
            result.setStatus(501);
            result.setData(null);

            return result;
        }


        try {
            // 解密用户信息
            WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);


            result = userWeixinService.setUsersWeixin(userInfo,userid);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            userGetInfoDto.setTips("服务器发生错误");
            userGetInfoDto.setSuccess(false);

            result.setData(userGetInfoDto);
            result.setStatus(501);
            result.setMessage("服务器发生错误");
        }


        return result;
    }

    /**
     *
     * @param token
     * @param encryptedData
     * @param iv
     * @return
     */
    @PostMapping("phonenumber")
    public CommonDto<PhonenumberInfo> phonenumber(@RequestBody Map<String,String> body) {
        CommonDto<PhonenumberInfo> result = new CommonDto<>();
        //先获取到用户的id
        try {
            int yhxinxi = userExistJudgmentService.getUserId(body.get("token"));
            String userid = String.valueOf(yhxinxi);
            String sessionKey = sessionKeyService.getSessionKey(userid);
            String info = WxMaCryptUtils.decrypt(sessionKey, body.get("encryptedData"), body.get("iv"));
            PhonenumberInfo phonenumberInfo = WxMaGsonBuilder.create().fromJson(info, PhonenumberInfo.class);
            result.setData(phonenumberInfo);
            result.setMessage("success");
            result.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("failed");
        }
        return result;
    }


    @PostMapping("wx/login")
    public CommonDto<UserExsitJudgmentDto> getWxPhonenumber(@RequestBody Map<String,String> body){
        String code = body.get("code");
        String token =body.get("token");

        CommonDto<UserExsitJudgmentDto> result = new CommonDto<>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();
        logger.info("请求进来了");
        if (StringUtils.isBlank(code)) {
            userExsitJudgmentDto.setToken(null);
            userExsitJudgmentDto.setSuccess(false);

            result.setMessage("empty jscode");
            result.setStatus(401);
            result.setData(userExsitJudgmentDto);
            return result;
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            this.logger.info(session.getExpiresin().toString());

            int yhxinxi = userExistJudgmentService.getUserId(token);
            String userid = String.valueOf(yhxinxi);

            String sessionKey = session.getSessionKey();

            boolean jieguo = sessionKeyService.setSessionKey(sessionKey,userid);

            if (!jieguo){
                userExsitJudgmentDto.setSuccess(false);
                userExsitJudgmentDto.setToken(null);

                result.setData(userExsitJudgmentDto);
                result.setStatus(501);
                result.setMessage("缓存sessionkey出错");
                return result;
            }

            result.setData(null);
            result.setMessage("success");
            result.setStatus(200);


        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            userExsitJudgmentDto.setToken(null);
            userExsitJudgmentDto.setSuccess(false);

            result.setMessage("服务器发生错误！");
            result.setStatus(501);
            result.setData(userExsitJudgmentDto);

        }

        return result;
    }


    @GetMapping("wx/test")
    public String textWx(){

        List<WxMaTemplateMessage.Data> datas = new ArrayList<>();
        datas.add(new WxMaTemplateMessage.Data("keyword1.DATA","用户"));
        datas.add(new WxMaTemplateMessage.Data("keyword2.DATA","说明"));
        datas.add(new WxMaTemplateMessage.Data("keyword3.DATA","认证时间"));
        try {
            this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.newBuilder().templateId("db8W77rbYLinzuEXFWFtiv2WFRke9LS_nqGblGDh3A8").formId("dsdsd").toUser("oA0AB0ajgNg7_Z2C100wZ1JbL760").data(datas).build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "Ok";
    }

}