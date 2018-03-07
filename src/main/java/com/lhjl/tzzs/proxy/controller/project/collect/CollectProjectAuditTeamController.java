package com.lhjl.tzzs.proxy.controller.project.collect;

import com.lhjl.tzzs.proxy.controller.GenericController;
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
    
    /**
     * 回显采集项目审核团队成员
     * @param projectId project_send_audit_b表的id，即"审核项目记录表"的主键id
     * @return
     */
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
