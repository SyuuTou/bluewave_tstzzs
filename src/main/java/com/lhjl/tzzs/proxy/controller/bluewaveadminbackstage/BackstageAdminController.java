package com.lhjl.tzzs.proxy.controller.bluewaveadminbackstage;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lanhaijulang on 2018/2/4.
 */
@RestController
public class BackstageAdminController extends GenericController {

    @Autowired
    private LoginService loginService;

    /**
     * 天使指数后台运营登录
     * @param body
     * @param appid 天使指数小程序以及运营后台的appid为1
     * @return
     */
    @PostMapping("/v{appid}/loginbackstage")
    public CommonDto<AdminUser> login(@RequestBody LoginReqBody body,@PathVariable("appid") Integer appid) {
        CommonDto<AdminUser> result = new CommonDto<>();
        try {
            result = loginService.login(body,appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}