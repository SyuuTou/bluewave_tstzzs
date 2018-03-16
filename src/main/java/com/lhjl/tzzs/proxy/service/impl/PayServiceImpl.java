package com.lhjl.tzzs.proxy.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayBaseResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PayRequestBody;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ActivityApprovalLogService;
import com.lhjl.tzzs.proxy.service.LogInfoService;
import com.lhjl.tzzs.proxy.service.PayService;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service("idataVCPayService")
public class PayServiceImpl implements PayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayService.class);

    @Autowired
    private UserMoneyRecordMapper userMoneyRecordMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersWeixinMapper usersWeixinMapper;

    @Autowired
    private MetaSceneMapper sceneMapper;
    @Autowired
    private UsersPayMapper usersPayMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private WxPayService payService;

    @Autowired
    private UserIntegralsService userIntegralsService;

    @Resource
    private ActivityApprovalLogService activityApprovalLogService;

    @Value("notifyURL")
    private String notifyURL;

    @Resource
    private LogInfoService logInfoService;

    @Autowired
    private UserIntegralsMapper userIntegralsMapper;
    @Autowired
    private UserIntegralConsumeMapper userIntegralConsumeMapper;

    @Override
    public CommonDto<Map<String, String>> generatePayInfo(PayRequestBody payRequestBody, Integer appId) {

        CommonDto<Map<String, String>> result = new CommonDto<>();

        UserMoneyRecord userMoneyRecord = new UserMoneyRecord();
        userMoneyRecord.setAppId(appId);
        userMoneyRecord.setId(payRequestBody.getMoneyId());

        userMoneyRecord = userMoneyRecordMapper.selectOne(userMoneyRecord);

        Users query = new Users();
        query.setUuid(payRequestBody.getUuid());

        Users users = usersMapper.selectOne(query);

        UsersWeixin queryWX = new UsersWeixin();
        queryWX.setUserId(users.getId());
        queryWX.setMetaAppId(appId);

        UsersWeixin usersWeixin = usersWeixinMapper.selectOne(queryWX);

        MetaScene queryMS = new MetaScene();
        queryMS.setKey(payRequestBody.getSceneKey());

        MetaScene scene = sceneMapper.selectOne(queryMS);

        String sceneType = payRequestBody.getSceneType();



        try {
            String tradeNo = generateTradeNo(userMoneyRecord.getId());
            UsersPay usersPay = new UsersPay();
            usersPay.setAmount(userMoneyRecord.getMoney());
            usersPay.setCreateTime(DateTime.now().toDate());
            usersPay.setOpenId(usersWeixin.getOpenid());
            usersPay.setPayDetail(scene.getDesc());
            usersPay.setPayStatus(0);
            usersPay.setReceivable(userMoneyRecord.getMoney());
            usersPay.setSceneKey(scene.getKey());
            usersPay.setUserId(usersWeixin.getUserId());
            usersPay.setTradeNo(tradeNo);
            usersPayMapper.insert(usersPay);
            StringBuilder sb = new StringBuilder(payRequestBody.getSceneKey());
            sb.append("|").append(sceneType).append("|").append(appId).append("|").append(payRequestBody.getBusinessId());
            WxPayUnifiedOrderRequest prepayInfo = WxPayUnifiedOrderRequest.newBuilder()
                    .openid(usersWeixin.getOpenid())
                    .outTradeNo(tradeNo)
                    .totalFee(WxPayBaseRequest.yuanToFee(userMoneyRecord.getMoney().toString()))
                    .body(scene.getDesc())
                    .attach(sb.toString())
                    .spbillCreateIp("39.106.44.53")
                    .build();
            Map<String,String> payInfo = payService.getPayInfo(prepayInfo);
//            WxPayUnifiedOrderResult payInfo =payService.unifiedOrder(prepayInfo);


            result.setData(payInfo);
            result.setMessage("success");
            result.setStatus(200);
        } catch (WxPayException e) {
            e.printStackTrace();
            result.setMessage("Failed");
            result.setStatus(500);
        }

        return result;
    }

    @Transactional
    @Override
    public void payNotifyHandler(WxPayOrderNotifyResult result) {

        LOGGER.info("Beginning notify handler........");
        UsersPay query = new UsersPay();
        query.setTradeNo(result.getOutTradeNo());

        UsersPay usersPay = usersPayMapper.selectOne(query);
        usersPay.setWxOrderNum(result.getTransactionId());
        usersPay.setPayStatus(1);
        usersPay.setPayTime(DateTime.now().toDate());
        usersPay.setReceived(new BigDecimal(WxPayBaseResult.feeToYuan(result.getTotalFee())));
        usersPayMapper.updateByPrimaryKey(usersPay);

        if (result.getAttach().split("\\|")[0].equals("RMB_PAYMENT")) {
            //悬赏支付的人民币金额进行锁定，锁定状态dataStatus=1
            UserIntegrals userIntegrals =new UserIntegrals();
            userIntegrals.setUserId(usersPay.getUserId());
            userIntegrals.setAppId(Integer.valueOf(result.getAttach().split("\\|")[2]));
            userIntegrals.setSceneKey(result.getAttach().split("\\|")[0]);

            userIntegrals.setConsumeNum(new BigDecimal(0));
            userIntegrals.setIntegralNum(new BigDecimal(WxPayBaseResult.feeToYuan(result.getTotalFee())));
            userIntegrals.setCreateTime(DateTime.now().toDate());
            userIntegrals.setCurrency(0);//0 代表人民币
            userIntegralsMapper.insert(userIntegrals);

            UserIntegralConsume userIntegralConsume=new UserIntegralConsume();
            userIntegralConsume.setUserId(usersPay.getUserId());
            userIntegralConsume.setAppId(Integer.valueOf(result.getAttach().split("\\|")[2]));
            userIntegralConsume.setSceneKey(result.getAttach().split("\\|")[0]);
//			Integer jb1=qj.intValue();
            //if(jb>=100){
            userIntegralConsume.setCostNum(new BigDecimal(WxPayBaseResult.feeToYuan(result.getTotalFee())));
            userIntegralConsume.setCreateTime(DateTime.now().toDate());
            userIntegralConsume.setCurrency(0);//0 代表人民币
            userIntegralConsumeMapper.insert(userIntegralConsume);

        } else if (result.getAttach().split("\\|")[1].equals("SERVICES")){

            UserToken queryToken = new UserToken();
            queryToken.setUserId(usersPay.getUserId());

            UserToken users = userTokenMapper.selectOne(queryToken);

            Map<String,Object> parms = new HashMap<>();
            parms.put("token",users.getToken());
            parms.put("sceneKey",result.getAttach().split("\\|")[0]);
            activityApprovalLogService.createActicityApprovalLog(parms);

            //支付完成后创建支付结果信息，zyy20171218
            Map<String,Object> parms1 = new HashMap<>();
            parms1.put("token",users.getToken());
            parms1.put("sceneKey",result.getAttach().split("\\|")[0]);
            parms1.put("actionType",3);
            logInfoService.saveElegantServiceLog(parms1);
        }else {
            userIntegralsService.payAfter(usersPay.getUserId(), usersPay.getSceneKey(), new BigDecimal(WxPayBaseResult.feeToYuan(result.getTotalFee())), 1, Integer.valueOf(result.getAttach().split("\\|")[2]));
        }
        LOGGER.info("Ending notify handler........");
    }

    private String generateTradeNo(Integer id) {
        String idStr =  String.valueOf(id);

        Integer length = 20;
        String zero = "0000000000000000000000000000000";
        StringBuilder stringBuilder = new StringBuilder("Idatavc");
        stringBuilder.append("_").append(zero.substring(0,length-idStr.length())).append(idStr);
        return MD5Util.md5Encode(stringBuilder.toString(),null).substring(0,10)+DateTime.now().getMillis();
    }

//    public static void main(String[] args) {
//        String idStr =  String.valueOf(12);
//
//        Integer length = 20;
//        String zero = "0000000000000000000000000000000";
//        StringBuilder stringBuilder = new StringBuilder("Idatavc");
//        stringBuilder.append("_").append(zero.substring(0,length-idStr.length())).append(idStr);
//        System.out.println(MD5Util.md5Encode(stringBuilder.toString(),null).substring(0,10)+DateTime.now().getMillis());
//    }
}
