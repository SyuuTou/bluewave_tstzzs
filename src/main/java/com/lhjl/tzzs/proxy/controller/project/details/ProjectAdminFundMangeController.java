package com.lhjl.tzzs.proxy.controller.project.details;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectManageDto;
import com.lhjl.tzzs.proxy.model.MetaInvestType;
import com.lhjl.tzzs.proxy.service.ProjectAdminManageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 14:58:30
 */
@RestController
public class ProjectAdminFundMangeController extends GenericController{

    @Resource
    private ProjectAdminManageService projectAdminManageService;

    /**
     * 获取基金管理信息
     * @return
     */
    @GetMapping("get/projectmanage")
    public CommonDto<ProjectManageDto> getProjectManage(Integer projectId){
        CommonDto<ProjectManageDto> result = new CommonDto<>();
        try {
            result = projectAdminManageService.getProjectMange(projectId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }

    /**
     * 添加或更新基金管理信息
     * @param body
     * @return
     */
    @PostMapping("/addOrUpdateManage")
    public CommonDto<String> addOrUpdateManage(@RequestBody ProjectManageDto body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAdminManageService.addOrUpdateManage(body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }

    /**
     * 得到所有投资类型
     * @return
     */
    @GetMapping("/getinvesttype")
    public CommonDto<List<MetaInvestType>> getInvestType( ){
        CommonDto<List<MetaInvestType>> result = new CommonDto<>();
        try {
            result = projectAdminManageService.getInvestType();
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }

    /**
     * 智能检索输入案例
     * @param inputWord
     * @return
     */
    @GetMapping("/getclassiccase")
    public CommonDto<List<String>> getClassicCase(String inputWord){
        CommonDto<List<String>> result = new CommonDto<>();
        try {
            result = projectAdminManageService.getClassicCase(inputWord);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }
}
