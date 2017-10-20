package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.MemberDto;
import com.lhjl.tzzs.proxy.dto.QzengDto;
import com.lhjl.tzzs.proxy.dto.YnumDto;
import com.lhjl.tzzs.proxy.dto.ZengDto;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;

import javassist.expr.NewArray;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserIntegralsServiceImpl implements UserIntegralsService {
	@Resource
	private UserIntegralsMapper userIntegralsMapper;

	@Resource
	private UsersMapper usersMapper;

	@Resource
	private UserLevelRelationMapper userLevelRelationMapper;
	@Resource
	private MetaSceneMapper metaSceneMapper;
	@Resource
	private MetaObtainIntegralMapper metaObtainIntegralMapper;
	@Resource
	private  UserMoneyRecordMapper userMoneyRecordMapper;

	/**
	 * 查询余额的接口
	 */
	@Override
	public CommonDto<Map<String,Integer>> findIntegralsY( YnumDto body) {
		CommonDto<Map<String, Integer>> result = new CommonDto<Map<String, Integer>>();
		Map<String,Integer> map =new HashMap<String,Integer>();
		String uuids = body.getUuids();
		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=null){
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
	 * 页面显示固定金额
	 * @param body
	 * @return
	 */
	@Override
	public CommonDto<Map<String,Object>> findIntegralsZeng(ZengDto body) {
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		Map<String,Object>map =new HashMap<String,Object>();
		String uuids = body.getUuids();
		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=0 && userId !=null){
			//获取用户id
			Integer leId =usersMapper.findByUserid(userId);
			if(leId != null){
				Float bei =usersMapper.findByBei(leId);
				String skey =body.getsKey();
				//下个等级
				leId=leId+1;
				Float xbei =usersMapper.findByBei(leId);
				Integer dnum = usersMapper.findByJinE(skey);
				if(skey !=null){
					if("okuF3LQg".equals(skey)){
						map.put("dnum",dnum);
						map.put("snum",(int) (bei*dnum));
						map.put("hnum",(int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("wvGw5Fh5".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("watiSqHQ".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("yMQ8UfyU".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("gLzc1hRF".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("wvTAMN5e".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("6IigxtMh".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("tfoguHA1".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
				}
			}else{
				leId=0;
				Float bei =usersMapper.findByBei(leId+1);
				String skey =body.getsKey();
				Integer dnum = usersMapper.findByJinE(skey);
				if(skey !=null){
					if("okuF3LQg".equals(skey)){
						map.put("dnum",dnum);
						map.put("snum",(int) (bei*dnum));
						map.put("hnum",(int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
					if("wvGw5Fh5".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("watiSqHQ".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("yMQ8UfyU".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("gLzc1hRF".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("wvTAMN5e".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
					if("6IigxtMh".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
					if("tfoguHA1".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						userMoneyRecord.setMoney(dnum);
						userMoneyRecord.setUserId(userId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
				}

			}

		}else{
			result.setStatus(5013);
			result.setMessage("你没有此权限，请完善资料");
		}
		result.setData(map);
		return result;
	}
	/**
	 * 其他金额充值页面显示
	 */
	public CommonDto<Map<String,Object>> findIntegralsQzeng(QzengDto body) {
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		Map<String,Object> map =new HashMap<String,Object>();
		String uuids = body.getUuids();
		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=0 && userId !=null){
			Integer leId =usersMapper.findByUserid(userId);
			if(leId !=null){
				Float bei =usersMapper.findByBei(leId);
				Integer dnum = body.getQj();
				if(dnum>=100){
					map.put("dnum",dnum);
					map.put("snum",(int)(bei*dnum));
					map.put("hnum",(int)(((bei+1)*dnum)));
					System.out.println(bei);
					System.out.println(dnum);
					if(leId<4){
						leId=leId+1;
						Float xbei =usersMapper.findByBei(leId);
						String userName = usersMapper.findByUserLevel(leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
					}else{
						result.setStatus(5000);
						result.setMessage("您已经是VIP投资人");
					}
					UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
					userMoneyRecord.setCreateTime(new Date());
					userMoneyRecord.setMoney(dnum);
					userMoneyRecord.setUserId(userId);
					userMoneyRecordMapper.insert(userMoneyRecord);
					map.put("Money_ID",userMoneyRecord.getId());
				}
				else{
					result.setStatus(5019);
					result.setMessage("充值金额不能小于100元");
				}
			}else{
				leId=0;
				Float bei =usersMapper.findByBei(leId+1);
				Integer dnum = body.getQj();
				if(dnum>=100){
					map.put("dnum",dnum);
					map.put("snum",(int)(bei*dnum));
					map.put("hnum",(int)(((bei+1)*dnum)));
					String userName = usersMapper.findByUserLevel(leId+1);
					leId=leId+1;
					Float xbei =usersMapper.findByBei(leId);
					map.put("xnum",(int) (xbei*dnum));
					map.put("userName",userName);
					UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
					userMoneyRecord.setCreateTime(new Date());
					userMoneyRecord.setMoney(dnum);
					userMoneyRecord.setUserId(userId);
					userMoneyRecordMapper.insert(userMoneyRecord);
					map.put("Money_ID",userMoneyRecord.getId());

				}
				else{
					result.setStatus(5019);
					result.setMessage("充值金额不能小于100元");
				}
			}
		}else{
			result.setStatus(5013);
			result.setMessage("你没有此权限，请完善资料");
		}
		result.setData(map);
		return result;
	}
	/**
	 * 查询交易明细接口
	 */
	public  CommonDto<List<Map<String, Object>>> findIntegralsDetailed(String uuids,Integer pageNum,Integer pageSize) {
		CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
		List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
		Map<String, Object> map =new HashMap<String, Object>();
		Integer userId= usersMapper.findByUuid(uuids);
		Integer beginNum = (pageNum-1)*pageSize;
		//最多返回100条记录
        /*if(beginNum > 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }else{
            pageSize = (100 - beginNum)>=pageSize?pageSize:(100-beginNum);
        }*/
		if(userId !=0 && userId !=null){
			list=userLevelRelationMapper.findByMing(userId, beginNum, pageSize);
			Integer leId =usersMapper.findByUserid(userId);
			if(leId !=null){
				Float bei =usersMapper.findByBei(leId);
				for (Map<String, Object> obj : list){
					UserIntegrals u =new  UserIntegrals();
					if(Integer.valueOf(String.valueOf(obj.get("integral_num")))>0){
						if("xHwofbNs".equals(String.valueOf(obj.get("scene_key")))){

							Integer dnum =Integer.valueOf(String.valueOf(obj.get("integral_num")));
							obj.put("dnum","充值"+dnum+"元");
							obj.put("hnum","+"+(int)((bei+1)*dnum));
							obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
							obj.put("end_time",String.valueOf(obj.get("end_time")).substring(0,10));
						}else{
							Integer dnum=Integer.valueOf(String.valueOf(obj.get("integral_num")));
							//Integer dnum1=(int)(hnum*(1-bei));
							obj.put("dnum","+"+dnum);
							obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
							obj.put("end_time",String.valueOf(obj.get("end_time")).substring(0,10));
							String skey =String.valueOf(obj.get("scene_key"));
							String userName = metaSceneMapper.selectbyDesc(skey);
							obj.put("userName",userName);
						}
					}else{
						String skey =String.valueOf(obj.get("scene_key"));
						String userName = metaSceneMapper.selectbyDesc(skey);
						obj.put("userName",userName);
						Integer dnum =Integer.valueOf(String.valueOf(obj.get("integral_num")));
						obj.put("dnum",dnum);
						obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
						obj.put("end_time",null);
					}
				}
			}else{
				leId=1;
				Float bei =usersMapper.findByBei(leId);
				for (Map<String, Object> obj : list){
					UserIntegrals u =new  UserIntegrals();
					if(Integer.valueOf(String.valueOf(obj.get("integral_num")))>0){
						if("xHwofbNs".equals(String.valueOf(obj.get("scene_key")))){

							Integer dnum =Integer.valueOf(String.valueOf(obj.get("integral_num")));
							obj.put("dnum","充值"+dnum+"元");
							obj.put("hnum","+"+(int)((bei+1)*dnum));
							obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
							obj.put("end_time",String.valueOf(obj.get("end_time")).substring(0,10));
						}else{
							Integer dnum=Integer.valueOf(String.valueOf(obj.get("integral_num")));
							//Integer dnum1=(int)(hnum*(1-bei));
							obj.put("dnum","+"+dnum);
							obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
							obj.put("end_time",String.valueOf(obj.get("end_time")).substring(0,10));
							String skey =String.valueOf(obj.get("scene_key"));
							String userName = metaSceneMapper.selectbyDesc(skey);
							obj.put("userName",userName);
						}
					}else{
						String skey =String.valueOf(obj.get("scene_key"));
						String userName = metaSceneMapper.selectbyDesc(skey);
						obj.put("userName",userName);
						Integer dnum =Integer.valueOf(String.valueOf(obj.get("integral_num")));
						obj.put("dnum",dnum);
						obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
						obj.put("end_time",null);
					}
				}

			}

		}
		if(list.size() <= 0){
			result.setStatus(202);
			result.setMessage("无查询数据");
			return result;
		}
		list.add(map);
		result.setData(list);
		return result;
	}
	/**
	 * 购买金币后插入金币记录表记录
	 * uuids：用户的uuid
	 * qj:支付的金额
	 */
	@Transactional
	public CommonDto<String> insertGold(String uuids,Integer qj) {
		CommonDto<String> result = new CommonDto<String>();
		Map<String,Integer> map =new HashMap<String,Integer>();
		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=0 && userId !=null){
			Integer leId =usersMapper.findByUserid(userId);
			if(leId !=null){
				Float bei =usersMapper.findByBei(leId);
				UserIntegrals userIntegrals =new UserIntegrals();
				userIntegrals.setUserId(userId);
				userIntegrals.setSceneKey("xHwofbNs");
				userIntegrals.setIntegralNum(qj);
				userIntegrals.setCreateTime(new Date());
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new Date());

				//获取该场景配置信息
				MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
				metaObtainIntegral.setSceneKey("xHwofbNs");
				metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
				calendar.add(Calendar.DAY_OF_YEAR,metaObtainIntegral.getPeriod());
				Date end = calendar.getTime();
				userIntegrals.setEndTime(end);
				userIntegrals.setBeginTime((new Date()));
				userIntegralsMapper.insert(userIntegrals);
				//购买金币赠送的记录
				UserIntegrals userIntegrals2 =new UserIntegrals();
				userIntegrals2.setUserId(userId);
				String sKey = userIntegralsMapper.findBySkey(leId);
				userIntegrals2.setSceneKey(sKey);
				Integer snum =(int)(qj*bei);
				userIntegrals2.setIntegralNum(snum);
				userIntegrals2.setCreateTime(new Date());
				MetaObtainIntegral metaObtainIntegral1 = new MetaObtainIntegral();
				metaObtainIntegral1.setSceneKey(sKey);
				Calendar calendar1 = new GregorianCalendar();
				metaObtainIntegral1 = metaObtainIntegralMapper.selectOne(metaObtainIntegral1);
				calendar1.add(Calendar.DAY_OF_YEAR, metaObtainIntegral1.getPeriod());
				Date end1 = calendar.getTime();
				userIntegrals2.setEndTime(end1);
				userIntegrals2.setBeginTime((new Date()));
				userIntegralsMapper.insert(userIntegrals2);
			}else{
				leId=0;
				Float bei =usersMapper.findByBei(leId+1);
				UserIntegrals userIntegrals =new UserIntegrals();
				userIntegrals.setUserId(userId);
				userIntegrals.setSceneKey("xHwofbNs");
				userIntegrals.setIntegralNum(qj);
				userIntegrals.setCreateTime(new Date());
				//时间场景
				Calendar calendar = new GregorianCalendar();
				MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
				metaObtainIntegral.setSceneKey("xHwofbNs");
				metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
				calendar.add(Calendar.DAY_OF_YEAR,metaObtainIntegral.getPeriod());
				Date end = calendar.getTime();
				userIntegrals.setEndTime(end);
				userIntegrals.setBeginTime((new Date()));
				userIntegralsMapper.insert(userIntegrals);
				//购买金币赠送的记录
				UserIntegrals userIntegrals2 =new UserIntegrals();
				userIntegrals2.setUserId(userId);
				String sKey = userIntegralsMapper.findBySkey(leId+1);
				userIntegrals2.setSceneKey(sKey);
				Integer snum =(int)(qj*bei);
				userIntegrals2.setIntegralNum(snum);
				userIntegrals2.setCreateTime(new Date());
				//时间场景
				MetaObtainIntegral metaObtainIntegral1 = new MetaObtainIntegral();
				metaObtainIntegral1.setSceneKey(sKey);
				Calendar calendar1 = new GregorianCalendar();
				metaObtainIntegral1 = metaObtainIntegralMapper.selectOne(metaObtainIntegral1);
				calendar1.add(Calendar.DAY_OF_YEAR, metaObtainIntegral1.getPeriod());
				Date end1 = calendar.getTime();
				userIntegrals2.setEndTime(end1);
				userIntegrals2.setBeginTime((new Date()));
				userIntegralsMapper.insert(userIntegrals2);

			}
		}
		return result;
	}


	/**
	 * 生成会员的java接口
	 * @param body
	 * @return
	 */
	@Transactional
	@Override
	public CommonDto<String> insertMember1(MemberDto body) {
		CommonDto<String> result = new CommonDto<String>();
		Users users =new Users();
		users.setUuid(body.getUuids());
		List<Users> list = usersMapper.select(users);
		if(list.size()<=0){
			users.setUuid(body.getUuids());
			users.setCreateTime(new Date());
			users.setStatus(0);
			usersMapper.insert(users);
		}else{
			result.setStatus(204);
			result.setMessage("您已经注册过");
		}
		return result;
	}
/**
 * 查询页面固定金额
 */
	@Override
	public CommonDto <List<MetaObtainIntegral>>findMoney(){
		CommonDto <List<MetaObtainIntegral>> result = new CommonDto <List<MetaObtainIntegral>>();
		List<MetaObtainIntegral> list =new ArrayList<MetaObtainIntegral>();
		List<MetaObtainIntegral> list2 =new ArrayList<MetaObtainIntegral>();
		MetaObtainIntegral obtainIntegral =new MetaObtainIntegral();
		list = metaObtainIntegralMapper.select(obtainIntegral);
		for(MetaObtainIntegral o : list){
			if(o.getUserLevel() == null){
				if(o.getIntegral()!=null){
					list2.add(o);
				}
			}
		}
		result.setData(list2);
		return result;
	}
}