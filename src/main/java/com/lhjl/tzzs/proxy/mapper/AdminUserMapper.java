package com.lhjl.tzzs.proxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface AdminUserMapper extends OwnerMapper<AdminUser> {

	List<AdminUser> selectTstzzsAdmins(@Param("keyword") String keyword);
}