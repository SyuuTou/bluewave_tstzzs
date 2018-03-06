package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.github.pagehelper.PageRowBounds;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ReportCommentDto.ReportCommentInputDto;
import com.lhjl.tzzs.proxy.dto.ReportCommentDto.ReportCommentOutputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ReportEventService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

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

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private ReportCommentConcenMapper reportCommentConcenMapper;


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
        reportComment.setAppId(appId);
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
    public CommonDto<List<ReportCommentOutputDto>> findReportComment(Integer appId, Integer reportId, String tokenName, Integer pageNo, Integer pageSize) {

        CommonDto<List<ReportCommentOutputDto>> result = new CommonDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ReportComment query = new ReportComment();
        query.setAppId(appId);
        query.setReportId(reportId);
        int offset = (pageNo - 1) * pageSize;
        int limit = pageSize;

        PageRowBounds rowBounds = new PageRowBounds(offset, limit);


        List<ReportComment> comments = reportCommentMapper.selectReportOrderByCreateTime(appId, reportId, offset, pageSize);
        List<ReportCommentOutputDto> reportCommentOutputDtoList = new ArrayList<>();
        ReportCommentConcen reportCommentConcen = new ReportCommentConcen();
        if (null != comments && comments.size()>0){

            for(ReportComment reportComment : comments){
                ReportCommentOutputDto reportCommentOutputDto = new ReportCommentOutputDto();
                reportCommentOutputDto.setColumnId(reportComment.getColumnId());
                reportCommentOutputDto.setCreateTime(reportComment.getCreateTime().getTime());
                reportCommentOutputDto.setId(reportComment.getId());
                reportCommentOutputDto.setMessage(reportComment.getMessage());
                reportCommentOutputDto.setReportId(reportComment.getReportId());
                reportCommentConcen.setAppId(appId);
                reportCommentConcen.setCommentId(reportComment.getId());
                reportCommentConcen.setYn(1);
                reportCommentOutputDto.setNum(reportCommentConcenMapper.selectCount(reportCommentConcen));
                reportCommentConcen.setToken(tokenName);
                reportCommentOutputDto.setIsLike(reportCommentConcenMapper.selectCount(reportCommentConcen));
                reportCommentConcen.setToken(null);
                reportCommentOutputDto.setToken(reportComment.getToken());
                reportCommentOutputDtoList.add(reportCommentOutputDto);
            }
            String[] token = new String[comments.size()];
            for (int i=0;i<comments.size();i++){
                token[i] = comments.get(i).getToken();
            }

            List<Users> usersList = usersMapper.selectUserListByToken(token);

            if (usersList.size()>0){
               for (ReportCommentOutputDto r:reportCommentOutputDtoList){
                   int count = 0;
                   r.setUserName("");
                   r.setUserHeadpic("");
                   r.setUserCompanyDuties("");
                   for (Users u :usersList){
                       if (String.valueOf(u.getUuid()).equals(String.valueOf(r.getToken()))){
                           String name = "";
                           if (u.getActualName() != null){
                               name = u.getActualName();
                           }
                          String headpic = "";
                           if (u.getHeadpicReal() != null){
                               headpic = u.getHeadpicReal();
                           }
                           String companyDuties ="";
                           if (u.getCompanyDuties() != null){
                               companyDuties = u.getCompanyDuties();
                           }

                           r.setUserName(name);
                           r.setUserHeadpic(headpic);
                           r.setUserCompanyDuties(companyDuties);
                       }
                   }
               }
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
    public CommonDto<String> updateReportCommentConcen(Integer appId, Long commentId, String token) {

        ReportCommentConcen reportCommentConcen = new ReportCommentConcen();
        reportCommentConcen.setAppId(appId);
        reportCommentConcen.setCommentId(commentId);
        reportCommentConcen.setToken(token);
        reportCommentConcen.setYn(1);
        reportCommentConcen.setCreateTime(DateTime.now().toDate());
        reportCommentConcenMapper.insert(reportCommentConcen);
        return new CommonDto<>("ok", "success", 200);
    }

    @Transactional
    @Override
    public CommonDto<String> deleteReportCommentConcen(Integer appId, Long commentId, String token) {

        ReportCommentConcen reportCommentConcen = new ReportCommentConcen();
        reportCommentConcen.setAppId(appId);
        reportCommentConcen.setCommentId(commentId);
        reportCommentConcen.setToken(token);
        reportCommentConcen.setYn(1);
        reportCommentConcenMapper.delete(reportCommentConcenMapper.selectOne(reportCommentConcen));

        return new CommonDto<>("ok", "success", 200);

    }

    @Transactional(readOnly = true)
    @Override
    public CommonDto<Integer> getReportCommentConcenNum(Integer appId, Long commentId, String token) {

        ReportCommentConcen reportCommentConcen = new ReportCommentConcen();
        reportCommentConcen.setAppId(appId);
        reportCommentConcen.setCommentId(commentId);
       if (StringUtil.isNotEmpty(token)) {
           reportCommentConcen.setToken(token);
       }
        reportCommentConcen.setYn(1);
        return new CommonDto<>(reportCommentConcenMapper.selectCount(reportCommentConcen), "success", 200);
    }


}
