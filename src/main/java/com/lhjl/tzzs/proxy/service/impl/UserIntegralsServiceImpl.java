package com.lhjl.tzzs.proxy.service.impl;

import java.math.BigDecimal;
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
import com.lhjl.tzzs.proxy.service.UserLevelService;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.MemberDto;
import com.lhjl.tzzs.proxy.dto.QzengDto;
import com.lhjl.tzzs.proxy.dto.YnumDto;
import com.lhjl.tzzs.proxy.dto.ZengDto;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;

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
	@Resource
	private UserLevelService userLevelService;
	@Resource
	private UserIntegralConsumeMapper userIntegralConsumeMapper;

	//购买会员等级场景
	private static final String ONE = "nEBlAOV9";
	private static final String TWO = "aMvVjSju";
	private static final String THREE = "y4Ep6YQT";
	private static final String FOUR = "N4VlBBJP";

	/**
	 * 查询余额的接口
	 */
	@Override
	public CommonDto<Map<String,Integer>> findIntegralsY(Integer appId, YnumDto body) {
		CommonDto<Map<String, Integer>> result = new CommonDto<Map<String, Integer>>();
		Map<String,Integer> map =new HashMap<String,Integer>();
		String uuids = body.getUuids();
		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=null){
			/*Integer z =userIntegralsMapper.findIntegralsZ(userId);
			Integer x =userIntegralsMapper.findIntegralsX(userId);*/
			Map<String, Object> u = userIntegralsMapper.findIntegralsU(appId,userId);
			BigDecimal xnum = (BigDecimal) u.get("xnum");
			BigDecimal znum = (BigDecimal) u.get("znum");
			Integer z = xnum.intValue();
			Integer x = znum.intValue();
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
	 *
	 * @param appId
	 * @param body
	 * @return
	 */
	@Override
	public CommonDto<Map<String,Object>> findIntegralsZeng(Integer appId, ZengDto body) {
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		Map<String,Object>map =new HashMap<String,Object>();
		String uuids = body.getUuids();
		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=0 && userId !=null){
			//获取用户id
			Integer leId =usersMapper.findByUserid(userId, appId);
			if(leId != null){
				Float bei =usersMapper.findByBei(appId,leId);
				String skey =body.getsKey();
				//下个等级
				leId=leId+1;
				Float xbei =usersMapper.findByBei(appId, leId);
				double dnum = usersMapper.findByJinE(appId,skey);
				if(skey !=null){
					if("okuF3LQg".equals(skey)){
						map.put("dnum",dnum);
						map.put("snum",(int) (bei*dnum));
						map.put("hnum",(int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}

						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("wvGw5Fh5".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("watiSqHQ".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("yMQ8UfyU".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("gLzc1hRF".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("wvTAMN5e".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());


					}
					if("6IigxtMh".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
					if("tfoguHA1".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						if(leId<5){
							String userName = usersMapper.findByUserLevel(appId,leId);
							map.put("xnum",(int) (xbei*dnum));
							map.put("userName",userName);
						}else{
							result.setStatus(5000);
							result.setMessage("您已经是VIP投资人");
						}
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
				}
			}else{
				leId=0;
				Float bei =usersMapper.findByBei(appId, leId+1);
				String skey =body.getsKey();
				Integer dnum = usersMapper.findByJinE(appId,skey);
				if(skey !=null){
					if("okuF3LQg".equals(skey)){
						map.put("dnum",dnum);
						map.put("snum",(int) (bei*dnum));
						map.put("hnum",(int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
					if("wvGw5Fh5".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("watiSqHQ".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("yMQ8UfyU".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("gLzc1hRF".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());

					}
					if("wvTAMN5e".equals(skey)){
						map.put("dnum",  dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
					if("6IigxtMh".equals(skey)){
						map.put("dnum", (int) dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
						userMoneyRecordMapper.insert(userMoneyRecord);
						map.put("Money_ID",userMoneyRecord.getId());
					}
					if("tfoguHA1".equals(skey)){
						map.put("dnum", dnum);
						map.put("snum", (int) (bei*dnum));
						map.put("hnum", (int) ((bei+1)*dnum));
						map.put("xnum",(int) (bei*dnum));
						String userName = usersMapper.findByUserLevel(appId,leId+1);
						leId=leId+1;
						Float xbei =usersMapper.findByBei(appId, leId);
						map.put("xnum",(int) (xbei*dnum));
						map.put("userName",userName);
						UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
						userMoneyRecord.setCreateTime(new Date());
						BigDecimal jnum =new BigDecimal(dnum);
						userMoneyRecord.setMoney(jnum );
						userMoneyRecord.setSceneKey(skey);
						userMoneyRecord.setUserId(userId);
						userMoneyRecord.setAppId(appId);
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
	 * 重构版页面显示选择值的接口
	 * @param appId
	 * @param body
	 * @return
	 */
	@Override
	public CommonDto<Map<String, Object>> recordUserPayAmount(Integer appId, ZengDto body) {

		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		Map<String,Object> map =new HashMap<String,Object>();


		//验证其他充值金额
		if (null != body.getQj()){
			CommonDto<Map<String,Object>> resulta =verifyLimitCount(body.getQj());
			if (resulta.getStatus() != 200){
				return resulta;
			}
		}

		String uuids = body.getUuids();
		Integer userId= usersMapper.findByUuid(uuids);

		if (null == userId || 0==userId){
			return new CommonDto<>(null,"你没有此权限，请完善资料",5013);
		}

		if (null == body.getsKey()){
			return new CommonDto<>(null,"请传入场景key",5013);
		}

		//获取当前用户等级
		Integer userLevelId =usersMapper.findByUserid(userId, appId);

		if (null == userLevelId){
			userLevelId = 1;
		}

		float discount = usersMapper.findByBei(appId,userLevelId);//折扣
		String sceneKey = body.getsKey();//场景key


		//查对应场景key是否存在
		MetaScene metaScene = new MetaScene();
		metaScene.setKey(sceneKey);

		List<MetaScene> metaSceneList = metaSceneMapper.select(metaScene);

		if (metaSceneList.size() < 1){
			return new CommonDto<>(null,"场景key无效",5013);
		}

		//不同充值方式取金额方式也不同
		Integer integral = 0;
		if (null != body.getQj()){
			integral = body.getQj().intValue();
		}else{
			integral = usersMapper.findByJinE(appId,sceneKey);//当前场景对应积分数量
		}


		map.put("dnum",integral);
		map.put("snum",(int) (discount*integral));
		map.put("hnum",(int) ((discount+1)*integral));
		map.put("xnum",(int) (discount*integral));
		if (userLevelId < 4){
			String userName = usersMapper.findByUserLevel(appId,userLevelId+1);
			Float nextDiscount =usersMapper.findByBei(appId, userLevelId+1);
			map.put("xnum",(int) (nextDiscount*integral));
			map.put("userName",userName);//会员名称
		}else {
			result.setStatus(5000);
			result.setMessage("您已经是VIP投资人");
		}

		UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
		userMoneyRecord.setCreateTime(new Date());
		BigDecimal jnum =new BigDecimal(integral);
		userMoneyRecord.setMoney(jnum );
		userMoneyRecord.setSceneKey(sceneKey);
		userMoneyRecord.setUserId(userId);
		userMoneyRecord.setAppId(appId);
		userMoneyRecordMapper.insert(userMoneyRecord);
		map.put("Money_ID",userMoneyRecord.getId());

		result.setMessage("success");
		result.setData(map);
		result.setStatus(200);

		return result;
	}


	/**
	 * 其他金额充值页面显示
	 */
	public CommonDto<Map<String,Object>> findIntegralsQzeng(Integer appId, QzengDto body) {
		CommonDto<Map<String,Object>> result = new CommonDto<Map<String,Object>>();
		Map<String,Object> map =new HashMap<String,Object>();
		String uuids = body.getUuids();
		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=0 && userId !=null){
			Integer leId =usersMapper.findByUserid(userId, appId);
			if(leId !=null) {
                Float bei = usersMapper.findByBei(appId, leId);
                BigDecimal dnum = body.getQj();
                Integer dnum1 = dnum.intValue();
                if (dnum1 >= 188) {
                    map.put("dnum", dnum);

                    map.put("snum", (int) (bei * dnum1));
                    map.put("hnum", (int) (((bei + 1) * dnum1)));
                    System.out.println(bei);
                    System.out.println(dnum);
                    if (leId < 4) {
                        leId = leId + 1;
                        Float xbei = usersMapper.findByBei(appId, leId);
                        String userName = usersMapper.findByUserLevel(appId,leId);
                        map.put("xnum", (int) (xbei * dnum1));
                        map.put("userName", userName);
                    } else {
                        result.setStatus(5000);
                        result.setMessage("您已经是VIP投资人");
                    }
                    UserMoneyRecord userMoneyRecord = new UserMoneyRecord();
                    userMoneyRecord.setCreateTime(new Date());
                    userMoneyRecord.setMoney(dnum);
                    userMoneyRecord.setSceneKey("dpGXuIqb");
                    userMoneyRecord.setUserId(userId);
					userMoneyRecord.setAppId(appId);
                    userMoneyRecordMapper.insert(userMoneyRecord);
                    map.put("Money_ID", userMoneyRecord.getId());
                } else {
                    result.setStatus(5019);
                    result.setMessage("充值金额不能小于188元");
                }
			}else{
				leId=0;
				Float bei =usersMapper.findByBei(appId, leId+1);
				BigDecimal dnum = body.getQj();
                Integer dnum1=dnum.intValue();
				if(dnum1>=188){
					map.put("dnum",dnum);

					map.put("snum",(int)(bei*dnum1));
					map.put("hnum",(int)(((bei+1)*dnum1)));
					String userName = usersMapper.findByUserLevel(appId,leId+1);
					leId=leId+1;
					Float xbei =usersMapper.findByBei(appId, leId);
					map.put("xnum",(int) (xbei*dnum1));
					map.put("userName",userName);
					UserMoneyRecord userMoneyRecord =new UserMoneyRecord();
					userMoneyRecord.setCreateTime(new Date());
					userMoneyRecord.setMoney(dnum);
					userMoneyRecord.setSceneKey("dpGXuIqb");
					userMoneyRecord.setUserId(userId);
					userMoneyRecord.setAppId(appId);
					userMoneyRecordMapper.insert(userMoneyRecord);
					map.put("Money_ID",userMoneyRecord.getId());
				}
				else{
					result.setStatus(5019);
					result.setMessage("充值金额不能小于188元");
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
	public  CommonDto<List<Map<String, Object>>> findIntegralsDetailed(Integer appId, String uuids, Integer pageNum, Integer pageSize) {
		CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
		List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
		Map<String, Object> map =new HashMap<>();
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
			list=userLevelRelationMapper.findByMing(appId,userId, beginNum, pageSize);
			Integer leId =usersMapper.findByUserid(userId, appId);
			if(leId !=null){
				Float bei =usersMapper.findByBei(appId, leId);
				for (Map<String, Object> obj : list){
					//UserIntegralConsume u =new  UserIntegralConsume();
					if(new BigDecimal(obj.get("cost_num").toString()).compareTo(new BigDecimal(0))>=0){
						if("xHwofbNs".equals(String.valueOf(obj.get("scene_key")))){
							BigDecimal dnum =new BigDecimal(obj.get("cost_num").toString());
							obj.put("dnum","充值"+dnum+"元");
							BigDecimal hnum =new BigDecimal(obj.get("num").toString());
							obj.put("hnum","+"+hnum);
							obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
							obj.put("end_time",String.valueOf(obj.get("end_time")).substring(0,10));
							obj.put("userName",null);
						}else{
							BigDecimal dnum =new BigDecimal(obj.get("cost_num").toString());
							//Integer dnum1=(int)(hnum*(1-bei));
							obj.put("dnum","+"+dnum);
							obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
							obj.put("end_time",String.valueOf(obj.get("end_time")).substring(0,10));
							String skey =String.valueOf(obj.get("scene_key"));
							/*String userName = metaSceneMapper.selectbyDesc(skey);
							obj.put("userName",userName);*/
							MetaScene m =new MetaScene();
							m.setKey(skey );
							MetaScene m1=metaSceneMapper.selectOne(m);
							obj.put("userName",  m1.getDesc());
							
						}
					}else{
						String skey =String.valueOf(obj.get("scene_key"));
						//String userName = metaSceneMapper.selectbyDesc(skey);
						//obj.put("userName",userName);
						BigDecimal dnum =new BigDecimal(obj.get("cost_num").toString());
						obj.put("dnum",dnum);
						obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
						MetaScene m =new MetaScene();
						m.setKey(skey );
						MetaScene m1=metaSceneMapper.selectOne(m);
						obj.put("userName",m1.getDesc());
						obj.put("end_time",null);
					}
				}
				/*UserIntegralConsume userIntegralConsume =new UserIntegralConsume();
				userIntegralConsume.setUserId(userId);
				List<UserIntegralConsume> list2 =userIntegralConsumeMapper.select(userIntegralConsume);

				for(UserIntegralConsume obj :list2) {
                    String skey = obj.getSceneKey();
                    //String userName = metaSceneMapper.selectbyDesc(skey);
					MetaScene m =new MetaScene();
					m.setKey(skey );
					MetaScene m1=metaSceneMapper.selectOne(m);
                    map.put("userName",m1.getDesc());
                    Integer dnum = obj.getCostNum();
                    map.put("dnum", dnum);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String startTime = sdf.format(obj.getBeginTime());
                    map.put("begin_time",startTime );
                    map.put("end_time", null);
                }*/
	
			}else{
				leId=1;
				Float bei =usersMapper.findByBei(appId, leId);
				for (Map<String, Object> obj : list) {
					UserIntegrals u = new UserIntegrals();
					if (new BigDecimal(obj.get("cost_num").toString()).compareTo(new BigDecimal(0))>=0) {
						if ("xHwofbNs".equals(String.valueOf(obj.get("scene_key")))) {
							BigDecimal dnum =new BigDecimal(obj.get("cost_num").toString());
							obj.put("dnum", "充值" + dnum + "元");
							Integer leId1 = 1;
							BigDecimal hnum =new BigDecimal(obj.get("num").toString());
//							Integer hnum =Integer.valueOf(String.valueOf(obj.get("num")));
							obj.put("hnum","+"+hnum);
							obj.put("begin_time", String.valueOf(obj.get("begin_time")).substring(0, 10));
							obj.put("end_time", String.valueOf(obj.get("end_time")).substring(0, 10));
							obj.put("userName", null);

						} else {
							BigDecimal dnum =new BigDecimal(obj.get("cost_num").toString());
							//Integer dnum1=(int)(hnum*(1-bei));
							obj.put("dnum", "+" + dnum);
							obj.put("begin_time", String.valueOf(obj.get("begin_time")).substring(0, 10));
							obj.put("end_time", String.valueOf(obj.get("end_time")).substring(0, 10));
							String skey = String.valueOf(obj.get("scene_key"));
							MetaScene m = new MetaScene();
							m.setKey(skey);
							MetaScene m1 = metaSceneMapper.selectOne(m);
							obj.put("userName", m1.getDesc());
							//String userName = metaSceneMapper.selectbyDesc(skey);
							//obj.put("userName",userName);

						}
					} else{
						String skey =String.valueOf(obj.get("scene_key"));
						//String userName = metaSceneMapper.selectbyDesc(skey);
						//obj.put("userName",userName);
						BigDecimal dnum =new BigDecimal(obj.get("cost_num").toString());
						obj.put("dnum",dnum);
						obj.put("begin_time",String.valueOf(obj.get("begin_time")).substring(0,10));
						MetaScene m =new MetaScene();
						m.setKey(skey );
						MetaScene m1=metaSceneMapper.selectOne(m);
						obj.put("userName",m1.getDesc());
						obj.put("end_time",null);
					}
				}
               /* UserIntegralConsume userIntegralConsume =new UserIntegralConsume();
                userIntegralConsume.setUserId(userId);
                List<UserIntegralConsume> list2 =userIntegralConsumeMapper.select(userIntegralConsume);
                for(UserIntegralConsume obj :list2) {
                    String skey = obj.getSceneKey();
                    //String userName = metaSceneMapper.selectbyDesc(skey);
					MetaScene m =new MetaScene();
					m.setKey(skey );
					MetaScene m1=metaSceneMapper.selectOne(m);
					map.put("userName",  m1.getDesc());
                    //map.put("userName", userName);
                    Integer dnum = obj.getCostNum();
                    map.put("dnum", dnum);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String startTime = sdf.format(obj.getBeginTime());
                    map.put("begin_time",startTime );
                    map.put("end_time", null);
                    list.add(map);
                }*/
			}
		}
		if( list.size() <= 0){
			result.setStatus(202);
			result.setMessage("无查询数据");
		}
		result.setData(list);
		return result;
	}
	/**
	 * 购买金币支付成功后插入金币记录表数据接口
	 * service接口：UserIntegralsService
	 * 方法名：insertGold
	 * 用户的uuid： uuids
	 * 支付的金额：qj
	 */
	@Transactional
	@Override
	public CommonDto<String> insertGold(Integer userId, BigDecimal qj, Integer appId) {
		CommonDto<String> result = new CommonDto<String>();
		Map<String,Integer> map =new HashMap<String,Integer>();
//		Integer userId= usersMapper.findByUuid(uuids);
		if(userId !=0 && userId !=null){
			Integer leId =usersMapper.findByUserid(userId,appId);
			if(leId !=null){
				Float bei =usersMapper.findByBei(appId, leId);
				UserIntegrals userIntegrals =new UserIntegrals();
				userIntegrals.setUserId(userId);
				userIntegrals.setAppId(appId);
				userIntegrals.setSceneKey("xHwofbNs");
				Integer jb=qj.intValue();
				if(jb>=0.01){
				userIntegrals.setConsumeNum(new BigDecimal(0));
				userIntegrals.setIntegralNum(qj);
				userIntegrals.setCreateTime(new Date());
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new Date());

				//获取该场景配置信息
				MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
				metaObtainIntegral.setSceneKey("xHwofbNs");
				metaObtainIntegral.setAppId(appId);
				metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
				calendar.add(Calendar.DAY_OF_YEAR,metaObtainIntegral.getPeriod());
				Date end = calendar.getTime();
				userIntegrals.setEndTime(end);
				userIntegrals.setBeginTime((new Date()));
				userIntegralsMapper.insert(userIntegrals);
				//购买金币赠送的记录
				UserIntegrals userIntegrals2 =new UserIntegrals();
				userIntegrals2.setUserId(userId);
				userIntegrals2.setAppId(appId);
				userIntegrals2.setConsumeNum(new BigDecimal(0));
				String sKey = userIntegralsMapper.findBySkey(appId, leId);
				userIntegrals2.setSceneKey(sKey);
				jb=qj.intValue();
				Integer snum =(int)(jb*bei);
				userIntegrals2.setIntegralNum(qj.multiply(new BigDecimal(bei)));
				userIntegrals2.setCreateTime(new Date());
				MetaObtainIntegral metaObtainIntegral1 = new MetaObtainIntegral();
				metaObtainIntegral1.setSceneKey(sKey);
                    metaObtainIntegral1.setAppId(appId);
				Calendar calendar1 = new GregorianCalendar();
				metaObtainIntegral1 = metaObtainIntegralMapper.selectOne(metaObtainIntegral1);
				calendar1.add(Calendar.DAY_OF_YEAR, metaObtainIntegral1.getPeriod());
				Date end1 = calendar.getTime();
				userIntegrals2.setEndTime(end1);
				userIntegrals2.setBeginTime((new Date()));
				userIntegralsMapper.insert(userIntegrals2);
				//插入总表数据
				UserIntegralConsume userIntegrals3=new UserIntegralConsume();
				userIntegrals3.setUserId(userId);
				userIntegrals3.setAppId(appId);
				userIntegrals3.setSceneKey("xHwofbNs");
//				Integer jb1=qj.intValue();
				//if(jb>=100){
				userIntegrals3.setCostNum(qj);
				userIntegrals3.setCreateTime(new Date());
				Calendar calendar3 = new GregorianCalendar();
				calendar3.setTime(new Date());

				//获取该场景配置信息
				MetaObtainIntegral metaObtainIntegral3 = new MetaObtainIntegral();
				metaObtainIntegral3.setSceneKey("xHwofbNs");
                    metaObtainIntegral3.setAppId(appId);
				metaObtainIntegral3 = metaObtainIntegralMapper.selectOne(metaObtainIntegral3);
				calendar3.add(Calendar.DAY_OF_YEAR,metaObtainIntegral3.getPeriod());
				Date end3 = calendar3.getTime();
				userIntegrals3.setEndTime(end3);
				userIntegrals3.setBeginTime((new Date()));
				userIntegralConsumeMapper.insert(userIntegrals3);
				//购买金币赠送的记录
				UserIntegralConsume userIntegrals4 =new UserIntegralConsume();
				userIntegrals4.setUserId(userId);
				userIntegrals4.setAppId(appId);
				String sKey4 = userIntegralsMapper.findBySkey(appId, leId);
				userIntegrals4.setSceneKey(sKey4);
//				jb=qj.intValue();
//				Integer snum4 =(int)(jb*bei);
				userIntegrals4.setCostNum(qj.multiply(new BigDecimal(bei)));
				userIntegrals4.setCreateTime(new Date());
				MetaObtainIntegral metaObtainIntegral4 = new MetaObtainIntegral();
				metaObtainIntegral4.setSceneKey(sKey);
                    metaObtainIntegral4.setAppId(appId);
				Calendar calendar4 = new GregorianCalendar();
				metaObtainIntegral4 = metaObtainIntegralMapper.selectOne(metaObtainIntegral4);
				calendar4.add(Calendar.DAY_OF_YEAR, metaObtainIntegral4.getPeriod());
				Date end4 = calendar4.getTime();
				userIntegrals4.setEndTime(end4);
				userIntegrals4.setBeginTime((new Date()));
				userIntegralConsumeMapper.insert(userIntegrals4);
			}else{
					result.setStatus(5019);
					result.setMessage("充值金额不能小于188元");
				}
			}else {
					leId = 0;
					Float bei = usersMapper.findByBei(appId, leId + 1);
					UserIntegrals userIntegrals = new UserIntegrals();
					userIntegrals.setUserId(userId);
					userIntegrals.setAppId(appId);
					userIntegrals.setSceneKey("xHwofbNs");
					Integer jb = qj.intValue();
					if(jb>=0.01){
					userIntegrals.setConsumeNum(new BigDecimal("0"));
					userIntegrals.setIntegralNum(qj);
					userIntegrals.setCreateTime(new Date());
					//时间场景
					Calendar calendar = new GregorianCalendar();
					MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
					metaObtainIntegral.setSceneKey("xHwofbNs");
                        metaObtainIntegral.setAppId(appId);
					metaObtainIntegral = metaObtainIntegralMapper.selectOne(metaObtainIntegral);
					calendar.add(Calendar.DAY_OF_YEAR, metaObtainIntegral.getPeriod());
					Date end = calendar.getTime();
					userIntegrals.setEndTime(end);
					userIntegrals.setBeginTime((new Date()));
					userIntegralsMapper.insert(userIntegrals);
					//购买金币赠送的记录
					UserIntegrals userIntegrals2 = new UserIntegrals();
					userIntegrals2.setUserId(userId);
					userIntegrals2.setAppId(appId);
					userIntegrals2.setConsumeNum(new BigDecimal("0"));
					String sKey = userIntegralsMapper.findBySkey(appId, leId + 1);
					userIntegrals2.setSceneKey(sKey);
//					jb = qj.intValue();
//					Integer snum = (int) (jb * bei);
					userIntegrals2.setIntegralNum(qj.multiply(new BigDecimal(bei)));
					userIntegrals2.setCreateTime(new Date());
					//时间场景
					MetaObtainIntegral metaObtainIntegral1 = new MetaObtainIntegral();
					metaObtainIntegral1.setSceneKey(sKey);
                        metaObtainIntegral1.setAppId(appId);
					Calendar calendar1 = new GregorianCalendar();
					metaObtainIntegral1 = metaObtainIntegralMapper.selectOne(metaObtainIntegral1);
					calendar1.add(Calendar.DAY_OF_YEAR, metaObtainIntegral1.getPeriod());
					Date end1 = calendar.getTime();
					userIntegrals2.setEndTime(end1);
					userIntegrals2.setBeginTime((new Date()));
					userIntegralsMapper.insert(userIntegrals2);
					UserIntegralConsume userIntegrals3=new UserIntegralConsume();
					userIntegrals3.setUserId(userId);
					userIntegrals3.setAppId(appId);
					userIntegrals3.setSceneKey("xHwofbNs");
//					Integer jb1=qj.intValue();
					//if(jb>=100){
					userIntegrals3.setCostNum(qj);
					userIntegrals3.setCreateTime(new Date());
					Calendar calendar3 = new GregorianCalendar();
					calendar3.setTime(new Date());

					//获取该场景配置信息
					MetaObtainIntegral metaObtainIntegral3 = new MetaObtainIntegral();
					metaObtainIntegral3.setSceneKey("xHwofbNs");
                        metaObtainIntegral3.setAppId(appId);
					metaObtainIntegral3 = metaObtainIntegralMapper.selectOne(metaObtainIntegral3);
					calendar3.add(Calendar.DAY_OF_YEAR,metaObtainIntegral3.getPeriod());
					Date end3 = calendar3.getTime();
					userIntegrals3.setEndTime(end3);
					userIntegrals3.setBeginTime((new Date()));
					userIntegralConsumeMapper.insert(userIntegrals3);
					//购买金币赠送的记录
					UserIntegralConsume userIntegrals4 =new UserIntegralConsume();
					userIntegrals4.setUserId(userId);
					String sKey4 = userIntegralsMapper.findBySkey(appId,leId+1);
					userIntegrals4.setSceneKey(sKey4);
					userIntegrals4.setAppId(appId);
					jb=qj.intValue();
					Integer snum4 =(int)(jb*bei);
					userIntegrals4.setCostNum(qj.multiply(new BigDecimal(bei)));
					userIntegrals4.setCreateTime(new Date());
					MetaObtainIntegral metaObtainIntegral4 = new MetaObtainIntegral();
					metaObtainIntegral4.setSceneKey(sKey4);
                        metaObtainIntegral4.setAppId(appId);
					Calendar calendar4 = new GregorianCalendar();
					metaObtainIntegral4 = metaObtainIntegralMapper.selectOne(metaObtainIntegral4);
					calendar4.add(Calendar.DAY_OF_YEAR, metaObtainIntegral4.getPeriod());
					Date end4 = calendar.getTime();
					userIntegrals4.setEndTime(end4);
					userIntegrals4.setBeginTime((new Date()));
					userIntegralConsumeMapper.insert(userIntegrals4);
				}else{
						result.setStatus(5019);
						result.setMessage("充值金额不能小于188元");
					}
			}
		}

		result.setStatus(200);
		result.setMessage("用户金币更新成功");
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
 * @param appId
 */
	@Override
	public CommonDto <List<MetaObtainIntegral>>findMoney(Integer appId){
		CommonDto <List<MetaObtainIntegral>> result = new CommonDto <List<MetaObtainIntegral>>();
		List<MetaObtainIntegral> list =new ArrayList<MetaObtainIntegral>();
		List<MetaObtainIntegral> list2 =new ArrayList<MetaObtainIntegral>();
		MetaObtainIntegral obtainIntegral =new MetaObtainIntegral();
		obtainIntegral.setAppId(appId);
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

	/**
	 * 支付之后调用
	 * @param userId 用户ID
	 * @param sceneKey 场景key
	 * @param payMoney 实际支付金额
	 * @param status 支付状态
	 * @param appId
	 * @return
	 */
	@Override
	public CommonDto<String> payAfter(Integer userId, String sceneKey, BigDecimal payMoney, int status, Integer appId) {
		CommonDto<String> result = new CommonDto<>();
		if(TWO.equals(sceneKey) || THREE.equals(sceneKey) || FOUR.equals(sceneKey)){
			result = userLevelService.changeLevel(userId, status,appId);
		}else{
			result = this.insertGold(userId, payMoney, appId);
		}
		return result;
	}

	/**
	 * 验证其他金额是否满足最低限度
	 * @param count
	 * @return
	 */
	private CommonDto<Map<String,Object>> verifyLimitCount(BigDecimal count){

		//获取最低限度
		MetaScene metaScene = new MetaScene();
		metaScene.setKey("RtlrV3oS");

		MetaScene metaSceneResult =  metaSceneMapper.selectOne(metaScene);

		if (null == metaSceneResult){
			return new CommonDto<>(null,"最低限度场景丢失",5013);
		}

		BigDecimal limitCount =new BigDecimal(metaSceneResult.getDesc());

		if (limitCount.compareTo(count) > 0){
			return new CommonDto<>(null,"充值金额必须大于188",5013);
		}

		return new CommonDto<>(null,"success",200);
	}
}