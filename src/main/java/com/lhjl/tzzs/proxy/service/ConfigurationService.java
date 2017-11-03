package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

public interface ConfigurationService {
    CommonDto<String> getConfiguration(Integer type);
}
