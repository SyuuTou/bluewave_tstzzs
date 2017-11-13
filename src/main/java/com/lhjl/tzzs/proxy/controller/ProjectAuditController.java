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
import com.lhjl.tzzs.proxy.service.ProjectAuditService;


@RestController
public class ProjectAuditController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAuditController.class);

    @Resource
    private ProjectAuditService projectAuditService;

    /**
     * 项目审核接口
     * @param body
     * @return
     */
    @PostMapping("admin/project/audit")
    public CommonDto<String> adminProjectAudit(@RequestBody ProjectAuditInputDto body){
        CommonDto<String> result  = new CommonDto<>();

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
    public CommonDto<List<Map<String, Object>>> findProjectall(Integer id){


        CommonDto<List<Map<String, Object>>> result =new CommonDto<List<Map<String, Object>>>();

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

}

