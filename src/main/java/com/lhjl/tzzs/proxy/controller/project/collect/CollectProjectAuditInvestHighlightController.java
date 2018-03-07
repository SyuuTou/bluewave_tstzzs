package com.lhjl.tzzs.proxy.controller.project.collect;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditInvestHighlightDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditInvestHighlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditInvestHighlightController extends GenericController{

    @Autowired
    private CollectProjectAuditInvestHighlightService collectProjectAuditInvestHighlightService;

    @GetMapping("/collectprojectauditinvesthighlight")
    public CommonDto<CollectProjectAuditInvestHighlightDto> getCollectProjectAuditInvestHighlight(Integer projectId){
        CommonDto<CollectProjectAuditInvestHighlightDto> result = new CommonDto<>();
        try {
            result = collectProjectAuditInvestHighlightService.getCollectProjectAuditInvestHighlight(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
