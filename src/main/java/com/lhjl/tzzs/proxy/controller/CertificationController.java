package com.lhjl.tzzs.proxy.controller;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.InvestorsNameDto;
import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
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
	@PostMapping ("search/certification/old")
    public CommonDto<List<ProjectInvestmentDto>> findcertification(@RequestBody  InvestorsNameDto body){
        CommonDto<List<ProjectInvestmentDto>> result =new CommonDto<List<ProjectInvestmentDto>>();
        try {
            Integer investorInstitutionId = body.getInvestorInstitutionId();
            if(investorInstitutionId != null){
            result = certificationService.findcertification(investorInstitutionId);
            }else{
                result.setStatus(5102);
                result.setMessage("机构id不能为空");
            }
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("服务器出现错误");
            log.error(e.getMessage(),e);
        }
        return result;
    }
}
