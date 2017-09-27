package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InvestmentInstitutionsMapper extends OwnerMapper<InvestmentInstitutions> {
    int add(InvestmentInstitutions investmentInstitutions);

}