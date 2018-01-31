package com.lhjl.tzzs.proxy.controller.common;

import com.lhjl.tzzs.proxy.utils.AliOssUtils;
import com.lhjl.tzzs.proxy.utils.GenericController;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController extends GenericController {


    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(
            @RequestParam("file") MultipartFile file) {


        try {
            return AliOssUtils.upload(file);
        } catch (IOException e) {
            this.logger.error(e.getMessage(),e.fillInStackTrace());
        }
        return "";
    }

}
