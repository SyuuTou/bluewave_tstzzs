package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaColumn;

import java.util.List;

public interface ColumnService {
    CommonDto<List<MetaColumn>> queryAll();
}
