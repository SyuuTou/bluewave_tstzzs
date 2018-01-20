package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.github.pagehelper.PageRowBounds;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ReportCommentDto.ReportCommentInputDto;
import com.lhjl.tzzs.proxy.dto.ReportCommentDto.ReportCommentOutputDto;
import com.lhjl.tzzs.proxy.mapper.ReportCollectionMapper;
import com.lhjl.tzzs.proxy.mapper.ReportCommentMapper;
import com.lhjl.tzzs.proxy.mapper.ReportConcernMapper;
import com.lhjl.tzzs.proxy.model.ReportCollection;
import com.lhjl.tzzs.proxy.model.ReportComment;
import com.lhjl.tzzs.proxy.model.ReportConcern;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ReportEventService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportEventServiceImpl extends GenericService implements ReportEventService {


    @Autowired
    private ReportCollectionMapper reportCollectionMapper;

    @Autowired
    private ReportCommentMapper reportCommentMapper;

    @Autowired
    private ReportConcernMapper reportConcernMapper;


    @Transactional
    @Override
    public CommonDto<String> saveReportCollection(Integer appId, ReportCollection reqBody) {

        reqBody.setAppId(appId);
        reportCollectionMapper.insert(reqBody);
        return new CommonDto<String>("ok","success",200);
    }
    @Transactional
    @Override
    public CommonDto<String> saveReportComment(Integer appId, ReportCommentInputDto reportCommentInputDto) {
        CommonDto<String> result = new CommonDto<>();
        ReportComment reportComment = new ReportComment();
        reportComment.setColumnId(reportCommentInputDto.getColumnId());
        reportComment.setAppId(reportCommentInputDto.getAppId());
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        reportComment.setCreateTime(DateUtils.parse(createTime));
        reportComment.setMessage(reportCommentInputDto.getMessage());
        reportComment.setToken(reportCommentInputDto.getToken());
        reportComment.setReportId(reportCommentInputDto.getReportId());
        Integer reportCommentInsertResult = reportCommentMapper.insert(reportComment);
        if (reportCommentInsertResult > 0) {
            result.setStatus(200);
            result.setMessage("success");
            result.setData("保存成功");
            return result;
        }
        result.setStatus(500);
        result.setMessage("failed");
        result.setData("保存失败");
        return result;
    }

    @Transactional
    @Override
    public CommonDto<String> saveReportConcen(Integer appId, ReportConcern reportConcern) {
        reportConcern.setAppId(appId);
        reportConcernMapper.insert(reportConcern);
        return new CommonDto<>("ok","success", 200);
    }
    @Transactional
    @Override
    public CommonDto<String> updateReportComment(Integer appId, ReportComment reportComment) {
         reportComment.setAppId(appId);
         reportCommentMapper.updateByPrimaryKeySelective(reportComment);
        return new CommonDto<>("ok","success",200);
    }
    @Transactional
    @Override
    public CommonDto<String> updateReportConcen(Integer appId, ReportConcern reportConcern) {

        reportConcern.setAppId(appId);
        reportConcernMapper.updateByPrimaryKeySelective(reportConcern);
        return new CommonDto<>("ok", "success", 200);
    }

    @Override
    public CommonDto<List<ReportCommentOutputDto>> findReportComment(Integer appId, Integer reportId, Integer pageNo, Integer pageSize) {

        CommonDto<List<ReportCommentOutputDto>> result = new CommonDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ReportComment query = new ReportComment();
        query.setAppId(appId);
        query.setReportId(reportId);
        int offset = (pageNo - 1) * pageSize;
        int limit = pageSize;

        PageRowBounds rowBounds = new PageRowBounds(offset, limit);

        List<ReportComment> comments = reportCommentMapper.selectReportOrderByCreateTime(appId, reportId, pageNo, pageSize);
        List<ReportCommentOutputDto> reportCommentOutputDtoList = new ArrayList<>();
        if (null != comments && comments.size()>0){
            for(ReportComment reportComment : comments){
                ReportCommentOutputDto reportCommentOutputDto = new ReportCommentOutputDto();
                reportCommentOutputDto.setColumnId(reportComment.getColumnId());
                reportCommentOutputDto.setCreateTime(reportComment.getCreateTime().getTime());
                reportCommentOutputDto.setId(reportComment.getId());
                reportCommentOutputDto.setMessage(reportComment.getMessage());
                reportCommentOutputDto.setReportId(reportComment.getReportId());
                reportCommentOutputDto.setNum(reportComment.getNum());
                reportCommentOutputDto.setToken(reportComment.getToken());
                reportCommentOutputDtoList.add(reportCommentOutputDto);

            }
        }
        result.setStatus(200);
        result.setMessage("success");
        result.setData(reportCommentOutputDtoList);
        return result;
    }

    @Override
    public CommonDto<Integer> findReportConcenNum(Integer appId, Integer reportId) {
        ReportConcern reportConcern = new ReportConcern();
        reportConcern.setAppId(appId);
        reportConcern.setReportId(reportId);
        reportConcern.setYn(1);

       return new CommonDto<>(reportConcernMapper.selectCount(reportConcern),"success", 200);

    }

    @Transactional
    @Override
    public CommonDto<String> updateReportCommentConcen(Integer appId, Long commentId) {

        ReportComment reportComment = reportCommentMapper.selectByPrimaryKey(commentId);
        Integer num = reportComment.getNum();
        if (null == num){
            num = 0;
        }
        reportComment.setNum(num+1);
        reportCommentMapper.updateByPrimaryKey(reportComment);
        return new CommonDto<>("ok", "success", 200);
    }

    @Transactional
    @Override
    public CommonDto<String> deleteReportCommentConcen(Integer appId, Long commentId) {

        ReportComment reportComment = reportCommentMapper.selectByPrimaryKey(commentId);
        Integer num = reportComment.getNum();
        if (null == num){
            num = 0;
        }else{
            num --;
        }
        reportComment.setNum(num);
        reportCommentMapper.updateByPrimaryKey(reportComment);
        return new CommonDto<>("ok", "success", 200);

    }

    @Transactional(readOnly = true)
    @Override
    public CommonDto<Integer> getReportCommentConcenNum(Integer appId, Long commentId) {

        ReportComment reportComment = reportCommentMapper.selectByPrimaryKey(commentId);
        return new CommonDto<>(reportComment.getNum(), "success", 200);
    }


}
