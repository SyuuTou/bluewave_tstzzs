package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorsMapper extends OwnerMapper<Investors> {
    List<ProjectInvestmentDto> findinvestorId (@Param("invesId") Integer invesId);
}