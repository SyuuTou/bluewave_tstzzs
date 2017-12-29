package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.FoundersWorkMapper;
import com.lhjl.tzzs.proxy.model.FoundersWork;
import com.lhjl.tzzs.proxy.service.FounderWorkService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FounderWorkServiceImpl implements FounderWorkService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FounderWorkServiceImpl.class);

    @Autowired
    private FoundersWorkMapper foundersWorkMapper;

    /**
     * 工作经历检索检索
     * @param inputsWords 输入内容
     * @param pageSize 返回数量
     * @return
     */
    @Override
    public CommonDto<List<FoundersWork>> findFounderWork(String inputsWords, Integer pageSize){
        CommonDto<List<FoundersWork>> result = new CommonDto<>();
        List<FoundersWork> list =new ArrayList<>();

        if (inputsWords == null || "undefined".equals(inputsWords)){
            inputsWords = "";
        }

        if (pageSize == null || pageSize <= 0){
            pageSize = 5;
        }
        Integer pageNum = 0;
        list = foundersWorkMapper.findFounderWorkIntelligent(inputsWords,pageNum,pageSize);

        result.setData(list);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    /**
     * 更新或者创建创始人工作背景方法
     * @param founderId
     * @param workExperience
     */
    @Override
    public void createOrUpdateFounderWork(Integer founderId, List<String> workExperience) {
        if (founderId == null){
            return;
        }

        FoundersWork foundersWork = new FoundersWork();
        foundersWork.setFounderId(founderId);

        List<FoundersWork> foundersWorkList = foundersWorkMapper.select(foundersWork);
        if (foundersWorkList.size() > 0){
            foundersWorkMapper.delete(foundersWork);
            if (workExperience != null){
                for (String s:workExperience){
                    FoundersWork foundersWorkForInsert = new FoundersWork();
                    foundersWorkForInsert.setFounderId(founderId);
                    foundersWorkForInsert.setWorkExperience(s);

                    foundersWorkMapper.insertSelective(foundersWorkForInsert);
                }
            }

        }else {
            if (workExperience != null){
                for (String s:workExperience){
                    FoundersWork foundersWorkForInsert = new FoundersWork();
                    foundersWorkForInsert.setFounderId(founderId);
                    foundersWorkForInsert.setWorkExperience(s);

                    foundersWorkMapper.insertSelective(foundersWorkForInsert);
                }
            }
        }
    }
}
