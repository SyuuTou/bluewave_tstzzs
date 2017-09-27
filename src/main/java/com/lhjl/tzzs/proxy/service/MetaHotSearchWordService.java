package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.model.MetaHotSearchWord;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.List;

public interface MetaHotSearchWordService {
    CommonDto<List<MetaHotSearchWord>> selectHotWord();
    CommonDto<String> updateHotWordAmount(Integer id);
}
