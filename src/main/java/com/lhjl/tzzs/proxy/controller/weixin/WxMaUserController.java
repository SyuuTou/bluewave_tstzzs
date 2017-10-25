package com.lhjl.tzzs.proxy.controller.weixin;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import com.lhjl.tzzs.proxy.dto.UserGetInfoDto;
import com.lhjl.tzzs.proxy.dto.UserYnDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

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

    @GetMapping("info")
    public CommonDto<UserGetInfoDto> info(String token, String signature, String rawData, String encryptedData, String iv) {
        CommonDto<UserGetInfoDto> result = new CommonDto<>();
        UserGetInfoDto userGetInfoDto = new UserGetInfoDto();

        //先获取到用户的id
        int yhxinxi = userExistJudgmentService.getUserId(token);
        String userid = String.valueOf(yhxinxi);
        if (userid == null){
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

}