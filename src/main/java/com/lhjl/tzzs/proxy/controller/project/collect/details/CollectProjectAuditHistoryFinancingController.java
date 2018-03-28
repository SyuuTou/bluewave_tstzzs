package com.lhjl.tzzs.proxy.controller.project.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditHistoryFinancingDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditHistoryFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditHistoryFinancingController extends GenericController{

    @Autowired
    private CollectProjectAuditHistoryFinancingService collectProjectAuditHistoryFinancingService;
    /**
     * 回显采集项目审核历史融资信息
     * @param projectId
     * @return
     */
    @GetMapping("/getcollectprojectaudithistoryfinancing")
    public CommonDto<List<CollectProjectAuditHistoryFinancingDto>> getCollectProjectAuditHistoryFinancing(Integer projectId){
        CommonDto<List<CollectProjectAuditHistoryFinancingDto>> result = new CommonDto<>();
        try {
            result = collectProjectAuditHistoryFinancingService.getCollectProjectAuditHistoryFinancing(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
