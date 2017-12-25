package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.UserFilterLogMapper;
import com.lhjl.tzzs.proxy.model.UserFilterLog;
import com.lhjl.tzzs.proxy.service.UserFilterLogService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserFilterLogImpl implements UserFilterLogService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserFilterLogImpl.class);

    @Resource
    private UserFilterLogMapper userFilterLogMapper;

    @Transactional
    @Override
    public CommonDto<String> userFilterAddLog(String investment_institutions_type,String investment_institutions_field,String financing_stage,String city,String education,String work,String user_id,String financingYear){
        CommonDto<String> result =new CommonDto<String>();

        Date now =new Date();

        //插入用信息点
        UserFilterLog userFilterLog = new UserFilterLog();
        userFilterLog.setInvestmentInstitutionsType(investment_institutions_type);
        userFilterLog.setInvestmentInstitutionsField(investment_institutions_field);
        userFilterLog.setFinancingStage(financing_stage);
        userFilterLog.setCity(city);
        userFilterLog.setEducation(education);
        userFilterLog.setDate(now);
        userFilterLog.setUserId(user_id);
        userFilterLog.setWork(work);
        userFilterLog.setFinancingYear(financingYear);
        //先查一查有没有数据了
        UserFilterLog ufl = new UserFilterLog();
        ufl.setUserId(user_id);

        List<UserFilterLog> userFilterLogs = userFilterLogMapper.select(ufl);
        if (userFilterLogs.size() !=0) {
            //有数据更新
            for (UserFilterLog usltemp : userFilterLogs) {
                usltemp.setInvestmentInstitutionsType(investment_institutions_type);
                usltemp.setInvestmentInstitutionsField(investment_institutions_field);
                usltemp.setFinancingStage(financing_stage);
                usltemp.setCity(city);
                usltemp.setEducation(education);
                usltemp.setDate(now);
                usltemp.setWork(work);
                usltemp.setFinancingYear(financingYear);
                userFilterLogMapper.updateByPrimaryKey(usltemp);
            }
        }else {
            //没数据新建
            userFilterLogMapper.insert(userFilterLog);
        }



        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> userFilterSearchLog(String id){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        UserFilterLog userFilterLog = new UserFilterLog();
        userFilterLog.setUserId(id);

        UserFilterLog userFilterLog1 = userFilterLogMapper.selectOne(userFilterLog);
        Map<String,Object> map = new HashMap<>();

        String[] investmentInstitutionsField = {};
        if (userFilterLog1.getInvestmentInstitutionsField() != null){
            investmentInstitutionsField = userFilterLog1.getInvestmentInstitutionsField().split(",");
        }

        String[] financingStage = {};
        if (userFilterLog1.getFinancingStage() != null){
            financingStage = userFilterLog1.getFinancingStage().split(",");
        }

        String[] city = {};
        if (userFilterLog1.getCity() != null){
            city = userFilterLog1.getCity().split(",");
        }

        String[] education = {};
        if (userFilterLog1.getEducation() != null){
            education = userFilterLog1.getEducation().split(",");
        }

        String[] work={};
        if (userFilterLog1.getWork() != null){
            work = userFilterLog1.getWork().split(",");
        }

        String[] financingYear = {};
        if (userFilterLog1.getFinancingYear() != null){
            financingYear = userFilterLog1.getFinancingYear().split(",");
        }

        map.put("investmentInstitutionsType",userFilterLog1.getInvestmentInstitutionsType());
        map.putIfAbsent("investmentInstitutionsType","");
        map.put("investmentInstitutionsField",investmentInstitutionsField);
        map.put("financingStage",financingStage);
        map.put("city",city);
        map.put("education",education);
        map.put("work",work);
        map.put("financingYear",financingYear);

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");

        return result;

    }

}