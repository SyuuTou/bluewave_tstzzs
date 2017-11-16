package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.InvestorsNameDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.CertificationService;

@RestController
public class CertificationController {
	private static Logger log = LoggerFactory.getLogger(CertificationController.class);
	@Autowired
	private CertificationService certificationService;

    /**
     * 查询机构接口
     * @param body
     * @return
     */
	@PostMapping ("search/certification")
    public CommonDto<List<Map<String, Object>>> findcertification(@RequestBody  InvestorsNameDto body){
        CommonDto<List<Map<String, Object>>> result =new CommonDto<List<Map<String, Object>>>();
        try {
            String investorsName = body.getInvestorsName();
            if(investorsName !=null ){
            result = certificationService.findcertification(investorsName);
            }else{
                result.setStatus(5102);
                result.setMessage("机构出现异常");
            }
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
