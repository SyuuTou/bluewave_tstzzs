package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FinancingLogDto.FinancingLogInputDto;
import com.lhjl.tzzs.proxy.dto.FinancingLogDto.FinancingLogOutputDto;
import com.lhjl.tzzs.proxy.service.ProjectAdminFinancingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
@RestController
public class ProjectAdminFinancingController extends GenericController {

    @Resource
    private ProjectAdminFinancingService projectAdminFinancingService;

    /**
     * 获取项目融资需求
     * @param projectId
     * @return
     */
    @GetMapping("/getFinancingLog")
    public CommonDto<FinancingLogOutputDto> getFinancingLog(Integer subjectId,Integer subjectType){
        CommonDto<FinancingLogOutputDto> result = new CommonDto<>();
        try {
            result = projectAdminFinancingService.getFinancingLog(subjectId,subjectType);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 增加或更新融资需求
     * @param body
     * @return
     */
    @PostMapping("/addOrUpdateFinancingLog")
    public CommonDto<String> addOrUpdateFinancingLog(@RequestBody FinancingLogInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAdminFinancingService.addOrUpdateFinancingLog(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
