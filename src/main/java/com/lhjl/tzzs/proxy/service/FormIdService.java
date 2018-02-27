package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.UserFormid;

public interface FormIdService {
    /**
     * 保存formid的接口
     * @param userId
     * @param formid
     * @param formidType
     * @return
     */
    CommonDto<String> saveFormid(Integer userId,String formid,Integer formidType);

    /**
     * 读取formid的接口
     * @param userId
     * @param formidType
     * @return
     */
    CommonDto<UserFormid> findUserFormid(Integer userId, Integer formidType);

    /**
     * 使用formid的接口
     * @param id
     * @return
     */
    CommonDto<String> useUserFormid(Integer id);
}
