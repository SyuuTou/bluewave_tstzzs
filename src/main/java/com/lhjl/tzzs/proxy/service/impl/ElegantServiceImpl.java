package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.BackstageElegantServiceInputDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.BackstageElegantServiceOutputDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.ElegantServiceInputDto;
import com.lhjl.tzzs.proxy.dto.ElegantServiceDto.ElegantServiceOutputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ElegantServiceService;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired MetaServiceTypeMapper metaServiceTypeMapper;

    /**
     * 获取精选活动列表的接口
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findElegantServiceList() {
        CommonDto<List<Map<String,Object>>> result  = new CommonDto<>();
        Date now = new Date();

        List<Map<String,Object>> list = new ArrayList<>();

        List<Map<String,Object>> elegantServiceList = elegantServiceMapper.findElegantServiceList();
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
    public CommonDto<String> insertElagantService(ElegantServiceInputDto body) {
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
            result.setData(null);
            result.setData(null);

            return result;
        }

        //判断是更新还是新建
        if (body.getElegantServiceId() == null || "".equals(body.getElegantServiceId())){
            //新建
            result = createElegantService(body);
        }else {
            //更新
            result = updateElegantService(body);
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
        elegantServiceOutputDto.setCooperationName(elegantServiceCooperation.getCooperationName());
        elegantServiceOutputDto.setVipPrice(String.valueOf(elegantService.getVipPrice()));
        elegantServiceOutputDto.setOriginalPrice(String.valueOf(elegantService.getOriginalPrice()));
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
    public CommonDto<List<BackstageElegantServiceOutputDto>> backstageElegantServiceList(BackstageElegantServiceInputDto body) {
        CommonDto<List<BackstageElegantServiceOutputDto>> result = new CommonDto<>();
        List<BackstageElegantServiceOutputDto> list = new ArrayList<>();

        Integer pageSize = pageSizeDefault;
        Integer pageNum = pageNumDefault;
        //输入参数初始化
        if (body.getPageSize() != null){
            pageSize = body.getPageSize();
        }

        if (body.getPageNumber() != null){
            pageNum = body.getPageNumber();
        }


        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");

        return result;
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
    public CommonDto<String> createElegantService(ElegantServiceInputDto body){
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
                metaObtainIntegral.setIntegral(originalPrice.intValue());
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
        elegantService.setWebSwitch(body.getWebSwitch());

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
        ElegantServiceDescriptionDetail elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
        elegantServiceDescriptionDetail.setCreateTime(now);
        elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);
        elegantServiceDescriptionDetail.setDescriptionType(1);//默认展示卡片详情
        elegantServiceDescriptionDetail.setDetailDescription(body.getDetailDescription());
        elegantServiceDescriptionDetail.setYn(1);//默认有效

        elegantServiceDescriptionDetailMapper.insertSelective(elegantServiceDescriptionDetail);

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


        return result;
    }

    /**
     * 更新精选服务的方法
     * @param body
     * @return
     */
    @Transactional
    public CommonDto<String> updateElegantService(ElegantServiceInputDto body){
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
        elegantService.setUnit(body.getUnit());
        elegantService.setSort(body.getSort());
        elegantService.setBackgroundPicture(body.getBackgroundPicture());
        elegantService.setBeginTime(beginTime);
        elegantService.setEndTime(endTime);
        elegantService.setWebSwitch(body.getWebSwitch());
        elegantService.setRecommendYn(body.getRecommendYn());
        elegantService.setOnOff(body.getOnOff());


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
        ElegantServiceDescriptionDetail elegantServiceDescriptionDetail = new ElegantServiceDescriptionDetail();
        elegantServiceDescriptionDetail.setElegantServiceId(elegantServiceId);

        ElegantServiceDescriptionDetail elegantServiceDescriptionDetailForUpdate = elegantServiceDescriptionDetailMapper.selectOne(elegantServiceDescriptionDetail);
        if (elegantServiceDescriptionDetailForUpdate != null){
            ElegantServiceDescriptionDetail elegantServiceDescriptionDetailUpdate = new ElegantServiceDescriptionDetail();
            elegantServiceDescriptionDetailUpdate.setElegantServiceId(elegantServiceDescriptionDetailForUpdate.getElegantServiceId());
            elegantServiceDescriptionDetailUpdate.setDetailDescription(body.getDetailDescription());
            elegantServiceDescriptionDetailUpdate.setId(elegantServiceDescriptionDetailForUpdate.getId());

            elegantServiceDescriptionDetailMapper.updateByPrimaryKeySelective(elegantServiceDescriptionDetailUpdate);
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

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }
}
