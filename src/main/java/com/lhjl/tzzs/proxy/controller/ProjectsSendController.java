package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendDto;
import com.lhjl.tzzs.proxy.dto.ProjectsSendDto;
import com.lhjl.tzzs.proxy.dto.TeamDto;
import com.lhjl.tzzs.proxy.dto.TeamDto1;
import com.lhjl.tzzs.proxy.service.ProjectsSendService;
import com.lhjl.tzzs.proxy.service.common.CommonUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 项目投递
 * Created by 蓝海巨浪 on 2017/10/23.
 */
@RestController
public class ProjectsSendController {
    private static Logger logger = LoggerFactory.getLogger(ProjectsSendController.class);

    @Resource
    private ProjectsSendService projectsSendService;
    @Resource
    private CommonUserService commonUserService;


    /**
     * 项目投递
     */
    @PostMapping("/rest/zyy/ctuisongsecond")
    public CommonDto<String> ctuisongsecond(@RequestBody ProjectsSendDto params){
        CommonDto<String> result = new CommonDto<>();

        try{
            //获取userId
            int userId = commonUserService.getLocalUserId(params.getToken());

            result = projectsSendService.ctuisongsecond(params, userId);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }

        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("项目投递异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 项目投递回显
     */
    @PostMapping("/rest/zyy/rtuisonghuixian")
    public CommonDto<Map<String, Object>> rtuisonghuixian(@RequestBody ProjectSendDto body){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        String token = body.getToken();
        String tsid = body.getTsid();
        try{
            //获取userId
            int userId = commonUserService.getLocalUserId(token);
            result = projectsSendService.rtuisonghuixian(userId, tsid);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("投递项目回显异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 融资历史记录
     */
    @PostMapping("/rest/zyy/ctuisongthird")
    public CommonDto<String> ctuisongthird(@RequestBody Map<String, String> body){
        CommonDto<String> result = new CommonDto<>();
        String tsid = body.get("tsid");
        String rongzilishi = body.get("rongzilishi");
        try{
            result = projectsSendService.ctuisongthird(tsid, rongzilishi);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("融资历史记录异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 融资历史回显
     */
    @GetMapping("/rest/zyy/rtuisongthird")
    public CommonDto<Map<String, Object>> rtuisongthird(String tsid, String token){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        //获取userId
        int userId = commonUserService.getLocalUserId(token);
        try{
            result = projectsSendService.rtuisongthird(tsid, userId);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("融资历史回显异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
    /**
     * 团队成员
     * @param body
     * @return
     */
    @PostMapping("save/team")
    public CommonDto<String> saveTeam(@RequestBody TeamDto body){
        CommonDto<String> result = new CommonDto<>();
        try{
            result = projectsSendService.saveTeam(body);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("保存团队成员记录异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
    /**
     * 删除团队成员
     * @param id
     * @return
     */
    @GetMapping("delete/team")
    public CommonDto<String> deleteTeam(Integer id ){
        CommonDto<String> result = new CommonDto<>();
        try{
            result = projectsSendService.deleteTeam(id);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("融资历史记录异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 根据id进行回显
     * @param id
     * @return
     */
    
    @GetMapping("rest/teamrecord")
    public CommonDto<Map<String, Object>> teamRecord(Integer id){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        try{
        	 result = projectsSendService.teamRecord(id);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("投递项目回显异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }


    /**
     * 修改页面
     * @param body
     * @return
     */
    @PostMapping("xiugai/team")
    public CommonDto<String> saveTeam1(@RequestBody TeamDto1 body){
        CommonDto<String> result = new CommonDto<>();
        try{
            result = projectsSendService.saveTeam1(body);
        }catch(Exception e){
            result.setStatus(501);
            result.setMessage("保存团队成员记录异常");
            logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
}