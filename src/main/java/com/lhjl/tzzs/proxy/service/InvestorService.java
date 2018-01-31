package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.model.Users;

public interface InvestorService {
	/**
	 * 投资人列表
	 * @param appid
	 * @param body 投资人列表请求体
	 * @return
	 */
	CommonDto<Map<String,Object>> listInvestorsInfos(Integer appid, InvestorListInputDto body);
	/**
     * 投资人的用户匹配
     * @param appid
     * @param keyword 搜索关键字    
     * @return
     */  
	CommonDto<List<Users>> matchUsers(Integer appid, String keyword);
	/**
     * 批量更换投资人的负责人
     * @param appid
     * @param InvestorIds 投资人的id数组
     * @param principal 负责人
     * @return
     */
	CommonDto<Boolean> changeIrPrincipalBatch(Integer appid, List<Integer> investorIds, String irPrincipal);


}
