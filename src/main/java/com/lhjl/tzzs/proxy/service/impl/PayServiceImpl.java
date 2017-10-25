package com.lhjl.tzzs.proxy.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayBaseResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.PayRequestBody;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.PayService;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

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
    private WxPayService payService;

    @Autowired
    private UserIntegralsService userIntegralsService;

    @Value("notifyURL")
    private String notifyURL;

    @Override
    public CommonDto<Map<String, String>> generatePayInfo(PayRequestBody payRequestBody) {

        CommonDto<Map<String, String>> result = new CommonDto<>();
        UserMoneyRecord userMoneyRecord = userMoneyRecordMapper.selectByPrimaryKey(payRequestBody.getMoneyId());

        Users query = new Users();
        query.setUuid(payRequestBody.getUuid());

        Users users = usersMapper.selectOne(query);

        UsersWeixin queryWX = new UsersWeixin();
        queryWX.setUserId(users.getId());

        UsersWeixin usersWeixin = usersWeixinMapper.selectOne(queryWX);

        MetaScene queryMS = new MetaScene();
        queryMS.setKey(payRequestBody.getSceneKey());

        MetaScene scene = sceneMapper.selectOne(queryMS);



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
            WxPayUnifiedOrderRequest prepayInfo = WxPayUnifiedOrderRequest.newBuilder()
                    .openid(usersWeixin.getOpenid())
                    .outTradeNo(tradeNo)
                    .totalFee(WxPayBaseRequest.yuanToFee(userMoneyRecord.getMoney().toString()))
                    .body(scene.getDesc())
                    .spbillCreateIp(payRequestBody.getLocalIp())
                    .build();
            Map<String, String> payInfo =payService.getPayInfo(prepayInfo);


            result.setData(payInfo);
        } catch (WxPayException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void payNotifyHandler(WxPayOrderNotifyResult result) {


        UsersPay query = new UsersPay();
        query.setTradeNo(result.getOutTradeNo());

        UsersPay usersPay = usersPayMapper.selectOne(query);
        usersPay.setWxOrderNum(result.getTransactionId());
        usersPay.setPayStatus(1);
        usersPay.setReceived(new BigDecimal(WxPayBaseResult.feeToYuan(result.getTotalFee())));

        userIntegralsService.payAfter(usersPay.getUserId(),usersPay.getSceneKey(),new BigDecimal(WxPayBaseResult.feeToYuan(result.getTotalFee())),1);
    }

    private String generateTradeNo(Integer id) {
        String idStr =  String.valueOf(id);

        Integer length = 20;
        String zero = "0000000000000000000000000000000";
        StringBuilder stringBuilder = new StringBuilder("Idatavc");
        stringBuilder.append("_").append(zero.substring(0,length-idStr.length())).append(idStr);
        return stringBuilder.toString();
    }
}
