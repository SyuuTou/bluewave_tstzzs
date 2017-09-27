package com.lhjl.tzzs.proxy.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.FollowService;

/**
 * 关注请求，取消关注记录
 * @author lmy
 *
 */
@RestController
public class FollowController {

    private static final Logger log = LoggerFactory.getLogger(FinancingController.class);

    @Resource
    private FollowService followService;

    /**
     *
     * @param status  关注状态0：未关注 1 已关注
     * @param projectId  项目id
     * @param userId  用户id
     * @return
     */
    @GetMapping("search/project/{status}/{projectId}/{userId}")
    public CommonDto<String> updateFollowStatus(@PathVariable Integer status,
                                                @PathVariable Integer projectId,@PathVariable String userId){
        CommonDto<String> result = new CommonDto<String>();
        try {
            followService.updateFollowStatus(status,projectId,userId);
            result.setMessage("操作成功");
        } catch (Exception e) {
            // TODO: handle exception
            result.setMessage("操作失败");
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
}