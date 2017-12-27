package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.UserLevelRelation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserLevelRelationMapper extends OwnerMapper<UserLevelRelation> {
	Integer findByUserIdLeid(@Param("userId") Integer userId);
	List<Map<String, Object>> findByMing(@Param("userId") Integer userId, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);

	List<String> findByUserIdLeid(@Param("userId") Integer userId, @Param("sceneKey") String sceneKey, @Param("now") String now);
}