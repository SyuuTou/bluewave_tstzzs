package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditTeamDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditTeamController extends GenericController{

    @Autowired
    private CollectProjectAuditTeamService collectProjectAuditTeamService;

    @GetMapping("/getcollectprojectauditteam")
    public CommonDto<CollectProjectAuditTeamDto> getCollectProjectAuditTeam(Integer projectId){
        CommonDto<CollectProjectAuditTeamDto> result = new CommonDto<>();
        try {
            result = collectProjectAuditTeamService.getCollectProjectAuditTeam(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
