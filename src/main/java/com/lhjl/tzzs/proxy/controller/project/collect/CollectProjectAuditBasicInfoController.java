package com.lhjl.tzzs.proxy.controller.project.collect;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditBasicInfoController extends GenericController{

    @Autowired
    private CollectProjectAuditBasicInfoService collectProjectAuditBasicInfoService;
    
    /**
     * 回显采集项目审核基本信息
     * @param projectId
     * @return
     */
    @GetMapping("/getcollectprojectauditbasicinfo")
    public CommonDto<CollectProjectAuditBasicInfoDto> getCollectProjectAuditBasicInfo(Integer projectId){
        CommonDto<CollectProjectAuditBasicInfoDto> result = new CommonDto<>();
        try {  
            result = collectProjectAuditBasicInfoService.getCollectProjectAuditBasicInfo(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
