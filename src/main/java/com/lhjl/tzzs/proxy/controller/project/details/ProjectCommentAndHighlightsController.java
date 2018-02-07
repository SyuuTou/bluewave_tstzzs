package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectsListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectsUpdateInputDto;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.model.MetaDataSourceType;
import com.lhjl.tzzs.proxy.model.MetaFollowStatus;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlanImage;
import com.lhjl.tzzs.proxy.model.ProjectFollowStatus;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.ProjectBusinessPlanService;
import com.lhjl.tzzs.proxy.service.ProjectsService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * 项目详情运营管理模块接口
 * @author IdataVC
 *
 */
@RestController
public class ProjectCommentAndHighlightsController extends GenericController {

	@Resource
    private ProjectsService projectsService;
	 /**
     * 根据id回显 公司全部信息(主要用于获取项目简介 以及 投资亮点)
     * @param appid
     * @param proId 项目id
     * @return
     */
    @GetMapping("/v{appid}/echo/proinfobyid")
    public CommonDto<Projects> echoProInfoById(@PathVariable Integer appid,Integer proId){
    	CommonDto<Projects> result =new CommonDto<>();
    	try {
    		result=projectsService.getProInfoById(appid,proId);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		  
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    
    /**
     * 根据id更新公司相关信息(主要用于更新项目简介 以及 投资亮点)
     * @param appid
     * @param proId  
     * @return
     */
    @PutMapping("/v{appid}/edit/proinfobyid")
    public CommonDto<Boolean> editProInfoById(@PathVariable Integer appid,@RequestBody Projects body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.updateProInfos(appid,body);
	    }catch(Exception e) {
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		  
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }

}
