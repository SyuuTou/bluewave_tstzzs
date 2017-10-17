package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.QzengDto;
import com.lhjl.tzzs.proxy.dto.YnumDto;
import com.lhjl.tzzs.proxy.dto.ZengDto;
import com.lhjl.tzzs.proxy.mapper.UserIntegralsMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;

@Service
public class UserIntegralsServiceImpl implements UserIntegralsService {
	@Resource
	private UserIntegralsMapper userIntegralsMapper;
	
    @Resource
    private UsersMapper usersMapper;
    /**
     * 查询余额的接口
     */
	@Override
	public CommonDto<Map<String,Integer>> findIntegralsY( YnumDto body) {
		CommonDto<Map<String, Integer>> result = new CommonDto<Map<String, Integer>>();
		Map<String,Integer> map =new HashMap<String,Integer>();
	    String uuids = body.getUuids();
	    Integer userId= usersMapper.findByUuid(uuids);
	    if(userId !=0 && userId !=null){
		Integer z =userIntegralsMapper.findIntegralsZ(userId);
		Integer x =userIntegralsMapper.findIntegralsX(userId);
		int y=z+x;
		map.put("ynum",y);
		}else{
			result.setStatus(5012);
			result.setMessage("该用户不存在");
		}
		result.setData(map);
		return result;
	}
	/**
	 * 查询充值金币的接口
	 */
	@Override
	public CommonDto<Map<String,Integer>> findIntegralsZeng(ZengDto body) {
		CommonDto<Map<String,Integer>> result = new CommonDto<Map<String,Integer>>();
		Map<String,Integer> map =new HashMap<String,Integer>();
	    String uuids = body.getUuids();
	    Integer userId= usersMapper.findByUuid(uuids);
	    if(userId !=0 && userId !=null){
	    	 Integer leId =usersMapper.findByUserid(userId);
	    	 if(leId !=0 && leId !=null){
	    		 Float bei =usersMapper.findByBei(leId);
	    		 leId=leId+1;
	    		 Float xbei =usersMapper.findByBei(leId);
	    		 
	    		 String skey =body.getsKey();
	    		 Integer dnum = usersMapper.findByJinE(skey);
	    		 if(skey !=null){
	    			 if("okuF3LQg".equals(skey)){
	    				 map.put("dnum",dnum);
	    				 map.put("snum",(int) (bei*dnum));
	    				 map.put("hnum",(int) ((bei+1)*dnum));
	    				 if(leId<5){
	    				 map.put("xnum",(int) (xbei*dnum));
	    				 }else{
	    					 result.setStatus(5000);
	    			 		 result.setMessage("您已经是VIP投资人");
	    				 }
	    				 
	    			 }
	    			 if("wvGw5Fh5".equals(skey)){
	    				 map.put("dnum", (int) dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
		    				 map.put("xnum",(int) (xbei*dnum));
		    				 }else{
		    					 result.setStatus(5000);
		    			 		 result.setMessage("您已经是VIP投资人");
		    				 }
		    				 
	    				 
	    			 }
	    			 if("watiSqHQ".equals(skey)){
	    				 map.put("dnum",  dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
		    				 map.put("xnum",(int) (xbei*dnum));
		    				 }else{
		    					 result.setStatus(5000);
		    			 		 result.setMessage("您已经是VIP投资人");
		    				 }
		    				 
	    				 
	    			 }
	    			 if("yMQ8UfyU".equals(skey)){
	    				 map.put("dnum", dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
		    				 map.put("xnum",(int) (xbei*dnum));
		    				 }else{
		    					 result.setStatus(5000);
		    			 		 result.setMessage("您已经是VIP投资人");
		    				 }
		    				 
	    				 
	    			 }
	    			 if("gLzc1hRF".equals(skey)){
	    				 map.put("dnum", (int) dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
		    				 map.put("xnum",(int) (xbei*dnum));
		    				 }else{
		    					 result.setStatus(5000);
		    			 		 result.setMessage("您已经是VIP投资人");
		    				 }
		    				 
	    				 
	    			 }
	    			 if("wvTAMN5e".equals(skey)){
	    				 map.put("dnum",  dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
		    				 map.put("xnum",(int) (xbei*dnum));
		    				 }else{
		    					 result.setStatus(5000);
		    			 		 result.setMessage("您已经是VIP投资人");
		    				 }
		    				 
	    				 
	    			 }
	    			 if("6IigxtMh".equals(skey)){
	    				 map.put("dnum", (int) dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
		    				 map.put("xnum",(int) (xbei*dnum));
		    				 }else{
		    					 result.setStatus(5000);
		    			 		 result.setMessage("您已经是VIP投资人");
		    				 }
		    				 
	    				 
	    			 }
	    			 if("tfoguHA1".equals(skey)){
	    				 map.put("dnum", dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
		    				 map.put("xnum",(int) (xbei*dnum));
		    				 }else{
		    					 result.setStatus(5000);
		    			 		 result.setMessage("您已经是VIP投资人");
		    				 }
		    				 
	    			 }
	    			
	    		 }
	    	 }else{
	    		result.setStatus(5013);
	 			result.setMessage("你没有此权限，请完善资料"); 
	    	 }
	    }
		result.setData(map);
		return result;
	}
	public CommonDto<Map<String,Integer>> findIntegralsQzeng(QzengDto body) {
		CommonDto<Map<String,Integer>> result = new CommonDto<Map<String,Integer>>();
		Map<String,Integer> map =new HashMap<String,Integer>();
	    String uuids = body.getUuids();
	    Integer userId= usersMapper.findByUuid(uuids);
	    if(userId !=0 && userId !=null){
	    	 Integer leId =usersMapper.findByUserid(userId);
	    	 if(leId !=0 && leId !=null){
	    		 Float bei =usersMapper.findByBei(leId);
	    		 leId=leId+1;
	    		 Float xbei =usersMapper.findByBei(leId);
	    		 Integer dnum = body.getQj();
	    		 if(dnum>100){
	    				 map.put("dnum",dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
	    				 map.put("xnum",(int) (xbei*dnum));
	    				 }else{
	    					 result.setStatus(5000);
	    			 		 result.setMessage("您已经是VIP投资人");
	    				 }
	    		 }
	    		 else{
	    			 result.setStatus(5019);
	 	 			result.setMessage("充值金额不能小于100元"); 
	    		 }
	     
	    	 }else{
	    		result.setStatus(5013);
	 			result.setMessage("你没有此权限，请完善资料"); 
	    	 }
	    }
		result.setData(map);
		return result;
	}
	public  CommonDto<List<Map<String, Object>>> findIntegralsMing(String uuids,Integer pageNum,Integer pageSize) {
		CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
		List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
		Map<String,Integer> map =new HashMap<String,Integer>();
	    Integer userId= usersMapper.findByUuid(uuids);
	    if(userId !=0 && userId !=null){
	    	 Integer leId =usersMapper.findByUserid(userId);
	    	 if(leId !=0 && leId !=null){
	    		 Float bei =usersMapper.findByBei(leId);
	    		 leId=leId+1;
	    		 Float xbei =usersMapper.findByBei(leId);
	    		 Integer dnum = body.getQj();
	    		 if(dnum>100){
	    				 map.put("dnum",dnum);
	    				 map.put("snum", (int) (bei*dnum));
	    				 map.put("hnum", (int) ((bei+1)*dnum));
	    				 if(leId<5){
	    				 map.put("xnum",(int) (xbei*dnum));
	    				 }else{
	    					 result.setStatus(5000);
	    			 		 result.setMessage("您已经是VIP投资人");
	    				 }
	    		 }
	    		 else{
	    			 result.setStatus(5019);
	 	 			result.setMessage("充值金额不能小于100元"); 
	    		 }
	     
	    	 }else{
	    		result.setStatus(5013);
	 			result.setMessage("你没有此权限，请完善资料"); 
	    	 }
	    }
		result.setData(map);
		return result;
	}



}
