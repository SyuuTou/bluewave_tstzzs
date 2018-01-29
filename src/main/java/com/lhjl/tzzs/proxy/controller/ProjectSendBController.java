package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto.ProjectLogoInfoOutputDto;
import com.lhjl.tzzs.proxy.model.ProjectSendFinancingHistoryB;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectSendBService;
import com.lhjl.tzzs.proxy.service.ProjectSendFinancingHistoryBService;
import com.lhjl.tzzs.proxy.service.ProjectSendTeamBService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectSendBController extends GenericService{

    @Resource
    private ProjectSendBService projectSendBService;

    @Resource
    private ProjectSendTeamBService projectSendTeamBService;

    @Resource
    private ProjectSendFinancingHistoryBService projectSendFinancingHistoryBService;

    /**
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

    /**
     * 读取提交项目团队成员列表信息
     * @param projectSendBId
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/project/send/teamlist")
    public CommonDto<List<ProjectSendTeamBOutDto>> getTeamList(Integer projectSendBId,@PathVariable Integer appid){
        CommonDto<List<ProjectSendTeamBOutDto>> result  = new CommonDto<>();

        try {
            result = projectSendTeamBService.readTeamMemberList(projectSendBId,appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }

    /**
     * 删除项目成员信息
     * @param projectSendMemberId
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/project/send/team/deleteone")
    public CommonDto<String> deleteTeamMember(Integer projectSendMemberId,@PathVariable Integer appid){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = projectSendTeamBService.deleteTeamMemberById(projectSendMemberId,appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }

    /**
     * 创建/更新提交项目团队成员接口
     * @param body
     * @param appid
     * @return
     */
    @PostMapping("v{appid}/project/send/team/createone")
    public CommonDto<String> createTeammember(@RequestBody ProjectSendTeamBDto body,@PathVariable Integer appid){
        CommonDto<String> result  = new CommonDto<>();

        try {
            result = projectSendTeamBService.createTeamMember(body,appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }

    /**
     * 根据id获取团队成员信息
     * @param projectSendMemberId
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/project/send/team/readone")
    public CommonDto<ProjectSendTeamBDto> getTeamMemberInfo(Integer projectSendMemberId,@PathVariable Integer appid){
        CommonDto<ProjectSendTeamBDto> result = new CommonDto<>();

        try {
            result = projectSendTeamBService.readTeamMemberById(projectSendMemberId, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }

    /**
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
     * 读取项目信息接口
     * @param token
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/read/project/sendinfo")
    public CommonDto<ProjectSendBOutDto> readProjectSendInfo(String token,@PathVariable Integer appid){
        CommonDto<ProjectSendBOutDto> result  = new CommonDto<>();

        try {
            result = projectSendBService.readProjectInfomation(token, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }
        return result;
    }

    /**
     * 创建项目提交信息的接口
     * @param body
     * @param appid
     * @return
     */
    @PostMapping("v{appid}/create/project/sendinfo")
    public CommonDto<String> createProjectSendInfo(@RequestBody ProjectSendBDto body,@PathVariable Integer appid){
        CommonDto<String> result  =new CommonDto<>();

        try {
            result = projectSendBService.updateProject(body, appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }

        return result;
    }

    /**
     * 读取提交项目基本信息的接口
     * @param projectSendId
     * @param appid
     * @return
     */
    @GetMapping("v{appid}/project/sendb/logoinfo")
    public CommonDto<ProjectLogoInfoOutputDto> readProjectLogoInfo(Integer projectSendId, @PathVariable Integer appid){
        CommonDto<ProjectLogoInfoOutputDto> result  = new CommonDto<>();
        try {
            result = projectSendBService.readProjectSendBLogoInfo(projectSendId,appid);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }
        return result;
    }
}
