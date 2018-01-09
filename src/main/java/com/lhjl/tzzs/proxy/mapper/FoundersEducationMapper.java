package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.FoundersEducation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoundersEducationMapper extends OwnerMapper<FoundersEducation> {

    /**
     * 根据输入内容查找教育经历
     * @param inputsWords 教育经历名称
     * @param pageSize 页面大小
     * @param pageNum 页面起始点
     * @return
     */
    List<FoundersEducation> findFounderEducationIntelligent(@Param("inputsWords") String inputsWords,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);
    
    /**
     * 根据用户Id获取教育经历信息
     * @param userId
     * @return
     */
    List<String> findFoundersEducationsByUserId(@Param("userId") Integer userId);
}