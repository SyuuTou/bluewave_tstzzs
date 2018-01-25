package com.lhjl.tzzs.proxy.utils;

import com.aliyun.oss.OSSClient;

import java.io.InputStream;

public class AliOssUtils {

    public static void putObject(String path, InputStream inputStream) {
        String endpoint = "http://oss-cn-zhangjiakou.aliyuncs.com";
        String accessKeyId = "LTAI6atHAVMu5Mqm";
        String accessKeySecret = "qA1jla5Zbo6P2GBJBZAP7qFH58wpR4";

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try{
        ossClient.putObject("ss-lhjl",path,inputStream);
        } finally {
            ossClient.shutdown();
        }
    }
}
