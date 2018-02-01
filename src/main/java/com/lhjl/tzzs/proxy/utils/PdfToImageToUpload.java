package com.lhjl.tzzs.proxy.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public final class PdfToImageToUpload {

    public static List<String> convert(File file) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        try {


            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            Integer iter = doc.getNumberOfPages();
            String imagePath = "";
            List<String> imageList = new ArrayList<>();
            for (int i = 0; i< iter;i++){
                BufferedImage image=renderer.renderImageWithDPI(i,224);
//                BufferedImage image = page.getContents(); //默认白色背景
                imagePath = "http://img.idatavc.com/upload/bp/"+MD5Util.md5Encode(image.toString(),null)+".jpg";
                imageList.add(imagePath);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg",os);
                AliOssUtils.putObject(imagePath, new ByteArrayInputStream(os.toByteArray()));
            }
            doc.close();

            return imageList;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;

    }
}
