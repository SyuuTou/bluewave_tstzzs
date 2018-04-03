package com.lhjl.tzzs.proxy.controller.project.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBOutDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditBasicInfoService;
import com.lhjl.tzzs.proxy.service.ProjectSendBService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 根据token和项目ID读取项目信息接口
     * @param token
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/read/project/sendinfo/{projectId}")
    public CommonDto<ProjectSendBOutDto> readProjectSendInfoById(String token, @PathVariable Integer projectId ,@PathVariable Integer appid){
        CommonDto<ProjectSendBOutDto> result  = new CommonDto<>();

        try {
            result = projectSendBService.readProjectInfomation(token, projectId, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
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

    /**
     * 根据token读取项目信息接口
     * @param token
     * @param appId
     * @return
     */
    @GetMapping("v{appId}/read/project/sendinfo/list")
    public CommonDto<List<ProjectSendBOutDto>> readProjectSendInfoList(String token, @PathVariable Integer appId, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        CommonDto<List<ProjectSendBOutDto>> result = null;

        try {
            result = projectSendBService.readProjectInfomationList(token, appId, pageNo, pageSize);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setStatus(502);
            result.setMessage(e.getMessage());
            result.setData(null);
        }

        return result;
    }

}
