package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentDataLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface InvestmentDataLogMapper extends OwnerMapper<InvestmentDataLog> {
    List<Map<String, Object>> saveInformation(@Param("userId") Integer userId,@Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
}