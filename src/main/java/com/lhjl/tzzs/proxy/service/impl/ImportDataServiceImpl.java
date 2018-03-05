package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ImportDataService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import com.lhjl.tzzs.proxy.utils.GenericController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/12.
 */
@Service
public class ImportDataServiceImpl implements ImportDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportDataServiceImpl.class);

    @Autowired
    private InvestorsMapper investorsMapper;

    @Autowired
    private InvestorDemandStageMapper investorDemandStageMapper;

    @Autowired
    private InvestorDemandSegmentationMapper investorDemandSegmentationMapper;

    @Autowired
    private InvestorDemandMapper investorDemandMapper;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private OriginalDataMapper originalDataMapper;

    @Autowired
    private OriginalDataClassifyMapper originalDataClassifyMapper;

    @Autowired
    private OriginalDataSegmentationMapper originalDataSegmentationMapper;

    @Autowired
    private OriginalDataStageMapper originalDataStageMapper;

    @Autowired
    private OriginalDataCityMapper originalDataCityMapper;

    @Autowired
    private DatasOperationManageMapper datasOperationManageMapper;

    @Autowired
    private InvestorCityMapper investorCityMapper;

    @Autowired
    private InvestmentInstitutionsTypeMapper investmentInstitutionsTypeMapper;


    @Override
    public CommonDto<String> importData() {

        CommonDto<String> result = new CommonDto<>();

        List<OriginalData> originalDataList = originalDataMapper.selectAll();

        if(null == originalDataList || originalDataList.size() == 0){
            result.setStatus(200);
            result.setMessage("success");
            result.setData("数据为空");
            return result;
        }
        for(OriginalData originalData : originalDataList) {

            if(null == originalData.getInstitutionName() || "".equals(originalData.getInstitutionName())){
                continue;
            }

            if("方旭资本".equals(originalData.getInstitutionName())){
                continue;
            }

            if("极客邦创投".equals(originalData.getInstitutionName()) && null == originalData.getWechatNumber()){
                continue;
            }

            if("春晓资本".equals(originalData.getInstitutionName()) && null == originalData.getWechatNumber()){
                continue;
            }

            if("星河互联".equals(originalData.getInstitutionName()) && null == originalData.getWechatNumber()){
                continue;
            }


            InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
            investmentInstitutions.setShortName(originalData.getInstitutionName());
            investmentInstitutions.setDataSourceType(2);
            investmentInstitutions.setComment(originalData.getInstitutionDesc());
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            long now = System.currentTimeMillis();
            String createTime = null;
            try {
                createTime = sdf.format(new Date(now));
            } catch (Exception e) {
                e.printStackTrace();
            }
            investmentInstitutions.setCreateTime(DateUtils.parse1(createTime));//创建时间
            investmentInstitutions.setApprovalStatus(2);//未审核


            InvestmentInstitutions investmentInstitutions1 = investmentInstitutionsMapper.selectByShortName(originalData.getInstitutionName());
            if (null == investmentInstitutions1){
                investmentInstitutionsMapper.insert(investmentInstitutions);
            }else{
                investmentInstitutions.setId(investmentInstitutions1.getId());
                investmentInstitutionsMapper.updateByPrimaryKeySelective(investmentInstitutions);
            }

            Integer investmentInstitutionsTypeInsertResult = -1;
            List<InvestmentInstitutionsType> investmentInstitutionsTypeList = new ArrayList<>();

            InvestmentInstitutionsType investmentInstitutionsType = new InvestmentInstitutionsType();
            investmentInstitutionsType.setInvestmentInstitutionsId(investmentInstitutions.getId());

            investmentInstitutionsTypeMapper.delete(investmentInstitutionsType);

            List<OriginalDataClassify> originalDataClassifyList = new ArrayList<>();
            originalDataClassifyList = originalDataClassifyMapper.selectClassifyByDataId(originalData.getId());
            String[] originalDataClassifyNameArr = null;
            if(null != originalDataClassifyList && originalDataClassifyList.size() != 0){
                List<String> originalDataClassifyNames = new ArrayList<>();
                for(OriginalDataClassify originalDataClassify : originalDataClassifyList){
                    originalDataClassifyNames.add(originalDataClassify.getClassifyName());
                }
                originalDataClassifyNameArr = new String[originalDataClassifyNames.size()];
                originalDataClassifyNames.toArray(originalDataClassifyNameArr);
            }

            if(null == originalDataClassifyNameArr){
                InvestmentInstitutionsType investmentInstitutionsType1 = new InvestmentInstitutionsType();
                investmentInstitutionsType1.setInvestmentInstitutionsId(investmentInstitutions.getId());
                investmentInstitutionsType1.setType(null);
                investmentInstitutionsTypeList.add(investmentInstitutionsType1);
            }else{
                for (int i=0; i < originalDataClassifyNameArr.length; i++){
                    InvestmentInstitutionsType investmentInstitutionsType1 = new InvestmentInstitutionsType();
                    investmentInstitutionsType1.setInvestmentInstitutionsId(investmentInstitutions.getId());
                    investmentInstitutionsType1.setType(originalDataClassifyNameArr[i]);
                    investmentInstitutionsTypeList.add(investmentInstitutionsType1);
                }
            }
            investmentInstitutionsTypeInsertResult = investmentInstitutionsTypeMapper.insertList(investmentInstitutionsTypeList);

            if (null == originalData.getUserName() || "".equals(originalData.getUserName())){
                continue;
            }
            Investors investors = new Investors();
            investors.setName(originalData.getUserName());
            investors.setInvestmentInstitutionsId(investmentInstitutions.getId());
            investors.setPosition(originalData.getCompanyDuties());
            investors.setInvestCaseDescription(originalData.getInvestmentPrepare());
            investors.setSingleInvestAmount(originalData.getOneInvestmentCount());
            investors.setYn(1);

            List<Map<String, Object>> result1 = investorsMapper.selectByInstitutionIdNameWeichatGroupId(originalData.getUserName(), investmentInstitutions.getId(), originalData.getWechatNumber());
            Integer investorId = null;
            if (null == result1 || result1.size() == 0){
                investorsMapper.insert(investors);
                investorId = investors.getId();
            }else{
                investors.setId((Integer)result1.get(0).get("ID"));
                investorsMapper.updateByPrimaryKeySelective(investors);
                investorId = (Integer)result1.get(0).get("ID");
            }


            InvestorDemand investorDemand = new InvestorDemand();
            investorDemand.setInvestorId(investorId);
            investorDemand.setDemand(originalData.getPreferenceAndPrecautions());
//          investorDemand.set  单笔投资金额

            InvestorDemand investorDemand1 = investorDemandMapper.selectByInvestorId(investorId);
            if(null == investorDemand1){
                investorDemandMapper.insert(investorDemand);
            }else{
                investorDemand.setId(investorDemand1.getId());
                investorDemandMapper.updateByPrimaryKeySelective(investorDemand);
            }

            Integer investorDemandSegmentationInsertResult = -1;
            List<InvestorDemandSegmentation> investorDemandSegmentationList = new ArrayList<>();

            InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
            investorDemandSegmentation.setInvestorDemandId(investorDemand.getId());
            investorDemandSegmentationMapper.delete(investorDemandSegmentation);

            List<OriginalDataSegmentation> originalDataSegmentationList = new ArrayList<>();
            originalDataSegmentationList = originalDataSegmentationMapper.selectByDataId(originalData.getId());
            String[] originalDataSegmentationNameArr = null;
            if(null != originalDataSegmentationList && originalDataSegmentationList.size() != 0){
                List<String> originalDataSegmentationNames = new ArrayList<>();
                for(OriginalDataSegmentation originalDataSegmentation : originalDataSegmentationList){
                    originalDataSegmentationNames.add(originalDataSegmentation.getSegmentationName());
                }
                originalDataSegmentationNameArr = new String[originalDataSegmentationNames.size()];
                originalDataSegmentationNames.toArray(originalDataSegmentationNameArr);
            }

            if(null == originalDataSegmentationNameArr){
                InvestorDemandSegmentation investorDemandSegmentation1 = new InvestorDemandSegmentation();
                investorDemandSegmentation1.setInvestorDemandId(investorDemand.getId());
                investorDemandSegmentation1.setSegmentation(null);
                investorDemandSegmentationList.add(investorDemandSegmentation1);
            }else{
                for (String investorDemandSegmentation_i : originalDataSegmentationNameArr){
                    InvestorDemandSegmentation investorDemandSegmentation1 = new InvestorDemandSegmentation();
                    investorDemandSegmentation1.setInvestorDemandId(investorDemand.getId());
                    investorDemandSegmentation1.setSegmentation(investorDemandSegmentation_i);
                    investorDemandSegmentationList.add(investorDemandSegmentation1);
                }
            }
            investorDemandSegmentationInsertResult = investorDemandSegmentationMapper.insertList(investorDemandSegmentationList);

            Integer investorDemandStageInsertResult = -1;
            List<InvestorDemandStage> investorDemandStageList = new ArrayList<>();

            InvestorDemandStage investorDemandStage = new InvestorDemandStage();
            investorDemandStage.setInvestorDemandId(investorDemand.getId());
            investorDemandStageMapper.delete(investorDemandStage);

            List<OriginalDataStage> originalDataStageList = new ArrayList<>();
            originalDataStageList = originalDataStageMapper.selectByDataId(originalData.getId());

            Integer[] originalDataStageArr = null;

            if(null != originalDataStageList && originalDataStageList.size() != 0){
                List<Integer> originalDataStages = new ArrayList<>();
                for(OriginalDataStage originalDataStage : originalDataStageList){
                    originalDataStages.add(originalDataStage.getMetaStageId());
                }
                originalDataStageArr = new Integer[originalDataStages.size()];
                originalDataStages.toArray(originalDataStageArr);
            }

            if(null == originalDataStageArr){
                InvestorDemandStage investorDemandStage1 = new InvestorDemandStage();
                investorDemandStage1.setInvestorDemandId(investorDemand.getId());
                investorDemandStage1.setMetaProjectStageId(null);
                investorDemandStageList.add(investorDemandStage);
            }else{
                for (Integer investorDemandStageId : originalDataStageArr){
                    InvestorDemandStage investorDemandStage1 = new InvestorDemandStage();
                    investorDemandStage1.setInvestorDemandId(investorDemand.getId());
                    investorDemandStage1.setMetaProjectStageId(investorDemandStageId);
                    investorDemandStageList.add(investorDemandStage1);
                }
            }
            investorDemandStageInsertResult = investorDemandStageMapper.insertList(investorDemandStageList);

            //所在城市
            Integer investorCityInsertResult = -1;
            List<InvestorCity> investorCityList = new ArrayList<>();

            InvestorCity investorCity = new InvestorCity();
            investorCity.setId(investorId);
            investorCityMapper.delete(investorCity);

            List<OriginalDataCity> originalDataCityList = new ArrayList<>();
            originalDataCityList = originalDataCityMapper.selectByDataId(originalData.getId());
            List<String> originalDataCityNames = new ArrayList<>();
            String[] originalDataCityNameArr = null;
            if(null != originalDataCityList && originalDataCityList.size() != 0){
                for(OriginalDataCity originalDataCity : originalDataCityList){
                    originalDataCityNames.add(originalDataCity.getCityName());
                }
                originalDataCityNameArr = new String[originalDataCityNames.size()];
                originalDataCityNames.toArray(originalDataCityNameArr);
            }

            if(null == originalDataCityNameArr){
                InvestorCity investorCity1 = new InvestorCity();
                investorCity1.setId(investorId);
                investorCity1.setCity(null);
                investorCityList.add(investorCity);
            }else{
                for (String investorCityName : originalDataCityNameArr){
                    InvestorCity investorCity1 = new InvestorCity();
                    investorCity1.setId(investorId);
                    investorCity1.setCity(investorCityName);
                    investorCityList.add(investorCity1);
                }
            }
            investorCityInsertResult = investorCityMapper.insertList(investorCityList);


            DatasOperationManage datasOperationManage = new DatasOperationManage();
            datasOperationManage.setDataId(investorId);
            datasOperationManage.setDataType("INVESTOR");
            DatasOperationManage result2 = datasOperationManageMapper.selectByPrimaryKey(datasOperationManage);
            if(null == result2){
                datasOperationManage.setWechatGroupId(originalData.getWechatNumber());
                datasOperationManage.setIrPrincipal(originalData.getChargePeople());
                datasOperationManage.setSourceType(originalData.getInstitutionSource());
                datasOperationManageMapper.insert(datasOperationManage);
            }else{
                datasOperationManage.setIrPrincipal(originalData.getChargePeople());
                datasOperationManage.setWechatGroupId(originalData.getWechatNumber());
                datasOperationManage.setSourceType(originalData.getInstitutionSource());
                datasOperationManageMapper.updateByPrimaryKeySelective(datasOperationManage);
            }

        }

        result.setStatus(200);
        result.setMessage("success");
        result.setData("导入成功");
        return result;
    }
}
