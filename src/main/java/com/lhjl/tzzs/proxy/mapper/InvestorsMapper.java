package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectInvestmentDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorsOutputDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto.InvestorSmartSearchOutputDto;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<Map<String, Object>> selectByInstitutionIdNameWeichatGroupId(@Param("name")String name, @Param("institutionId") Integer institutionId, @Param("weiChatGroupId") String weiChatGroupId);

    Investors maxSerialNumber();

    /**
     * 智能检索投资人（姓名，机构简称，职务）
     */
    List<InvestorSmartSearchOutputDto> smartSearchInvestor(@Param("keyword") String keyword);
}