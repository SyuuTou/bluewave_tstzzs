package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.dto.XiangsiDto;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;


@RestController
public class ProjectAuditController extends GenericController {

    @Resource
    private ProjectAuditService projectAuditService;

    @GetMapping("search/likes")
    public CommonDto<List<Map<String, Object>>> findProject(Integer id){


        CommonDto<List<Map<String, Object>>> result =new CommonDto<List<Map<String, Object>>>();

        try {
            result = projectAuditService.findProject(id);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目id不存在");
            this.LOGGER.error(e.getMessage());
        }
        return result;
    }
    @GetMapping("search/likes/all")
    public  CommonDto<List<XiangsiDto>> findProjectall(int id,Integer pageNumber,Integer pageSize){
    	 CommonDto<List<XiangsiDto>> result =new  CommonDto<List<XiangsiDto>>();

        try {
            result = projectAuditService.findProjectall(id,pageNumber,pageSize);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("服务器端发生错误");
            this.LOGGER.error(e.getMessage());
        }
        return result;
    }

    /**
     * 查找关注状态
     * @param id
     * @return
     */
    @GetMapping("search/followyn")
    public  CommonDto<Map<String,Object>> findFollow(Integer id,String token){
        CommonDto<Map<String,Object>>  result =new  CommonDto<Map<String,Object>>();

        try {
            result = projectAuditService.findFollow(id,token);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目id不存在");
            this.LOGGER.error(e.getMessage());
        }
        return result;
    }

    @GetMapping("set/project/administractor")
    public CommonDto<String> setProjectAdministractor(Integer projetcId,Integer userId){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = projectAuditService.adminAddAdministractor(projetcId,userId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }
        return result;
    }
}

