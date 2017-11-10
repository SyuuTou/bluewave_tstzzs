package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.mapper.AdminProjectRatingLogMapper;
import com.lhjl.tzzs.proxy.model.AdminProjectRatingLog;
import com.lhjl.tzzs.proxy.service.ProjectRatingService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectRatingServiceImpl implements ProjectRatingService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAuditServiceImpl.class);

    @Autowired
    private AdminProjectRatingLogMapper adminProjectRatingLogMapper;

    @Override
    public CommonDto<String> projectRating(ProjectRatingDto body){
        CommonDto<String> result = new CommonDto<>();
        Date now =new Date();

        if (body.getProjectId() == null){
            result.setStatus(50001);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.getRatingStage() == null){
            result.setData(null);
            result.setMessage("评级级别不能为空");
            result.setStatus(50001);
            return result;
        }
        //先判断项目是否已经评过等级了
        AdminProjectRatingLog adminProjectRatingLogForSearch = new AdminProjectRatingLog();
        adminProjectRatingLogForSearch.setProjectId(body.getProjectId());

        List<AdminProjectRatingLog> adminProjectRatingLogList = adminProjectRatingLogMapper.select(adminProjectRatingLogForSearch);

        if (adminProjectRatingLogList.size() > 0){
            Integer aprlid  = adminProjectRatingLogList.get(0).getId();

            AdminProjectRatingLog adminProjectRatingLogForUpdate = new AdminProjectRatingLog();
            adminProjectRatingLogForUpdate.setId(aprlid);
            adminProjectRatingLogForUpdate.setRatingStage(body.getRatingStage());
            if (body.getRatingAdminName() != null){
                adminProjectRatingLogForUpdate.setRatingAdminName(body.getRatingAdminName());
            }
            if (body.getRatingDiscription() != null){
                adminProjectRatingLogForUpdate.setRatingDescription(body.getRatingDiscription());
            }
            adminProjectRatingLogForUpdate.setRatingTime(now);

            //更新数据
            adminProjectRatingLogMapper.updateByPrimaryKeySelective(adminProjectRatingLogForUpdate);
        }else{
            AdminProjectRatingLog adminProjectRatingLog = new AdminProjectRatingLog();
            adminProjectRatingLog.setProjectId(body.getProjectId());
            adminProjectRatingLog.setRatingStage(body.getRatingStage());
            if (body.getRatingAdminName() != null){
                adminProjectRatingLog.setRatingAdminName(body.getRatingAdminName());
            }
            if (body.getRatingDiscription() != null){
                adminProjectRatingLog.setRatingDescription(body.getRatingDiscription());
            }
            adminProjectRatingLog.setRatingTime(now);

            //插入数据
            adminProjectRatingLogMapper.insertSelective(adminProjectRatingLog);
        }



        result.setStatus(200);
        result.setMessage("success");
        result.setData(null);

        return result;
    }
}
