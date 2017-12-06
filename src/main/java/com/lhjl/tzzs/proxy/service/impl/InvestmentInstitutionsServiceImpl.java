package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionSearchOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsAddressMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsStageMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddress;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class InvestmentInstitutionsServiceImpl implements InvestmentInstitutionsService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentInstitutionsServiceImpl.class);

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private InvestmentInstitutionsSegmentationMapper investmentInstitutionsSegmentationMapper;

    @Autowired
    private InvestmentInstitutionsStageMapper investmentInstitutionsStageMapper;

    @Autowired
    private InvestmentInstitutionsAddressMapper investmentInstitutionsAddressMapper;

    @Override
    public CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComlexInfo(Map<String,Integer> body){
        CommonDto<InvestmentInstitutionComplexOutputDto> result =  new CommonDto<>();

        if (body.get("investorInstitutionId") == null){
            result.setStatus(50001);
            result.setMessage("机构id不能为空");
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("机构id不能为空");

            return result;
        }

        InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(body.get("investorInstitutionId"));
        if (investmentInstitutions == null){
            result.setMessage("当前机构id没有找到对应的机构");
            result.setStatus(50001);
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("当前机构id没有找到对应的机构");

            return result;
        }else {
            InvestmentInstitutionComplexOutputDto investmentInstitutionComplexOutputDto = new InvestmentInstitutionComplexOutputDto();
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionDesc(investmentInstitutions.getKenelCase());
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionLogo(investmentInstitutions.getLogo());
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionName(investmentInstitutions.getShortName());

            result.setData(investmentInstitutionComplexOutputDto);
            result.setMessage("success");
            result.setStatus(200);

        }



        return result;
    }

    /**
     * 根据输入词搜索机构信息接口
     * @param inputsWords 输入的词
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionSearchOutputDto>> getInstitutionIntelligentSearch(String inputsWords,Integer pageSize){
        CommonDto<List<InvestmentInstitutionSearchOutputDto>> result = new CommonDto<>();
        List<InvestmentInstitutionSearchOutputDto> list = new ArrayList<>();

        if (inputsWords == null || "undefined".equals(inputsWords)){
           inputsWords = "";
        }
        if (pageSize == null || pageSize <= 0){
            pageSize = 5;
        }


        List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.findeInvestmentByShortName(inputsWords,0,pageSize);
        if (investmentInstitutionsList.size() > 0){
            for(InvestmentInstitutions ii:investmentInstitutionsList){
                InvestmentInstitutionSearchOutputDto investmentInstitutionSearchOutputDto = new InvestmentInstitutionSearchOutputDto();
                investmentInstitutionSearchOutputDto.setInstitutionFullName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionShortName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionId(ii.getId());

                list.add(investmentInstitutionSearchOutputDto);
            }

            result.setData(list);
            result.setMessage("success");
            result.setStatus(200);
        }else {
            result.setStatus(200);
            result.setMessage("success");
            result.setData(list);
        }

        return result;
    }

    /**
     * 获取机构详情的接口
     * @param institutionId
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> findInstitutionDetails(Integer institutionId){
        CommonDto<Map<String,Object>> result  = new CommonDto<>();

        if (institutionId == null){
            result.setMessage("机构id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        Map<String,Object> map = new HashMap<>();

        //先获取机构简介
        InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(institutionId);
        if (investmentInstitutions == null){
            result.setMessage("输入的机构id不存在，请检查");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        //获取机构的关注领域
        List<Map<String,Object>> segmentList = new ArrayList<>();
        segmentList = investmentInstitutionsSegmentationMapper.findSegment(institutionId);
        for (Map<String,Object> m:segmentList){
            if (m.get("name") == null ){
                m.put("name","");
            }

            if (m.get("segmentation_logo") == null){
                m.put("segmentation_logo","http://img.idatavc.com/static/seg/jiaoyu.png");
            }
        }

        //获取机构的关注阶段
        List<Map<String,Object>> stageList = new ArrayList<>();
        stageList = investmentInstitutionsStageMapper.findInstitutionStage(institutionId);

        //获取机构的城市、地址、邮箱、电话
        Example iiaExample = new Example(InvestmentInstitutionsAddress.class);
        iiaExample.and().andEqualTo("investmentInstitutionId",institutionId);

        List<Map<String,Object>> listiia = new ArrayList<>();
        List<InvestmentInstitutionsAddress> investmentInstitutionsAddressList = investmentInstitutionsAddressMapper.selectByExample(iiaExample);
        if (investmentInstitutionsAddressList.size() > 0){
            for (InvestmentInstitutionsAddress iia:investmentInstitutionsAddressList){
                Map<String,Object> mapForY = new HashMap<>();
                if (iia.getTown() == null){
                    mapForY.put("town","");
                }else {
                    mapForY.put("town",iia.getTown());
                }

                if (iia.getDetailAddress() == null){
                    mapForY.put("detailAddress","");
                }else {
                    mapForY.put("detailAddress",iia.getDetailAddress());
                }

                if (iia.getEmail() == null){
                    mapForY.put("email","");
                }else {
                    mapForY.put("email",iia.getEmail());
                }

                if (iia.getPhoneCountryCode() == null && iia.getPhoneDistrictCode() == null && iia.getPhoneNumber() ==null){
                    mapForY.put("phoneNumber","");
                }else {
                    String phonenumber = "";
                    if (iia.getPhoneCountryCode() != null){
                        phonenumber = phonenumber + iia.getPhoneCountryCode() + "-";
                    }
                    if (iia.getPhoneDistrictCode() != null){
                        phonenumber = phonenumber + iia.getPhoneDistrictCode() + "-";
                    }
                    if (iia.getPhoneNumber() != null){
                        phonenumber = phonenumber + iia.getPhoneNumber();
                    }

                    mapForY.put("phoneNumber",phonenumber);
                }

                listiia.add(mapForY);
            }
        }

        //组装返回数据
        map.put("institutionDesc",investmentInstitutions.getComment());
        map.put("institutionSegmentation",segmentList);
        map.put("institutionStage",stageList);
        map.put("address",listiia);
        map.put("institutionLogo",investmentInstitutions.getLogo());
        map.put("institutionName",investmentInstitutions.getShortName());

        result.setStatus(200);
        result.setData(map);
        result.setMessage("success");

        return result;
    }
}
