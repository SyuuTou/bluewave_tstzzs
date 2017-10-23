package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.MetaObtainIntegral;

public interface UserIntegralsService {
     /**
      * 查询余额
      * @param body
      * @return
      */
	CommonDto<Map<String,Integer>> findIntegralsY( YnumDto body);
	/**
	 * 页面显示查询页面固定选择值的接口
	 * @param body
	 * @return
	 */
	CommonDto<Map<String,Object>>findIntegralsZeng(ZengDto body);
	/**
	 * 
	 * 页面显示查询其他金额选择
	 * @param body
	 * @return
	 */
	CommonDto<Map<String,Object>> findIntegralsQzeng(QzengDto body);
	/**
	 * 交易明细
	 * @param uuids
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	CommonDto<List<Map<String, Object>>>findIntegralsDetailed(String uuids,Integer pageNum,Integer pageSize);
	/**
	 * 购买金币产生金币记录表
	 * @param qj
	 * @param uuids
	 * @return
	 */
	CommonDto<String> insertGold(String uuids,Integer qj,String skey);
	/**
	 * 生成会员的接口
	 * @param body
	 * @return
	 */
	CommonDto<String> insertMember1(MemberDto body);
	/**
	 * 查询固定金额的接口
	 * @return
	 */
	CommonDto <List<MetaObtainIntegral>>findMoney();
}
