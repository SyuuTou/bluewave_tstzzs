package com.lhjl.tzzs.proxy.controller.common;

import com.lhjl.tzzs.proxy.utils.AliOssUtils;
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


        String name = file.getOriginalFilename();
        String path = "upload/"+name;
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
