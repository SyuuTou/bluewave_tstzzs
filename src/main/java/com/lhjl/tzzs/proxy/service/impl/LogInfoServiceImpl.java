package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.UserChooseRecordMapper;
import com.lhjl.tzzs.proxy.model.UserChooseRecord;
import com.lhjl.tzzs.proxy.service.LogInfoService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class LogInfoServiceImpl implements LogInfoService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogInfoServiceImpl.class);

    @Autowired
    private UserChooseRecordMapper userChooseRecordMapper;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    /**
     * 记录用户操作记录的接口
     * @param token 用户token
     * @param data 对应数据
     * @param sceneKey 场景
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> saveUserInfo(String token, String data, String sceneKey){
        CommonDto<String> result = new CommonDto<>();
        if (token == null || "".equals(token) || "undefined".equals(token)){
            log.info("记录用户操作记录接口场景，此时用户的token输入为空");
        }

        if (sceneKey == null || "".equals(sceneKey) || "undefined".equals(sceneKey)){
            log.info("记录用户操作记录接口场景,此时用户的场景（scenekey）没有传入");
        }

        Date now = new Date();

        //将结果输出一份到log里
        log.info("用户的token为：{}",token);
        log.info("用户输入信息为：{}",data);
        log.info("场景为：{}",sceneKey);
        log.info("操作时间是：{}",now);

        Integer userId = userExistJudgmentService.getUserId(token);
        if (userId == -1){
            log.info("!!!!!!!!!!!!!!!!!!用户的token已经失效，找不到对应的用户，对应的token为{}",token);
        }

        UserChooseRecord userChooseRecord = new UserChooseRecord();
        userChooseRecord.setCreateTime(now);
        userChooseRecord.setDatas(data);
        userChooseRecord.setSceneKey(sceneKey);
        userChooseRecord.setUserId(userId);

        userChooseRecordMapper.insertSelective(userChooseRecord);

        result.setData(null);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    /**
     * 记录精选活动操作记录接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<String> saveElegantServiceLog(Map<String, Object> body) {
        CommonDto<String> result = new CommonDto<>();

        Date now = new Date();
        if (body.get("token") == null || "".equals(body.get("token")) || "undefined".equals(body.get("token"))){
            result.setMessage("缺少必要参数用户id");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.get("sceneKey") == null || "".equals(body.get("sceneKey")) || "undefined".equals(body.get("sceneKey"))){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("缺少必要参数场景key");

            return result;
        }

        if (body.get("actionType") == null || "".equals(body.get("actionType")) || "undefined".equals(body.get("actionType"))){
            result.setMessage("缺少必要参数操作类型");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        String token = (String)body.get("token");
        Integer userId = userExistJudgmentService.getUserId(token);

        if (userId == -1){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户token无效");

            return result;
        }

        String sceneKey = (String) body.get("sceneKey");
        Integer actionType = (Integer)body.get("actionType");

        UserChooseRecord userChooseRecord = new UserChooseRecord();
        userChooseRecord.setUserId(userId);
        userChooseRecord.setSceneKey(sceneKey);
        userChooseRecord.setCreateTime(now);
        userChooseRecord.setActionType(actionType);
        userChooseRecord.setLogType(1);

        userChooseRecordMapper.insertSelective(userChooseRecord);

        result.setMessage("success");
        result.setStatus(200);
        result.setData(null);

        return result;
    }
}
