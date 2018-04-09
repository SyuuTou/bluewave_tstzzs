package com.lhjl.tzzs.proxy.controller.project.platform.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectsSendDto;
import com.lhjl.tzzs.proxy.service.ProjectInformationDetailsService;
import com.lhjl.tzzs.proxy.service.ProjectsSendService;
import com.lhjl.tzzs.proxy.service.common.CommonUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zyy on 2017/11/27.
 */
@RestController
public class ProjectInformationDetailsController extends GenericController{

    @Resource
    private ProjectInformationDetailsService projectInformationDetailsService;

    @Resource
    private CommonUserService commonUserService;

    /**
     * ZYY
     * 项目详情的项目修改
     */
    @PostMapping("project/information/details")
    public CommonDto<String> informationDetails(@RequestBody ProjectsSendDto params){
        CommonDto<String> result = new CommonDto<>();

        try{
            //获取userId
            int userId = commonUserService.getLocalUserId(params.getToken());

            result = projectInformationDetailsService.informationDetails(params, userId);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }

        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("项目修改异常");
            
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

}
