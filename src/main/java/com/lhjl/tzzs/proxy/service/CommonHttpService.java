package com.lhjl.tzzs.proxy.service;


import com.lhjl.tzzs.proxy.dto.FindPwdReqBody;
import com.lhjl.tzzs.proxy.dto.LoginReqBody;
import com.lhjl.tzzs.proxy.dto.SendsecuritycodeReqBody;
import com.lhjl.tzzs.proxy.dto.ZhuceReqBody;

import java.util.List;

public interface  CommonHttpService {


    String  requestLogin(LoginReqBody loginReqBody);

    String requestSendsecuritycode(SendsecuritycodeReqBody sendsecuritycodeReqBody);

    String requestZhuce(ZhuceReqBody zhuceReqBody);

    String requestFindPwd(FindPwdReqBody findPwdReqBody);
}
