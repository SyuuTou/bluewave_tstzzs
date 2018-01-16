package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.InvestorsDemandService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import com.lhjl.tzzs.proxy.service.common.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 蓝海巨浪 on 2017/10/24.
 */
@Service
public class InvestorsDemandServiceImpl implements InvestorsDemandService{

    @Resource
    private CommonUserService commonUserService;
    @Resource
    private InvestorDemandMapper investorDemandMapper;
    @Resource
    private EvaluateService evaluateService;

    @Resource
    private UserLoginService userLoginService;

    @Autowired
    private InvestorDemandSegmentationMapper investorDemandSegmentationMapper;

    @Autowired
    private InvestorDemandCharacterMapper investorDemandCharacterMapper;

    @Autowired
    private InvestorDemandSpeedwayMapper investorDemandSpeedwayMapper;

    @Autowired
    private InvestorDemandStageMapper investorDemandStageMapper;

    //投资阶段
    private static final String[] ROUNDNAME = {"种子轮","天使轮","Pre-A轮","A轮","B轮","C轮","Pre-IPO轮","战略投资","并购"};

    //地域偏好
    private static final String[] SECTION = {"全球","中国","海外","硅谷","华南","华东","华北","西南","北京","上海","广州","深圳","成都","厦门","福州","长沙"
            ,"武汉","西安","大连","天津","杭州","南京","苏州","青岛"};

    /**
     * 投资偏好记录
     * @param body 请求对象
     * @return
     */
    @Override
    public CommonDto<String> newulingyu(InvestorsDemandDto body) {
        CommonDto<String> result = new CommonDto<>();
        InvestorDemand investorDemand = new InvestorDemand();

        int userId = commonUserService.getLocalUserId(body.getToken());
        investorDemand.setUserid(userId);
        if(body.getIndustryta7tradename() != null && !"".equals(body.getIndustryta7tradename())){
            investorDemand.setIndustry(body.getIndustryta7tradename());
        }
        if(body.getTouzi() != null && !"".equals(body.getTouzi())){
            investorDemand.setFinancingStage(body.getTouzi());
        }
        if(body.getCity() != null && !"".equals(body.getCity())){
            investorDemand.setCityPreference(body.getCity());
        }
        if(body.getXiaxian() != null && !"".equals(body.getXiaxian())){
            investorDemand.setInvestmentAmountLow(new BigDecimal(body.getXiaxian()));
        }
        if(body.getShangxian() != null && !"".equals(body.getShangxian())){
            investorDemand.setInvestmentAmountHigh(new BigDecimal(body.getShangxian()));
        }
        if(body.getUser7recentlyco_noana() != null && !"".equals(body.getUser7recentlyco_noana())){
            investorDemand.setRecentlyConcernedSubdivisionCircuit(body.getUser7recentlyco_noana());
        }
        if(body.getUser7foundertra_noana() !=null && !"".equals(body.getUser7foundertra_noana())){
            investorDemand.setConcernedFoundersCharacteristic(body.getUser7foundertra_noana());
        }
        if(body.getXuqiu() != null && !"".equals(body.getXuqiu())){
            investorDemand.setDemand(body.getXuqiu());
        }

        investorDemand.setCreatTime(new Date());

        investorDemandMapper.insert(investorDemand);

        result.setStatus(200);
        result.setMessage("投资偏好记录成功");
        return result;
    }

    /**
     * 投资偏好回显
     * @param token 用户token
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> newttouzilyrz(String token) {
        CommonDto<Map<String, Object>>  result = new CommonDto<>();
        Map<String, Object> data = new HashMap<>();

        CommonDto<Map<String, List<LabelList>>> hotsdatas = evaluateService.queryHotData();
        //获取记录信息
        int userId = commonUserService.getLocalUserId(token);
        Example example = new Example(InvestorDemand.class);
        example.and().andEqualTo("userid", userId);
        example.setOrderByClause("creat_time desc");
        List<InvestorDemand> investorDemands = investorDemandMapper.selectByExample(example);
        InvestorDemand investorDemand = null;
        if(investorDemands.size() > 0){
            investorDemand = investorDemands.get(0);
        }

        //行业领域(send_logs)
        List<LabelList> industrys = hotsdatas.getData().get("industryKey");
        List<InvestorsDemandLabel> demandLabels = new ArrayList<>();
        for(int i = 0; i<industrys.size(); i++){
            InvestorsDemandLabel label = new InvestorsDemandLabel();
            label.setIndustrycl7describe(i);
            label.setIndustrycl7orderofarr(i+1);
            label.setIndustrycl7tradename(industrys.get(i).getName());
            label.setIndustryta7tradename(industrys.get(i).getName());
            label.setIsgouxuan(false);
            if(investorDemand != null && investorDemand.getIndustry() != null && !"".equals(investorDemand.getIndustry())){
                String[] industryArray = investorDemand.getIndustry().split(",");
                for(String string : industryArray){
                    if(string.equals(industrys.get(i).getName())){
                        label.setIsgouxuan(true);
                    }
                }
            }
            demandLabels.add(label);
        }
        data.put("shuzu", demandLabels);

        //投资阶段
        List<Boolean> roundsNames = new ArrayList<>();
        for(String roundName : ROUNDNAME){
            roundsNames.add(false);
        }
        for(int i = 0; i<ROUNDNAME.length; i++){
            if(investorDemand != null && investorDemand.getFinancingStage() != null && !"".equals(investorDemand.getFinancingStage())){
                String[] roundNameArray = investorDemand.getFinancingStage().split(",");
                for(String string : roundNameArray){
                    if(string.equals(ROUNDNAME[i])){
                        roundsNames.set(i, true);
                    }
                }
            }
        }
        data.put("a", roundsNames);

        //地域偏好
        List<Boolean> cities = new ArrayList<>();
        for(String string : SECTION){
            cities.add(false);
        }
        for(int i=0; i<SECTION.length; i++){
            if(investorDemand != null && investorDemand.getCityPreference() != null && !"".equals(investorDemand.getCityPreference())){
                String[] cityArray = investorDemand.getCityPreference().split(",");
                for(String string : cityArray){
                    if(string.equals(SECTION[i])){
                        cities.set(i, true);
                    }
                }
            }
        }
        data.put("b", cities);

        if(investorDemand != null){
            data.put("user7singleinve_noana", investorDemand.getInvestmentAmountHigh());
            data.put("user7singlethro_noana", investorDemand.getInvestmentAmountLow());
            data.put("user7excessfield4", investorDemand.getDemand());
            List<String> recentlys = new ArrayList<>();
            if(investorDemand.getRecentlyConcernedSubdivisionCircuit() != null && !"".equals(investorDemand.getRecentlyConcernedSubdivisionCircuit())){
                String[] recentlyArray = investorDemand.getRecentlyConcernedSubdivisionCircuit().split(",");
                for(String string : recentlyArray){
                    recentlys.add(string);
                }
            }
            data.put("user7recentlyco_noana", recentlys);

            List<String> founders = new ArrayList<>();
            if(investorDemand.getConcernedFoundersCharacteristic() != null && !"".equals(investorDemand.getConcernedFoundersCharacteristic())){
                String[] founderArray = investorDemand.getConcernedFoundersCharacteristic().split(",");
                for(String string : founderArray){
                    founders.add(string);
                }
            }
            data.put("user7foundertra_noana", founders);
        }else{
            data.put("user7singleinve_noana", "");
            data.put("user7singlethro_noana", "");
            data.put("user7excessfield4", "");
            data.put("user7recentlyco_noana", new ArrayList<String>());
            data.put("user7foundertra_noana", new ArrayList<String>());
        }

        result.setStatus(200);
        result.setMessage("投资偏好回显数据获取成功");
        result.setData(data);
        return result;
    }

    /**
     * 判断投资偏好是否填完
     * @param token
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> investorsDemandYn(String token){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        Integer userId = commonUserService.getLocalUserId(token);

        InvestorDemand investorDemand = new InvestorDemand();
        investorDemand.setUserid(userId);

        List<InvestorDemand> investorDemands = investorDemandMapper.select(investorDemand);
        if (investorDemands.size() > 0){
            InvestorDemand investorDemandForJudgment  = investorDemands.get(0);

            String   finacingStage = investorDemandForJudgment.getFinancingStage();
            String  industry = investorDemandForJudgment.getIndustry();
            String  demand = investorDemandForJudgment.getDemand();

            if (finacingStage == null || "".equals(finacingStage) ){
                obj.put("message","投资偏好没有填写完成");
                obj.put("success",false);

                result.setMessage("投资偏好没有填写完成");
                result.setStatus(50001);
                result.setData(obj);

                return result;
            }

            if (industry == null || "".equals(industry) ){
                obj.put("message","投资偏好没有填写完成");
                obj.put("success",false);

                result.setMessage("投资偏好没有填写完成");
                result.setStatus(50001);
                result.setData(obj);

                return result;
            }

            obj.put("message","投资偏好填写完成");
            obj.put("success",true);

            result.setData(obj);
            result.setStatus(200);
            result.setMessage("投资偏好填写完成");

            return result;
        }

        obj.put("message","投资偏好没有填写完成");
        obj.put("success",false);

        result.setMessage("投资偏好没有填写完成");
        result.setStatus(50001);
        result.setData(obj);

        return result;
    }

    /**
     * 创建投资风向标/融资需求的方法
     * @param body
     * @param appid
     * @return
     */
    @Override
    @Transactional
    public CommonDto<String> createInvestorsDemand(InvestorDemandInputsDto body, Integer appid) {
        CommonDto<String> result  = new CommonDto<>();
        Date now = new Date();

        if (body.getToken() == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("用户token不能为空");

            return result;
        }

        if (body.getCharacter() == null || body.getCharacter().size() < 1){
            result.setMessage("请填写关注创始人特质");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getFuture() == null || "".equals(body.getFuture()) || "undefined".equals(body.getFuture())){
            result.setMessage("请填写2018年展望");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getSegmentation() == null || body.getSegmentation().size() < 1){
            result.setMessage("请选择投资领域");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getSpeedway() == null || body.getSpeedway().size() < 1){
            result.setMessage("请输入最近关注细分赛道");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getStage() == null || body.getStage().size() < 1){
            result.setMessage("请选择投资轮次");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (appid == null){
            result.setMessage("请配置应用id");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        Integer userId = userLoginService.getUserIdByToken(body.getToken(),appid);
        if (userId == -1){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("用户token无效");

            return result;
        }

        //兼容老版本
        String industry = null;
        if (body.getSegmentation().size() > 0){
            industry = String.join(",",body.getSegmentation());
        }

        String financingStage = null;
        if (body.getStage().size() > 0){
            financingStage = String.join(",",body.getSegmentation());
        }

        String speedWay = null;
        if (body.getSpeedway().size() > 0){
            speedWay = String.join(",",body.getSpeedway());
        }

        String character = null;
        if (body.getCharacter().size() > 0){
            character = String.join(",",body.getCharacter());
        }


        //准备数据
        InvestorDemand investorDemand = new InvestorDemand();
        investorDemand.setUserid(userId);
        investorDemand.setAppid(appid);
        if (industry != null){
            investorDemand.setIndustry(industry);
        }
        if (financingStage != null){
            investorDemand.setFinancingStage(financingStage);
        }
        if (speedWay != null){
            investorDemand.setRecentlyConcernedSubdivisionCircuit(speedWay);
        }
        if (character != null){
            investorDemand.setConcernedFoundersCharacteristic(character);
        }
        if (body.getInvestmentAmountHigh() != null){
            investorDemand.setInvestmentAmountHigh(body.getInvestmentAmountHigh());
        }
        if (body.getInvestmentAmountLow() != null){
            investorDemand.setInvestmentAmountLow(body.getInvestmentAmountLow());
        }
        if (body.getInvestmentAmountHighDollars() != null){
            investorDemand.setInvestmentAmountHighDollars(body.getInvestmentAmountHighDollars());
        }
        if (body.getInvestmentAmountLowDollars() != null){
            investorDemand.setInvestmentAmountLowDollars(body.getInvestmentAmountLowDollars());
        }
        investorDemand.setFuture(body.getFuture());
        investorDemand.setDemandStatus(3);//默认不完整
        investorDemand.setAppid(appid);

        //查找原来是否有数据
        Example idExample = new Example(InvestorDemand.class);
        idExample.and().andEqualTo("userid",userId);
        idExample.setOrderByClause("creat_time desc");

        Integer investorDemandId = null;
        List<InvestorDemand> investorDemandList = investorDemandMapper.selectByExample(idExample);
        if (investorDemandList.size()>0){
            investorDemand.setId(investorDemandList.get(0).getId());
            investorDemandId = investorDemandList.get(0).getId();
            investorDemandMapper.updateByPrimaryKeySelective(investorDemand);
        }else {
            investorDemand.setCreatTime(now);
            investorDemandMapper.insertSelective(investorDemand);
            investorDemandId = investorDemand.getId();
        }

        // 更新领域
        InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
        investorDemandSegmentation.setInvestorDemandId(investorDemandId);
        investorDemandSegmentation.setAppid(appid);

        investorDemandSegmentationMapper.delete(investorDemandSegmentation);

        if (body.getSegmentation().size() > 0){
            for (String s:body.getSegmentation()){
                InvestorDemandSegmentation investorDemandSegmentationForInsert = new InvestorDemandSegmentation();
                investorDemandSegmentationForInsert.setAppid(appid);
                investorDemandSegmentationForInsert.setInvestorDemandId(investorDemandId);
                investorDemandSegmentationForInsert.setSegmentation(s);

                investorDemandSegmentationMapper.insertSelective(investorDemandSegmentationForInsert);
            }
        }

        // 更新赛道
        InvestorDemandSpeedway investorDemandSpeedway = new InvestorDemandSpeedway();
        investorDemandSpeedway.setInvestorDemandId(investorDemandId);
        investorDemandSpeedway.setAppid(appid);

        investorDemandSpeedwayMapper.delete(investorDemandSpeedway);

        if (body.getSpeedway().size() > 0){
            for (String s:body.getSpeedway()){
                InvestorDemandSpeedway investorDemandSpeedwayForInsert = new InvestorDemandSpeedway();
                investorDemandSpeedwayForInsert.setAppid(appid);
                investorDemandSpeedwayForInsert.setInvestorDemandId(investorDemandId);
                investorDemandSpeedwayForInsert.setSpeedway(s);

                investorDemandSpeedwayMapper.insertSelective(investorDemandSpeedwayForInsert);
            }
        }

        // 更新阶段
        InvestorDemandStage investorDemandStage = new InvestorDemandStage();
        investorDemandStage.setInvestorDemandId(investorDemandId);
        investorDemandStage.setAppid(appid);

        investorDemandStageMapper.delete(investorDemandStage);

        if (body.getStage().size() > 0){
            for (Integer i :body.getStage()){
                InvestorDemandStage investorDemandStageForInsert = new InvestorDemandStage();
                investorDemandStageForInsert.setInvestorDemandId(investorDemandId);
                investorDemandStageForInsert.setAppid(appid);
                investorDemandStageForInsert.setMetaProjectStageId(i);

                investorDemandStageMapper.insertSelective(investorDemandStageForInsert);
            }
        }

        // 更新特质
        InvestorDemandCharacter investorDemandCharacter = new InvestorDemandCharacter();
        investorDemandCharacter.setAppid(appid);
        investorDemandCharacter.setInvestorDemandId(investorDemandId);

        investorDemandCharacterMapper.delete(investorDemandCharacter);

        if (body.getCharacter().size() > 0){
            for (String s1:body.getCharacter()){
                InvestorDemandCharacter investorDemandCharacterForInsert = new InvestorDemandCharacter();
                investorDemandCharacterForInsert.setInvestorDemandId(investorDemandId);
                investorDemandCharacterForInsert.setCharacter(s1);
                investorDemandCharacterForInsert.setAppid(appid);

                investorDemandCharacterMapper.insertSelective(investorDemandCharacterForInsert);
            }
        }

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }

    /**
     * 获取投资风向标/融资需求列表
     * @param body
     * @param appid
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> getInvestorDemand(InvestorDemandListInputDto body, Integer appid) {
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> map = new HashMap<>();


        return result;
    }
}
