package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;

public interface CertificationService {
	CommonDto<List<Map<String, Object>>> findcertification(String investorsName);
}
