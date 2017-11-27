package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionInformation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestmentInstitutionInformationMapper extends OwnerMapper<InvestmentInstitutionInformation> {

    List <InvestmentInstitutionInformation>findInformation(@Param("userId") Integer userId);

}