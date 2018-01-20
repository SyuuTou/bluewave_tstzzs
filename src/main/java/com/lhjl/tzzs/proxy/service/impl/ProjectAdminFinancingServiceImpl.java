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
    public CommonDto<FinancingLogOutputDto> getFinancingLog(Integer projectId) {
        CommonDto<FinancingLogOutputDto> result = new CommonDto<>();
        FinancingLogOutputDto financingLogOutputDto = new FinancingLogOutputDto();
        if(projectId == null){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }
        ProjectFinancingLog projectFinancingLog = projectFinancingLogMapper.selectByProjectId(projectId);
        if(null != projectFinancingLog){
            financingLogOutputDto.setStage(projectFinancingLog.getStage());
            financingLogOutputDto.setAmount(projectFinancingLog.getAmount());
            financingLogOutputDto.setCurrencyType(projectFinancingLog.getCurrency());
            financingLogOutputDto.setProjectId(projectId);
            financingLogOutputDto.setShareDivest(projectFinancingLog.getShareDivest());
            financingLogOutputDto.setFinancingApplication(projectFinancingLog.getProjectFinancingUseful());
        }
        result.setStatus(200);
        result.setMessage("success");
        result.setData(financingLogOutputDto);
        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateFinancingLog(Integer projectId, FinancingLogInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("请输入相关信息");
            return result;
        }
        ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
        projectFinancingLog.setProjectId(projectId);
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        projectFinancingLog.setAmount(body.getAmount());
        projectFinancingLog.setCurrency(body.getCurrencyType());
        projectFinancingLog.setStage(body.getStage());
        projectFinancingLog.setProjectFinancingUseful(body.getFinancingApplication());
        projectFinancingLog.setShareDivest(body.getShareDivest());
        Integer projectFinancingLogInsertResult = null;
        if(null == body.getProjectId()){
            projectFinancingLog.setCreateTime(DateUtils.parse(createTime));
            projectFinancingLogInsertResult = projectFinancingLogMapper.insert(projectFinancingLog);
        }else{
            projectFinancingLog.setUpdateTime(DateUtils.parse(createTime));
            projectFinancingLogInsertResult = projectFinancingLogMapper.updateByPrimaryKey(projectFinancingLog);
        }
        if(projectFinancingLogInsertResult > 0){
            result.setStatus(200);
            result.setMessage("success");
            result.setData("保存成功");
            return result;
        }
        result.setStatus(300);
        result.setMessage("failed");
        result.setData("保存失败");
        return result;
    }
}
