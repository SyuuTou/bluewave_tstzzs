package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.ChangePrincipalInputDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.VIPOutputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
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
	CommonDto<Boolean> changeIrPrincipalBatchOrSingle(Integer appid, ChangePrincipalInputDto body);
	/**
	 * 回显投资人的运营管理信息
	 * @param appid
	 * @param id 投资人id
	 * @return
	 */
	CommonDto<DatasOperationManage> echoInvestorsManagementInfo(Integer appid, Integer id);
	/**
     * 更新或者保存投资人的运营管理信息
     * @param appid
     * @param body
     * @return
     */
	CommonDto<Boolean> saveOrUpdateInvestorsManagement(Integer appid, DatasOperationManage body);
	/**
     * 获取天使投资指数的所有后台管理员,即后台运营人员，负责人
     * @param appid
     * @return
     */
	CommonDto<List<AdminUser>> getTstzzsAdmin(Integer appid,String keyword);
	/**
	 * 回显投资人会员信息
	 * @param appid
	 * @param id 投资人id
	 * @return
	 */
	CommonDto<VIPOutputDto> echoInvestorsVIPInfo(Integer appid, Integer userId);


}
