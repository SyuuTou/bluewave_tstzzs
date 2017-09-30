package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.service.InvestmentBackstageService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class InvestmentBackstageImpl implements InvestmentBackstageService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentBackstageImpl.class);

    @Resource
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Transactional
    @Override
    public CommonDto<String> adminAddInvestmentBackstage(InvestmentInstitutions body){
        CommonDto<String> result =new CommonDto<String>();

        String short_name = body.getShortName();
        String commet = body.getCommet();
        log.info("short_name={}",short_name);
        log.info("commet={}",commet);


        if (StringUtil.isEmpty(short_name)){
            result.setStatus(50001);
            result.setMessage("请输入机构简称");

            return result;
        }
        if (StringUtil.isEmpty(commet)){
            result.setStatus(50001);
            result.setMessage("请输入机构备注");

            return result;
        }

        int type = -1;
        if (body.getType() != null){
            type =body.getType();
            log.info("type={}",type);
        }else {
            result.setMessage("请选择机构类型");
            result.setStatus(50002);
            return result;
        }

        Date now =new Date();

        InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
        investmentInstitutions.setShortName(short_name);
        investmentInstitutions.setCreateTime(now);
        investmentInstitutions.setCommet(commet);
        investmentInstitutions.setType(type);

        investmentInstitutionsMapper.insert(investmentInstitutions);

        result.setStatus(200);
        result.setMessage("success");
        return result;
    }
}
