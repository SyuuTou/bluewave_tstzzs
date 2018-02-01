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
        logger.info("请求进来了login接口");
        if (StringUtils.isBlank(code)) {
            userExsitJudgmentDto.setToken(null);
            userExsitJudgmentDto.setSuccess(false);

            logger.info("用户code为空");
            logger.info("empty jscode");

            result.setMessage("empty jscode");
            result.setStatus(401);
            result.setData(userExsitJudgmentDto);
            return result;
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            logger.info("登录注册场景");
            logger.info("SessionKey：");
            this.logger.info(session.getSessionKey());
            logger.info("Openid：");
            this.logger.info(session.getOpenid());
            logger.info("Expiresin：");
            this.logger.info(session.getExpiresin().toString());

            //TODO 可以增加自己的逻辑，关联业务相关数据
            String openId = session.getOpenid();
            String sessionKey = session.getSessionKey();
            result = userExistJudgmentService.userExistJudgment(openId);
            String userid =String.valueOf(result.getData().getYhid());
            //给sessionKey加上前缀
            String cacheKeyId = "sessionkey:" + userid;

            boolean jieguo = sessionKeyService.setSessionKey(sessionKey,cacheKeyId);

            if (!jieguo){
                userExsitJudgmentDto.setSuccess(false);
                userExsitJudgmentDto.setToken(null);

                logger.info("缓存sessionkey出错");

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

        logger.info("解析用户信息场景");
          logger.info("token:");
        logger.info(body.get("token"));
          logger.info("signature:");
        logger.info(body.get("signature"));
          logger.info("rawData:");
        logger.info(body.get("rawData"));
          logger.info("encryptedData:");
        logger.info(body.get("encryptedData"));
          logger.info("iv:");
        logger.info(body.get("iv"));

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

            logger.info("解析用户信息场景");
            logger.info("userid：");
            logger.info(userid);
            logger.info(body.get("token非法，请检查token"));

            result.setMessage("token非法，请检查token");
            result.setStatus(501);
            result.setData(null);

            return result;
        }
        //sessionkey加前缀
        String redisKeyId = "sessionkey:" + userid;
        logger.info(redisKeyId);
        //取到sessionKey
        String sessionKey = sessionKeyService.getSessionKey(redisKeyId);

        logger.info("解析用户信息场景获取的sessionKey为：");
        logger.info(sessionKey);

        if (sessionKey == "" || sessionKey == null){
            userGetInfoDto.setSuccess(false);
            userGetInfoDto.setTips("没有找到当前用户的sessionKey信息,无法完成解码");

            logger.info("解析用户信息场景");
            logger.info(body.get("没有找到当前用户的sessionKey信息,无法完成解码"));

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

            logger.info("解析用户信息场景");
            logger.info(body.get("user check failed"));

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
     * @param body
     * @return
     */
    @PostMapping("phonenumber")
    public CommonDto<PhonenumberInfo> phonenumber(@RequestBody Map<String,String> body) {
        CommonDto<PhonenumberInfo> result = new CommonDto<>();
        //先获取到用户的id
        try {
            int yhxinxi = userExistJudgmentService.getUserId(body.get("token"));
            if (yhxinxi == -1){
                result.setData(null);
                result.setMessage("用户token非法，请检查");
                result.setStatus(502);

                logger.info("获取手机号场景");
                logger.info("token为：");
                logger.info(body.get("token"));

                return result;
            }

            String userid = String.valueOf(yhxinxi);
            String redisKeyId = "sessionkey:" + userid;
            String sessionKey = sessionKeyService.getSessionKey(redisKeyId);
            if (sessionKey == null){
                result.setStatus(502);
                result.setMessage("没有获取到用户的sessionkey信息");
                result.setData(null);

                logger.info("获取手机号场景");
                logger.info("redisKeyId为：");
                logger.info(redisKeyId);


                return result;
            }

            logger.info("获取手机号场景");
            logger.info("sessionKey为：");
            logger.info(sessionKey);
            logger.info("encryptedData为：");
            logger.info(body.get("encryptedData"));
            logger.info("iv为：");
            logger.info(body.get("iv"));

            String info = WxMaCryptUtils.decrypt(sessionKey, body.get("encryptedData"), body.get("iv"));
            PhonenumberInfo phonenumberInfo = WxMaGsonBuilder.create().fromJson(info, PhonenumberInfo.class);

            logger.info("解析完毕以后phonenumber为：{}",phonenumberInfo.getPhoneNumber());
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
        logger.info("请求进来了wxlogin接口");
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
            String redisKeyId = "sessionkey:" + userid;
            boolean jieguo = sessionKeyService.setSessionKey(sessionKey,redisKeyId);

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
    public String textWx(String formId){

        List<WxMaTemplateMessage.Data> datas = new ArrayList<>();
        datas.add(new WxMaTemplateMessage.Data("keyword1.DATA","用户"));
        datas.add(new WxMaTemplateMessage.Data("keyword2.DATA","说明"));
        datas.add(new WxMaTemplateMessage.Data("keyword3.DATA","认证时间"));
        try {
            this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder().templateId("IQL59_p78hezrN9Oz6UAStwSyFk8ZbLgVPaPqEi1KyA").formId(formId).toUser("oA0AB0eQ76iCm2ADN9i9kiL2XUgE").data(datas).build());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "Ok";
    }

    @PostMapping("check/{token}")
    public CommonDto<String> checkName(@PathVariable String token){
        CommonDto<String> result = null;

        try {
            result = userWeixinService.checkName(token);
        } catch (Exception e) {
            logger.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setData("");
            result.setMessage("error");
            result.setStatus(500);
        }

        return result;
    }

    @PostMapping("formId")
    public CommonDto<String> saveFormId(@RequestBody Map<String,String> body){
        CommonDto<String> result = null;

        result = userWeixinService.saveFormId(body);

        return result;
    }

    /**
     * 检查sessionkey是否存在接口
     * @param body
     * @return
     */
    @PostMapping("check/sessionkey")
    public CommonDto<Boolean> checkSessionkey(@RequestBody Map<String,String> body){
        CommonDto<Boolean> result = new CommonDto<>();

        if (body.get("token") ==null || "".equals(body.get("token")) || "undedined".equals(body.get("token"))){
            result.setData(false);
            result.setStatus(502);
            result.setMessage("用户token不能为空");

            return result;
        }

        try {

            //用token换取用户id
            Integer userid = userExistJudgmentService.getUserId(body.get("token"));
            if (userid == -1){

                logger.info("验证sessionkey场景");
                logger.info("用户token无效，请检查");

                result.setMessage("用户token无效，请检查");
                result.setStatus(502);
                result.setData(false);

                return result;
            }

            String redisKeyId = "sessionkey:" + userid;
            logger.info(redisKeyId);
            //取到sessionKey
            String sessionKey = sessionKeyService.getSessionKey(redisKeyId);

            if (sessionKey == null){

                logger.info("验证sessionkey场景");
                logger.info("没有获取到用户的session");

                result.setData(false);
                result.setStatus(502);
                result.setMessage("没有获取到用户的session");

                return result;
            }

            result.setMessage("success");
            result.setStatus(200);
            result.setData(true);


        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("不存在sessionkey");
            result.setStatus(502);
            result.setData(false);
        }

        return result;
    }
}