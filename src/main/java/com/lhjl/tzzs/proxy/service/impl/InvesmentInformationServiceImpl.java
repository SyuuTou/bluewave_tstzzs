package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionInformationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendLogsMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionInformation;
import com.lhjl.tzzs.proxy.model.ProjectSendLogs;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.service.InvesmentInformationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zyy on 2017/11/22.
 */
@Service
public class InvesmentInformationServiceImpl  implements InvesmentInformationService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvesmentInformationServiceImpl.class);

    @Resource
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private ProjectSendLogsMapper projectSendLogsMapper;

    @Value("${statistics.beginTime}")
    private String beginTime;

    @Value("${statistics.endTime}")
    private String endTime;
    
    @Autowired
    private InvestmentInstitutionInformationMapper investmentInstitutionInformationMapper;
    /**
     * 获取50机构信息（分页）
     *
     * @param pageNum  页数
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionsDto>> findFiveInvestmentNew(Integer pageNum, Integer pageSize, String token) {
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();

        //计算查询起始记录
        Integer beginNum = (pageNum - 1) * pageSize;
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken = userTokenMapper.selectOne(userToken);
        List<InvestmentInstitutionsDto> list = investmentInstitutionsMapper.findInvestment50New("1", beginNum, pageSize,beginTime,endTime);
        //判断是否还有查询结果
//        if(investmentInstitutions.size() <= 0){
//            result.setStatus(202);
//            result.setMessage("无查询数据");
//            return result;
//        }
        for(InvestmentInstitutionsDto d : list) {
            //带回是否投递的状态
            ProjectSendLogs projectSendLogs = new ProjectSendLogs();
            projectSendLogs.setUserid(userToken.getUserId());
            List<ProjectSendLogs> logsList = projectSendLogsMapper.select(projectSendLogs);
            List<Integer> a = new LinkedList<Integer>();
            Integer[] workArray1 = null;
            //查询项目投递过的记录
            for (ProjectSendLogs c : logsList) {
                a.add(c.getId());
                workArray1 = new Integer[a.size()];
                workArray1 = a.toArray(workArray1);
            }
            if (workArray1 != null) {
                //根据记录id查找出机构的id
                List<Integer> list3 = investmentInstitutionsMapper.serachSendProjectId(workArray1);
                List<Integer> b = new LinkedList<Integer>();
                Integer[] workArray2 = null;
                for (Integer c : list3) {
                    b.add(c);
                    workArray2 = new Integer[b.size()];
                    workArray2 = b.toArray(workArray2);
                }
                d.setSendyn(false);
                for (int e : workArray2) {
                    if (e == d.getId()) {
                        d.setSendyn(true);
                    }
                }
                 InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
                    List<InvestmentInstitutionInformation> list1=investmentInstitutionInformationMapper.findInformation(userToken.getUserId());
                    if(list1.size()>0){
                        List<String> e = new LinkedList<String>();
                        String[] workArray3 = null;
                        for (InvestmentInstitutionInformation c : list1) {
                            e.add(c.getShortName());
                            workArray3 = new String[e.size()];
                            workArray3 = e.toArray(workArray3);
                        }
                        for (String f : workArray3) {
                            if (f.equals(d.getShortName())) {
                                d.setSendyn(true);
                            }
                        }
                    }
            } else {
                d.setSendyn(false);
                 InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
                    List<InvestmentInstitutionInformation> list1=investmentInstitutionInformationMapper.findInformation(userToken.getUserId());
                    if(list1.size()>0){
                        List<String> e = new LinkedList<String>();
                        String[] workArray3 = null;
                        for (InvestmentInstitutionInformation c : list1) {
                            e.add(c.getShortName());
                            workArray3 = new String[e.size()];
                            workArray3 = e.toArray(workArray3);
                        }
                        for (String f : workArray3) {
                            if (f.equals(d.getShortName())) {
                                d.setSendyn(true);
                            }
                        }
                    }
            }
        }
        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 获取非50机构信息（分页）
     *
     * @param pageNum  页数
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionsDto>> findNotFiveInvestmentNew(Integer pageNum, Integer pageSize,String token) {
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();

        //计算查询起始记录
        Integer beginNum = (pageNum - 1) * pageSize;

        //最多返回160条记录
        if (beginNum > 150) {
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（150条）");
            return result;
        } else {
            pageSize = (150 - beginNum) >= pageSize ? pageSize : (150 - beginNum);
        }
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken = userTokenMapper.selectOne(userToken);
        List<InvestmentInstitutionsDto> list = investmentInstitutionsMapper.findInvestmentallNew("-1", beginNum, pageSize,beginTime,endTime);
        for(InvestmentInstitutionsDto d : list) {
            //带回是否投递的状态
            ProjectSendLogs projectSendLogs = new ProjectSendLogs();
            projectSendLogs.setUserid(userToken.getUserId());
            List<ProjectSendLogs> logsList = projectSendLogsMapper.select(projectSendLogs);
            List<Integer> a = new LinkedList<Integer>();
            //查询项目投递过的记录
            Integer[] workArray1 = null;
            for (ProjectSendLogs c : logsList) {
                a.add(c.getId());
                workArray1 = new Integer[a.size()];
                workArray1 = a.toArray(workArray1);
            }
            if (workArray1 != null) {
                //根据记录id查找出机构的id
                List<Integer> list3 = investmentInstitutionsMapper.serachSendProjectId(workArray1);
                List<Integer> b = new LinkedList<Integer>();
                Integer[] workArray2 = null;
                for (Integer c : list3) {
                    b.add(c);
                    workArray2 = new Integer[b.size()];
                    workArray2 = b.toArray(workArray2);
                }
                d.setSendyn(false);
                for (int e : workArray2) {
                    if (e == d.getId()) {
                        d.setSendyn(true);
                    }
                }
                 InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
                    List<InvestmentInstitutionInformation> list1=investmentInstitutionInformationMapper.findInformation(userToken.getUserId());
                    if(list1.size()>0){
                        List<String> e = new LinkedList<String>();
                        String[] workArray3 = null;
                        for (InvestmentInstitutionInformation c : list1) {
                            e.add(c.getShortName());
                            workArray3 = new String[e.size()];
                            workArray3 = e.toArray(workArray3);
                        }
                        for (String f : workArray3) {
                            if (f.equals(d.getShortName())) {
                                d.setSendyn(true);
                            }
                        }
                    }
            } else {
                d.setSendyn(false);
                 InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
                    List<InvestmentInstitutionInformation> list1=investmentInstitutionInformationMapper.findInformation(userToken.getUserId());
                    if(list1.size()>0){
                        List<String> e = new LinkedList<String>();
                        String[] workArray3 = null;
                        for (InvestmentInstitutionInformation c : list1) {
                            e.add(c.getShortName());
                            workArray3 = new String[e.size()];
                            workArray3 = e.toArray(workArray3);
                        }
                        for (String f : workArray3) {
                            if (f.equals(d.getShortName())) {
                                d.setSendyn(true);
                            }
                        }
                    }
                
            }
        }
        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 创始人 推荐/已投递的接口集合
     * @param token
     * @return
     */
	@Override
	public CommonDto<List<InvestmentInstitutionsDto>> findRecommendCreater(String token) {
		 CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
        List<InvestmentInstitutionsDto> list =new ArrayList<>();
		 UserToken userToken = new UserToken();
	        userToken.setToken(token);
	        userToken = userTokenMapper.selectOne(userToken);
	        if(userToken !=null){
                ProjectSendLogs projectSendLogs = new ProjectSendLogs();
                projectSendLogs.setUserid(userToken.getUserId());
                List<ProjectSendLogs> logsList = projectSendLogsMapper.select(projectSendLogs);
                if(logsList.size()>0) {
                    //查询项目投递过的记录
                    List<Integer> a = new LinkedList<Integer>();
                    Integer[] workArray1 = null;
                    for (ProjectSendLogs c : logsList) {
                        a.add(c.getId());
                        workArray1 = new Integer[a.size()];
                        workArray1 = a.toArray(workArray1);
                    }
                    //根据记录id查找出机构的id
                    List<Integer> list3 = investmentInstitutionsMapper.deliverySendProjectId(workArray1);
                    List<Integer> b = new LinkedList<Integer>();
                    Integer[] workArray2 = null;
                    for (Integer c : list3) {
                        b.add(c);
                        workArray2 = new Integer[b.size()];
                        workArray2 = b.toArray(workArray2);
                    }
                     list =investmentInstitutionsMapper.findRecommendAll(workArray2);
                    for(InvestmentInstitutionsDto ii :list){
                        ii.setSendyn(true);
                    }
                }else{
                    result.setStatus(5102);
                    result.setMessage("用户未投递过项目");
                }
	        	
	        }else{
	        	 result.setStatus(5101);
	             result.setMessage("用户token不存在");
	        	
	        }
	        result.setData(list);
		return result;
	}

    /**
     * 投资人 推荐/已投递的接口集合
     * @param token
     * @return
     */
	//**@Override
	public CommonDto<List<InvestmentInstitutionsDto>> findRecommendInvestor(String token) {
		 CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
	        List<InvestmentInstitutionsDto> list =new ArrayList<>();
			 UserToken userToken = new UserToken();
		        userToken.setToken(token);
		        userToken = userTokenMapper.selectOne(userToken);
		        if(userToken !=null){
                    InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
                    List<InvestmentInstitutionInformation> list1=investmentInstitutionInformationMapper.findInformation(userToken.getUserId());
                    if(list1.size()>0){
                        //查询到投递过的机构名称
                        List<String> e = new LinkedList<String>();
                        String[] workArray3 = null;
                        for (InvestmentInstitutionInformation c : list1) {
                            e.add(c.getShortName());
                            workArray3 = new String[e.size()];
                            workArray3 = e.toArray(workArray3);
                        }
                        list =investmentInstitutionsMapper.findRecommendInvestor(workArray3);
                        for(InvestmentInstitutionsDto pp :list){
                            pp.setSendyn(true);
                        }
	                }else{
	                    result.setStatus(5102);
	                    result.setMessage("用户未投递过项目");
	                }
		        	
		        }else{
		        	 result.setStatus(5101);
		             result.setMessage("用户token不存在");
		        	
		        }
		        result.setData(list);
			return result;
		}
}
