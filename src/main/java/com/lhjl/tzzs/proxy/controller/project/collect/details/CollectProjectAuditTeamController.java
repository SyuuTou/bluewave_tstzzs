package com.lhjl.tzzs.proxy.controller.project.collect.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CollectProjectAuditTeamDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendTeamBDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendTeamBOutDto;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditTeamService;
import com.lhjl.tzzs.proxy.service.ProjectSendTeamBService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class CollectProjectAuditTeamController extends GenericController{

	@Resource
    private CollectProjectAuditTeamService collectProjectAuditTeamService;  
    @Resource
    private ProjectSendTeamBService projectSendTeamBService;
    
    /**
     * 回显采集项目审核团队成员
     * @param projectId project_send_audit_b表的id，即"审核项目记录表"的主键id
     * @return
     */
    @GetMapping("/getcollectprojectauditteam")
    public CommonDto<CollectProjectAuditTeamDto> getCollectProjectAuditTeam(Integer projectId){
        CommonDto<CollectProjectAuditTeamDto> result = new CommonDto<>();
        try {
            result = collectProjectAuditTeamService.getCollectProjectAuditTeam(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
    
    /**
     * ZYY
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
     * ZYY
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
     * ZYY
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
     * ZYY
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
