package com.lhjl.tzzs.proxy.service;

import java.math.BigDecimal;
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
	 * 购买金币支付成功后插入金币记录表数据接口
	 * service接口：UserIntegralsService
	 * 方法名：insertGold
	 * 用户的uuid： uuids
	 * 支付的金额：qj
	 */
	CommonDto<String> insertGold(String uuids,BigDecimal qj);
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
