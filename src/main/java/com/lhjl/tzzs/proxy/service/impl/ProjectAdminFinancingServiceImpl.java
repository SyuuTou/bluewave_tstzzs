package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FinancingLogDto.FinancingLogInputDto;
import com.lhjl.tzzs.proxy.dto.FinancingLogDto.FinancingLogOutputDto;
import com.lhjl.tzzs.proxy.mapper.ProjectFinancingLogMapper;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.service.ProjectAdminFinancingService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
@Service
public class ProjectAdminFinancingServiceImpl implements ProjectAdminFinancingService {

    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;

    @Override
    public CommonDto<FinancingLogOutputDto> getFinancingLog(Integer subjectId,Integer subjectType) {
        CommonDto<FinancingLogOutputDto> result = new CommonDto<>();
        if(subjectId == null){
            result.setStatus(500);
            result.setMessage("请输入相应的主体id");
            result.setData(null);
            return result;
        }
        if(subjectType == null){
            result.setStatus(500);
            result.setMessage("请输入相应的主体类型");
            result.setData(null);
            return result;
        }
        
        FinancingLogOutputDto financingLogOutputDto = new FinancingLogOutputDto();
        
        ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
        
        if(Integer.valueOf(1).equals(subjectType)) {//项目
            projectFinancingLog = projectFinancingLogMapper.selectByProjectId(subjectId);
            if(null != projectFinancingLog){
                financingLogOutputDto.setId(projectFinancingLog.getId());
                financingLogOutputDto.setStage(projectFinancingLog.getStage());
                financingLogOutputDto.setAmount(projectFinancingLog.getAmount());
                financingLogOutputDto.setCurrencyType(projectFinancingLog.getCurrency());
                financingLogOutputDto.setProjectId(subjectId);
                financingLogOutputDto.setShareDivest(projectFinancingLog.getShareDivest());
                financingLogOutputDto.setFinancingApplication(projectFinancingLog.getProjectFinancingUseful());
            }
        }else if(Integer.valueOf(2).equals(subjectType)) {//机构
        	
        }
        
        result.setStatus(200);
        result.setMessage("success");
        result.setData(financingLogOutputDto);
        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateFinancingLog(FinancingLogInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        
        if(null == body){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData("请输入相关信息");
            return result;
        }
        if(null == body.getSubjectType()){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData("请输入主体类型");
            return result;
        }
        if(null == body.getProjectId()){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData("请输入主体id");
            return result;
        }
        
        //项目的融资历史
        ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
        projectFinancingLog.setId(body.getId());
        projectFinancingLog.setProjectId(body.getProjectId());
        projectFinancingLog.setAmount(body.getAmount());
        projectFinancingLog.setCurrency(body.getCurrencyType());
        projectFinancingLog.setStage(body.getStage());
        projectFinancingLog.setProjectFinancingUseful(body.getFinancingApplication());
        projectFinancingLog.setShareDivest(body.getShareDivest());
        
        //固有信息设置
        projectFinancingLog.setApprovalStatus(1);
        projectFinancingLog.setYn(0);
        
        if(Integer.valueOf(1).equals(body.getSubjectType())) {//项目
            if(null == body.getId()){
                projectFinancingLog.setCreateTime(new Date());
                projectFinancingLogMapper.insertSelective(projectFinancingLog);
                result.setData("保存成功");
            }else{
                projectFinancingLog.setUpdateTime(new Date());
                projectFinancingLogMapper.updateByPrimaryKeySelective(projectFinancingLog);
                result.setData("更新成功");
            }
        }else if(Integer.valueOf(2).equals(body.getSubjectType())) {//机构
        	//TODO 机构融资需求信息后台数据有待完善
        	
        	result.setData("成功");
        }
        
        
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }
}
