package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.dto.XiangsiDto;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;


@RestController
public class ProjectAuditController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAuditController.class);

    @Resource
    private ProjectAuditService projectAuditService;

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
            log.info(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
    @GetMapping("search/likes")
    public CommonDto<List<Map<String, Object>>> findProject(Integer id){


        CommonDto<List<Map<String, Object>>> result =new CommonDto<List<Map<String, Object>>>();

        try {
            //初始化分页信息
            result = projectAuditService.findProject(id);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目id不存在");
            log.error(e.getMessage());
        }
        return result;
    }
    @GetMapping("search/likes/all")
    public  CommonDto<List<XiangsiDto>> findProjectall(Integer id){
    	 CommonDto<List<XiangsiDto>> result =new  CommonDto<List<XiangsiDto>>();

        try {
            //初始化分页信息
            result = projectAuditService.findProjectall(id);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目id不存在");
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 查找关注状态
     * @param id
     * @return
     */
    @GetMapping("search/followyn")
    public  CommonDto<Map<String,Object>> findFollow(Integer id,String token){
        CommonDto<Map<String,Object>>  result =new  CommonDto<Map<String,Object>>();

        try {
            //初始化分页信息
            result = projectAuditService.findFollow(id,token);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目id不存在");
            log.error(e.getMessage());
        }
        return result;
    }

    @GetMapping("set/project/administractor")
    public CommonDto<String> setProjectAdministractor(Integer projetcId,Integer userId){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAuditService.adminAddAdministractor(projetcId,userId);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }
        return result;
    }
}

