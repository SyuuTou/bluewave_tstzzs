package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.github.pagehelper.PageRowBounds;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ReportCollectionMapper;
import com.lhjl.tzzs.proxy.mapper.ReportCommentMapper;
import com.lhjl.tzzs.proxy.mapper.ReportConcernMapper;
import com.lhjl.tzzs.proxy.model.ReportCollection;
import com.lhjl.tzzs.proxy.model.ReportComment;
import com.lhjl.tzzs.proxy.model.ReportConcern;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ReportEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportEventServiceImpl extends GenericService implements ReportEventService {


    @Autowired
    private ReportCollectionMapper reportCollectionMapper;

    @Autowired
    private ReportCommentMapper reportCommentMapper;

    @Autowired
    private ReportConcernMapper reportConcernMapper;

    @Override
    public CommonDto<String> saveReportCollection(Integer appId, ReportCollection reqBody) {

        reqBody.setAppId(appId);
        reportCollectionMapper.insert(reqBody);
        return new CommonDto<String>("ok","success",200);
    }

    @Override
    public CommonDto<String> saveReportComment(Integer appId, ReportComment reportComment) {
        reportComment.setAppId(appId);
        reportCommentMapper.insert(reportComment);
        return new CommonDto<>("ok", "success", 200);
    }

    @Override
    public CommonDto<String> saveReportConcen(Integer appId, ReportConcern reportConcern) {
        reportConcern.setAppId(appId);
        reportConcernMapper.insert(reportConcern);
        return new CommonDto<>("ok","success", 200);
    }

    @Override
    public CommonDto<String> updateReportComment(Integer appId, ReportComment reportComment) {
         reportComment.setAppId(appId);
         reportCommentMapper.updateByPrimaryKeySelective(reportComment);
        return new CommonDto<>("ok","success",200);
    }

    @Override
    public CommonDto<String> updateReportConcen(Integer appId, ReportConcern reportConcern) {

        reportConcern.setAppId(appId);
        reportConcernMapper.updateByPrimaryKeySelective(reportConcern);
        return new CommonDto<>("ok", "success", 200);
    }

    @Override
    public CommonDto<List<ReportComment>> findReportComment(Integer appId, Integer reportId, Integer pageNo, Integer pageSize) {

        ReportComment query = new ReportComment();
        query.setAppId(appId);
        query.setReportId(reportId);
        int offset = (pageNo - 1) * pageSize;
        int limit = pageSize;

        PageRowBounds rowBounds = new PageRowBounds(offset, limit);

        List<ReportComment> comments = reportCommentMapper.selectByRowBounds(query, rowBounds);

        return new CommonDto<>(comments,"success", 200);
    }

    @Override
    public CommonDto<Integer> findReportConcenNum(Integer appId, Integer reportId) {
        ReportConcern reportConcern = new ReportConcern();
        reportConcern.setAppId(appId);
        reportConcern.setReportId(reportId);
        reportConcern.setYn(1);

       return new CommonDto<>(reportConcernMapper.selectCount(reportConcern),"success", 200);

    }


}
