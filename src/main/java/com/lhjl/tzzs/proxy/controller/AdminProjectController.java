package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.AdminProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AdminProjectController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AdminProjectController.class);

    @Resource
    private AdminProjectService adminProjectService;

    /**
     * 后台获取项目列表接口
     * @param pageNum 页数
     * @param pageSize 每页显示数量
     * @param shortName 项目简称（搜索用）
     * @param projectType 项目类型：0数据导入项目，1表示用户提交项目
     * @return
     */
    @GetMapping("admin/get/project/list")
    public CommonDto<List<Map<String,Object>>> adminGetProjectList(Integer pageNum,Integer pageSize,String shortName,Integer projectType){
            CommonDto<List<Map<String,Object>>> result = new CommonDto<>();
            List<Map<String,Object>> list = new ArrayList<>();

            try {
                result = adminProjectService.getProjectList(pageNum,pageSize,shortName,projectType);
            }catch (Exception e){
                log.error(e.getMessage(),e.fillInStackTrace());
                result.setData(list);
                result.setMessage("服务器端发生错误");
                result.setStatus(502);
            }

            return result;
    }

}
