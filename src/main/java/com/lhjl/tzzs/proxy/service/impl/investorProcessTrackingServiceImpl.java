package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorOperateLogDto;
import com.lhjl.tzzs.proxy.mapper.InvestorOperationLogMapper;
import com.lhjl.tzzs.proxy.model.InvestorOperationLog;
import com.lhjl.tzzs.proxy.service.InvestorProcessTrackingService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class investorProcessTrackingServiceImpl implements InvestorProcessTrackingService {

    @Autowired
    private InvestorOperationLogMapper investorOperationLogMapper;

    @Transactional
    @Override
    public CommonDto<String> addorupdateOperateLog(InvestorOperateLogDto body) {
        CommonDto<String> result = new CommonDto<>();
        
        InvestorOperationLog investorOperationLog = new InvestorOperationLog();
        investorOperationLog.setInvestorId(body.getInvestorId());
        investorOperationLog.setOperator(body.getOperator());
        investorOperationLog.setOperateContent(body.getOperateContent());
        
        investorOperationLog.setYn(0);

        if(null == body.getId()){
            investorOperationLog.setCreateTime(new Date());
            investorOperationLogMapper.insertSelective(investorOperationLog);
        }else{
            investorOperationLog.setUpdateTime(new Date());
            investorOperationLogMapper.updateByPrimaryKeySelective(investorOperationLog);
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData("保存成功");
        return result;
    }

    @Override
    public CommonDto<List<InvestorOperateLogDto>> getInvestorOperateLogList(Integer investorId) {
        CommonDto<List<InvestorOperateLogDto>> result = new CommonDto<>();
        List<InvestorOperateLogDto> investorOperateLogDtoList = new ArrayList<>();
        
        List<InvestorOperationLog> investorOperationLogList = investorOperationLogMapper.selectAllInvestorOperationLog(investorId);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null != investorOperationLogList && investorOperationLogList.size()>0){
        	investorOperationLogList.forEach(e -> {
                InvestorOperateLogDto obj = new InvestorOperateLogDto();
                obj.setId(e.getId());
                obj.setInvestorId(investorId);
                obj.setOperateContent(e.getOperateContent());
                if(e.getUpdateTime() != null) {
                	obj.setOperateTime(sdf.format(e.getUpdateTime()));
                }
                obj.setOperator(e.getOperator());
                
                investorOperateLogDtoList.add(obj);
            });
        }

        result.setData(investorOperateLogDtoList);
        result.setStatus(200);
        result.setMessage("message");
        return result;
    }
    
    @Transactional
    @Override
    public CommonDto<String> deleteInvestorOperateLog(Long logId) {
        CommonDto<String> result = new CommonDto<>();
        if(investorOperationLogMapper.existsWithPrimaryKey(logId)) {
        	InvestorOperationLog investorOperationLog=new InvestorOperationLog();
        	investorOperationLog.setId((long)logId);
        	investorOperationLog.setYn(1);
        	
        	investorOperationLogMapper.updateByPrimaryKeySelective(investorOperationLog);
        }
        
        result.setData("删除成功");
        result.setStatus(200);
        result.setMessage("success");
        return result;

    }
}
