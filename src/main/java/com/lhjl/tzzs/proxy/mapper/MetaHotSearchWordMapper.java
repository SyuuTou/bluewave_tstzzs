package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaHotSearchWord;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

import java.util.List;

public interface MetaHotSearchWordMapper extends OwnerMapper<MetaHotSearchWord> {
    List<MetaHotSearchWord> searchHotword();
}