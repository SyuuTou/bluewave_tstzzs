package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.CompanyIntelligentOutputDto;
import com.lhjl.tzzs.proxy.service.SubjectService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SubjectController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SubjectController.class);

    @Resource
    private SubjectService subjectService;


    /**
     * 公司检索接口
     * @param inputsWords 输入内容
     * @param pageSize 一页返回数量
     * @param inputsType 搜全程还是搜简称
     * @return
     */
    @GetMapping("company/intelligent/search")
    public CommonDto<List<CompanyIntelligentOutputDto>> getCompanyIntelligentSearch(String inputsWords, Integer pageSize,Integer inputsType){
        CommonDto<List<CompanyIntelligentOutputDto>> result = new CommonDto<>();

        try {
            result = subjectService.getCompanyIntelligent(inputsWords,pageSize,inputsType);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());

            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }
        return result;
    }
}
