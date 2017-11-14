package com.lhjl.tzzs.proxy.service.common;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.util.StringUtil;

@Component
public class SessionKeyService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SessionKeyService.class);

    @Autowired
    private JedisCommonService jedisCommonService;

    //缓存sessionkey
    public boolean setSessionKey(String sessionKey,String cacheKeyId){
        boolean result = false;
        if (StringUtil.isEmpty(sessionKey)){
            result = false;
        }
        Jedis jedis = null;
        try {
            jedis = jedisCommonService.getJedis();
            jedis.set(cacheKeyId,sessionKey);
            result = true;
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }

        return result;
    }

    //获取sessionkey
    public String getSessionKey(String cacheKeyId){
        String result = "";
        if (StringUtil.isEmpty(cacheKeyId)){
            result="";
        }

        Jedis jedis = null;
        try {
            jedis = jedisCommonService.getJedis();
            String sessionKey = jedis.get(cacheKeyId);
            result = sessionKey;
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }

        return result;
    }
}
