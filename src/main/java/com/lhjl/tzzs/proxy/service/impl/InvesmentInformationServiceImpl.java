package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import com.lhjl.tzzs.proxy.dto.SaveInformationDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionInformation;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.model.ProjectSendLogs;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.service.InvesmentInformationService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zyy on 2017/11/22.
 */
@Service
public class InvesmentInformationServiceImpl  implements InvesmentInformationService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvesmentInformationServiceImpl.class);

    @Value("${pageNum}")
    private Integer pageNumDefalut;

    @Value("${pageSize}")
    private Integer pageSizeDefalut;

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

    @Resource
    private InvesmentInformationServiceImplUtil invesmentInformationServiceImplUtil;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Autowired
    private InvestorsMapper investorsMapper;
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
        List<InvestmentInstitutionsDto> list = invesmentInformationServiceImplUtil.getSearchBase50All(pageNum, pageSize);
        //判断是否还有查询结果
//        if(investmentInstitutions.size() <= 0){
//            result.setStatus(202);
//            result.setMessage("无查询数据");
//            return result;
//        }

       //zyy 20171127 注销
//        for(InvestmentInstitutionsDto d : list) {
//            //带回是否投递的状态
//            ProjectSendLogs projectSendLogs = new ProjectSendLogs();
//            projectSendLogs.setUserid(userToken.getUserId());
//            List<ProjectSendLogs> logsList = projectSendLogsMapper.select(projectSendLogs);
//            List<Integer> a = new LinkedList<Integer>();
//            Integer[] workArray1 = null;
//            //查询项目投递过的记录
//            for (ProjectSendLogs c : logsList) {
//                a.add(c.getId());
//                workArray1 = new Integer[a.size()];
//                workArray1 = a.toArray(workArray1);
//            }
//            if (workArray1 != null) {
//                //根据所有提交项目记录id，查找出对应的机构的id
//                List<Integer> list3 = investmentInstitutionsMapper.serachSendProjectId(workArray1);
//                List<Integer> b = new LinkedList<Integer>();
//                Integer[] workArray2 = null;
//                for (Integer c : list3) {
//                    b.add(c);
//                    workArray2 = new Integer[b.size()];
//                    workArray2 = b.toArray(workArray2);
//                }
//                d.setSendyn(false);
//                for (int e : workArray2) {
//                    if (e == d.getId()) {
//                        d.setSendyn(true);
//                    }
//                }
//                //如果投资人提交数据了，在机构列表处也显示已投
//                 InvestmentInstitutionInformation investmentInstitutionInformation = new InvestmentInstitutionInformation();
//                    List<InvestmentInstitutionInformation> list1 = investmentInstitutionInformationMapper.findInformation(userToken.getUserId());
//                    if(list1.size()>0){
//                        List<String> e = new LinkedList<String>();
//                        String[] workArray3 = null;
//                        for (InvestmentInstitutionInformation c : list1) {
//                            e.add(c.getShortName());
//                            workArray3 = new String[e.size()];
//                            workArray3 = e.toArray(workArray3);
//                        }
//                        for (String f : workArray3) {
//                            if (f.equals(d.getShortName())) {
//                                d.setSendyn(true);
//                            }
//                        }
//                    }
//            } else {
//                d.setSendyn(false);
//
//                //如果投资人提交数据了，在机构列表处也显示已投
//                 InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
//                    List<InvestmentInstitutionInformation> list1=investmentInstitutionInformationMapper.findInformation(userToken.getUserId());
//                    if(list1.size()>0){
//                        List<String> e = new LinkedList<String>();
//                        String[] workArray3 = null;
//                        for (InvestmentInstitutionInformation c : list1) {
//                            e.add(c.getShortName());
//                            workArray3 = new String[e.size()];
//                            workArray3 = e.toArray(workArray3);
//                        }
//                        for (String f : workArray3) {
//                            if (f.equals(d.getShortName())) {
//                                d.setSendyn(true);
//                            }
//                        }
//                    }
//            }
//        }
        //先查到当前登录人的id
        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setMessage("用户token无效");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        //判断身份类型
        Example investorExample = new Example(Investors.class);
        investorExample.and().andEqualTo("userId",userId).andEqualTo("yn",1);
        List<Investors> investorsSearch = investorsMapper.selectByExample(investorExample);
        Integer leixing = 0;//身份类型，0表示非投资人，1表示投资人

        if (investorsSearch.size() > 0){
            leixing = 1;
        }

        if (leixing == 0){
          //创始人的逻辑
            list = setInvestmentInstitutionsCYZ(list,userId);
        }else{
            //非创始人的时候的逻辑
            list = setInvestmentInstitutionsTZR(list,userId);
        }


        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 创始人已提交机构的回显判断接口
     * @param list 机构列表
     * @param userId 用户id
     * @return
     */
    private List<InvestmentInstitutionsDto> setInvestmentInstitutionsCYZ(List<InvestmentInstitutionsDto> list,Integer userId){

        //通过用户id，获取用户提交项目记录的id数组；
        List<Integer> pslId = new ArrayList<>();//提交记录ids

        Example pslExample = new Example(ProjectSendLogs.class);
        pslExample.and().andEqualTo("userid",userId);

        List<ProjectSendLogs> projectSendLogsList = projectSendLogsMapper.selectByExample(pslExample);
        if (projectSendLogsList.size() > 0){
            for (ProjectSendLogs psl:projectSendLogsList){
                pslId.add(psl.getId());
            }
        }


        List<Integer> jgids = new ArrayList<>();
        //判断是否已经有提交数据
        if (pslId.size() > 0){
            //有的情况 找到对应的投递机构id；
            jgids = investmentInstitutionsMapper.serachSendProjectIdZ(pslId);

            //拿到机构id后给机构列表加上标识
            for (int i=0 ;i < jgids.size();i++){
                for (InvestmentInstitutionsDto iid: list){
                    Integer a = iid.getId();
                    Integer b = jgids.get(i);
                    if (String.valueOf(a).equals(String.valueOf(b))){
                        iid.setSendyn(true);
                    }
                }
            }

        }

        return list;
    }

    /**
     * 投资人列表回显
     * @param list 机构列表
     * @param userId 用户id
     * @return
     */
    private List<InvestmentInstitutionsDto> setInvestmentInstitutionsTZR(List<InvestmentInstitutionsDto> list,Integer userId){


        //通过用户id获取到用户的提交记录
        Example iiiExample = new Example(InvestmentInstitutionInformation.class);
        iiiExample.and().andEqualTo("userId",userId);

        List<InvestmentInstitutionInformation> investmentInstitutionInformationList = investmentInstitutionInformationMapper.selectByExample(iiiExample);
        if (investmentInstitutionInformationList.size() > 0){
           //判断名称是否和原有的机构名称相同，相同即勾选上
            for (InvestmentInstitutionInformation iii :investmentInstitutionInformationList){
                for (InvestmentInstitutionsDto iid:list){
                    if (iii.getShortName().equals(iid.getShortName())){
                        iid.setSendyn(true);
                    }
                }
            }
        }

        return list;
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

        if (list.size() > 0){
            for (InvestmentInstitutionsDto iid:list){
                if (iid.getLogo() == null){
                    iid.setLogo("http://img.idatavc.com/static/logo/jg_default.png");
                }

                String kenerlCase = "";
                if (iid.getKenelCase() == null){
                    if (iid.getComment() != null){
                        kenerlCase = iid.getComment();
                    }
                }else {
                    kenerlCase = iid.getKenelCase();
                }

                iid.setCommet(kenerlCase);
            }
        }

        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            result.setMessage("用户token无效");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        //判断身份类型
        Example investorExample = new Example(Investors.class);
        investorExample.and().andEqualTo("userId",userId).andEqualTo("yn",1);
        List<Investors> investorsSearch = investorsMapper.selectByExample(investorExample);
        Integer leixing = 0;//身份类型，0表示非投资人，1表示投资人

        if (investorsSearch.size() > 0){
            leixing = 1;
        }

        if (leixing == 0){
            //创始人的逻辑
            list = setInvestmentInstitutionsCYZ(list,userId);
        }else{
            //非创始人的时候的逻辑
            list = setInvestmentInstitutionsTZR(list,userId);
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

    /**
     * 获取最近活跃的机构
     * @param body
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionsDto>> findRecentlyInstitution(SaveInformationDto body) {
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<>();
        List<InvestmentInstitutionsDto> list = new ArrayList<>();

        //初始化参数
        if (body.getToken() == null){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户token不能为空");

            return result;
        }

        if (body.getPageNum() == null || body.getPageNum() <1){
            body.setPageNum(pageNumDefalut);
        }

        if (body.getPageSize() == null || body.getPageSize() < 1){
            body.setPageSize(pageSizeDefalut);
        }

        Integer userId = userExistJudgmentService.getUserId(body.getToken());
        if (userId == -1 ){
            result.setMessage("用户token无效");
            result.setStatus(502);
            result.setData(null);

            return result;
        }



        Integer startPage = (body.getPageNum()-1)*body.getPageSize();
        Integer pageSize = body.getPageSize();

        //最多返回150条记录
        if (startPage > 150) {
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（150条）");
            return result;
        } else {
            pageSize = (150 - startPage) >= pageSize ? pageSize : (150 - startPage);
        }

        list = investmentInstitutionsMapper.findRecentlyActiveInstitution(startPage,pageSize);
        if (list.size() > 0){
            for (InvestmentInstitutionsDto iid:list){
                if (iid.getCount() == null){
                    iid.setCount(0);
                }

                if (iid.getKenelCase() == null){
                    if (iid.getComment() != null){
                        iid.setKenelCase(iid.getComment());
                    }else {
                        iid.setKenelCase("");
                    }
                }
            }
        }

        //判断身份类型
        Example investorExample = new Example(Investors.class);
        investorExample.and().andEqualTo("userId",userId).andEqualTo("yn",1);
        List<Investors> investorsSearch = investorsMapper.selectByExample(investorExample);
        Integer leixing = 0;//身份类型，0表示非投资人，1表示投资人

        if (investorsSearch.size() > 0){
            leixing = 1;
        }

        if (leixing == 0){
            //创始人的逻辑
            list = setInvestmentInstitutionsCYZ(list,userId);
        }else{
            //非创始人的时候的逻辑
            list = setInvestmentInstitutionsTZR(list,userId);
        }

        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }
}
