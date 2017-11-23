package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.FoundersWork;
import com.lhjl.tzzs.proxy.service.FounderWorkService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FounderWorkController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FounderWorkController.class);

    @Resource
    private FounderWorkService founderWorkService;
    /**
     * 工作经历检索检索
     * @param inputsWords 输入内容
     * @param pageSize 返回数量
     * @return
     */
    @GetMapping("work/intelligent/search")
    public CommonDto<List<FoundersWork>> findWorkIntelligentSearch(String inputsWords,Integer pageSize){
        CommonDto<List<FoundersWork>> result = new CommonDto<>();
        try {
            result = founderWorkService.findFounderWork(inputsWords,pageSize);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }
}
