package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorOperateLogDto;
import com.lhjl.tzzs.proxy.mapper.InvestorOperationLogMapper;
import com.lhjl.tzzs.proxy.model.InvestorOperationLog;
import com.lhjl.tzzs.proxy.service.InvestorProcessTrackingService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public CommonDto<String> addorupdateOperateLog(InvestorOperateLogDto body) {
        CommonDto<String> result = new CommonDto<>();
        InvestorOperationLog investorOperationLog = new InvestorOperationLog();

        investorOperationLog.setInvestorId(body.getInvestorId());
        investorOperationLog.setOperator(body.getOperator());
        investorOperationLog.setOperateContent(body.getOperateContent());
        investorOperationLog.setYn(0);

        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer investorOperationLogInsertResult = -1;
        if(null == body.getId()){
            investorOperationLog.setCreateTime(DateUtils.parse(createTime));
            investorOperationLog.setUpdateTime(DateUtils.parse(createTime));
            investorOperationLogInsertResult =  investorOperationLogMapper.insert(investorOperationLog);
        }else{
            investorOperationLog.setUpdateTime(DateUtils.parse(createTime));
            investorOperationLogInsertResult =  investorOperationLogMapper.updateByPrimaryKey(investorOperationLog);
        }

        //TODO 怎么拿到investor_ID
        if(investorOperationLogInsertResult > 0){
            result.setMessage("success");
            result.setStatus(200);
            result.setData("保存成功");
            return result;
        }
        result.setMessage("failed");
        result.setStatus(300);
        result.setData("保存失败");
        return result;
    }

    @Override
    public CommonDto<List<InvestorOperateLogDto>> getInvestorOperateLogList(Integer investorId) {
        CommonDto<List<InvestorOperateLogDto>> result = new CommonDto<>();
        List<InvestorOperateLogDto> investorOperateLogDtoList = new ArrayList<>();
        List<InvestorOperationLog> investorOperationLogList = investorOperationLogMapper.selectAllInvestorOperationLog(investorId);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null == investorOperationLogList || investorOperationLogList.isEmpty()){
            result.setData(null);
            result.setStatus(200);
            result.setMessage("success");
            return result;
        }
        investorOperationLogList.forEach(investorOperationLog_i -> {
            InvestorOperateLogDto investorOperateLogDto1 = new InvestorOperateLogDto();
            investorOperateLogDto1.setId(investorOperationLog_i.getId());
            investorOperateLogDto1.setInvestorId(investorId);
            investorOperateLogDto1.setOperateContent(investorOperationLog_i.getOperateContent());
            if(investorOperationLog_i.getUpdateTime() != null) {
            	investorOperateLogDto1.setOperateTime(sdf.format(investorOperationLog_i.getUpdateTime()));
            }
            investorOperateLogDto1.setOperator(investorOperationLog_i.getOperator());
            investorOperateLogDtoList.add(investorOperateLogDto1);
        });

        result.setData(investorOperateLogDtoList);
        result.setStatus(200);
        result.setMessage("message");
        return result;
    }

    @Override
    public CommonDto<String> deleteInvestorOperateLog(Integer logId) {
        CommonDto<String> result = new CommonDto<>();
        InvestorOperationLog investorOperationLog = investorOperationLogMapper.selectByPrimaryKey(logId);
        if(null == investorOperationLog){
            result.setData("没有该记录，不能进行删除");
            result.setStatus(300);
            result.setMessage("failed");
            return result;
        }
        investorOperationLog.setYn(1);
        Integer investorOperationLogInsertResult = investorOperationLogMapper.updateByPrimaryKey(investorOperationLog);

        if(investorOperationLogInsertResult > 0){
            result.setData("删除成功");
            result.setStatus(200);
            result.setMessage("success");
            return result;
        }

        result.setData("删除失败");
        result.setStatus(500);
        result.setMessage("服务端错误");
        return result;

    }
}
