package com.lhjl.tzzs.proxy.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.model.MetaObtainIntegral;

public interface UserIntegralsService {
     /**
      * 查询余额
      *
      * @param appId
      * @param body
      * @return
      */
	CommonDto<Map<String,Integer>> findIntegralsY(Integer appId, YnumDto body);
	/**
	 * 页面显示查询页面固定选择值的接口
	 *
	 * @param appId
	 * @param body
	 * @return
	 */
	CommonDto<Map<String,Object>>findIntegralsZeng(Integer appId, ZengDto body);

	/**
	 * 重构版页面显示选择值的接口
	 * @param appId
	 * @param body
	 * @return
	 */
	CommonDto<Map<String,Object>>recordUserPayAmountMember(Integer appId, ZengDto body);

	/**
	 * 充值接口
	 * @param appId
	 * @param body
	 * @return
	 */
	CommonDto<Integer>recordUserPayAmount(Integer appId, ZengDto body);
	/**
	 * 
	 * 页面显示查询其他金额选择
	 *
	 * @param appId
	 * @param body
	 * @return
	 */
	CommonDto<Map<String,Object>> findIntegralsQzeng(Integer appId, QzengDto body);
	/**
	 * 交易明细
	 *
     * @param appId
     * @param uuids
     * @param pageNum
     * @param pageSize
     * @return
	 */
	CommonDto<List<Map<String, Object>>>findIntegralsDetailed(Integer appId, String uuids, Integer pageNum, Integer pageSize);
	/**
	 * 购买金币支付成功后插入金币记录表数据接口
	 * service接口：UserIntegralsService
	 * 方法名：insertGold
	 * 用户的uuid： uuids
	 * 支付的金额：qj
	 */
	CommonDto<String> insertGold(Integer userId, BigDecimal qj, Integer appId);
	/**
	 * 生成会员的接口
	 * @param body
	 * @return
	 */
	CommonDto<String> insertMember1(MemberDto body);
	/**
	 * 查询固定金额的接口
	 * @return
	 * @param appId
	 */
	CommonDto <List<MetaObtainIntegral>>findMoney(Integer appId);

	/**
	 * 支付之后调用
	 * @param userId 用户ID
	 * @param sceneKey 场景key
	 * @param payMoney 实际支付金额
	 * @param status 支付状态
	 * @param appId
	 * @return
	 */
	CommonDto<String> payAfter(Integer userId, String sceneKey, BigDecimal payMoney, int status, Integer appId);
}
