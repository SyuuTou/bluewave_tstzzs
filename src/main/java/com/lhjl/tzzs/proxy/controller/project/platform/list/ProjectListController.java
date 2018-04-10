package com.lhjl.tzzs.proxy.controller.project.platform.list;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PagingOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectsListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectsListOutputDto;
import com.lhjl.tzzs.proxy.dto.ProjectsUpdateInputDto;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.model.MetaDataSourceType;
import com.lhjl.tzzs.proxy.model.MetaFollowStatus;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlanImage;
import com.lhjl.tzzs.proxy.model.ProjectFollowStatus;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
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
public class ProjectListController extends GenericController {

	@Resource
    private ProjectsService projectsService;
	@Resource
	private InvestmentInstitutionsService investmentInstitutionsService;
	/**
     * 天使投资指数的项目列表（兼容机构列表）
     * @param appid
     * @param body
     * @return
     */
    @PostMapping("/v{appid}/list/projects")  
    public CommonDto<PagingOutputDto<ProjectsListOutputDto>> listProject(@PathVariable("appid") Integer appid,@RequestBody ProjectsListInputDto body){
    	CommonDto<PagingOutputDto<ProjectsListOutputDto>> result=new CommonDto<>();
    	try {
    		result=projectsService.listProInfos(appid,body);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    }
    /**
     * 更新项目的跟进状态
     * @param appid
     * @param body 项目的id以及项目的更新状态
     * @return
     */
    @PutMapping("/v{appid}/update/projects/status")
    public CommonDto<Boolean> updateFollowStatus(@PathVariable("appid") Integer appid,@RequestBody ProjectsUpdateInputDto body){
    	CommonDto<Boolean> result = new CommonDto<>();
    	try {
    		if(body.getSubjectType().equals(Integer.valueOf(1))) {//项目跟进状态变更
        		result=projectsService.updateFollowStatus(appid,body);
    		}else if(body.getSubjectType().equals(Integer.valueOf(2))){//机构跟进状态变更
    			result=investmentInstitutionsService.updateFollowStatus(appid,body);
    		}else {
    			//TODO 其他公司类型的信息更新
    		}
    	}catch(Exception e) {
    		this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    }

    /**
     * 项目的跟进状态回显
     * @param projectId
     * @param appid
     * @return
     */
    @GetMapping("/v{appid}/get/projects/status")
    public CommonDto<ProjectFollowStatus> getProjectsStatus(Integer projectId, @PathVariable Integer appid){
        CommonDto<ProjectFollowStatus> result = new CommonDto<>();

        try {
            result = projectsService.getFollowStatus(projectId, appid);
        }catch (Exception e){
        	this.LOGGER.error(e.getMessage(),e.fillInStackTrace());

            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }
    /**
     * 读取项目跟进状态元数据
     * @return
     */
    @GetMapping("/v{appid}/source/status")
    public CommonDto<List<MetaFollowStatus>> getStatusSource(@PathVariable Integer appid ){
    	CommonDto<List<MetaFollowStatus>> result =new CommonDto<>();
    	try {
    		result=projectsService.getFollowStatusSource(appid);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    	
    }
    /**
     * 读取项目来源元数据
     * @return
     */
    @GetMapping("/v{appid}/source/projects")
    public CommonDto<List<MetaDataSourceType>> getProjectsSource(@PathVariable Integer appid ){
    	CommonDto<List<MetaDataSourceType>> result =new CommonDto<>();
    	try {
    		result=projectsService.getProjectsSource(appid);
    	}catch(Exception e) {
    		this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
    	}
    	return result;
    	
    }

}
