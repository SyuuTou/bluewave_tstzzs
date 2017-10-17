package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.UserLevelRelation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface UserLevelRelationMapper extends OwnerMapper<UserLevelRelation> {
	List<Map<String, Object>> findByMing(@Param("userId") Integer userId, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
}