package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.UserExsitJudgmentDto;
import com.lhjl.tzzs.proxy.dto.UserYnDto;

import java.util.Map;

public interface UserExistJudgmentService {
    CommonDto<UserExsitJudgmentDto> userExistJudgment(String oppenId);
    CommonDto<UserYnDto> userYn(String token);
    int getUserId(String token);
}
