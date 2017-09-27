package com.lhjl.tzzs.proxy.service;

import org.apache.ibatis.annotations.Param;

/**
 * 关注
 * @author PP
 *
 */
public interface FollowService {

    /**
     * 更改关注状态
     * @param status
     * @param projectId
     */
    void updateFollowStatus(@Param("status") Integer status,@Param("projectId")Integer projectId,@Param("userId")String userId);
}