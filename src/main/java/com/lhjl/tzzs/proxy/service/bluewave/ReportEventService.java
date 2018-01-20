package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.ReportCollection;
import com.lhjl.tzzs.proxy.model.ReportComment;
import com.lhjl.tzzs.proxy.model.ReportConcern;

import java.util.List;

public interface ReportEventService {
    CommonDto<String> saveReportCollection(Integer appId, ReportCollection reqBody);

    CommonDto<String> saveReportComment(Integer appId, ReportComment reportComment);

    CommonDto<String> saveReportConcen(Integer appId, ReportConcern reportConcern);

    CommonDto<String> updateReportComment(Integer appId, ReportComment reportComment);

    CommonDto<String> updateReportConcen(Integer appId, ReportConcern reportConcern);

    CommonDto<List<ReportComment>> findReportComment(Integer appId, Integer reportId, Integer pageNo, Integer pageSize);

    CommonDto<Integer> findReportConcenNum(Integer appId, Integer reportId);

    CommonDto<String> updateReportCommentConcen(Integer appId, Long commentId);

    CommonDto<String> deleteReportCommentConcen(Integer appId, Long commentId);

    CommonDto<Integer> getReportCommentConcenNum(Integer appId, Long commentId);
}
