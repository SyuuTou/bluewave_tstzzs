package com.lhjl.tzzs.proxy.controller.project.collect.list;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendAuditBInputDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectAuditBService;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class CollectProjectAuditController extends GenericService{

    @Resource
    private ProjectAuditBService projectAuditBService;
    
    @Resource
    private ProjectAuditService projectAuditService;

    /**
     * 审核提交项目
     * @param body
     * @param appid
     * @return
     */
    @PostMapping("/v{appid}/send/project/audit")
    public CommonDto<String> auditProjectSend(@RequestBody ProjectSendAuditBInputDto body, @PathVariable Integer appid){
        CommonDto<String> result  = new CommonDto<>();
        try {
            result = projectAuditBService.auditProjectSend(body, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
    
    /**
     * 后台审核项目接口
     * @param projectSourceId 项目源id
     * @param auditStatus  审核状态
     * @param projctSourceType 项目源类型
     * @param auditDescription 审核描述
     * @param auditAdminName 审核人名称
     * @return
     */
    @GetMapping("admin/project/audit")
    public CommonDto<String> adminProjectAudit(Integer projectSourceId,Integer auditStatus,Integer projctSourceType,String auditDescription,String auditAdminName){
        CommonDto<String> result  = new CommonDto<>();

        if (projectSourceId == null){
            result.setStatus(502);
            result.setMessage("项目源id不能为空");
            result.setData(null);

            return result;
        }

        if (auditStatus == null){
            result.setStatus(502);
            result.setMessage("项目审核状态不能为空");
            result.setData(null);

            return result;
        }

        if (projctSourceType == null){
            result.setStatus(502);
            result.setMessage("项目源类型不能为空");
            result.setData(null);

            return result;
        }

        if (auditDescription == null){
            auditDescription = "";
        }

        if (auditAdminName == null){
            auditAdminName = "";
        }

        //为了避免多改，先在这里把入参整合一下
        ProjectAuditInputDto body =new ProjectAuditInputDto();
        body.setAuditAdminName(auditAdminName);
        body.setAuditDescription(auditDescription);
        body.setAuditStatus(auditStatus);
        body.setProjctSourceType(projctSourceType);
        body.setProjectSourceId(projectSourceId);


        try {
            result = projectAuditService.adminAuditProject(body);
        }catch (Exception e){
            this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
}
