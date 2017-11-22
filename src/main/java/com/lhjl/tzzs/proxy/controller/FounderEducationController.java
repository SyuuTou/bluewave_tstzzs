package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.FoundersEducation;
import com.lhjl.tzzs.proxy.service.FounderEducationService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FounderEducationController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FounderEducationController.class);

    @Resource
    private FounderEducationService founderEducationService;

    /**
     * 通过输入内容获取相似教育经历接口
     * @param inputsWords 输入内容
     * @param pageSize 显示条数
     * @return
     */
    @GetMapping("eductation/intelligent/search")
    public CommonDto<List<FoundersEducation>> educationIntelligentSearch(String inputsWords,Integer pageSize){
        CommonDto<List<FoundersEducation>> result = new CommonDto<>();
        try {

            result = founderEducationService.getFounderEducationIntelligent(inputsWords,pageSize);

        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());

            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }
        return result;
    }
}
