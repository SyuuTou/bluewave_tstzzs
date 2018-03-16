package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper extends OwnerMapper<AdminUser> {
	/**
	 * 关键字智能搜索相关的管理员
	 * @param keyword 此处的keyword搜索的是admin_user中的name，相当于用户名
	 * @return
	 */
	List<AdminUser> selectTstzzsAdmins(@Param("keyword") String keyword);
	/**
	 * 关键字智能搜索相关的管理员（作为备用）
	 * @param keyword 此处的keyword搜索的是users表中的真实姓名，符合用户使用的真实场景
	 * @return
	 */
	List<AdminUser> selectTstzzsAdminsByActualName(@Param("keyword") String keyword);

	Integer selectByLoginBody(@Param("username") String username, @Param("password") String password);
}