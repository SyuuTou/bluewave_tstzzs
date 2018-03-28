package com.lhjl.tzzs.proxy.controller.project.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditHistoryFinancingDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendFinancingHistoryBDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditHistoryFinancingService;
import com.lhjl.tzzs.proxy.service.ProjectSendFinancingHistoryBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditHistoryFinancingController extends GenericController{

	@Resource
    private CollectProjectAuditHistoryFinancingService collectProjectAuditHistoryFinancingService;
    @Resource
    private ProjectSendFinancingHistoryBService projectSendFinancingHistoryBService;
    /**
     * CCG
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
    /**
     * ZYY
     * 读取项目融资历史的接口
     * @param projectSendBId
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/read/project/send/financing/history")
    public CommonDto<List<ProjectSendFinancingHistoryBDto>> readProjectSendFinancingHistory(Integer projectSendBId, @PathVariable Integer appid){
        CommonDto<List<ProjectSendFinancingHistoryBDto>> result = new CommonDto<>();

        try {
            result = projectSendFinancingHistoryBService.readProjectSendFinancingHistoryB(projectSendBId,appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }
        return result;
    }

    /**
     * ZYY
     * 创建项目融资历史
     * @param param
     * @param appid
     * @return
     */
    @PostMapping("v{appid}/project/send/financing/history")
    public CommonDto<String> createProjectSendFinancingHistory(@RequestBody Map<String,List<ProjectSendFinancingHistoryBDto>> param , @PathVariable Integer appid){
        CommonDto<String> result  =  new CommonDto<>();
        List<ProjectSendFinancingHistoryBDto> body = param.get("body");
        try {
            result = projectSendFinancingHistoryBService.creatProjectSendFinancingHistoryB(body, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误"); 
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }
    
    
}
