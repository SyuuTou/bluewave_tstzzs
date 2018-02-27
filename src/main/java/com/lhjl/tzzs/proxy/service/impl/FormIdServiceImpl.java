package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.UserFormidMapper;
import com.lhjl.tzzs.proxy.model.UserFormid;
import com.lhjl.tzzs.proxy.service.FormIdService;
import com.lhjl.tzzs.proxy.service.GenericService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class FormIdServiceImpl extends GenericService implements FormIdService {

    @Autowired
    private UserFormidMapper userFormidMapper;

    /**
     * 保存formid的接口
     * @param userId
     * @param formid
     * @param formidType
     * @return
     */
    @Override
    public CommonDto<String> saveFormid(Integer userId, String formid, Integer formidType) {

        UserFormid userFormid = new UserFormid();
        userFormid.setCreateTime(DateTime.now().toDate());
        userFormid.setFormid(formid);
        userFormid.setFormidType(formidType);
        userFormid.setUsersId(userId);
        userFormid.setYn(0);

        userFormidMapper.insertSelective(userFormid);

        return new CommonDto<>(null,"success",200);
    }

    /**
     * 读取formid的接口
     * @param userId
     * @param formidType
     * @return
     */
    @Override
    public CommonDto<UserFormid> findUserFormid(Integer userId, Integer formidType) {

        if (null == userId){
            return new CommonDto<>(null,"用户id不能为空",502);
        }

        Example userFormidExample = new Example(UserFormid.class);
        userFormidExample.and().andEqualTo("usersId",userId).andEqualTo("yn",0);
        if (null != formidType){
            userFormidExample.and().andEqualTo("formidType",formidType);
        }
        userFormidExample.setOrderByClause("create_time desc");

        List<UserFormid> userFormidList =  userFormidMapper.selectByExample(userFormidExample);

        if (userFormidList.size() > 0){
            return new CommonDto<>(userFormidList.get(0),"success",200);
        }

        return new CommonDto<>(null,"用户formid不存在,请检查",502);
    }

    /**
     * 使用formid的接口
     * @param id
     * @return
     */
    @Override
    public CommonDto<String> useUserFormid(Integer id) {

        if (null == id){
            return new CommonDto<>(null,"formid对应的id不能为空",502);
        }
        UserFormid userFormidForUpdate = new UserFormid();
        userFormidForUpdate.setYn(1);
        userFormidForUpdate.setUserTime(DateTime.now().toDate());
        userFormidForUpdate.setId(id);

        userFormidMapper.updateByPrimaryKey(userFormidForUpdate);

        return new CommonDto<>(null,"success",200);
    }
}
