package com.lhjl.tzzs.proxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ElegantServiceService;
import com.lhjl.tzzs.proxy.service.FormIdService;
import com.lhjl.tzzs.proxy.service.SendTemplateService;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import com.lhjl.tzzs.proxy.service.bluewave.BlueUserInfoService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ElegantServiceImpl implements ElegantServiceService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ElegantServiceImpl.class);

    @Value("${pageNum}")
    private Integer pageNumDefault;

    @Value("${pageSize}")
    private Integer pageSizeDefault;

    @Autowired
    private ElegantServiceMapper elegantServiceMapper;

    @Autowired
    private MetaSceneMapper metaSceneMapper;

    @Autowired
    private MetaObtainIntegralMapper metaObtainIntegralMapper;

    @Autowired
    private ElegantServiceCooperationMapper elegantServiceCooperationMapper;

    @Autowired
    private ElegantServiceDescriptionMapper elegantServiceDescriptionMapper;

    @Autowired 
    private ElegantServiceDescriptionDetailMapper elegantServiceDescriptionDetailMapper;

    @Autowired
    private ElegantServiceIdentityTypeMapper elegantServiceIdentityTypeMapper;

    @Autowired
    private ElegantServiceServiceTypeMapper elegantServiceServiceTypeMapper;

    @Autowired
    private MetaUserLevelMapper metaUserLevelMapper;

    @Autowired
    private MetaIdentityTypeMapper metaIdentityTypeMapper;

    @Autowired
    private MetaServiceTypeMapper metaServiceTypeMapper;

    @Autowired
    private ElegantServiceApproveTypeMapper elegantServiceApproveTypeMapper;

    @Autowired
    private ElegantServiceMemberTypeMapper elegantServiceMemberTypeMapper;

    @Autowired
    private ElegantServiceDescriptionUrlMapper elegantServiceDescriptionUrlMapper;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ElegantServiceRelevantProjectMapper elegantServiceRelevantProjectMapper;

    @Autowired
    private ElegantServiceParticipateMapper elegantServiceParticipateMapper;

    @Autowired
    private ElegantServiceParticipateFeedbackImagesMapper elegantServiceParticipateFeedbackImagesMapper;
    @Autowired
    private ElegantServiceParticipateFeedbackTextMapper elegantServiceParticipateFeedbackTextMapper;

    @Resource
    private SendTemplateService sendTemplateService;

    @Resource
    private UserLoginService userLoginService;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersWeixinMapper usersWeixinMapper;

    @Resource
    private FormIdService formIdService;

    /**
     * 获取精选活动列表的接口
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findElegantServiceList(ElegantServiceSearchInputDto body, Integer appid, String token) {
        CommonDto<List<Map<String,Object>>> result  = new CommonDto<>();
        Date now = new Date();

        List<Map<String,Object>> list = new ArrayList<>();

        Integer sortOrder = null;
        if (body.getCreateTimeOrder() == null){
            sortOrder = 1;
        }

        if (body.getPageNum() == null){
            body.setPageNum(pageNumDefault);
        }
        if (body.getPageSize() == null){
            body.setPageSize(pageSizeDefault);
        }


        CommonDto<Map<String, Object>> userInfo = userInfoService.getUserInfo(token);
        if (null != userInfo.getData().get("approveId")) {
            Integer[] approveIds = (Integer[]) userInfo.getData().get("approveId");
            body.setApproveType(Arrays.asList(approveIds));
        }
        if (null != userInfo.getData().get("levelId")){
            body.setMemberType((Integer)userInfo.getData().get("levelId"));
        }
        if (null != userInfo.getData().get("isLeadInvestor")){
            body.setIsLeadInvestor((Integer)userInfo.getData().get("isLeadInvestor"));
        }


        //转数组
        Integer[] identityType = {};
        if (null != body.getIdentityType() && body.getIdentityType().size()>0){
            Integer[] identityTypeA  = new Integer[body.getIdentityType().size()];
            for (int i = 0;i<body.getIdentityType().size();i++){
                identityTypeA[i] = body.getIdentityType().get(i);
            }
            identityType = identityTypeA;
        }

        Integer[] serviceType = {};
        if (null != body.getServiceType() && body.getServiceType().size()>0){
            Integer[] serviceTypeA = new Integer[body.getServiceType().size()];
            for (int i=0;i<body.getServiceType().size();i++){
                serviceTypeA[i] = body.getServiceType().get(i);
            }
            serviceType = serviceTypeA;
        }

        Integer startPage = (body.getPageNum()-1)*body.getPageSize();
        List<Map<String,Object>> elegantServiceList = elegantServiceMapper.findElegantServiceList(body.getRecommendYn(),
                body.getCreateTimeOrder(),sortOrder,appid,identityType,serviceType,body.getSearchWord(),body.getApproveType(),body.getIsLeadInvestor(),body.getIsReward(),body.getMemberType(),startPage,body.getPageSize());
        if (elegantServiceList.size() > 0){
            for (Map<String,Object> m:elegantServiceList){

                m.putIfAbsent("original_price","");
                m.putIfAbsent("background_picture","http://img.idatavc.com/static/img/serverwu.png");

                //判断是否在时间范围
                if (m.get("begin_time") != null && m.get("end_time") != null){
                    Date beginTime = (Date)m.get("begin_time");
                    Date endTime = (Date)m.get("end_time");
                    if (beginTime.getTime()<now.getTime()  && now.getTime()<endTime.getTime()){
                        list.add(m);
                    }
                }else {
                    list.add(m);
                }
            }
        }

        result.setData(list);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    /**
     * 根据服务id获取服务详情的接口
     * @param elegantServiceId 服务id
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> findElegantServiceById(Integer elegantServiceId) {
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        if (elegantServiceId == null){
            result.setData(null);
            result.setMessage("服务id不能为空");
            result.setStatus(502);

            return result;
        }

        Map<String,Object> map = elegantServiceMapper.findElegantServiceById(elegantServiceId);
        map.putIfAbsent("background_picture","http://img.idatavc.com/static/img/serverwu.png");
        map.putIfAbsent("original_price","");

        result.setStatus(200);
        result.setMessage("success");
        result.setData(map);

        return result;
    }

    /**
     * 配置服务信息的接口
     * @param body
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> insertElagantService(ElegantServiceInputDto body,Integer appid) {
        CommonDto<String> result  = new CommonDto<>();
        //先判断参数是否都录入了
        if (body.getServiceName() == null || "".equals(body.getServiceName()) || "undefined".equals(body.getServiceName())){
            result.setStatus(502);
            result.setMessage("请输入服务名称");
            result.setData(null);

            return result;
        }

        if (body.getCooperationName() == null || "".equals(body.getCooperationName()) || "undefined".equals(body.getCooperationName())){
            result.setStatus(502);
            result.setMessage("请输入合作方");
            result.setData(null);

            return result;
        }

        if (body.getUnit() == null || "".equals(body.getUnit()) || "undefined".equals(body.getUnit())){
            result.setStatus(502);
            result.setMessage("请输入限制单位");
            result.setData(null);

            return result;
        }

        if (body.getOriginalPrice() == null || "".equals(body.getOriginalPrice()) || "undefined".equals(body.getOriginalPrice()) ){
            result.setStatus(502);
            result.setMessage("请输入会员价格");
            result.setData(null);

            return result;
        }

        if (body.getVipPrice() == null|| "".equals(body.getVipPrice()) || "undefined".equals(body.getVipPrice())){
            result.setStatus(502);
            result.setMessage("请输入VIP会员价格");
            result.setData(null);

            return result;
        }

        if (body.getSort() == null ){
            result.setStatus(502);
            result.setMessage("请输入排序");
            result.setData(null);

            return result;
        }

        if (body.getBackgroundPicture() == null|| "".equals(body.getBackgroundPicture()) || "undefined".equals(body.getBackgroundPicture())){
            result.setStatus(502);
            result.setMessage("请选择封面");
            result.setData(null);

            return result;
        }

        if (body.getDescription() == null|| "".equals(body.getDescription()) || "undefined".equals(body.getDescription())){
            result.setStatus(502);
            result.setMessage("请输入描述");
            result.setData(null);

            return result;
        }

        if (body.getDetailDescription() == null|| "".equals(body.getDetailDescription()) || "undefined".equals(body.getDetailDescription())){
            result.setStatus(502);
            result.setMessage("请输入详细描述");
            result.setData(null);

            return result;
        }

        if (body.getBeginTime() == null){
            result.setStatus(502);
            result.setMessage("请输入上架时间");
            result.setData(null);

            return result;
        }

        if (body.getEndTime() == null){
            result.setStatus(502);
            result.setMessage("请输入下架时间");
            result.setData(null);

            return result;
        }

        if (body.getIdentityType() == null|| "".equals(body.getIdentityType()) || "undefined".equals(body.getIdentityType())){
            result.setStatus(502);
            result.setMessage("请选择身份类型");
            result.setData(null);

            return result;
        }

        if (body.getServiceType() == null|| "".equals(body.getServiceType()) || "undefined".equals(body.getServiceType())){
            result.setStatus(502);
            result.setMessage("请选择身份类型");
            result.setData(null);

            return result;
        }

        if (body.getWebSwitch() == null){
            result.setStatus(502);
            result.setMessage("请传入网页开关值");
            result.setData(null);

            return result;
        }

        if (body.getRecommendYn() == null){
            result.setStatus(502);
            result.setMessage("请传入推荐开关的值");
            result.setData(null);

            return result;
        }

        if (body.getOnOff() == null){
            result.setStatus(502);
            result.setMessage("请传入是否隐藏的值");
            result.setData(null);

            return result;
        }

        //开始判断值的合理性
        BigDecimal originalPrice = new BigDecimal(body.getOriginalPrice());
        originalPrice = originalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

        BigDecimal vipPrice = new BigDecimal(body.getVipPrice());
        vipPrice = vipPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

        int orig = originalPrice.compareTo(BigDecimal.ZERO);
        int vipc = vipPrice.compareTo(BigDecimal.ZERO);
        if (orig < 0){
            result.setMessage("原价必须大于0");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        if (vipc < 0){
            result.setMessage("vip价格必须大于0");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        //判断是更新还是新建
        if (body.getElegantServiceId() == null || "".equals(body.getElegantServiceId())){
            //新建
            result = createElegantService(body,appid);
        }else {
            //更新
            result = updateElegantService(body,appid); 
        }

        result.setData(null);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    /**
     * 获取基础身份类型的方法
     * @return
     */
    @Cacheable(value = "getMetaIdentityType", keyGenerator = "wiselyKeyGenerator")
    @Override
    public CommonDto<List<MetaIdentityType>> getMetaIdentityType() {
        CommonDto<List<MetaIdentityType>> result = new CommonDto<>();
        List<MetaIdentityType> list = new ArrayList<>();
        list = metaIdentityTypeMapper.selectAll();

        result.setMessage("success");
        result.setData(list);
        result.setStatus(200);

        return result;
    }

    /**
     * 获取基础服务类型的方法
     * @return
     */
    @Cacheable(value = "getMetaServiceType", keyGenerator = "wiselyKeyGenerator")
    @Override
    public CommonDto<List<MetaServiceType>> getMetaServiceType() {
        CommonDto<List<MetaServiceType>> result = new CommonDto<>();

        List<MetaServiceType> list = new ArrayList<>();
        list = metaServiceTypeMapper.selectAll();

        result.setStatus(200);
        result.setData(list);
        result.setMessage("success");

        return result;
    }

    /**
     * 获取精选服务详情的接口
     * @param elegantServiceId 精选服务id
     * @return
     */
    @Override
    public CommonDto<ElegantServiceOutputDto> getElegantServiceInfo(Integer elegantServiceId) {
        CommonDto<ElegantServiceOutputDto> result = new CommonDto<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //先验证参数
        if(elegantServiceId == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("精选服务id不能为空");

            return result;
        }

        //获取主表信息
        ElegantService elegantService = elegantServiceMapper.selectByPrimaryKey(elegantServiceId);
        if (elegantService == null){
            result.setMessage("当前精选服务id，没有找到对应信息，请检查id是否正确");
            result.setData(null);
            result.setStatus(502);

            return result;
        }
        //获取合作方表信息
        ElegantServiceCooperation elegantServiceCooperationForSearch = new ElegantServiceCooperation();
        elegantServiceCooperationForSearch.setElegantServiceId(elegantServiceId);

        ElegantServiceCooperation elegantServiceCooperation = elegantServiceCooperationMapper.selectOne(elegantServiceCooperationForSearch);

        //获取描述表信息
        ElegantServiceDescription elegantServiceDescriptionForSearch = new ElegantServiceDescription();
        elegantServiceDescriptionForSearch.setElegantServiceId(elegantServiceId);

        ElegantServiceDescription elegantServiceDescription = elegantServiceDescriptionMapper.selectOne(elegantServiceDescriptionForSearch);

        //获取详细描述表信息
        ElegantServiceDescriptionDetail elegantServiceDescriptionDetailForSearch = new ElegantServiceDescriptionDetail();
        elegantServiceDescriptionDetailForSearch.setElegantServiceId(elegantServiceId);

        ElegantServiceDescriptionDetail elegantServiceDescriptionDetail = elegantServiceDescriptionDetailMapper.selectOne(elegantServiceDescriptionDetailForSearch);

        //获取服务-身份类型关系表信息
        ElegantServiceIdentityType elegantServiceIdentityTypeForSearch = new ElegantServiceIdentityType();
        elegantServiceIdentityTypeForSearch.setElegantServiceId(elegantServiceId);

        List<ElegantServiceIdentityType> elegantServiceIdentityTypeList = elegantServiceIdentityTypeMapper.select(elegantServiceIdentityTypeForSearch);

        //获取服务-服务类型关系表信息
        ElegantServiceServiceType elegantServiceServiceTypeForSearch = new ElegantServiceServiceType();
        elegantServiceServiceTypeForSearch.setElegantServiceId(elegantServiceId);

        List<ElegantServiceServiceType> elegantServiceServiceTypeList = elegantServiceServiceTypeMapper.select(elegantServiceServiceTypeForSearch);

        //整理返回数据
        ElegantServiceOutputDto elegantServiceOutputDto = new ElegantServiceOutputDto();
        elegantServiceOutputDto.setServiceName(elegantService.getServiceName());
        String cooperationName = "";
        if (elegantServiceCooperation != null){
            cooperationName = elegantServiceCooperation.getCooperationName();
        }
        elegantServiceOutputDto.setCooperationName(cooperationName);
        if (elegantService.getVipPrice() == null){
            elegantServiceOutputDto.setVipPrice("");
        }else {
            elegantServiceOutputDto.setVipPrice(String.valueOf(elegantService.getVipPrice()));
        }
//        elegantServiceOutputDto.setVipPrice(String.valueOf(elegantService.getVipPrice()));
        if (elegantService.getOriginalPrice() == null){
            elegantServiceOutputDto.setOriginalPrice("");
        }else {
            elegantServiceOutputDto.setOriginalPrice(String.valueOf(elegantService.getOriginalPrice()));
        }
//        elegantServiceOutputDto.setOriginalPrice(String.valueOf(elegantService.getOriginalPrice()));
        elegantServiceOutputDto.setUnit(elegantService.getUnit());
        elegantServiceOutputDto.setSort(elegantService.getSort());
        elegantServiceOutputDto.setBackgroundPicture(elegantService.getBackgroundPicture());
        String description = "";
        if (elegantServiceDescription != null){
            description = elegantServiceDescription.getDescription();
        }
        elegantServiceOutputDto.setDescription(description);
        String descriptionDetail = "";
        if (elegantServiceDescriptionDetail != null){
            descriptionDetail = elegantServiceDescriptionDetail.getDetailDescription();
        }
        elegantServiceOutputDto.setDetailDescription(descriptionDetail);
        elegantServiceOutputDto.setBeginTime(sdf.format(elegantService.getBeginTime()));
        elegantServiceOutputDto.setEndTime(sdf.format(elegantService.getEndTime()));
        elegantServiceOutputDto.setWebSwitch(elegantService.getWebSwitch());
        elegantServiceOutputDto.setOnOff(elegantService.getOnOff());
        elegantServiceOutputDto.setRecommendYn(elegantService.getRecommendYn());

        //把投资人类型，服务类型放到数组里
        List<Integer> identityType = new ArrayList<>();
        List<Integer> serviceType = new ArrayList<>();
        if (elegantServiceIdentityTypeList.size()>0){
            for (ElegantServiceIdentityType est:elegantServiceIdentityTypeList){
                identityType.add(est.getMetaIdentityTypeId());
            }
        }
        if (elegantServiceServiceTypeList.size() > 0){
            for (ElegantServiceServiceType esst:elegantServiceServiceTypeList){
                serviceType.add(esst.getMetaServiceTypeId());
            }
        }

        elegantServiceOutputDto.setIdentityType(identityType);
        elegantServiceOutputDto.setServiceType(serviceType);

        result.setStatus(200);
        result.setData(elegantServiceOutputDto);
        result.setMessage("success");

        return result;
    }

    /**
     * 删除精选活动的接口
     * @param elegantServiceId 精选服务id
     * @return
     */
    @Override
    public CommonDto<String> deleteElegantServiceInfo(Integer elegantServiceId) {
        CommonDto<String> result = new CommonDto<>();

        if (elegantServiceId == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("精选服务id不能为空，请检查");

            return result;
        }

        ElegantService elegantService = new ElegantService();
        elegantService.setId(elegantServiceId);
        elegantService.setYn(0);

        elegantServiceMapper.updateByPrimaryKeySelective(elegantService);

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }


    /**
     * 后台获取精选服务列表接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> backstageElegantServiceList(BackstageElegantServiceInputDto body,Integer appid){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> map = new HashMap<>();

        Integer pageSize = pageSizeDefault;
        Integer pageNum = pageNumDefault;
        //输入参数初始化
        if (body.getPageSize() != null){
            pageSize = body.getPageSize();
        }

        if (body.getCurrentPage() != null){
            pageNum = body.getCurrentPage();
        }

        if (body.getSearchWord() == null){
            body.setSearchWord("");
        }

        //格式化时间
        String beginTime = body.getBeginTime();
        String endTime = body.getEndTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");


        Integer startPage = (pageNum-1)*pageSize;

        //获取服务主要信息
        List<Map<String,Object>> mapList = elegantServiceMapper.findBackstageElegantServiceList(body.getSearchWord(),appid,beginTime,endTime,body.getApproveType(),body.getIsLeadInvestor(),body.getIsReward(),body.getMemberType(),startPage,pageSize);
        if (mapList.size()>0){
            for (Map<String,Object> m:mapList){
                Integer esid = (Integer) m.get("id");
                List<Map<String,Object>> serviceIdentityTypeList = new ArrayList<>();
                serviceIdentityTypeList = elegantServiceIdentityTypeMapper.getServiceIndentyTypeByServiceId(esid);

                //解析成字符串
                String serviceIdentityType = "";
                for (Map<String,Object> sitl:serviceIdentityTypeList){
                    String jieguo = (String) sitl.get("type_name");
                    serviceIdentityType = serviceIdentityType + jieguo + ",";
                }
                if (serviceIdentityType.length() > 1){
                    serviceIdentityType = serviceIdentityType.substring(0,serviceIdentityType.length()-1);
                }

                m.put("serviceIdentityType",serviceIdentityType);

                List<Map<String,Object>> serviceTypeList = new ArrayList<>();
                serviceTypeList =elegantServiceServiceTypeMapper.getServiceTypeByServiceId(esid);

                String serviceType = "";
                for (Map<String,Object> st:serviceTypeList){
                    String jieguo = (String) st.get("service_type_name");
                    serviceType = serviceType + jieguo + ",";
                }
                if (serviceType.length() > 1){
                    serviceType = serviceType.substring(0,serviceType.length()-1);
                }
                m.put("serviceType",serviceType);

                //格式化时间
                String begin_time = "";
                if (m.get("begin_time") != null){
                    Date beginTimes = (Date) m.get("begin_time");
                    begin_time = sdf1.format(beginTimes);
                }

                String end_time = "";
                if (m.get("end_time") != null){
                    Date endTimes = (Date)m.get("end_time");
                    end_time = sdf1.format(endTimes);
                }

                m.put("begin_time",begin_time);
                m.put("end_time",end_time);
            }
        }

        //获取数据总量
        Integer allCount =0;
        allCount = elegantServiceMapper.selectCountBySearch(body.getSearchWord(),appid,body.getApproveType(),body.getIsLeadInvestor(),body.getIsReward(),body.getMemberType(),beginTime,endTime);

        //往结果里放数据
        map.put("list",mapList);
        map.put("currentPage",pageNum);
        map.put("total",allCount);
        map.put("pageSize",pageSize);

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Override
    public CommonDto<ElegantService> getElegantServiceInfo(Integer appId, Integer elegantServiceId) {

        ElegantService elegantService = elegantServiceMapper.selectByPrimaryKey(elegantServiceId);

        ElegantServiceDescription queryElegantServiceDescription = new ElegantServiceDescription();
        queryElegantServiceDescription.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceDescription(elegantServiceDescriptionMapper.selectOne(queryElegantServiceDescription));

        ElegantServiceCooperation queryElegantServiceCooperation = new ElegantServiceCooperation();
        queryElegantServiceCooperation.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceCooperation(elegantServiceCooperationMapper.selectOne(queryElegantServiceCooperation));

        ElegantServiceDescriptionDetail queryElegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
        queryElegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceDescriptionDetail(elegantServiceDescriptionDetailMapper.selectOne(queryElegantServiceDescriptionDetail));

        ElegantServiceApproveType queryElegantServiceApproveType = new ElegantServiceApproveType();
        queryElegantServiceApproveType.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceApproveTypes(elegantServiceApproveTypeMapper.select(queryElegantServiceApproveType));

        ElegantServiceIdentityType queryElegantServiceIdentityType = new ElegantServiceIdentityType();
        queryElegantServiceIdentityType.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceIdentityTypes(elegantServiceIdentityTypeMapper.select(queryElegantServiceIdentityType));

        ElegantServiceMemberType queryElegantServiceMemberType = new ElegantServiceMemberType();
        queryElegantServiceMemberType.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceMemberTypes(elegantServiceMemberTypeMapper.select(queryElegantServiceMemberType));

        ElegantServiceServiceType queryElegantServiceServiceType = new ElegantServiceServiceType();
        queryElegantServiceServiceType.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceServiceTypes(elegantServiceServiceTypeMapper.select(queryElegantServiceServiceType));

        ElegantServiceRelevantProject elegantServiceRelevantProject = new ElegantServiceRelevantProject();
        elegantServiceRelevantProject.setElegantServiceId(elegantServiceId);
        elegantService.setElegantServiceRelevantProject(elegantServiceRelevantProjectMapper.selectOne(elegantServiceRelevantProject));

        return new CommonDto<>(elegantService, "success", 200);
    }

    /**
     * 保存反馈信息的接口
     * @param body
     * @param appId
     * @param token
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> saveOrUpdateParticipate(ElegantServiceParticipate body, Integer appId, String token) {

        Integer elegantServiceParticipateId = null;

        if (null != body.getId()){
            ElegantServiceParticipate elegantServiceParticipate = new ElegantServiceParticipate();
            elegantServiceParticipate.setStatus(1);
            elegantServiceParticipate.setCompletionTime(DateTime.now().toDate());
            elegantServiceParticipate.setId(body.getId());
            elegantServiceParticipateMapper.updateByPrimaryKeySelective(elegantServiceParticipate);

            elegantServiceParticipateId = body.getId();
        }else {
            body.setAppid(appId);
            body.setCreateTime(DateTime.now().toDate());
            elegantServiceParticipateMapper.insertSelective(body);

            elegantServiceParticipateId = body.getId();
        }

        // 更新图片和文字
        saveOrUpdateElegantServiceParticipateFeedback(elegantServiceParticipateId,body);
        if (null == body.getFormid()){
            return new CommonDto<>(null,"formid不能为空",502);
        }
        //保存formid
        Integer userId = userLoginService.getUserIdByToken(body.getToken(),appId);
        if (userId == -1){
            return new CommonDto<>(null,"token失效",502);
        }

        CommonDto<String> resultS =  formIdService.saveFormid(userId,body.getFormid(),null);
        if (resultS.getStatus() != 200){
            return resultS;
        }

        return new CommonDto<>("ok","success",200);
    }

    /**
     * 查询反馈列表的接口
     * @param appId
     * @param elegantServiceId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Transactional
    @Override
    public CommonDto<List<ElegantServiceParticipate>> queryParticipate(Integer appId, Integer elegantServiceId, Integer pageNo, Integer pageSize) {

        if (null == elegantServiceId){
            return new CommonDto<>(null,"服务id不能为空",502);
        }

        if (null == pageNo){
            pageNo = pageNumDefault;
        }

        if (null == pageSize){
            pageSize = pageSizeDefault;
        }

        Integer startPage = (pageNo-1)*pageSize;
        List<ElegantServiceParticipate> elegantServiceParticipates = elegantServiceParticipateMapper.getElegantServiceParticipateList(appId, elegantServiceId, pageNo, pageSize);

        return new CommonDto<>(elegantServiceParticipates,"success", 200);
    }

    /**
     * 查询单条反馈的接口
     * @param appId
     * @param elegantServiceParticipateId
     * @param token
     * @return
     */
    @Override
    public CommonDto<ElegantServiceParticipate> queryParticipate(Integer appId, Integer elegantServiceParticipateId, String token) {

        if (null == elegantServiceParticipateId){
            return new CommonDto<>(null,"精选服务反馈id不能为空",502);
        }

        ElegantServiceParticipate elegantServiceParticipate = elegantServiceParticipateMapper.getElegantServiceParticipateById(appId, elegantServiceParticipateId);

        if (null == elegantServiceParticipate){
            return new CommonDto<>(null,"没有找到当前反馈id对应的反馈内容",502);
        }

        //获取图文信息
        ElegantServiceParticipateFeedbackImages elegantServiceParticipateFeedbackImagesForSearch = new ElegantServiceParticipateFeedbackImages();
        elegantServiceParticipateFeedbackImagesForSearch.setElegantServiceParticipateId(elegantServiceParticipateId);

        List<ElegantServiceParticipateFeedbackImages> elegantServiceParticipateFeedbackImages = elegantServiceParticipateFeedbackImagesMapper.select(elegantServiceParticipateFeedbackImagesForSearch);

        ElegantServiceParticipateFeedbackText elegantServiceParticipateFeedbackTextForSearch = new ElegantServiceParticipateFeedbackText();
        elegantServiceParticipateFeedbackTextForSearch.setElegantServiceParticipateId(elegantServiceParticipateId);

        List<ElegantServiceParticipateFeedbackText> elegantServiceParticipateFeedbackText = elegantServiceParticipateFeedbackTextMapper.select(elegantServiceParticipateFeedbackTextForSearch);

        elegantServiceParticipate.setFeedbackImages(elegantServiceParticipateFeedbackImages);
        elegantServiceParticipate.setFeedbackTexts(elegantServiceParticipateFeedbackText);

        Integer userId = userLoginService.getUserIdByToken(elegantServiceParticipate.getToken(),appId);
        if (userId == -1){
            return new CommonDto<>(null,"当前记录的用户tokne无效",502);
        }

        CommonDto<UserFormid> resultS = formIdService.findUserFormid(userId,null);
        if (resultS.getStatus() != 200){
            CommonDto<ElegantServiceParticipate> result = new CommonDto<>();
            result.setStatus(resultS.getStatus());
            result.setMessage(resultS.getMessage());
            return result;
        }

        elegantServiceParticipate.setFormid(resultS.getData().getFormid());

        return new CommonDto<>(elegantServiceParticipate,"success", 200);
    }

    @Override
    public CommonDto<String> saveOrUpdateParticipateFeedback(ElegantServiceParticipateDto body, Integer appId, String token) {

        ElegantServiceParticipateFeedbackText espftRecord = new ElegantServiceParticipateFeedbackText();
        espftRecord.setElegantServiceParticipateId(body.getElegantServiceParticipateFeedbackText().getElegantServiceParticipateId());

        elegantServiceParticipateFeedbackTextMapper.delete(espftRecord);

        ElegantServiceParticipateFeedbackImages espfiRecord = new ElegantServiceParticipateFeedbackImages();
        espfiRecord.setElegantServiceParticipateId(body.getElegantServiceParticipateFeedbackText().getElegantServiceParticipateId());
        elegantServiceParticipateFeedbackImagesMapper.delete(espfiRecord);

        for(ElegantServiceParticipateFeedbackImages temp : body.getElegantServiceParticipateFeedbackImages()) {
            elegantServiceParticipateFeedbackImagesMapper.insert(temp);
        }

        elegantServiceParticipateFeedbackTextMapper.insert(body.getElegantServiceParticipateFeedbackText());

        return null;
    }

    @Transactional
    @Override
    public CommonDto<String> updateParticipateStatus(ElegantServiceParticipate body, Integer appId) {

        if (null == body.getId() || null == body.getStatus()){
            return new CommonDto<>(null,"记录id和状态都不能为空",502);
        }

        body.setCompletionTime(DateTime.now().toDate());

        elegantServiceParticipateMapper.updateByPrimaryKeySelective(body);

        // 发模板消息
        CommonDto<String> result = elegantServiceParticipateSendTamplate(body, appId);
        if (result.getStatus() != 200){
            return result;
        }

        return new CommonDto<>(null,"success",200);
    }


    /**
     * 随机字符生成器
     * @param length
     * @return
     */
    private static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            stringBuffer.append(base.charAt(number));
        }
        return stringBuffer.toString();
    }

    /**
     * 新建精选服务方法
     * @param body
     * @return
     */
    @Transactional
    public CommonDto<String> createElegantService(ElegantServiceInputDto body,Integer appid){
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        //价格转化
        BigDecimal originalPrice = new BigDecimal(body.getOriginalPrice());
        originalPrice = originalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

        BigDecimal vipPrice = new BigDecimal(body.getVipPrice());
        vipPrice = vipPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        //上下架时间转化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime = null;
        Date endTime =null;
        try {
            beginTime = sdf.parse(body.getBeginTime());
            endTime = sdf.parse(body.getEndTime());

        }catch (java.lang.Exception e){
            log.error("时间解析出错");
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("时间解析出错，请检查输入时间");
            result.setStatus(502);

            return result;
        }
        //生成场景码
        String sceneKey = getRandomString(8);
        //保险起见先看看有没重复的scenceKey
        Example sceneExample = new Example(MetaScene.class);
        sceneExample.and().andEqualTo("key",sceneKey);

        List<MetaScene> metaSceneList = metaSceneMapper.selectByExample(sceneExample);
        if (metaSceneList.size() > 0){
            sceneKey = getRandomString(8);
        }
        //先创建场景信息
        MetaScene metaScene = new MetaScene();
        metaScene.setCreateTime(now);
        metaScene.setDesc(body.getServiceName());
        metaScene.setKey(sceneKey);
        metaScene.setYn(0);

        metaSceneMapper.insertSelective(metaScene);

         float one = 1;
        //创建场景获取积分场景表信息
          //获取元数据表会员id
          List<MetaUserLevel> metaUserLevelList = metaUserLevelMapper.selectAll();
        if (metaUserLevelList.size() > 0){
            for (MetaUserLevel mul: metaUserLevelList){
                MetaObtainIntegral metaObtainIntegral = new MetaObtainIntegral();
                metaObtainIntegral.setCreateTime(now);
                metaObtainIntegral.setSceneKey(sceneKey);
                metaObtainIntegral.setUserLevel(mul.getId());
                metaObtainIntegral.setIntegral(originalPrice);
                metaObtainIntegral.setPeriod(365);
                metaObtainIntegral.setYn(0);
                metaObtainIntegral.setRatio(one);

                metaObtainIntegralMapper.insertSelective(metaObtainIntegral);
            }

        }else {
            result.setMessage("用户等级元数据表被谁删了？现在没数据了");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        //创建精选活动表
        ElegantService elegantService = new ElegantService();
        elegantService.setServiceName(body.getServiceName());
        elegantService.setOriginalPrice(originalPrice);
        elegantService.setVipPrice(vipPrice);
        elegantService.setPreVipPriceDescript("VIP会员");//默认前缀
        elegantService.setPriceUnit(0);//默认人民币
        elegantService.setUnit(body.getUnit());
        elegantService.setBackgroundPicture(body.getBackgroundPicture());
        elegantService.setBeginTime(beginTime);
        elegantService.setEndTime(endTime);
        elegantService.setOnOff(body.getOnOff());
        elegantService.setRecommendYn(body.getRecommendYn());
        elegantService.setSort(body.getSort());
        elegantService.setCreateTime(now);
        elegantService.setScenceKey(sceneKey);
        elegantService.setYn(1);//默认是未删除的，有效的
        elegantService.setAppid(appid);
        elegantService.setWebSwitch(body.getWebSwitch());
        elegantService.setIsReward(body.getIsReward());
        elegantService.setIsLeadInvestor(body.getIsLeadInvestor());
        elegantService.setCommissionPublish(body.getCommissionPublish());
        elegantService.setCommissionPublishFixed(body.getCommissionPublishFixed());
        elegantService.setCommissionReceiver(body.getCommissionReceiver());
        elegantService.setCommissionReceiverFixed(body.getCommissionReceiverFixed());
        elegantService.setCustomButtonLabel(body.getCustomButtonLabel());
        elegantService.setEntrepreneurLandingPage(body.getEntrepreneurLandingPage());
        elegantService.setInvestorLandingPage(body.getInvestorLandingPage());
        elegantService.setOrthorLandingPage(body.getOrthorLandingPage());
        elegantService.setQuantity(body.getQuantity());
        elegantServiceMapper.insertSelective(elegantService);

        //拿到服务表的id
        int elegantServiceId = elegantService.getId();

        //创建合作方表
        ElegantServiceCooperation elegantServiceCooperation = new ElegantServiceCooperation();
        elegantServiceCooperation.setElegantServiceId(elegantServiceId);
        elegantServiceCooperation.setCooperationName(body.getCooperationName());
        elegantServiceCooperation.setCreateTime(now);
        elegantServiceCooperation.setYn(1);//默认没被删除

        elegantServiceCooperationMapper.insertSelective(elegantServiceCooperation);
        //创建描述表
        ElegantServiceDescription elegantServiceDescription = new ElegantServiceDescription();
        elegantServiceDescription.setElegantServiceId(elegantServiceId);
        elegantServiceDescription.setCreateTime(now);
        elegantServiceDescription.setDescription(body.getDescription());
        elegantServiceDescription.setYn(1);//默认有效

        elegantServiceDescriptionMapper.insertSelective(elegantServiceDescription);
        //创建详细描述表
        if (null != body.getDetailDescription()) {
            ElegantServiceDescriptionDetail elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
            elegantServiceDescriptionDetail.setCreateTime(now);
            elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);
            if (body.getWebSwitch() == 1) {
                elegantServiceDescriptionDetail.setDescriptionType(0);
            } else {
                elegantServiceDescriptionDetail.setDescriptionType(1);//默认展示卡片详情
            }
            elegantServiceDescriptionDetail.setDetailDescription(body.getDetailDescription());
            elegantServiceDescriptionDetail.setYn(1);//默认有效

            elegantServiceDescriptionDetailMapper.insertSelective(elegantServiceDescriptionDetail);
        }
        //创建服务-身份类型关系表
          //解析输入参数
        String[] identityType = body.getIdentityType().split(",");
          //找到元数据表的id
        for (String s:identityType){
            Integer identityTypeId = Integer.parseInt(s);

            ElegantServiceIdentityType elegantServiceIdentityType = new ElegantServiceIdentityType();
            elegantServiceIdentityType.setElegantServiceId(elegantServiceId);
            elegantServiceIdentityType.setMetaIdentityTypeId(identityTypeId);
            elegantServiceIdentityType.setCreateTime(now);

            elegantServiceIdentityTypeMapper.insertSelective(elegantServiceIdentityType);
        }

        //创建服务-服务类型关系表
          //解析服务类型
        String[] serviceType = body.getServiceType().split(",");
        for (String s:serviceType){
            Integer serviceTypeId = Integer.parseInt(s);
            ElegantServiceServiceType elegantServiceServiceType = new ElegantServiceServiceType();
            elegantServiceServiceType.setElegantServiceId(elegantServiceId);
            elegantServiceServiceType.setMetaServiceTypeId(serviceTypeId);

            elegantServiceServiceTypeMapper.insertSelective(elegantServiceServiceType);
        }
        elegantServiceApproveMemberHandler(body, elegantServiceId);

        if (null != body.getDetailUrls() && body.getDetailUrls().size() > 0) {
            ElegantServiceDescriptionUrl elegantServiceDescriptionUrl = null;
            StringBuffer stringBuffer = new StringBuffer("");
            for (String url : body.getDetailUrls()) {
                elegantServiceDescriptionUrl = new ElegantServiceDescriptionUrl();
                elegantServiceDescriptionUrl.setUrl(url);
                elegantServiceDescriptionUrl.setElegantServiceId(elegantServiceId);
                stringBuffer.append("<p ><br></p > <p >< img src=\"").append(url).append("\"/></p >");
                elegantServiceDescriptionUrlMapper.insert(elegantServiceDescriptionUrl);
            }
            if (null == body.getDetailDescription() ) {
                ElegantServiceDescriptionDetail elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
                elegantServiceDescriptionDetail.setCreateTime(now);
                elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);
                elegantServiceDescriptionDetail.setDescriptionType(1);//默认展示卡片详情
                elegantServiceDescriptionDetail.setDetailDescription(stringBuffer.toString());
                elegantServiceDescriptionDetail.setYn(1);//默认有效

                elegantServiceDescriptionDetailMapper.insertSelective(elegantServiceDescriptionDetail);
            }
        }

        return result;
    }

    /**
     * 更新精选服务的方法
     * @param body
     * @return
     */
    @Transactional
    public CommonDto<String> updateElegantService(ElegantServiceInputDto body,Integer appid){
        CommonDto<String> result  = new CommonDto<>();
        Date now = new Date();

        //价格转化
        BigDecimal originalPrice = new BigDecimal(body.getOriginalPrice());
        originalPrice = originalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

        BigDecimal vipPrice = new BigDecimal(body.getVipPrice());
        vipPrice = vipPrice.setScale(2,BigDecimal.ROUND_HALF_UP);

        //上下架时间转化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime = null;
        Date endTime =null;
        try {
            beginTime = sdf.parse(body.getBeginTime());
            endTime = sdf.parse(body.getEndTime());

        }catch (java.lang.Exception e){
            log.error("时间解析出错");
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("时间解析出错，请检查输入时间");
            result.setStatus(502);

            return result;
        }

        //服务id
        Integer elegantServiceId = body.getElegantServiceId();

        //首先更新精选服务表的信息
        ElegantService elegantService = new ElegantService();
        elegantService.setId(elegantServiceId);
        elegantService.setServiceName(body.getServiceName());
        elegantService.setOriginalPrice(originalPrice);
        elegantService.setVipPrice(vipPrice);
        elegantService.setPreVipPriceDescript("VIP会员");//默认前缀
        elegantService.setPriceUnit(0);//默认人民币
        elegantService.setUnit(body.getUnit());
        elegantService.setBackgroundPicture(body.getBackgroundPicture());
        elegantService.setBeginTime(beginTime);
        elegantService.setEndTime(endTime);
        elegantService.setOnOff(body.getOnOff());
        elegantService.setRecommendYn(body.getRecommendYn());
        elegantService.setSort(body.getSort());
        elegantService.setCreateTime(now);
        elegantService.setYn(1);//默认是未删除的，有效的
        elegantService.setAppid(appid);
        elegantService.setWebSwitch(body.getWebSwitch());
        elegantService.setIsReward(body.getIsReward());
        elegantService.setIsLeadInvestor(body.getIsLeadInvestor());
        elegantService.setCommissionPublish(body.getCommissionPublish());
        elegantService.setCommissionPublishFixed(body.getCommissionPublishFixed());
        elegantService.setCommissionReceiver(body.getCommissionReceiver());
        elegantService.setCommissionReceiverFixed(body.getCommissionReceiverFixed());
        elegantService.setCustomButtonLabel(body.getCustomButtonLabel());
        elegantService.setEntrepreneurLandingPage(body.getEntrepreneurLandingPage());
        elegantService.setInvestorLandingPage(body.getInvestorLandingPage());
        elegantService.setOrthorLandingPage(body.getOrthorLandingPage());
        elegantService.setQuantity(body.getQuantity());


        elegantServiceMapper.updateByPrimaryKeySelective(elegantService);

        //更新合作机构表
        ElegantServiceCooperation elegantServiceCooperation = new ElegantServiceCooperation();
        elegantServiceCooperation.setElegantServiceId(elegantServiceId);

        ElegantServiceCooperation elegantServiceCooperationForUpdate = elegantServiceCooperationMapper.selectOne(elegantServiceCooperation);

        if (elegantServiceCooperationForUpdate != null){

            ElegantServiceCooperation elegantServiceCooperationUpdate = new ElegantServiceCooperation();

            elegantServiceCooperationUpdate.setCooperationName(body.getCooperationName());
            elegantServiceCooperationUpdate.setElegantServiceId(elegantServiceCooperationForUpdate.getElegantServiceId());
            elegantServiceCooperationUpdate.setId(elegantServiceCooperationForUpdate.getId());

            elegantServiceCooperationMapper.updateByPrimaryKeySelective(elegantServiceCooperationUpdate);

        }else {
            result.setMessage("合作机构表信息不存在，可能由于手动删数据，删出问题了");
            result.setData(null);
            result.setStatus(502);

            return result;
        }


        //更新精选服务描述表
        ElegantServiceDescription elegantServiceDescription = new ElegantServiceDescription();
        elegantServiceDescription.setElegantServiceId(elegantServiceId);

        ElegantServiceDescription elegantServiceDescriptionForUpdate = elegantServiceDescriptionMapper.selectOne(elegantServiceDescription);

        if (elegantServiceDescriptionForUpdate != null){
            ElegantServiceDescription elegantServiceDescriptionUpdate = new ElegantServiceDescription();

            elegantServiceDescriptionUpdate.setElegantServiceId(elegantServiceDescriptionForUpdate.getElegantServiceId());
            elegantServiceDescriptionUpdate.setDescription(body.getDescription());
            elegantServiceDescriptionUpdate.setId(elegantServiceDescriptionForUpdate.getId());

            elegantServiceDescriptionMapper.updateByPrimaryKeySelective(elegantServiceDescriptionUpdate);
        }else {
            result.setMessage("合作机构表信息不存在，可能由于手动删数据，删出问题了");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        //更新精选服务详细描述表
        if(null != body.getDetailDescription()) {
            ElegantServiceDescriptionDetail elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
            elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);

            ElegantServiceDescriptionDetail elegantServiceDescriptionDetailForUpdate = elegantServiceDescriptionDetailMapper.selectOne(elegantServiceDescriptionDetail);
            if (elegantServiceDescriptionDetailForUpdate != null) {
                ElegantServiceDescriptionDetail elegantServiceDescriptionDetailUpdate = new ElegantServiceDescriptionDetail();
                elegantServiceDescriptionDetailUpdate.setElegantServiceId(elegantServiceDescriptionDetailForUpdate.getElegantServiceId());
                elegantServiceDescriptionDetailUpdate.setDetailDescription(body.getDetailDescription());
                elegantServiceDescriptionDetailUpdate.setId(elegantServiceDescriptionDetailForUpdate.getId());
                if (body.getWebSwitch() == 1) {
                    elegantServiceDescriptionDetailUpdate.setDescriptionType(0);
                } else {
                    elegantServiceDescriptionDetailUpdate.setDescriptionType(1);
                }

                elegantServiceDescriptionDetailMapper.updateByPrimaryKeySelective(elegantServiceDescriptionDetailUpdate);
            }else{
                elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
                elegantServiceDescriptionDetail.setCreateTime(now);
                elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);
                if (body.getWebSwitch() == 1) {
                    elegantServiceDescriptionDetail.setDescriptionType(0);
                } else {
                    elegantServiceDescriptionDetail.setDescriptionType(1);//默认展示卡片详情
                }
                elegantServiceDescriptionDetail.setDetailDescription(body.getDetailDescription());
                elegantServiceDescriptionDetail.setYn(1);//默认有效

                elegantServiceDescriptionDetailMapper.insertSelective(elegantServiceDescriptionDetail);
            }
        }
        //更新身份类型表
          //先删除原来的数据
        ElegantServiceIdentityType elegantServiceIdentityTypeForDelete = new ElegantServiceIdentityType();
        elegantServiceIdentityTypeForDelete.setElegantServiceId(elegantServiceId);

        elegantServiceIdentityTypeMapper.delete(elegantServiceIdentityTypeForDelete);

          //新建新的数据
        String[] identityType = body.getIdentityType().split(",");
        for (String s:identityType){
            Integer identityTypeId = Integer.parseInt(s);

            ElegantServiceIdentityType elegantServiceIdentityType = new ElegantServiceIdentityType();
            elegantServiceIdentityType.setElegantServiceId(elegantServiceId);
            elegantServiceIdentityType.setMetaIdentityTypeId(identityTypeId);
            elegantServiceIdentityType.setCreateTime(now);

            elegantServiceIdentityTypeMapper.insertSelective(elegantServiceIdentityType);
        }

        //更新服务类型关系表
            //先删除原来的
        ElegantServiceServiceType elegantServiceServiceTypeForDelete = new ElegantServiceServiceType();
        elegantServiceServiceTypeForDelete.setElegantServiceId(elegantServiceId);

        elegantServiceServiceTypeMapper.delete(elegantServiceServiceTypeForDelete);

           //创建新的
        String[] serviceType = body.getServiceType().split(",");
        for (String s:serviceType){
            Integer serviceTypeId = Integer.parseInt(s);
            ElegantServiceServiceType elegantServiceServiceType = new ElegantServiceServiceType();
            elegantServiceServiceType.setElegantServiceId(elegantServiceId);
            elegantServiceServiceType.setMetaServiceTypeId(serviceTypeId);

            elegantServiceServiceTypeMapper.insertSelective(elegantServiceServiceType);
        }


        elegantServiceApproveMemberHandler(body, elegantServiceId);


        if (null != body.getDetailUrls() && body.getDetailUrls().size() > 0) {
            ElegantServiceDescriptionUrl elegantServiceDescriptionUrlForDelete = new ElegantServiceDescriptionUrl();
            elegantServiceDescriptionUrlForDelete.setElegantServiceId(elegantServiceId);
            elegantServiceDescriptionUrlMapper.delete(elegantServiceDescriptionUrlForDelete);
            ElegantServiceDescriptionDetail elegantServiceDescriptionDetailForDelete = new ElegantServiceDescriptionDetail();



            ElegantServiceDescriptionUrl elegantServiceDescriptionUrl = null;
            StringBuffer stringBuffer = new StringBuffer("");
            for (String url : body.getDetailUrls()) {
                elegantServiceDescriptionUrl = new ElegantServiceDescriptionUrl();
                elegantServiceDescriptionUrl.setUrl(url);
                elegantServiceDescriptionUrl.setElegantServiceId(elegantServiceId);
                stringBuffer.append("<p ><br></p > <p >< img src=\"").append(url).append("\"/></p >");
                elegantServiceDescriptionUrlMapper.insert(elegantServiceDescriptionUrl);
            }
            if (null == body.getDetailDescription() ) {
                ElegantServiceDescriptionDetail elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
                elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);

                ElegantServiceDescriptionDetail elegantServiceDescriptionDetailForUpdate = elegantServiceDescriptionDetailMapper.selectOne(elegantServiceDescriptionDetail);
                if (elegantServiceDescriptionDetailForUpdate != null) {
                    ElegantServiceDescriptionDetail elegantServiceDescriptionDetailUpdate = new ElegantServiceDescriptionDetail();
                    elegantServiceDescriptionDetailUpdate.setElegantServiceId(elegantServiceDescriptionDetailForUpdate.getElegantServiceId());
                    elegantServiceDescriptionDetailUpdate.setDetailDescription(stringBuffer.toString());
                    elegantServiceDescriptionDetailUpdate.setId(elegantServiceDescriptionDetailForUpdate.getId());
                    if (body.getWebSwitch() == 1) {
                        elegantServiceDescriptionDetailUpdate.setDescriptionType(0);
                    } else {
                        elegantServiceDescriptionDetailUpdate.setDescriptionType(1);
                    }

                    elegantServiceDescriptionDetailMapper.updateByPrimaryKeySelective(elegantServiceDescriptionDetailUpdate);
                }else{
                    elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
                    elegantServiceDescriptionDetail.setCreateTime(now);
                    elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);
                    if (body.getWebSwitch() == 1) {
                        elegantServiceDescriptionDetail.setDescriptionType(0);
                    } else {
                        elegantServiceDescriptionDetail.setDescriptionType(1);//默认展示卡片详情
                    }
                    elegantServiceDescriptionDetail.setDetailDescription(stringBuffer.toString());
                    elegantServiceDescriptionDetail.setYn(1);//默认有效

                    elegantServiceDescriptionDetailMapper.insertSelective(elegantServiceDescriptionDetail);
                }
            }
        }


        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }

    public void elegantServiceApproveMemberHandler(ElegantServiceInputDto body, Integer elegantServiceId) {
        ElegantServiceApproveType elegantServiceApproveTypeRecord = new ElegantServiceApproveType();
        elegantServiceApproveTypeRecord.setElegantServiceId(elegantServiceId);

        elegantServiceApproveTypeMapper.delete(elegantServiceApproveTypeRecord);
		if(body.getApproveTypes() != null) {
			for (Integer approveType : body.getApproveTypes() ){
	            ElegantServiceApproveType elegantServiceApproveType = new ElegantServiceApproveType();
	            elegantServiceApproveType.setElegantServiceId(elegantServiceId);
	            elegantServiceApproveType.setApproveType(approveType);
	            elegantServiceApproveTypeMapper.insert(elegantServiceApproveType);
	        }
		}
        
        ElegantServiceMemberType elegantServiceMemberTypeRecord = new ElegantServiceMemberType();
        elegantServiceMemberTypeRecord.setElegantServiceId(elegantServiceId);
        elegantServiceMemberTypeMapper.delete(elegantServiceMemberTypeRecord);
        if(body.getMemberTypes() != null) {
        	for (Integer memberType : body.getMemberTypes() ){
                ElegantServiceMemberType elegantServiceMemberType = new ElegantServiceMemberType();
                elegantServiceMemberType.setElegantServiceId(elegantServiceId);
                elegantServiceMemberType.setMemberTypeId(memberType);
                elegantServiceMemberTypeMapper.insert(elegantServiceMemberType);
            }
        }

        if (null != body.getProjectId() && null!=body.getProjectShortName()){
            ElegantServiceRelevantProject elegantServiceRelevantProjectRecord = new ElegantServiceRelevantProject();
            elegantServiceRelevantProjectRecord.setElegantServiceId(elegantServiceId);
            elegantServiceRelevantProjectMapper.delete(elegantServiceRelevantProjectRecord);
            elegantServiceRelevantProjectRecord.setProjectId(body.getProjectId());
            elegantServiceRelevantProjectRecord.setProjectShortName(body.getProjectShortName());
            elegantServiceRelevantProjectMapper.insert(elegantServiceRelevantProjectRecord);
        }

    }

    /**
     * 保存图片和描述的接口
     * @param elegantServiceParticipateId
     * @param body
     */
    @Transactional
    public void saveOrUpdateElegantServiceParticipateFeedback(Integer elegantServiceParticipateId, ElegantServiceParticipate body){

        //先删除原来的
        ElegantServiceParticipateFeedbackImages elegantServiceParticipateFeedbackImages = new ElegantServiceParticipateFeedbackImages();
        elegantServiceParticipateFeedbackImages.setElegantServiceParticipateId(elegantServiceParticipateId);

        elegantServiceParticipateFeedbackImagesMapper.delete(elegantServiceParticipateFeedbackImages);

        ElegantServiceParticipateFeedbackText elegantServiceParticipateFeedbackText = new ElegantServiceParticipateFeedbackText();
        elegantServiceParticipateFeedbackText.setElegantServiceParticipateId(elegantServiceParticipateId);

        elegantServiceParticipateFeedbackTextMapper.delete(elegantServiceParticipateFeedbackText);

        //新增新的
        if (null != body.getFeedbackImages() && body.getFeedbackImages().size()>0){
            body.getFeedbackImages().forEach(e->{
                e.setElegantServiceParticipateId(elegantServiceParticipateId);

                elegantServiceParticipateFeedbackImagesMapper.insertSelective(e);
            });
        }

        if (null != body.getFeedbackTexts() && body.getFeedbackTexts().size() > 0){
            body.getFeedbackTexts().forEach(e->{
                e.setElegantServiceParticipateId(elegantServiceParticipateId);

                elegantServiceParticipateFeedbackTextMapper.insertSelective(e);
            });
        }
    }

    /**
     * 精选服务反馈发消息接口
     * @param body
     * @param appId
     * @return
     */
    @Transactional
    public CommonDto<String> elegantServiceParticipateSendTamplate(ElegantServiceParticipate body, Integer appId){
        CommonDto<String> result = new CommonDto<>();

        String userName = "";
        String formId = "";

        Integer userId = userLoginService.getUserIdByToken(body.getToken(),appId);

        CommonDto<UserFormid> resulta = formIdService.findUserFormid(userId,null);
        if (resulta.getStatus() != 200){
            result.setMessage(resulta.getMessage());
            result.setStatus(resulta.getStatus());

            return result;
        }
        formId = resulta.getData().getFormid();

        ElegantServiceParticipate elegantServiceParticipate = elegantServiceParticipateMapper.selectByPrimaryKey(body.getId());
        if (null == elegantServiceParticipate){
            return new CommonDto<>(null,"当前id没有找到对应的反馈信息",502);
        }

        ElegantService elegantService = elegantServiceMapper.selectByPrimaryKey(elegantServiceParticipate.getElegantServiceId());

        Users usersForSearch = new Users();
        usersForSearch.setUuid(elegantService.getCreator());

        Users users = usersMapper.selectOne(usersForSearch);
        userName = users.getActualName();
        if (null==userName){
            UsersWeixin usersWeixinForSearch = new UsersWeixin();
            usersWeixinForSearch.setUserId(users.getId());
            userName = usersWeixinMapper.selectOne(usersWeixinForSearch).getNickName();
        }

        if (null == userName){
            userName = "";
        }

        if (body.getStatus() == 2){
            String title = "天使投资指数悬赏任务";
            String messege = "你完成了"+userName+"的悬赏任务,奖励已入钱包,快去查看吧";

            result = sendTemplateService.sendTemplateByFormId(userId,2,formId,messege,title);

        }else {
            result.setData(null);
            result.setStatus(200);
            result.setMessage("success");
        }


        return result;
    }
}
