package com.lhjl.tzzs.proxy.service.common;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CommonImageService {

    @Autowired
    private WxMaService qrcodeService;

    public CommonDto<String>  qrcode(){

        InputStream in = null;
        byte[] data = null;
        try {
            File qrcode = qrcodeService.getQrcodeService().createWxCode("",255);

            in = new FileInputStream(qrcode);
            data = new byte[in.available()];
            in.read(data);
            in.close();

        } catch (WxErrorException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonDto<>(new String(Base64.encodeBase64(data)),"", 200);
    }
}
