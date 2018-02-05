package com.lhjl.tzzs.proxy.controller.bluewaveadminbackstage;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //后台登录
    @PostMapping("/loginbackstage")
    public CommonDto<Boolean> login(@RequestBody LoginReqBody body) {
        CommonDto<Boolean> result = new CommonDto<>();
        try {
            result = loginService.login(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(false);
            result.setStatus(502);
        }
        return result;
    }

}