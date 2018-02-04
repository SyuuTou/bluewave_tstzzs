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
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
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
             BufferedImage qcode = ImageIO.read(qrcode);;
            //处理图片将其压缩成正方形的小图
            BufferedImage  convertImage= scaleByPercentage(qcode, width,width);
            //裁剪成圆形 （传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的）
            convertImage = convertCircular(convertImage);
            //生成的图片位置
            ImageIO.write(convertImage, "png", os);
//            Thumbnails.of(qrcode).size(width,width).sourceRegion().outputFormat("png").toOutputStream(os);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(os.toByteArray());
    }

    /**
     * 缩小Image，此方法返回源图像按给定宽度、高度限制下缩放后的图像
     * @param inputImage
     * @param maxWidth：压缩后宽度
     * @param maxHeight：压缩后高度
     * @throws java.io.IOException
     * return
     */
    public static BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight) throws Exception {
        //获取原始图像透明度类型
        int type = inputImage.getColorModel().getTransparency();
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        //开启抗锯齿
        RenderingHints renderingHints=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //使用高质量压缩
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        BufferedImage img = new BufferedImage(newWidth, newHeight, type);
        Graphics2D graphics2d =img.createGraphics();
        graphics2d.setRenderingHints(renderingHints);
        graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
        graphics2d.dispose();
        return img;
    }

    /**
     * 传入的图像必须是正方形的 才会 圆形  如果是长方形的比例则会变成椭圆的
     * @param url 用户头像地址
     * @param imageFile
     * @return
     * @throws IOException
     */
    public static BufferedImage convertCircular(BufferedImage imageFile) throws IOException{
//        BufferedImage bi1 = ImageIO.read(imageFile);
        //这种是黑色底的
//        BufferedImage bi2 = new BufferedImage(bi1.getWidth(),bi1.getHeight(),BufferedImage.TYPE_INT_RGB);

        //透明底的图片
        BufferedImage bi2 = new BufferedImage(imageFile.getWidth(),imageFile.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        Ellipse2D.Double shape = new Ellipse2D.Double(0,0,imageFile.getWidth()+20,imageFile.getHeight()+20);
        Graphics2D g2 = bi2.createGraphics();
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(imageFile,0,0,null);
        //设置颜色
        g2.setBackground(Color.green);
        g2.dispose();
        return bi2;
    }
}
