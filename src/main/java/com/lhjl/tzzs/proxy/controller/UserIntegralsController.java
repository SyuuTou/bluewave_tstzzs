package com.lhjl.tzzs.proxy.controller;



import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.model.MetaObtainIntegral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InterviewDto;
import com.lhjl.tzzs.proxy.dto.MemberDto;
import com.lhjl.tzzs.proxy.dto.MingDto;
import com.lhjl.tzzs.proxy.dto.QzengDto;
import com.lhjl.tzzs.proxy.dto.ShuruDto;
import com.lhjl.tzzs.proxy.dto.YnumDto;
import com.lhjl.tzzs.proxy.dto.ZengDto;
import com.lhjl.tzzs.proxy.model.Interview;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class UserIntegralsController {
	private static final Logger log = LoggerFactory.getLogger(UserIntegralsController.class);

	@Resource
	private UserIntegralsService userIntegralsService;
	/**
	 * 查询余额接口
	 * @param body
	 * @return
	 */
	@PostMapping("search/ynum")
	public CommonDto<Map<String,Integer>> findIntegralsY(@RequestBody YnumDto body){
		CommonDto<Map<String, Integer>> result = new CommonDto<Map<String, Integer>>();
		try {
			result =userIntegralsService.findIntegralsY(body);
			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("项目显示页面异常，请稍后再试");
			log.error(e.getMessage(),e.fillInStackTrace());
		}
		return result;
	}
	/**
	 * 页面显示固定金额
	 * @param body
	 * @return
	 */
	@PostMapping("search/zeng")
	public CommonDto<Map<String,Object>>findIntegralsZeng(@RequestBody ZengDto body){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		try {

			result =userIntegralsService.findIntegralsZeng(body);
			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			log.error(e.getMessage());
		}
		return result;
	}
	/**
	 * 页面显示其他金额的算法
	 * @param body
	 * @return
	 */
	@PostMapping("search/qzeng")
	public CommonDto<Map<String,Object>> findIntegralsZeng(@RequestBody QzengDto body){
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		try {

			result =userIntegralsService.findIntegralsQzeng(body);
			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			log.error(e.getMessage());
		}
		return result;
	}
	/**
	 * 查找交易明细
	 * @param body
	 * @return
	 */
	@PostMapping("search/detailed")
	public CommonDto<List<Map<String,Object>>> findIntegralsDetailed(@RequestBody MingDto body){
		CommonDto<List<Map<String,Object>>> result = new CommonDto<List<Map<String,Object>>>();
		Integer pageNum = body.getPageNum();
		Integer pageSize = body.getPageSize();
		try {
			//初始化分页信息
			if(pageNum == null){
				pageNum = 1;
			}
			if(pageSize == null){
				pageSize = 20;
			}
			String uuids =body.getUuids();
			result = userIntegralsService.findIntegralsDetailed(uuids,pageNum,pageSize);

			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			log.error(e.getMessage());
		}
		return result;
	}
	/**
	 * 查询页面金额
	 * @return
	 */
	@PostMapping("search/money")
	public CommonDto <List<MetaObtainIntegral>>findMoney(){
		CommonDto <List<MetaObtainIntegral>> result = new CommonDto <List<MetaObtainIntegral>>();
		try {

			result =userIntegralsService.findMoney();
			if(result.getStatus() == null){
				result.setStatus(200);
				result.setMessage("success");
			}
		} catch (Exception e) {
			result.setStatus(5101);
			result.setMessage("显示页面异常，请稍后再试");
			log.error(e.getMessage());
		}
		return result;

	}

//	@PostMapping("integrals/save")
//	public CommonDto<String> insertGold(@RequestBody QzengDto body){
//		CommonDto<String> result = new CommonDto<String>();
//		String uuids = body.getUuids();
//		Integer qj = body.getQj();
//		try {
//			result =userIntegralsService.insertGold(uuids, qj);
//			if(result.getStatus() == null){
//				result.setStatus(200);
//				result.setMessage("success");
//			}
//		} catch (Exception e) {
//			result.setStatus(5101);
//			result.setMessage("显示页面异常，请稍后再试");
//			log.error(e.getMessage());
//		}
//		return result;
//
//	}

}
