package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;

public interface CertificationService {
	CommonDto<List<ProjectInvestmentDto>> findcertification(String investorsName);
}
