package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiNewDto;

/**
 * Created by zyy on 2017/11/24.
 */
public interface InvestorsApprovalNewService {
    /**
     * 保存认证信息
     * @param params
     * @return
     */
    CommonDto<String> saveTouZiNew(TouZiNewDto params);
}
