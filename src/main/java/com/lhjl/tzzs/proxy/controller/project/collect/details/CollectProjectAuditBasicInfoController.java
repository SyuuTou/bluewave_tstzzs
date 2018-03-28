package com.lhjl.tzzs.proxy.controller.project.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBOutDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditBasicInfoService;
import com.lhjl.tzzs.proxy.service.ProjectSendBService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditBasicInfoController extends GenericController{

	@Resource
    private CollectProjectAuditBasicInfoService collectProjectAuditBasicInfoService;
    @Resource
    private ProjectSendBService projectSendBService;
    
    /**
     * 回显采集项目审核基本信息 
     * @param projectId
     * @return
     */
    @GetMapping("/getcollectprojectauditbasicinfo")
    public CommonDto<CollectProjectAuditBasicInfoDto> getCollectProjectAuditBasicInfo(Integer projectId){
        CommonDto<CollectProjectAuditBasicInfoDto> result = new CommonDto<>();
        try {  
            result = collectProjectAuditBasicInfoService.getCollectProjectAuditBasicInfo(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
    
    /**
     * ZYY
     * 根据token读取项目信息接口
     * @param token
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/read/project/sendinfo")
    public CommonDto<ProjectSendBOutDto> readProjectSendInfo(String token,@PathVariable Integer appid){
        CommonDto<ProjectSendBOutDto> result  = new CommonDto<>();

        try {
            result = projectSendBService.readProjectInfomation(token, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }
        return result;
    }

}
