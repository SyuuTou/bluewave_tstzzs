package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper extends OwnerMapper<AdminUser> {

	List<AdminUser> selectTstzzsAdmins(@Param("keyword") String keyword);

	Integer selectByLoginBody(@Param("username") String username, @Param("password") String password);
}