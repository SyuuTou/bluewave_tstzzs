package com.lhjl.tzzs.proxy.controller.weixin;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.utils.JsonUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    private UserExistJudgmentService userExistJudgmentService;

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
            result = userExistJudgmentService.userExistJudgment(openId);
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

//    @GetMapping("logina")
//    public CommonDto<UserExsitJudgmentDto> logina(String code){
//        CommonDto<UserExsitJudgmentDto> result = new CommonDto<UserExsitJudgmentDto>();
//
//        try {
//            result = userExistJudgmentService.userExistJudgment(code);
//        }catch (Exception e){
//            logger.error(e.getMessage(),e.fillInStackTrace());
//            result.setData(null);
//            result.setStatus(501);
//            result.setMessage("请求失败");
//        }
//
//        return result;
//    }
    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("info")
    public String info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }

}