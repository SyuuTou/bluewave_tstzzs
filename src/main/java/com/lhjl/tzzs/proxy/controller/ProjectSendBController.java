package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.*;
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
     * 创建/更新提交项目接口
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
}
