package com.lhjl.tzzs.proxy.controller.project.collect.submit;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendAuditBInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectAuditBService;
import com.lhjl.tzzs.proxy.service.ProjectSendBService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class CollectProjectSubmitController extends GenericService{

    @Resource
    private ProjectSendBService projectSendBService;

    /**
     * 创建项目提交信息
     * @param body
     * @param appid
     * @return
     */
    @PostMapping("v{appid}/create/project/sendinfo")
    public CommonDto<String> createProjectSendInfo(@RequestBody ProjectSendBDto body,@PathVariable Integer appid){
        CommonDto<String> result  =new CommonDto<>();

        try {
            result = projectSendBService.updateProject(body, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }
}
