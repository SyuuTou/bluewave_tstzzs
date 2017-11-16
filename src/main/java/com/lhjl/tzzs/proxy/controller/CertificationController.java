package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.CertificationService;

@RestController
public class CertificationController {
	private static Logger log = LoggerFactory.getLogger(CertificationController.class);
	@Autowired
	private CertificationService certificationService;
    /**
     * 后台查询机构
     * @param investorsName
     * @return
     */
	@GetMapping("search/certification")
    public CommonDto<List<Map<String, Object>>> findcertification(String investorsName){
        CommonDto<List<Map<String, Object>>> result =new CommonDto<List<Map<String, Object>>>();
        try {
            result = certificationService.findcertification(investorsName);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("项目显示页面异常，请稍后再试");
            log.error(e.getMessage(),e);
        }
        return result;
    }
}
