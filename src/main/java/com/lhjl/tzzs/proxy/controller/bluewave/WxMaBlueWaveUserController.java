package com.lhjl.tzzs.proxy.controller.bluewave;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import com.lhjl.tzzs.proxy.dto.UserGetInfoDto;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import com.lhjl.tzzs.proxy.service.UserWeixinService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import com.lhjl.tzzs.proxy.service.common.SessionKeyService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class WxMaBlueWaveUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxService;

    @Resource
    private SessionKeyService sessionKeyService;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Resource
    private UserWeixinService userWeixinService;

    @Resource
    private UserLoginService userLoginService;

    /**
     * 登陆接口
     */
    @GetMapping("/v{appid}/login")
    public CommonDto<UserExsitJudgmentDto> login(String code,@PathVariable("appid") Integer appid) {
        CommonDto<UserExsitJudgmentDto> result = new CommonDto<>();
        UserExsitJudgmentDto userExsitJudgmentDto =new UserExsitJudgmentDto();

        try {
            result = userLoginService.loginComplex(code, appid);
        }catch (WxErrorException we){

        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            userExsitJudgmentDto.setToken(null);
            userExsitJudgmentDto.setSuccess(false);

            result.setMessage("服务器发生错误！");
            result.setStatus(501);
            result.setData(userExsitJudgmentDto);

            return result;
        }

        return result;
    }

    /**
     * 获取微信授权的信息接口
     * @param appid
     * @param body
     * @return
     */
   @PostMapping("/v{appid}/info")
    public CommonDto<String> userWechatInfo(@PathVariable Integer appid,@RequestBody Map<String,Object> body){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = userLoginService.analysisUserInfo(appid,body);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
   }

    /**
     * 检查用户token是否有效
     * @param appid
     * @param token
     * @return
     */
   @GetMapping("/v{appid}/check/token")
    public CommonDto<String> checkToken(@PathVariable Integer appid, String token){
        CommonDto<String> result  = new CommonDto<>();
        try {
        result = userLoginService.checkUserToken(token,appid);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
   }

    /**
     * 检查用户sessionkey是否有效
     * @param appid
     * @param sessionkey
     * @return
     */
   @GetMapping("/v{appid}/check/sessionkey")
    public CommonDto<Boolean> checkSessionKey(@PathVariable Integer appid,String sessionkey,String token){
        CommonDto<Boolean> result = new CommonDto<>();

        try {
            result = userLoginService.checkUserSessionKey(appid, sessionkey, token);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
   }
}
