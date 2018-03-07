package com.lhjl.tzzs.proxy.controller.project.collect;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditFinancingDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditFinancingController extends GenericController{

    @Autowired
    private CollectProjectAuditFinancingService collectProjectAuditFinancingService;

    @GetMapping("/getcollectprojectauditfinancing")
    public CommonDto<CollectProjectAuditFinancingDto> getCollectProjectAuditFinancing(Integer projectId){
        CommonDto<CollectProjectAuditFinancingDto> result = new CommonDto<>();
        try {
            result = collectProjectAuditFinancingService.getCollectProjectAuditFinancing(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
