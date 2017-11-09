package com.lhjl.tzzs.proxy.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.Map;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class JsonUtils {
    private static final ObjectMapper JSON = new ObjectMapper();

    static {
        JSON.setSerializationInclusion(Include.NON_NULL);
        JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
    }

    public static String toJson(Object obj) {
        try {
            return JSON.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T fromJsonToObject(String json, TypeReference<Map<String, Object>> clas){
        try {
            return JSON.readValue(json,clas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        String json = "{\"checkAgencyStatus\":true,\"agency\":[{\"name\":\"种子轮\",\"value\":\"种子轮\",\"checked\":false},{\"name\":\"天使轮\",\"value\":\"天使轮\",\"checked\":true},{\"name\":\"Pre-A轮\",\"value\":\"Pre-A轮\",\"checked\":false},{\"name\":\"A轮\",\"value\":\"A轮\",\"checked\":false}],\"rzMoney\":\"500\",\"rzCurrency\":[{\"name\":\"人民币\",\"value\":0,\"checked\":true},{\"name\":\"美元\",\"value\":1,\"checked\":false}],\"gzMoney\":\"5000\",\"gzCurrency\":[{\"name\":\"人民币\",\"value\":0,\"checked\":true},{\"name\":\"美元\",\"value\":1,\"checked\":false}],\"touzilist\":[{\"tzname\":\"机构1\",\"gfzhanbi\":\"10\",\"jiahao\":true,\"jianhao\":false},{\"tzname\":\"机构2\",\"gfzhanbi\":\"5\",\"jiahao\":false,\"jianhao\":true},{\"tzname\":\"机构3\",\"gfzhanbi\":\"5\",\"jiahao\":false,\"jianhao\":true}],\"touzilistCheckStatus\":false,\"financingDate\":\"2017-05-06\"}";

        Map<String ,Object> result =JsonUtils.fromJsonToObject(json, new TypeReference<Map<String, Object>>(){});
        System.out.println("sssss");
    }
}