package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorsDemandDto;
import com.lhjl.tzzs.proxy.service.InvestorsDemandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 投资人偏好
 * Created by 蓝海巨浪 on 2017/10/24.
 */
@RestController
public class InvestorsDemandController {

    private static Logger logger = LoggerFactory.getLogger(InvestorsDemandController.class);

    @Resource
    private InvestorsDemandService investorsDemandService;

    /**
     * 投资偏好数据回显
     * @param token
     * @return
     */
    @GetMapping("/rest/hangye/newttouzilyrz")
    public CommonDto<Map<String, Object>> newttouzilyrz(String token){
        CommonDto<Map<String, Object>>  result = new CommonDto<>();
        try{
            result = investorsDemandService.newttouzilyrz(token);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("投资偏好回显数据回去异常");
            logger.error(e.getMessage(), e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 投资偏好记录
     * @param body 请求对象
     * @return
     */
    @PostMapping("/rest/user/newulingyu")
    public CommonDto<String> newulingyu(@RequestBody InvestorsDemandDto body){
        CommonDto<String> result = new CommonDto<>();
        try{
            result = investorsDemandService.newulingyu(body);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("投资偏好回显数据回去异常");
            logger.error(e.getMessage(), e.fillInStackTrace());
        }
        return result;
    }
}
