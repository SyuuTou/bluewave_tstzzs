package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import com.lhjl.tzzs.proxy.dto.UserYnDto;


public interface UserExistJudgmentService {
    CommonDto<UserExsitJudgmentDto> userExistJudgment(String oppenId, Integer appid, String unionid);
    CommonDto<UserYnDto> userYn(String token);
    int getUserId(String token);
}
