package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.AdminUserListInputDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdministratorOutputDto;
import com.lhjl.tzzs.proxy.dto.UserChooseLogDto.UserElegantServiceInputDto;
import com.lhjl.tzzs.proxy.model.MetaUserLevel;
import com.lhjl.tzzs.proxy.model.Users;

import java.util.List;
import java.util.Map;

/**
 * Created by 蓝海巨浪 on 2017/10/25.
 */
public interface UserInfoService {
	/**
	 * 测试用-->获得分页数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Users> listSplit(Integer pageNum,Integer pageSize);
	/**
	 * ****用于测试用-->根据用户ID获取该用户信息
	 * @param userId
	 * @return
	 */
	CommonDto<Users> getUserByUserId(int userId);
    /**
     * 获取个人资料
     * @param userId 用户ID
     * @return
     */
    CommonDto<Map<String, Object>> newrxsdqyh(int userId);

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonDto<List<Map<String,Object>>> getUserList(Integer pageNum, Integer pageSize);

    /**
     * 管理员获取用户
     * @param body
     * @return
     */
    CommonDto<Map<String,Object>> adminGetUserList(AdminUserListInputDto body);

    /**
     * 获取用户可用formid
     * @param userId 用户id
     * @return
     */
    CommonDto<String> getUserFormid(Integer userId);

    /**
     * 设置formid为失效的接口
     * @param formid
     * @return
     */
    CommonDto<String> setUserFormid(String formid);

    /**
     * 用token换取用户真实姓名，头像，公司名称的接口
     * @param body
     * @return
     */
    CommonDto<ProjectAdministratorOutputDto> getUserComplexInfo(Map<String,String> body);

    /**
     * 获取用户是否报名的接口
     * @param token
     * @return
     */
    CommonDto<String> getUserActivity(String token);

    /**
     * 活动申请页面信息回显接口
     * @param token
     * @return
     */
    CommonDto<Map<String,Object>> getUserActivityInfo(String token);

    /**
     *  获取用户投资机构id
     * @param token
     * @return
     */
    CommonDto<Integer> getUserInvestmentInstitution(String token);

    /**
     * 获取用户精选活动信息列表的接口
     * @param body
     * @return
     */
    CommonDto<Map<String,Object>> getElegantServiceLogList(UserElegantServiceInputDto body);

    /**
     * 设置记录的联系状态
     * @param logId
     * @return
     */
    CommonDto<String> setElegantServiceLogStatus(Integer logId);

    CommonDto<List<MetaUserLevel>> getMetaUserLevel();
}
