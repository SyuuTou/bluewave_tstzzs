package com.lhjl.tzzs.proxy.controller;



import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.MingDto;
import com.lhjl.tzzs.proxy.dto.QzengDto;
import com.lhjl.tzzs.proxy.dto.ShuruDto;
import com.lhjl.tzzs.proxy.dto.YnumDto;
import com.lhjl.tzzs.proxy.dto.ZengDto;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;

import java.util.List;
import java.util.Map;

@RestController
public class UserIntegralsController {
	  private static final Logger log = LoggerFactory.getLogger(UserIntegralsController.class);

	    @Resource
	    private UserIntegralsService userIntegralsService;
	    /**
	     * 查询余额
	     * @param body
	     * @return
	     */
	    @PostMapping("search/ynum")
	    public CommonDto<Map<String,Integer>> findIntegralsY(@RequestBody YnumDto body){
	    	CommonDto<Map<String, Integer>> result = new CommonDto<Map<String, Integer>>();
	    	 try {
	             result =userIntegralsService.findIntegralsY(body);
	             result.setStatus(200);
	             result.setMessage("success");
	         } catch (Exception e) {
	             result.setStatus(5101);
	             result.setMessage("项目显示页面异常，请稍后再试");
	             log.error(e.getMessage());
	         }
	        return result;
	    }
	    /**
	     * 查询充值金币数量
	     * @param body
	     * @return
	     */
	    @PostMapping("search/zeng")
	    public CommonDto<Map<String,Integer>> findIntegralsZeng(@RequestBody ZengDto body){
	    	CommonDto<Map<String,Integer>> result = new CommonDto<Map<String,Integer>>();
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
	    @PostMapping("search/qzeng")
	    public CommonDto<Map<String,Integer>> findIntegralsZeng(@RequestBody QzengDto body){
	    	CommonDto<Map<String,Integer>> result = new CommonDto<Map<String,Integer>>();
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
	    @PostMapping("search/ming")
	    public CommonDto<List<Map<String,Object>>> findIntegralsMing(@RequestBody MingDto body){
	    	CommonDto<List<Map<String,Object>>> result = new CommonDto<List<Map<String,Object>>>();
	        Integer pageNum = body.getPageNum();
	        Integer pageSize = body.getPageSize();
	        pageNum=1;
	        pageSize=20;
	    	  
	    	        try {
	    	            //初始化分页信息
	    	            if(pageNum == null){
	    	                pageNum = 1;
	    	            }
	    	            if(pageSize == null){
	    	                pageSize = 20;
	    	            }
	    	            String uuids =body.getUuids();
	    	            result = userIntegralsService.findIntegralsMing(uuids,pageNum,pageSize);
	    		 
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
	    

}
