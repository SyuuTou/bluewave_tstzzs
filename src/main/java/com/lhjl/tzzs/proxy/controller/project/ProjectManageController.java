package com.lhjl.tzzs.proxy.controller.project;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlanImage;
import com.lhjl.tzzs.proxy.service.ProjectBusinessPlanService;
import com.lhjl.tzzs.proxy.service.ProjectsService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
/**
 * 项目详情运营管理模块接口
 * @author IdataVC
 *
 */
@RestController
public class ProjectManageController extends GenericController {

	@Resource
    private ProjectsService projectsService;

	/**
	 * 回显项目的运营管理信息
	 * @param appid
	 * @param projectId 项目id
	 * @return
	 */
    @GetMapping("/v{appid}/echo/project/management")
    public CommonDto<DatasOperationManage> echoInvestorsManagementInfo(@PathVariable Integer appid,Integer projectId){
    	CommonDto<DatasOperationManage> result =new CommonDto<>();
    	try {
    		result = projectsService.echoProjectManagementInfo(appid,projectId);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
        return result;
    }
    /**
     * 更新或者保存项目的运营管理
     * @param appid
     * @param body
     * @return
     */
    @PostMapping("/v{appid}/saveorupdate/project/management")
    public CommonDto<Boolean> saveOrUpdateInvestorsManagement(@PathVariable Integer appid,@RequestBody DatasOperationManage body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result = projectsService.saveOrUpdateProjectManagement(appid,body);
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
    		
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
        return result;
    }

}
