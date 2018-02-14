package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectInputDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectOutputDto;
import com.lhjl.tzzs.proxy.service.InstitutionsProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class InstitutionsProjectController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InstitutionsProjectController.class);

    @Resource  
    private InstitutionsProjectService institutionsProjectService;

    /**
     * 获取机构投资项目列表接口
     * @param body
     * @return
     */
    @PostMapping("find/institution/project")
    public CommonDto<List<Map<String,Object>>> findInstitutionProject(@RequestBody InstitutionsProjectInputDto body){
        CommonDto<List<Map<String,Object>>> result  = new CommonDto<>();
        try {
            result = institutionsProjectService.findInstitutionProject(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setData(null);
            result.setStatus(502);
        }
        return result;
    }
}
