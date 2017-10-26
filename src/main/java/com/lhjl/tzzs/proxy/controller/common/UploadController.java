package com.lhjl.tzzs.proxy.controller.common;

import com.lhjl.tzzs.proxy.utils.AliOssUtils;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(
            @RequestParam("file") MultipartFile file) {

        int randomnum = (int)Math.random()*10000;
        int randomnum1 = (int)Math.random()*100;

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
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        return path;
    }

}
