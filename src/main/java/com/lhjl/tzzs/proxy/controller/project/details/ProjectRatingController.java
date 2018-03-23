package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.AdminCreatProjectDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.model.AdminProjectRatingLog;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import com.lhjl.tzzs.proxy.service.ProjectRatingService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


@RestController
public class ProjectRatingController extends GenericController {

    @Resource
    private ProjectRatingService projectRatingService;
    
    @Resource
    private InvestmentInstitutionsService investmentInstitutionsService;

    /**
     * 编辑项目评级信息
     * @param projectId
     * @param ratingStage(0表示D级,1表示C级,2表示B级,3表示A级,4表示S级)
     * @param ratingDiscription
     * @param ratingAdminName
     * @return
     */
    @GetMapping("admin/project/rating")
    public CommonDto<String> projectRatingLevel(Integer subjectId,Integer ratingStage,String ratingDiscription,String ratingAdminName,Integer subjectType){
        CommonDto<String> result = new CommonDto<>();

        //参数转换
        ProjectRatingDto body = new ProjectRatingDto();
        body.setSubjectId(subjectId);
        body.setRatingAdminName(ratingAdminName);
        body.setRatingDiscription(ratingDiscription);
        body.setRatingStage(ratingStage);
        body.setSubjectType(subjectType);


        try {
        	if(body.getSubjectType().equals(1)) {//项目评级
        		result = projectRatingService.projectRating(body);
        	}else if(body.getSubjectType().equals(2)) {//机构评级
        		//TODO 机构评级（待完善）
        		result= investmentInstitutionsService.institutionRating(body);
        	}else {
        		//TODO 其他项目类别的评级
        	}
        }catch (Exception e){
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    /**
     * 后台管理员审核后，获取事件接受的机构id信息接口
     * @param sourceId 源数据id
     * @param idType id类型，0表示项目提交记录表，1表示项目表信息
     * @return
     */
    @GetMapping("admin/creat/event")
    public CommonDto<AdminCreatProjectDto> adminCreateEvent(Integer sourceId,Integer idType){
        CommonDto<AdminCreatProjectDto> result = new CommonDto<>();

        try {
            result = projectRatingService.adminCreateEvent(sourceId,idType);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }

    @GetMapping("admin/get/project/rating")
    public CommonDto<AdminProjectRatingLog> adminGetProjectRating(Integer projectId){
        CommonDto<AdminProjectRatingLog> result = new CommonDto<>();

        try {
            result = projectRatingService.getProjectRatingInfo(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }

    /**
     * 新版获取机构ids和提交人token的接口
     * @param projectId
     * @return
     */
    @GetMapping("admin/creat/event/new")
    public CommonDto<AdminCreatProjectDto> adminCreateEventNew(Integer projectId){
        CommonDto<AdminCreatProjectDto> result = new CommonDto<>();

        try {
            result = projectRatingService.getUserAndInvestmentInstitutionIds(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }

}
