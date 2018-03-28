package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlanImage;
import com.lhjl.tzzs.proxy.model.ProjectProgress;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.ProjectBusinessPlanService;
import com.lhjl.tzzs.proxy.service.ProjectsService;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;

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
public class ProjectProgressController extends GenericController {
	
	@Resource
    private ProjectsService projectsService;
    @Resource
    private UserLoginService userLoginService;
    @Resource
    private UserInfoService userInfoService;
	/**
     * 根据appid以及token获取用户信息
     * @param appid
     * @param userId
     * @return
     */
    @GetMapping("/v{appid}/echo/user/byappidandtoken")
    public CommonDto<Users> getUserById(@PathVariable Integer appid,String token){
    	CommonDto<Users> result =new CommonDto<>();
    	try {
    		Integer userId = userLoginService.getUserIdByToken(token,appid);
			result=userInfoService.getUserByUserId(userId);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;  
    }
    /**
     * 根据id删除进展的信息
     * @param appid
     * @param id
     * @param subjectType
     * @return
     */
    @DeleteMapping("/v{appid}/del/progressinfobyid")
    public CommonDto<Boolean> deleteProgressInfoById(@PathVariable Integer appid,Integer id,Integer subjectType){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.removeProgressInfoById(appid,id,subjectType);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 增加或者更新公司进展的消息
     * @param appid
     * @param body
     * @return
     */
    @PostMapping("/v{appid}/saveorupdate/progress")
    public CommonDto<Boolean> saveOrUpdateProgressInfo(@PathVariable("appid") Integer appid,@RequestBody ProjectProgress body){
    	CommonDto<Boolean> result =new CommonDto<>();
    	try {
    		result=projectsService.saveOrUpdateProgressInfo(appid,body);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(false);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }
    /**
     * 公司进展列表
     * @param appid
     * @param companyId 公司id
     * @return
     */
    @GetMapping("/v{appid}/list/project/progress")
    public CommonDto<List<ProjectProgress>> listProProgress(@PathVariable("appid") Integer appid,Integer companyId,Integer subjectType){
    	CommonDto<List<ProjectProgress>> result =new CommonDto<>();
    	try {
    		result=projectsService.listProProgress(appid,companyId,subjectType);
	    }catch(Exception e) {  
	    	this.LOGGER.info(e.getMessage(),e.fillInStackTrace());
    		    
    		result.setData(null);
    		result.setMessage("fail");
    		result.setStatus(500);
	    }
    	return result;
    }

}
