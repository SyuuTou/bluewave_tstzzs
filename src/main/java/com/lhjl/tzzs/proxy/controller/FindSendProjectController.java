package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.FindSendProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by zyy on 2017/11/17.
 */
@RestController
public class FindSendProjectController {

    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);
    @Autowired
    private FindSendProjectService findSendProjectService;

    /**
     * 查询提交项目列表
     *
     * @param shortName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("find/send/list")
    public CommonDto<List<Map<String, Object>>> findIntegralsSend(String shortName, Integer pageNum, Integer pageSize) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        try {
            //初始化分页信息
            if (pageNum == null) {
                pageNum = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }

            result = findSendProjectService.findIntegralsSend(shortName, pageNum, pageSize);

            if (result.getStatus() == null) {
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("显示页面异常，请稍后再试");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
}
