package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ReportCommentDto.ReportCommentInputDto;
import com.lhjl.tzzs.proxy.dto.ReportCommentDto.ReportCommentOutputDto;
import com.lhjl.tzzs.proxy.model.ReportCollection;
import com.lhjl.tzzs.proxy.model.ReportComment;
import com.lhjl.tzzs.proxy.model.ReportConcern;

import java.util.List;

public interface ReportEventService {
    CommonDto<String> saveReportCollection(Integer appId, ReportCollection reqBody);

    CommonDto<String> saveReportComment(Integer appId, ReportCommentInputDto reportCommentInputDto);

    CommonDto<String> saveReportConcen(Integer appId, ReportConcern reportConcern);

    CommonDto<String> updateReportComment(Integer appId, ReportComment reportComment);

    CommonDto<String> updateReportConcen(Integer appId, ReportConcern reportConcern);

    CommonDto<List<ReportCommentOutputDto>> findReportComment(Integer appId, Integer reportId, String token, Integer pageNo, Integer pageSize);

    CommonDto<Integer> findReportConcenNum(Integer appId, Integer reportId);

    CommonDto<String> updateReportCommentConcen(Integer appId, Long commentId, String token);

    CommonDto<String> deleteReportCommentConcen(Integer appId, Long commentId, String token);

    CommonDto<Integer> getReportCommentConcenNum(Integer appId, Long commentId, String token);
}
