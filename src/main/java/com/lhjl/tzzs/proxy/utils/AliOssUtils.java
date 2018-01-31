package com.lhjl.tzzs.proxy.utils;

import com.aliyun.oss.OSSClient;
import com.lhjl.tzzs.proxy.service.GenericService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AliOssUtils  {


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

    public static String upload(MultipartFile file) throws IOException {
        int randomnum = (int)(Math.random()*10000);
        int randomnum1 = (int)(Math.random()*9000);

        String randomnumString = String.valueOf(randomnum);
        String randomnumString1 = String.valueOf(randomnum1);

        String md5String =  MD5Util.md5Encode(randomnumString,"utf-8");

        String name = file.getOriginalFilename();
        String path = "upload/"+md5String+"_"+randomnumString1+"_"+name;
        try {
            if (!file.isEmpty()) {
                AliOssUtils.putObject(path, file.getInputStream());
            }
        }catch(Exception e){
            throw e;
        }
        return path;
    }
}
