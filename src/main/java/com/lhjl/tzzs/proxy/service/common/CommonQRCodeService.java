package com.lhjl.tzzs.proxy.service.common;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

@Component
public class CommonQRCodeService extends GenericService {

    @Autowired
    private WxMaService qrcodeService;

    public CommonDto<String> createWXaQRCode(String path, Integer width){

        CommonDto<String> result = new CommonDto<>();

        try {
            File qrcode = qrcodeService.getQrcodeService().createWxCode(path,width);
            byte imageData[] = new byte[(int) qrcode.length()];
            FileInputStream imageInFile = new FileInputStream(qrcode);
            imageInFile.read(imageData);
            result.setData( Base64.getEncoder().encodeToString(imageData));
            result.setMessage("success");
            result.setStatus(200);
        } catch (WxErrorException e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        } catch (FileNotFoundException e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        } catch (IOException e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }
}
