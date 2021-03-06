package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Report;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportMapper extends OwnerMapper<Report> {
    List<Report> selectReport(@Param("institutionId") Integer institutionId,@Param("columnId") Integer columnId,
                              @Param("offset") Integer offset,@Param("limit") Integer limit);
    Integer selectReportCount(@Param("institutionId") Integer institutionId,@Param("columnId") Integer columnId,
                              @Param("offset") Integer offset,@Param("limit") Integer limit);
}