package com.lhjl.tzzs.proxy.service.common;


import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.utils.SmsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class SmsCommonService {

    private static final Logger log = LoggerFactory.getLogger(SmsCommonService.class);

    @Autowired
    private JedisCommonService jedisCommonService;

    public CommonDto<String> sendSMS(String userId, String phoneNum) {

        CommonDto<String> result = new CommonDto<String>();
        if (StringUtils.isEmpty(phoneNum)) {
            result.setMessage("手机号不能为空。");
            result.setStatus(50001);
            return result;
        }

        Jedis jedis = jedisCommonService.getJedis();
        StringBuffer cacheKey = new StringBuffer("SMS:");
        cacheKey.append(userId).append(":").append(phoneNum);


        try {


            String code = "";
            if (jedis.exists(cacheKey.toString())) {
                code = jedis.get(cacheKey.toString());
            } else {
                code = generateRandomArray(6);
                jedis.set(cacheKey.toString(), code);
                jedis.expire(cacheKey.toString(), 300);
            }
            SendSmsResponse response = SmsUtils.sendSms(phoneNum, code);
            if (response.getCode() != null && response.getCode().equals("OK")) {
                result.setStatus(200);
                result.setMessage("success");
                result.setData(code);
            }else{
                result.setStatus(50003);
                result.setMessage("请求过于频繁，请60s后再试。");
                result.setData(code);
            }
        } catch (ClientException e) {
            log.error(e.getErrMsg(), e.getErrCode(), e.getMessage());

            result.setMessage(e.getErrMsg());
            result.setStatus(500);
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }

        return result;
    }


    public CommonDto<String> verifySMS(String userId, String phoneNum, String code) {

        CommonDto<String> result = new CommonDto<String>();
        if (StringUtils.isEmpty(phoneNum)) {
            result.setMessage("手机号不能为空。");
            result.setStatus(50001);
            return result;
        }

        Jedis jedis = null;
        try {
            jedis = jedisCommonService.getJedis();
            StringBuffer cacheKey = new StringBuffer("SMS:");
            cacheKey.append(userId).append(":").append(phoneNum);


            String codeLocal = jedis.get(cacheKey.toString());
            if (StringUtils.isEmpty(codeLocal) || !codeLocal.equals(code)) {
                result.setMessage("请重新获取验证码，验证码不正确。");
                result.setStatus(50002);
                return result;
            }
            result.setData("验证成功。");
            result.setMessage("success");
            result.setStatus(200);
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }

        return result;
    }


    /**
     * 随机生成 num位数字字符数组
     *
     * @param num
     * @return
     */
    public String generateRandomArray(int num) {
        String chars = "0123456789";
        char[] rands = new char[num];
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            rands[i] = chars.charAt(rand);
        }
        return String.valueOf(rands);
    }


}
