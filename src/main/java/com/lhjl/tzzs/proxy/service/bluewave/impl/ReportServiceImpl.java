package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.github.pagehelper.PageRowBounds;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.CommonTotal;
import com.lhjl.tzzs.proxy.dto.EventDto;
import com.lhjl.tzzs.proxy.dto.ProInfoDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class ReportServiceImpl extends GenericService implements ReportService {


    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private ReportColumnService columnService;
    @Autowired
    private ReportSegmentationService segmentationService;
    @Autowired
    private ReportLabelService labelService;
    @Resource
    private UserLoginService userLoginService;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ReportColumnMapper reportColumnMapper;
    @Autowired
    private ReportSegmentationMapper reportSegmentationMapper;
    @Autowired
    private ReportLabelMapper reportLabelMapper;
    
    @Autowired
    private MetaColumnMapper metaColumnMapper;
    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;
    @Autowired
    private ReportCompanyLabelMapper reportCompanyLabelMapper;
    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ReportInstitutionRelationMapper reportInstitutionRelationMapper;

    @Value("${event.trigger.url}")
    private String eventUrl;


    @Transactional(readOnly = true)
    @Override
    public CommonDto<List<Map<String,Object>>> queryReport(Integer appId, ReportReqBody reqBody) {
    	
        CommonTotal<List<Map<String,Object>>> result = new CommonTotal<>();

        Report report = new Report();
        report.setComments(reqBody.getComments());
        report.setContent(reqBody.getContent());
        report.setCoverUrl(reqBody.getCoverUrl());
        report.setCreater(reqBody.getCreater());
        report.setFromRul(reqBody.getFromRul());
        report.setId(reqBody.getId());
        report.setSourceTextUrl(reqBody.getSourceTextUrl());
        report.setStatus(reqBody.getStatus());
        report.setSubTitle(reqBody.getSubTitle());
        report.setTitle(reqBody.getTitle());
        report.setWeightingFactor(reqBody.getWeightingFactor());
        report.setAuthor(reqBody.getAuthor());
//        System.err.println(report+"*****report");
        
        int offset = (reqBody.getPageNo() - 1) * reqBody.getPageSize();
        int limit = reqBody.getPageSize();
        List<Report>  list = null;
        PageRowBounds rowBounds = new PageRowBounds(offset, limit);
        if (reqBody.getColumnId()!=null) {
        	list = new ArrayList<Report>();
        	ReportColumn queryReportColumn = new ReportColumn();
        	queryReportColumn.setColumnId(reqBody.getColumnId());
        	List<ReportColumn> reportColumns = reportColumnMapper.selectByRowBounds(queryReportColumn, rowBounds);
        	for(ReportColumn rc : reportColumns) {
        	    ReportInstitutionRelation queryInstitutionRelation = new ReportInstitutionRelation();
        	    queryInstitutionRelation.setReportId(rc.getReportId());
        	    queryInstitutionRelation.setInstitutionId(reqBody.getInvestmentInstitutionId());
        	    if (null != reportInstitutionRelationMapper.selectOne(queryInstitutionRelation)) {
                    list.add(reportMapper.selectByPrimaryKey(rc.getReportId()));
                }
        	}
        }else {
        	list = reportMapper.selectByRowBounds(report, rowBounds);
        }
        
        List<Map<String,Object>> lists=new ArrayList<>();
        for(Report tmp:list) {
        	Map<String,Object> map=new HashMap<>();
        	Integer releaseId = tmp.getId();
        	map.put("report", tmp);
        	
        	ReportColumn rc =new ReportColumn();
        	rc.setReportId(releaseId);
        	List<ReportColumn> ReportColumns = reportColumnMapper.select(rc);
        	List<Integer> columns=new ArrayList<>();
        	if(ReportColumns!=null) {
        		ReportColumns.forEach((e)->{
            		columns.add(e.getColumnId());
            	});
        	}
        	map.put("columns", columns);
        	
        	ReportSegmentation rs =new ReportSegmentation();
        	rs.setReportId(releaseId);
        	List<ReportSegmentation> ReportSegmentations = reportSegmentationMapper.select(rs);
        	List<Integer> segmentations=new ArrayList<>();
        	if(ReportSegmentations!=null) {
        		ReportSegmentations.forEach((e)->{
        			segmentations.add(e.getSegmentationId());
            	});
        	}
        	map.put("segmentations", segmentations);
        	
        	ReportLabel rl=new ReportLabel();
        	rl.setReportId(releaseId);
        	List<ReportLabel> ReportLabels = reportLabelMapper.select(rl);
        	List<String> labels =new ArrayList<>();
        	if(ReportLabels != null) {
        		ReportLabels.forEach((e)->{
        			labels.add(e.getName());
        		});
        	}
        	map.put("lables", labels);
        	lists.add(map);
        }
        result.setData(lists);
        result.setMessage("success");
        result.setStatus(200);
        result.setTotal(rowBounds.getTotal());
  
        
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public CommonDto<Map<String,Object>> getReportById(Integer appId, Integer id) {
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> map=new HashMap<>();
        
        Report report = reportMapper.selectByPrimaryKey(id);
        Integer reportId = report.getId();
        map.put("report", report);
//        获取report的相关附属信息
        ReportColumn rc =new ReportColumn();
    	rc.setReportId(reportId);
    	List<ReportColumn> ReportColumns = reportColumnMapper.select(rc);
    	List<Integer> columns=new ArrayList<>();
    	if(ReportColumns!=null) {
    		ReportColumns.forEach((e)->{
        		columns.add(e.getColumnId());
        	});
    	}
    	map.put("columns", columns);
//        ReportColumn rc =new ReportColumn();
//    	rc.setReportId(reportId);
//    	List<ReportColumn> rcs = reportColumnMapper.select(rc);
//    	
//    	List<MetaColumn> metaColumns = new ArrayList<>();
//    	if(rcs != null) {
//    		rcs.forEach((e)->{
//    			MetaColumn mc=new MetaColumn();
//    			mc.setId(e.getColumnId());
//    			MetaColumn metaColumn = metaColumnMapper.selectOne(mc);
//    			metaColumns.add(metaColumn);
//        	});
//    	}
//    	report.setColumns(metaColumns);
//**************************
//    	ReportSegmentation  rs=new ReportSegmentation();
//    	rs.setReportId(reportId);
//    	List<ReportSegmentation> rss = reportSegmentationMapper.select(rs);
//    	
//    	List<MetaSegmentation> metaSegmentations = new ArrayList<>();
//    	if(rss != null) {
//    		rss.forEach((e)->{
//    			MetaSegmentation ms=new MetaSegmentation();
//    			ms.setId(e.getSegmentationId());
//    			MetaSegmentation metaSegmentation = metaSegmentationMapper.selectOne(ms);
//    			metaSegmentations.add(metaSegmentation);
//        	});
//    	}
//    	report.setSegmentations(metaSegmentations);
//    	
    	ReportSegmentation rs =new ReportSegmentation();
    	rs.setReportId(reportId);
    	List<ReportSegmentation> ReportSegmentations = reportSegmentationMapper.select(rs);
    	List<Integer> segmentations=new ArrayList<>();
    	if(ReportSegmentations!=null) {
    		ReportSegmentations.forEach((e)->{
    			segmentations.add(e.getSegmentationId());
        	});
    	}
    	map.put("segmentations", segmentations);
//**************************** 
//    	ReportLabel rl=new ReportLabel();
//    	rl.setReportId(reportId);
//    	List<ReportLabel> reportLabels = reportLabelMapper.select(rl);
//    	report.setReportLabels(reportLabels);
    	
    	ReportLabel rl=new ReportLabel();
    	rl.setReportId(reportId);
    	List<ReportLabel> ReportLabels = reportLabelMapper.select(rl);
    	List<String> labels =new ArrayList<>();
    	if(ReportLabels != null) {
    		ReportLabels.forEach((e)->{
    			labels.add(e.getName());
    		});
    	}
    	map.put("labels", labels);

    	//设置返回机构id
        ReportInstitutionRelation reportInstitutionRelation = new ReportInstitutionRelation();
        reportInstitutionRelation.setReportId(reportId);
        reportInstitutionRelation.setAppid(appId);

        List<Integer> reportInstitutionRelations = new ArrayList<>();
        List<ReportInstitutionRelation> reportInstitutionRelationList = reportInstitutionRelationMapper.select(reportInstitutionRelation);
        for (ReportInstitutionRelation rir:reportInstitutionRelationList){
            reportInstitutionRelations.add(rir.getInstitutionId());
        }
        map.put("institutionId",reportInstitutionRelations);
    	
    	//设置相关的项目信息
    	ReportCompanyLabel rcl=new ReportCompanyLabel();
    	rcl.setReportId(reportId);
    	//取得ReportCompanyLabel的多个映射实体
    	List<ReportCompanyLabel> reportCompanyLabels = reportCompanyLabelMapper.select(rcl);
    	List<String> companyLabels =new ArrayList<>();
    	/**
    	 * 项目的相关信息
    	 */
    	List<ProInfoDto> proInfoList = new ArrayList<>();
    	if(reportCompanyLabels != null) {
    		
    		reportCompanyLabels.forEach((e)->{
        		companyLabels.add(e.getCompanyName());
        	});
        	//companyLabels表示的是该report关联的额所有的项目的简称
        	companyLabels.forEach((e)->{
        		Projects pro=new Projects();
        		pro.setShortName(e);
        		try { //根据简称搜索唯一的一条项目信息
        			pro = projectsMapper.selectOne(pro);
        			System.err.println(pro+"****pro");
        		}catch(Exception ex) {
        			this.LOGGER.info(ex.getMessage(), ex.fillInStackTrace());
        			result.setData(null);
        			result.setMessage("公司的简称不唯一");
        			result.setStatus(500);
//        			return result;
        		}
        		//根据该项目信息获取该项目的相关  简称、 logo、 地域 、 一句话简介、 轮次 、领域  
        		if(pro != null) {
        			ProInfoDto projectsSimpleInfo = projectsMapper.getProjectsSimpleInfos(pro.getId());
            		proInfoList.add(projectsSimpleInfo);  
        		}
        	});
    	}
    	
    	map.put("proInfos", proInfoList);
    	
        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    @Transactional
    @Override
    public CommonDto<String> saveOrUpdate(Integer appId, ReportReqBody reqBody) {
       
    	
    	CommonDto<String> result = new CommonDto<>();

        Report report = new Report();
        report.setComments(reqBody.getComments());
        report.setContent(reqBody.getContent());
        report.setCoverUrl(reqBody.getCoverUrl());
        report.setCreater(reqBody.getCreater());
        report.setFromRul(reqBody.getFromRul());
        report.setId(reqBody.getId());
        report.setSourceTextUrl(reqBody.getSourceTextUrl());
        report.setStatus(reqBody.getStatus());
        report.setSubTitle(reqBody.getSubTitle());
        report.setTitle(reqBody.getTitle());
        report.setWeightingFactor(reqBody.getWeightingFactor());
        report.setCreater(reqBody.getCreater());
        report.setYn(reqBody.getYn());
        report.setSubTitle(reqBody.getSubTitle());
        report.setAuthor(reqBody.getAuthor());
        Integer num = null;
        Integer reportId = null;
        if (null == report.getId()){
        	report.setCreateTime(new Date());
            num = reportMapper.insert(report);
            reportId = report.getId();
            this.sendNewsEvent(reqBody.getCreater(),num,reqBody.getColumns());
        }else{
        	report.setUpdateTime(new Date());
        	reportId = reqBody.getId();
            num = reportMapper.updateByPrimaryKeySelective(report);
        }
        List<MetaColumn> columns = reqBody.getColumns();
        List<Integer> segmentations = reqBody.getSegmentations();
        List<String> labels = reqBody.getReportLabels();
        List<String> reportCompanyLabels = reqBody.getReportCompanyLabels();
        
        //进行关联公司标签的删除插入
        ReportCompanyLabel rcl=new ReportCompanyLabel();
        rcl.setReportId(report.getId());
        reportCompanyLabelMapper.delete(rcl);
        reportCompanyLabels.forEach((e)->{
        	rcl.setCompanyName(e);
        	reportCompanyLabelMapper.insertSelective(rcl);
        });
        
       ReportColumn metaColumn =new ReportColumn();
        metaColumn.setReportId(report.getId());

        columnService.deleteAll(report.getId());
        for (MetaColumn column : columns){
            metaColumn.setColumnId(column.getId());
            columnService.save(metaColumn);
        }

        ReportSegmentation reportSegmentation = new ReportSegmentation();
        reportSegmentation.setReportId(report.getId());
        segmentationService.deleteAll(report.getId());
        for (Integer segmentation : segmentations){
            reportSegmentation.setSegmentationId(segmentation);
            segmentationService.save(reportSegmentation);
        }

        ReportLabel reportLabel = new ReportLabel();
        reportLabel.setReportId(report.getId());

        labelService.deleteAll(report.getId());
        for (String lablel : labels){
            reportLabel.setName(lablel);
            labelService.save(reportLabel);
        }

        //插入文章相关机构信息
        //先删除原理的机构id
        ReportInstitutionRelation reportInstitutionRelationForDelete = new ReportInstitutionRelation();
        reportInstitutionRelationForDelete.setAppid(appId);
        reportInstitutionRelationForDelete.setReportId(report.getId());

        reportInstitutionRelationMapper.delete(reportInstitutionRelationForDelete);

        //插入新的
        if (reqBody.getInstitutionId() != null && reqBody.getInstitutionId().size() > 0){
            for (Integer i: reqBody.getInstitutionId()){
                ReportInstitutionRelation reportInstitutionRelation = new ReportInstitutionRelation();
                reportInstitutionRelation.setReportId(reportId);
                reportInstitutionRelation.setAppid(appId);
                reportInstitutionRelation.setInstitutionId(i);

                reportInstitutionRelationMapper.insertSelective(reportInstitutionRelation);
            }
        }


        result.setMessage("success");
        result.setStatus(200);
        result.setData(String.valueOf(num));

        return result;
    }

    private void sendNewsEvent(String creater, Integer projectId, List<MetaColumn> columnList) {
        EventDto eventDto = new EventDto();
        List<Integer> projectIds = new ArrayList<>();
        projectIds.add(projectId);
        eventDto.setFromUser(creater);
        eventDto.setProjectIds(projectIds);
        eventDto.setColumnList(columnList);
        eventDto.setEventType("NEWS");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EventDto> entity = new HttpEntity<>(eventDto, headers);
        HttpEntity<CommonDto<String>> investors =  restTemplate.exchange(eventUrl+"/trigger/event", HttpMethod.POST,entity,new ParameterizedTypeReference<CommonDto<String>>(){} );
    }
}
