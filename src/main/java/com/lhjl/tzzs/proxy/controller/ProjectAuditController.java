package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


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
}
