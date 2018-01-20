package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ReportComment;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportCommentMapper extends OwnerMapper<ReportComment> {

    List<ReportComment> selectReportOrderByCreateTime(@Param("appId") Integer appId, @Param("reportId") Integer reportId,
                                                         @Param("offset") Integer offset, @Param("limit") Integer limit);
}