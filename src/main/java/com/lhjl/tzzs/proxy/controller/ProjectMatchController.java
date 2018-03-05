package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.ProjectMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@RestController
public class ProjectMatchController extends GenericController{

    @Autowired
    private ProjectMatchService projectMatchService;

    @GetMapping("/getprojectmatch")
    public CommonDto<Map<String, Object>> getProjectMatch(String searchWord){
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        try {
            result = projectMatchService.getProjectMatch(searchWord);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }

}
