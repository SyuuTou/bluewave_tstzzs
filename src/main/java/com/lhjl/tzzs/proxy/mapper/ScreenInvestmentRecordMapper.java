package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ScreenInvestmentRecord;
import com.lhjl.tzzs.proxy.model.SearchInvestmentRecord;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScreenInvestmentRecordMapper extends OwnerMapper<ScreenInvestmentRecord> {
    ScreenInvestmentRecord serachScreenRecord(@Param("userId") Integer userId);
}