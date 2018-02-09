package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorsOutputDto;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorsMapper extends OwnerMapper<Investors> {

    List<ProjectInvestmentDto> findinvestorId (@Param("invesId") Integer invesId);
    /**
     * 返回投资人的分页列表
     * @param body
     * @return
     */
    List<InvestorsOutputDto> listInvestorsInfos(InvestorListInputDto body);
    /**
     * 返回投资人列表的总记录数
     * @param body
     * @return
     */
    Long getInvestorsListCount(InvestorListInputDto body);


    Investors selectByUserId(@Param("userId") Integer userId);
}