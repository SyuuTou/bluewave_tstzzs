package com.lhjl.tzzs.proxy.controller.project.collect.list;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PagingOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendAuditBInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListOutputDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectAuditBService;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class CollectProjectListController extends GenericService{

    @Resource
    private ProjectAuditBService projectAuditBService;


    /**
     * 采集项目审核列表
     * @param body
     * @param appid
     * @return
     */
    @PostMapping("/v{appid}/get/project/send/list")
    public CommonDto<PagingOutputDto<ProjectSendBAdminListOutputDto>> getProjectSendList(@RequestBody ProjectSendBAdminListInputDto body, @PathVariable Integer appid){
        CommonDto<PagingOutputDto<ProjectSendBAdminListOutputDto>> result  = new CommonDto<>();

        try {
            result = projectAuditBService.getProjectSendList(body, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }
    
    
}
