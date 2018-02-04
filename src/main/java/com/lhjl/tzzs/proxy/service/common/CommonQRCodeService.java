package com.lhjl.tzzs.proxy.service.common;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import me.chanjar.weixin.common.exception.WxErrorException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Base64;

@Component
public class CommonQRCodeService extends GenericService {

    @Autowired
    private WxMaService qrcodeService;

    public InputStream createWXaQRCode(String path, Integer width){

        CommonDto<String> result = new CommonDto<>();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            File qrcode = qrcodeService.getQrcodeService().createWxCode(path,width);
            Thumbnails.of(qrcode).size(width,width).outputFormat("png").toOutputStream(os);
//            return FileUtils.openInputStream(qrcode);
//            byte imageData[] = new byte[(int) qrcode.length()];
//            FileInputStream imageInFile = new FileInputStream(qrcode);
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write(imageInFile,"png", os);
//            imageInFile.read(imageData);
//            result.setData( Base64.getEncoder().encodeToString(imageData));
//            result.setMessage("success");
//            result.setStatus(200);
        } catch (WxErrorException e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        } catch (IOException e) {
            this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
        }
        return new ByteArrayInputStream(os.toByteArray());
    }
}
