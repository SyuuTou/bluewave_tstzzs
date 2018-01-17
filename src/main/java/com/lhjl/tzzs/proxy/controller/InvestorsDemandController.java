package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.*;
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

    /**
     * 获取投资风向标/融资需求的接口
     * @param body
     * @param appid
     * @return
     */
    @PostMapping("/v{appid}/get/investors/demandlist")
    public CommonDto<Map<String,Object>> getInvestorsDemandList(@RequestBody InvestorDemandListInputDto body,@PathVariable Integer appid){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        try {
            result = investorsDemandService.getInvestorDemand(body, appid);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }

    /**
     * 获取用户投资需求是否填写完毕的接口（新）
     * @param token
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/get/completeyn")
    public CommonDto<Map<String,Object>> getDemandCompleteYn(String token,@PathVariable Integer appid){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        try {
            result = investorsDemandService.getDemandCompeteYn(token, appid);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }

    /**
     * 融资需求信息回显接口/投资风向标
     * @param token
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/get/investors/demand")
    public CommonDto<InvestorDemandListOutputDto> getInvestorsDemandById(String token,@PathVariable Integer appid){
        CommonDto<InvestorDemandListOutputDto> result  = new CommonDto<>();

        try {
            result = investorsDemandService.getInvestorsDemand(token,appid);
        }catch (Exception e){
            logger.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }
}
