package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectsSendDto;

/**
 * Created by lmy on 2017/11/27.
 */
public interface ProjectInformationDetailsService {
    CommonDto<String> informationDetails(ProjectsSendDto params, int userId);

}
