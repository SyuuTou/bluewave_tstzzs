package com.lhjl.tzzs.proxy.controller.feilu;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.UserFeedbackService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserFeedbackController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserYnController.class);

    @Resource
    UserFeedbackService userFeedbackService;

    @PostMapping("/feedback")
    public CommonDto<String> userFeedback(@RequestBody Map<String,String> body){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = userFeedbackService.useraddFeedback(body);
        }catch (Exception e){
            result.setMessage("服务器发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }
}
