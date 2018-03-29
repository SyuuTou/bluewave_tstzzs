package com.lhjl.tzzs.proxy.controller.project.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto.ProjectLogoInfoOutputDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditBasicInfoService;
import com.lhjl.tzzs.proxy.service.ProjectSendBService;
import com.lhjl.tzzs.proxy.service.ProjectSendTeamBService;

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
public class CollectProjectAuditLogoInfoController extends GenericController{

    @Resource
    private ProjectSendBService projectSendBService;
    
    /**
     * 回显提交项目详情头部基本信息的接口
     * @param projectSendId
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/project/sendb/logoinfo")
    public CommonDto<ProjectLogoInfoOutputDto> readProjectLogoInfo(Integer projectSendId, @PathVariable Integer appid){
        CommonDto<ProjectLogoInfoOutputDto> result  = new CommonDto<>();
        try {
            result = projectSendBService.readProjectSendBLogoInfo(projectSendId,appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }
        return result;
    }

}
