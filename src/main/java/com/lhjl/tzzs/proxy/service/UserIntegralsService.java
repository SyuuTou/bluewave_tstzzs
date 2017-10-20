package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.MetaObtainIntegral;

public interface UserIntegralsService {
	CommonDto<Map<String,Integer>> findIntegralsY( YnumDto body);
	CommonDto<Map<String,Object>>findIntegralsZeng(ZengDto body);
	CommonDto<Map<String,Object>> findIntegralsQzeng(QzengDto body);
	CommonDto<List<Map<String, Object>>>findIntegralsDetailed(String uuids,Integer pageNum,Integer pageSize);
	CommonDto<String> insertGold( QzengDto body);
	CommonDto<String> insertMember1(MemberDto body);
	CommonDto <List<MetaObtainIntegral>>findMoney();
}
