package com.lhjl.tzzs.proxy.service.impl;



import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.lhjl.tzzs.proxy.service.ScreenAndSearchInstitutionService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lmy on 2017/11/20.
 */
@Service
public class ScreenAndSearchInstitutionServiceImpl implements ScreenAndSearchInstitutionService {

    @Value("${pageNum}")
    private Integer defalutPageNum;

    @Value("${pageSize}")
    private Integer getDefalutPageSize;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private SearchInvestmentRecordMapper searchInvestmentRecordMapper;


    @Autowired
    private ScreenInvestmentRecordMapper  screenInvestmentRecordMapper;


    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ProjectSendLogsMapper projectSendLogsMapper;

    @Autowired
    private InvestmentInstitutionInformationMapper investmentInstitutionInformationMapper;

    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;

    @Autowired
    private MetaProjectStageMapper metaProjectStageMapper;

    @Resource
    private UserLoginService userLoginService;


    /**
     * 搜索机构列表
     */
    @Override
    public CommonDto<List<InvestmentInstitutionsDto>> searchInstitution(String shortName, Integer pageNum,
                                                                        Integer pageSize, String token) {
        CommonDto<List<InvestmentInstitutionsDto>>  result = new CommonDto<List<InvestmentInstitutionsDto>>  ();
        Map<String,Object> map =new HashedMap();
        List<InvestmentInstitutionsDto> list = new ArrayList<>();
        //最多返回100条记录
        /*if(beginNum > 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }else{
            pageSize = (100 - beginNum)>=pageSize?pageSize:(100-beginNum);
        }*/

        Integer beginNum = (pageNum - 1) * pageSize;
        //如果插入的字段为空
        if(shortName == null || "".equals(shortName)){
            result.setStatus(5006);
            result.setMessage("请输入搜索的字段");
        }else {
            UserToken userToken = new UserToken();
            userToken.setToken(token);
            userToken = userTokenMapper.selectOne(userToken);
            //查询所有机构列表
            list = investmentInstitutionsMapper.serachInstitution(shortName, beginNum, pageSize);
            for(InvestmentInstitutionsDto d : list) {

               //处理logo
                String logo = "http://img.idatavc.com/static/logo/jg_default.png";
                if (d.getLogo() == null){
                    d.setLogo(logo);
                }

                //处理机构简介为空处理
                String description = "";
                if (d.getKenelCase() == null){
                    d.setKenelCase(description);
                }


                //带回是否投递的状态
                ProjectSendLogs projectSendLogs = new ProjectSendLogs();
                projectSendLogs.setUserid(userToken.getUserId());
                List<ProjectSendLogs> logsList = projectSendLogsMapper.select(projectSendLogs);
                List<Integer> a = new LinkedList<Integer>();
                Integer[] workArray1 = null;
                for (ProjectSendLogs c : logsList) {
                    a.add(c.getId());
                    workArray1 = new Integer[a.size()];
                    workArray1 = a.toArray(workArray1);
                }
                if (workArray1 != null) {
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
                    //判断投资人
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
            //判断用户的token
            if (userToken != null) {
                SearchInvestmentRecord searchInvestmentRecord = new SearchInvestmentRecord();
                searchInvestmentRecord.setShortName(shortName);
                searchInvestmentRecord.setUserId(userToken.getUserId());
                searchInvestmentRecord=searchInvestmentRecordMapper.selectOne(searchInvestmentRecord);
                //判断是否同个用户输入的字
                if(searchInvestmentRecord ==null) {
                    SearchInvestmentRecord searchInvestmentRecord1 = new SearchInvestmentRecord();
                    searchInvestmentRecord1.setCreateTime(new Date());
                    searchInvestmentRecord1.setShortName(shortName);
                    searchInvestmentRecord1.setUserId(userToken.getUserId());
                    searchInvestmentRecord1.setAmout(1);
                    searchInvestmentRecordMapper.insert(searchInvestmentRecord1);
                }else{
                    searchInvestmentRecord.setCreateTime(new Date());
                    Integer amount =searchInvestmentRecord.getAmout()+1;
                    searchInvestmentRecord.setAmout(amount);
                    searchInvestmentRecordMapper.updateByPrimaryKey(searchInvestmentRecord);
                }
                //投递的状态

            } else {
                result.setStatus(5007);
                result.setMessage("token不存在");
            }
        }
        result.setData(list);
        return result;
    }

    /**
     * 历史记录的显示8条
     * @param token
     * @return
     */
    @Override
    public CommonDto<List<SearchInvestmentRecord>> searchInstitutionRecord(String token) {
        CommonDto<List<SearchInvestmentRecord>> result = new CommonDto<List<SearchInvestmentRecord>>();
        List<SearchInvestmentRecord> list = new ArrayList<>();
        //插入记录表信息
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken = userTokenMapper.selectOne(userToken);
        if(userToken !=null){
            Integer userId=userToken.getUserId();
            list=searchInvestmentRecordMapper.serachInstitutionRecord(userId);

        }else{
            result.setStatus(5001);
            result.setMessage("token不存在");
        }
        result.setData(list);
        return result;
    }
    /***
     * 查询热门的搜索8条
     */
    @Override
    public CommonDto<List<SearchInvestmentRecord>> searchInstitutionHot() {
        CommonDto<List<SearchInvestmentRecord>> result = new CommonDto<List<SearchInvestmentRecord>>();
        List<SearchInvestmentRecord> list = new ArrayList<>();
        list=searchInvestmentRecordMapper.serachInstitutionHot();
        result.setData(list);
        return result;
    }
    /**
     * 筛选页面回显
     */

    @Override
    public CommonDto<Map<String, Object>> screenInstitution(String token) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        CommonDto<Map<String,List<LabelList>>> data = evaluateService.queryHotData();
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken = userTokenMapper.selectOne(userToken);
        if(userToken !=null){
            Integer userId=userToken.getUserId();
            ScreenInvestmentRecord  screenInvestmentRecord =new ScreenInvestmentRecord();
            screenInvestmentRecord.setUserId(userId);
            List<ScreenInvestmentRecord> list2 = screenInvestmentRecordMapper.select(screenInvestmentRecord);
            //判断是否有数据
            if(list2.size()>0){
                ScreenInvestmentRecord screenInvestmentRecord1=screenInvestmentRecordMapper.serachScreenRecord(userId);
                //选择的为50机构
                Integer investmentType = -1;
                if (screenInvestmentRecord1.getInvestmentType() != null){
                    investmentType = screenInvestmentRecord1.getInvestmentType();
                }

                if(investmentType == 0){
                    List<LabelList> list1 =new LinkedList<>();
                    LabelList labelList =new LabelList();
                    labelList.setName("50指数机构");
                    labelList.setValue("50指数机构");
                    labelList.setChecked(true);
                    LabelList labelList1 =new LabelList();
                    labelList1.setName("行业指数机构");
                    labelList1.setValue("行业指数机构");
                    labelList1.setChecked(false);
                    list1.add(labelList);
                    list1.add(labelList1);
                    map.put("investment_type",list1);
                    map.put("investTypeName","50指数机构");
                }else if (investmentType == 1){
                    List<LabelList> list1 =new LinkedList<>();
                    LabelList labelList =new LabelList();
                    labelList.setName("50指数机构");
                    labelList.setValue("50指数机构");
                    labelList.setChecked(false);
                    LabelList labelList1 =new LabelList();
                    labelList1.setName("行业指数机构");
                    labelList1.setValue("行业指数机构");
                    labelList1.setChecked(true);
                    list1.add(labelList);
                    list1.add(labelList1);
                    map.put("investment_type",list1);
                    map.put("investTypeName","行业指数机构");
                }else {
                    List<LabelList> list1 =new LinkedList<>();
                    LabelList labelList =new LabelList();
                    labelList.setName("50指数机构");
                    labelList.setValue("50指数机构");
                    labelList.setChecked(false);
                    LabelList labelList1 =new LabelList();
                    labelList1.setName("行业指数机构");
                    labelList1.setValue("行业指数机构");
                    labelList1.setChecked(false);
                    list1.add(labelList);
                    list1.add(labelList1);
                    map.put("investment_type",list1);
                    map.put("investTypeName","行业指数机构");
                }
                //轮次的回显
                if(screenInvestmentRecord1.getStage() ==null || "".equals(screenInvestmentRecord1.getStage())) {
                    List<LabelList> list3 = new LinkedList<>();
                    LabelList labelList3 = new LabelList();
                    labelList3.setName("全部");
                    labelList3.setValue("全部");
                    labelList3.setChecked(true);
                    LabelList labelList4 = new LabelList();
                    labelList4.setName("天使轮");
                    labelList4.setValue("天使轮");
                    labelList4.setChecked(false);
                    LabelList labelList5 = new LabelList();
                    labelList5.setName("Pre-A轮");
                    labelList5.setValue("Pre-A轮");
                    labelList5.setChecked(false);
                    LabelList labelList6 = new LabelList();
                    labelList6.setName("A轮");
                    labelList6.setValue("A轮");
                    labelList6.setChecked(false);
                    list3.add(labelList3);
                    list3.add(labelList4);
                    list3.add(labelList5);
                    list3.add(labelList6);
                    map.put("stage", list3);
                    map.put("stageName","");

                }else{
                    List<LabelList> list3 = new LinkedList<>();
                    LabelList labelList3 = new LabelList();
                    labelList3.setName("全部");
                    labelList3.setValue("全部");
                    labelList3.setChecked(false);
                    LabelList labelList4 = new LabelList();
                    labelList4.setName("天使轮");
                    labelList4.setValue("天使轮");
                    labelList4.setChecked(false);
                    LabelList labelList5 = new LabelList();
                    labelList5.setName("Pre-A轮");
                    labelList5.setValue("Pre-A轮");
                    labelList5.setChecked(false);
                    LabelList labelList6 = new LabelList();
                    labelList6.setName("A轮");
                    labelList6.setValue("A轮");
                    labelList6.setChecked(false);
                    list3.add(labelList3);
                    list3.add(labelList4);
                    list3.add(labelList5);
                    list3.add(labelList6);
                    for (LabelList label :list3) {
                        String[] industryArray = screenInvestmentRecord1.getStage().split(",");
                        for (String string : industryArray) {
                            if (string.equals(label.getName())){
                                label.setChecked(true);
                            }
                        }
                    }
                    map.put("stage", list3);
                    map.put("stageName",screenInvestmentRecord1.getStage());
                }
                //领域回显
                if(null == screenInvestmentRecord1.getDomain() || "".equals(screenInvestmentRecord1.getDomain())){
                    //选择全部时
                    List<LabelList> industryKeies = data.getData().get("industryKey");
                    LabelList list =new LabelList();
                    List<LabelList>  listb =new ArrayList();
                    list.setName("全部");
                    list.setValue("全部");
                    list.setChecked(true);
                    listb.add(list);
                    listb.addAll(industryKeies);
                    map.put("industryKey",listb);
                    map.put("industryName","");
                }else {
                    List<LabelList> industryKeies = data.getData().get("industryKey");
                    LabelList list =new LabelList();
                    List<LabelList>  listb =new ArrayList();
                    list.setName("全部");
                    list.setValue("全部");
                    list.setChecked(false);
                    listb.add(list);
                    listb.addAll(industryKeies);
                    for(LabelList labelList : listb){
                        String [] induAry =screenInvestmentRecord1.getDomain().split(",");
                        for(String string :induAry){
                            if(string.equals(labelList.getName())){
                                labelList.setChecked(true);
                            }
                        }
                    }
                    map.put("industryKey",listb);
                    map.put("industryName",screenInvestmentRecord1.getDomain());
                }
            }else{
                //机构类型
                List<LabelList> list1 =new LinkedList<>();
                LabelList labelList =new LabelList();
                labelList.setName("50指数机构");
                labelList.setValue("50指数机构");
                labelList.setChecked(false);
                LabelList labelList1 =new LabelList();
                labelList1.setName("行业指数机构");
                labelList1.setValue("行业指数机构");
                labelList1.setChecked(false);
                list1.add(labelList);
                list1.add(labelList1);
                map.put("investment_type",list1);
                map.put("investTypeName","50指数机构");
                //轮次
                List<LabelList> list3 =new LinkedList<>();
                LabelList labelList3 =new LabelList();
                labelList3.setName("全部");
                labelList3.setValue("全部");
                labelList3.setChecked(true);
                LabelList labelList4 =new LabelList();
                labelList4.setName("天使轮");
                labelList4.setValue("天使轮");
                labelList4.setChecked(false);
                LabelList labelList5=new LabelList();
                labelList5.setName("Pre-A轮");
                labelList5.setValue("Pre-A轮");
                labelList5.setChecked(false);
                LabelList labelList6=new LabelList();
                labelList6.setName("A轮");
                labelList6.setValue("A轮");
                labelList6.setChecked(false);
                list3.add(labelList3);
                list3.add(labelList4);
                list3.add(labelList5);
                list3.add(labelList6);
                map.put("stage",list3);
                map.put("stageName","");
                //领域
                List<LabelList> industryKeies = data.getData().get("industryKey");
                LabelList list =new LabelList();
                List<LabelList>  listb =new ArrayList();
                list.setName("全部");
                list.setValue("全部");
                list.setChecked(true);
                listb.add(list);
                listb.addAll(industryKeies);
                map.put("industryKey",listb);
                map.put("industryName","");
            }

        }else{
            result.setStatus(5001);
            result.setMessage("token不存在");
        }

        result.setData(map);
        return result;
    }

    /**
     * 存储筛选的记录
     * @param body
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> savescreenInstitution(SaveScreenDto body) {
        CommonDto<String> result=new CommonDto<String>();
        String token =body.getToken();
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken = userTokenMapper.selectOne(userToken);
        if(userToken !=null){
            ScreenInvestmentRecord screenInvestmentRecord =new ScreenInvestmentRecord();
            screenInvestmentRecord.setCreateTime(new Date());
            screenInvestmentRecord.setUserId(userToken.getUserId());
            String investmentType = body.getInvestmentType();
            if(!"".equals(investmentType)  && null !=investmentType){
                screenInvestmentRecord.setInvestmentType(Integer.parseInt(investmentType));
            }
            String stage = body.getStage();
            if(null !=stage && !"".equals(stage)){
                screenInvestmentRecord.setStage(stage);
            }
            String domain = body.getDomain();
            if(null !=domain  && !"".equals(domain)){
                screenInvestmentRecord.setDomain(domain);
            }
            screenInvestmentRecordMapper.insert(screenInvestmentRecord);
        }else {
            result.setStatus(5001);
            result.setMessage("token不存在");
        }
        return result;
    }

    /**
     * 筛选结果页面
     * @param body
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionsDto>> screenInstitutionAll(SaveScreenDto body) {
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
        List<InvestmentInstitutionsDto> list = new ArrayList<>();
        String domain = body.getDomain();//领域
        String investmentType = body.getInvestmentType(); //机构类型
        String stage = body.getStage();
        Integer pageNum = body.getPageNum();
        Integer pageSize = body.getPageSize();
        String token = body.getToken();
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken = userTokenMapper.selectOne(userToken);
        if (userToken != null) {
            if (pageNum == null) {
                pageNum = 1;
            }
            if (pageSize == null) {
                pageSize = 20;
            }
            Integer beginNum = (pageNum - 1) * pageSize;
            //属于领域与轮次的项目
            String[] domains = null;
            String[] stages = null;
            Integer[] types = null;

            if (domain != null && !"".equals(domain)) {
                domains = domain.split(",");
            }

            if (stage != null && !"".equals(stage)) {
                stages = stage.split(",");
            }
            if ("1".equals(investmentType)) {
                investmentType = "1";
                String[] type2 = investmentType.split(",");
                types = new Integer[type2.length];
                for (int i = 0; i < type2.length; i++) {
                    types[i] = Integer.parseInt(type2[i]);
                }
            }
            //先获取到领域和阶段对应的元数据表中的id用来接下来的一步查询
            List<Integer> metaSegmentation = null;
            metaSegmentation = metaSegmentationMapper.findMetaSegmentationBySegmentation(domains);
            List<Integer> metaProjectStage = null;
            metaProjectStage = metaProjectStageMapper.findMetaProjectStageByName(stages);

            //list转数组
            List<Integer> c1 =new LinkedList<Integer>();
            Integer [] workArray2a =null;
            for(Integer d: metaSegmentation){
                c1.add(d);
                workArray2a= new Integer[c1.size()];
                workArray2a= c1.toArray(workArray2a);
            }

            List<Integer> b1 =new LinkedList<Integer>();
            Integer [] workArray1a =null;
            for(Integer d: metaProjectStage){
                b1.add(d);
                workArray1a= new Integer[b1.size()];
                workArray1a=b1.toArray(workArray1a);
            }

            //获取机构id列表
            List<Integer> integerList =getIntegerList(workArray2a,workArray1a);
            //判断是否有符合条件的机构
            Integer[] workArray = null;
            if(integerList.size()>0) {
                List<Integer> a = new LinkedList<Integer>();
                for (Integer d : integerList) {
                    a.add(d);
                    workArray = new Integer[a.size()];
                    workArray = a.toArray(workArray);
                }
            }else{
                result.setStatus(5108);
                result.setMessage("无查询数据");
            }
            //循环插入项目的id
            list=investmentInstitutionsMapper.screenInstitutionAll(workArray,types,beginNum,pageSize);
            for(InvestmentInstitutionsDto d : list){

                //处理logo
                String logo = "http://img.idatavc.com/static/logo/jg_default.png";
                if (d.getLogo() == null){
                    d.setLogo(logo);
                }

                //处理机构简介为空处理
                String description = "";
                if (d.getKenelCase() == null){
                    d.setKenelCase(description);
                }

                //带回是否投递的状态
                ProjectSendLogs projectSendLogs =new ProjectSendLogs();
                projectSendLogs.setUserid(userToken.getUserId());
                List<ProjectSendLogs> logsList =projectSendLogsMapper.select(projectSendLogs);
                List<Integer> a=new LinkedList<Integer>();
                Integer [] workArray1 =null;
                for(ProjectSendLogs c: logsList){
                    a.add(c.getId());
                    workArray1= new Integer [a.size()];
                    workArray1=a.toArray(workArray1);
                }
                if( workArray1 !=null){
                    List<Integer> list3 = investmentInstitutionsMapper.serachSendProjectId(workArray1);
                    List<Integer> b=new LinkedList<Integer>();
                    Integer [] workArray2 =null;
                    for(Integer c: list3){
                        b.add(c);
                        workArray2= new Integer [b.size()];
                        workArray2=b.toArray(workArray2);
                    }
                    d.setSendyn(false);
                    for(int e :workArray2){
                        if(e == d.getId()){
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
                }else{
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
        } else {
            result.setStatus(5101);
            result.setMessage("用户token为空");
        }
        result.setData(list);
        return result;
    }

    /**
     * 机构筛选新接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionsDto>> screnInstitutionAllNew(SaveScreenDto body) {
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<>();
        List<InvestmentInstitutionsDto> list = new ArrayList<>();

        if (body.getToken() == null || "".equals(body.getToken()) || "undefined".equals(body.getToken())){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户token不能为空");

            return result;
        }

        Integer userId = userLoginService.getUserIdByToken(body.getToken(),1);

        String domain = "";//领域
        if (body.getDomain() != null){
            domain = body.getDomain();
        }
        Integer investmentType = null; //机构类型
        if (body.getInvestmentType() != null){
            if ("50指数机构".equals(body.getInvestmentType())){
                investmentType = 1;
            }else {
                investmentType = 0;
            }
        }

        String stage = "";
        if (body.getStage() != null){
            stage = body.getStage();
        }

        Integer pageNum = defalutPageNum;
        if (body.getPageNum() != null){
            pageNum = body.getPageNum();
        }

        Integer pageSize = getDefalutPageSize;
        if (body.getPageSize() != null){
            pageSize = body.getPageSize();
        }
        String[] domains = {};
        if (domain != ""){
             domains = domain.split(",");
        }

        String[] stages = {};
        if (stage != ""){
            stages = stage.split(",");
        }

        Integer types = investmentType;

        Integer startPage = (pageNum -1)*pageSize;

        if (stages.length < 1){
            stages = null;
        }

        if (domains.length < 1){
            domains = null;
        }

        List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.filterInvestmentInstitution(startPage,pageSize,domains,stages,types);

        List<Integer> institutionIds = investmentInstitutionsMapper.selectUserApprovalInstitution(userId);

        if (investmentInstitutionsList.size() > 0){
            for (InvestmentInstitutions iis :investmentInstitutionsList){
                InvestmentInstitutionsDto investmentInstitutionsDto = new InvestmentInstitutionsDto();
                investmentInstitutionsDto.setId(iis.getId());
                String logo = "http://img.idatavc.com/static/logo/jg_default.png";
                if (iis.getLogo() != null){
                    logo = iis.getLogo();
                }
                investmentInstitutionsDto.setLogo(logo);
                investmentInstitutionsDto.setShortName(iis.getShortName());
                investmentInstitutionsDto.setCommet(iis.getCommet());
                investmentInstitutionsDto.setCreateTime(iis.getCreateTime());
                investmentInstitutionsDto.setType(iis.getType());
                investmentInstitutionsDto.setCaseUrl(iis.getCaseUrl());
                String kenelCase = "";
                if (iis.getKenelCase() == null){
                    if (iis.getComment() != null){
                        kenelCase = iis.getComment();
                    }

                }else {
                    kenelCase = iis.getKenelCase();
                }

                investmentInstitutionsDto.setKenelCase(kenelCase);
                investmentInstitutionsDto.setCommet(iis.getCommet());
                investmentInstitutionsDto.setCity(iis.getCity());
                investmentInstitutionsDto.setStage(iis.getStage());
                investmentInstitutionsDto.setRepresentative(iis.getRepresentative());
                investmentInstitutionsDto.setApprovalStatus(iis.getApprovalStatus());
                investmentInstitutionsDto.setHomeUrl(iis.getHomeUrl());
                investmentInstitutionsDto.setYn(iis.getYn());
                investmentInstitutionsDto.setSort(iis.getSort());
                investmentInstitutionsDto.setCount(iis.getCount());
                if (institutionIds.size() > 0){
                    int a = 0;
                    for (Integer ii :institutionIds){
                        if (String.valueOf(iis.getId()).equals(String.valueOf(ii))){
                         a++;
                        }
                    }
                    if (a >0){
                        investmentInstitutionsDto.setSendyn(true);
                    }else {
                        investmentInstitutionsDto.setSendyn(false);
                    }
                }else {
                    investmentInstitutionsDto.setSendyn(false);
                }

                list.add(investmentInstitutionsDto);
            }
        }


        result.setData(list);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    /**
     * 获取满足条件机构id的私有方法
     * @param domains 领域数组
     * @param stages 阶段数组
     * @return
     */
    @Cacheable(value = "getIntegerList", keyGenerator = "wiselyKeyGenerator")
    public List<Integer> getIntegerList(Integer[] domains,Integer[] stages){

        List<Integer> result = projectsMapper.screenInvestmentAll(domains,stages);

        return result;
    }
}