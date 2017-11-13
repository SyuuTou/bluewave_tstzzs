package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MiniappFormid;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MiniappFormidMapper extends OwnerMapper<MiniappFormid> {
    List<MiniappFormid> findFormiDesc(@Param("userId") Integer userId);
}