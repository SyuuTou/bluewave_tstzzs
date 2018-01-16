package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestorDemandInputsDto;
import com.lhjl.tzzs.proxy.dto.InvestorsDemandDto;
import com.lhjl.tzzs.proxy.service.InvestorsDemandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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

    /**
     * 判断投资偏好是否填写完成
     * @param body
     * @return
     */
    @PostMapping("/investorsDemandYn")
    public CommonDto<Map<String,Object>> investorsDemandYn(@RequestBody Map<String,String> body){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        String token = body.get("token");

        try {
            result = investorsDemandService.investorsDemandYn(token);

        }catch (Exception e){
            Map<String,Object> obj = new HashMap<>();
            obj.put("message","服务器发生错误");
            obj.put("success",false);

            result.setData(obj);
            result.setMessage("服务器发生错误");
            result.setStatus(502);
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 创建投资风向标/融资需求的接口
     * @param body
     * @param appid
     * @return
     */
    @PostMapping("/v{appid}/create/investors/demand")
    public CommonDto<String> createInvestorsDemand(@RequestBody InvestorDemandInputsDto body, @PathVariable Integer appid){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = investorsDemandService.createInvestorsDemand(body, appid);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
