package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;

public interface CertificationNewService {
    CommonDto<List<ProjectInvestmentDto>> findcertificationNew(Integer investorInstitutionId);
}
