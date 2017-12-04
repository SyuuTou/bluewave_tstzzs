package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectInputDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectOutputDto;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstitutionsProjectController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InstitutionsProjectController.class);

    @PostMapping("find/institution/project")
    public CommonDto<List<InstitutionsProjectOutputDto>> findInstitutionProject(@RequestBody InstitutionsProjectInputDto body){
        CommonDto<List<InstitutionsProjectOutputDto>> result  = new CommonDto<>();

        return result;
    }
}
