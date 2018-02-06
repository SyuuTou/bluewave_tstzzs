package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
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
     * 增加或更新团队成员信息
     * @param projectId
     * @return
     */
    @PostMapping("/{projectId}/addOrUpdatePojectTeamIntro")
    public CommonDto<String> addOrUpdatePojectTeamIntro(@PathVariable Integer projectId, @RequestBody String teamIntroduction){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAdminTeamIntroService.addOrUpdatePojectTeamIntro(projectId, teamIntroduction);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

    @GetMapping("/getpojectteamintro")
    public CommonDto<Map<String, Object>> getPojectTeamIntro(Integer projectId){

        CommonDto<Map<String, Object>> result = new CommonDto<>();
        try {
            result = projectAdminTeamIntroService.getPojectTeamIntro(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
