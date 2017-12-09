package com.lhjl.tzzs.proxy.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.Follow;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

/**
 * 关注
 * @author PP
 *
 */
@Mapper
public interface FollowMapper extends OwnerMapper<Follow> {

    /**
     * 更改关注状态
     * @param
     */
    void updateFollowStatus(Follow follow);
   Follow findfollowyn(@Param("userId") String userId,@Param("id") Integer id);

   List<Map<String,Object>> getProjectsFollowByIds(@Param("projectsIds") Integer[] projectsIds);

}