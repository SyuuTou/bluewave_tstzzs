package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendAuditBInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListInputDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectAuditBService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectAuditBController extends GenericService{

    @Resource
    private ProjectAuditBService projectAuditBService;

    /**
     * 读取审核项目列表的接口
     * @return
     */
    @PostMapping("/v{appid}/get/project/send/list")
    public CommonDto<Map<String,Object>> getProjectSendList(@RequestBody ProjectSendBAdminListInputDto body, @PathVariable Integer appid){
        CommonDto<Map<String,Object>> result  = new CommonDto<>();

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

    @PostMapping("/v{appid}/send/project/audit")
    public CommonDto<String> auditProjectSend(@RequestBody ProjectSendAuditBInputDto body, @PathVariable Integer appid){
        CommonDto<String> result  = new CommonDto<>();

        try {

        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
}
