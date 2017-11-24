package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.List;
import java.util.Map;

public interface AdminProjectService {
    /**
     * 后台获取项目列表接口
     * @param pageNum 页数
     * @param pageSize 每页显示数量
     * @param shortName 项目简称（搜索用）
     * @param projectType 项目类型：0数据导入项目，1表示用户提交项目
     * @return
     */
    CommonDto<List<Map<String,Object>>> getProjectList(Integer pageNum,Integer pageSize,String shortName,Integer projectType);

    /**
     * 一键设置没有管理员项目的管理员
     * @param administractorId 管理员id
     * @return
     */
    CommonDto<String> setProjectAdminOnestep(Integer administractorId);
}
