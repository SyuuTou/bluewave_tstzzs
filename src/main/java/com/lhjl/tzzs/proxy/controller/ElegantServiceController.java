package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.ElegantServiceService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ElegantServiceController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ElegantServiceController.class);

    @Resource
    private ElegantServiceService elegantServiceService;

    /**
     * 获取精选活动列表的接口
     * @return
     */
    @GetMapping("elegantservice/list")
    public CommonDto<List<Map<String,Object>>> getElegantServiceList(){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();

        try {
            result = elegantServiceService.findElegantServiceList();
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误，请检查");
        }

        return result;
    }
}
