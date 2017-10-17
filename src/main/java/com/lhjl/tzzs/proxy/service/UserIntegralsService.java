package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.QzengDto;
import com.lhjl.tzzs.proxy.dto.YnumDto;
import com.lhjl.tzzs.proxy.dto.ZengDto;

public interface UserIntegralsService {
	 CommonDto<Map<String, Integer>> findIntegralsY( YnumDto body);
	 CommonDto<Map<String,Integer>> findIntegralsZeng(ZengDto body);
	 CommonDto<Map<String,Integer>> findIntegralsQzeng(QzengDto body);
	 CommonDto<List<Map<String, Object>>>findIntegralsMing(String uuids,Integer pageNum,Integer pageSize);
}
