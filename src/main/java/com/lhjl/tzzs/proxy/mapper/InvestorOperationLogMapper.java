package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestorOperationLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorOperationLogMapper extends OwnerMapper<InvestorOperationLog> {
    List<InvestorOperationLog> selectAllInvestorOperationLog(@Param("investorId")Integer investorId);
}