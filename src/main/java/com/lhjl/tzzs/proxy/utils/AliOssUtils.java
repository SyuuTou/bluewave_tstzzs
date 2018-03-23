package com.lhjl.tzzs.proxy.utils;

import com.aliyun.oss.OSSClient;
import com.lhjl.tzzs.proxy.service.GenericService;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String name = file.getOriginalFilename();
        String md5String =  MD5Util.md5Encode(name,"utf-8");

        String path = "upload/"+md5String+"_"+DateTime.now().millisOfDay().getAsString()+".jpg";
        try {
            if (!file.isEmpty()) {
                AliOssUtils.putObject(path, file.getInputStream());
            }
        }catch(Exception e){
            throw e;
        }
        return path;
    }

    public static void main(String[] args) {
        String testTxt = "8个文件aslkajs行";
//  注意[\u4E00-\u9FA5]里面的斜杠字符，千万不可省略，不区分大小写
//        Pattern pat = Pattern.compile("^(\\d+)[\u4E00-\u9FA5]");
        Pattern pat = Pattern.compile("([\u4e00-\u9fa5]+)");
        Matcher mat = pat.matcher(testTxt);
//        if(mat.matches()) {
//            System.out.println(mat.group(0));
//        }
        String value = null;
        while (mat.find()) {
            value = mat.group(0);
            System.out.println(value);
        }
    }
}
