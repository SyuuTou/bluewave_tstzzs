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
import java.util.List;

@Service
public class UserFilterLogImpl implements UserFilterLogService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserFilterLogImpl.class);

    @Resource
    private UserFilterLogMapper userFilterLogMapper;

    @Transactional
    @Override
    public CommonDto<String> userFilterAddLog(String investment_institutions_type,String investment_institutions_field,String financing_stage,String city,String education,String work,String user_id){
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
    public CommonDto<UserFilterLog> userFilterSearchLog(String id){
        CommonDto<UserFilterLog> result = new CommonDto<UserFilterLog>();

        UserFilterLog userFilterLog = new UserFilterLog();
        userFilterLog.setUserId(id);

        UserFilterLog userFilterLog1 = userFilterLogMapper.selectOne(userFilterLog);

        result.setData(userFilterLog1);
        result.setStatus(200);
        result.setMessage("success");

        return result;

    }

}
