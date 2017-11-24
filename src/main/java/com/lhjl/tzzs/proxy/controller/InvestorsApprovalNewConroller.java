package com.lhjl.tzzs.proxy.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TouZiNewDto;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zyy on 2017/11/24.
 */
@RestController
public class InvestorsApprovalNewConroller {
    private static final Logger log = LoggerFactory.getLogger(InvestorsApprovalNewConroller.class);
    @Resource
    private InvestorsApprovalNewService investorsApprovalNewService;

    @Value("${pageNum}")
    private String defaultPageNum;

    @Value("${pageSize}")
    private String defaultPageSize;

    @Autowired
    private WxMaService wxService;



    /**
     * 1.2.4 投资人记录信息
     * @param params
     * @return
     */

    @PostMapping("1_2_4/rest/user/newuyhxinxi1")
    public CommonDto<String> insertGoldNew(@RequestBody TouZiNewDto params){
        CommonDto<String>result = new CommonDto<String>();
        try {

            result=investorsApprovalNewService.saveTouZiNew(params);
            if(result.getStatus() == null){
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5101);
            result.setMessage("显示页面异常，请稍后再试");
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
