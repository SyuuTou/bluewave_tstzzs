package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.ProjectAmountService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ProjectAmountController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAmountController.class);

    @Resource
    private ProjectAmountService projectAmountService;

    @RequestMapping(value = "admin/project/amount", method = RequestMethod.GET)
    @ResponseBody
    public CommonDto<String> setProjectAmount(){
        CommonDto<String> result = null;

        try {
            projectAmountService.projectAmoutAdd();
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            result.setData(null);
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    @RequestMapping(value = "admin/project/amount/allcount", method = RequestMethod.GET)
    @ResponseBody
    public CommonDto<String> setProjectAllAmount(){
        CommonDto<String> result =null;

        try{
            projectAmountService.projetcAllAmountAdd();
        }catch (Exception e){

            result.setMessage(e.getMessage());
            result.setStatus(501);
            result.setData(null);
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;

    }

}
