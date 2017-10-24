package com.lhjl.tzzs.proxy.controller.feilu;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserYnDto;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserYnController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserYnController.class);

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @GetMapping("useryn")
    public CommonDto<UserYnDto> userYn(String token){
        CommonDto<UserYnDto> result = new CommonDto<>();

        try {
            result = userExistJudgmentService.userYn(token);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(501);
            result.setMessage("服务器发生错误");
        }

        return result;
    }

}
