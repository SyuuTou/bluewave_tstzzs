package com.lhjl.tzzs.proxy.controller.project.platform.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TeamIntroductionDto;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamIntroInputDto;
import com.lhjl.tzzs.proxy.service.ProjectAdminTeamIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/6.
 */
@RestController
public class ProjectAdminTeamIntroController extends GenericController{

    @Autowired
    private ProjectAdminTeamIntroService projectAdminTeamIntroService;

    /**
     * 增加或更新团队成员团队介绍
     * @param body
     * @return
     */
    @PostMapping("/addOrUpdatePojectTeamIntro")
    public CommonDto<String> addOrUpdatePojectTeamIntro(@RequestBody ProjectTeamIntroInputDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAdminTeamIntroService.addOrUpdatePojectTeamIntro(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
    
    /**
     * 获取团队成员的介绍信息
     * @param projectId 主体id
     * @param subjectType 主体类型：1项目；2机构
     * @return
     */
    @GetMapping("/getpojectteamintro")
    public CommonDto<TeamIntroductionDto> getPojectTeamIntro(Integer projectId,Integer subjectType){

        CommonDto<TeamIntroductionDto> result = new CommonDto<>();
        try {
            result = projectAdminTeamIntroService.getPojectTeamIntro(projectId,subjectType);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
