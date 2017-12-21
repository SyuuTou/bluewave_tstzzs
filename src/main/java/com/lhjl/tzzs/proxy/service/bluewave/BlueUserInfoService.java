package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.UserHeadPicOutputDto;

public interface BlueUserInfoService {

    CommonDto<UserHeadPicOutputDto> getUserHeadpic(Integer appid, String token);
}
