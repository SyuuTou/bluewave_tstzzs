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


public final class PdfToImageToUpload {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        try {

//            String filename = args[0];
//            String savefile = args[1];

            PDDocument doc = PDDocument.load(new File("/Users/zhhu/Downloads/美国天使投资数据与方法.pdf"));
            PDFRenderer renderer = new PDFRenderer(doc);
            Integer iter = doc.getNumberOfPages();
            String imagePath = "";

            for (int i = 0; i< iter;i++){
                BufferedImage image=renderer.renderImageWithDPI(i,224);
//                BufferedImage image = page.getContents(); //默认白色背景
                imagePath = "upload/bp/"+MD5Util.md5Encode(image.toString(),null)+".jpg";
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg",os);
                AliOssUtils.putObject(imagePath, new ByteArrayInputStream(os.toByteArray()));
            }
            doc.close();

//            // load PDF document
//            PDFDocument document = new PDFDocument();
//            document.load(new File("/Users/zhhu/Downloads/美国天使投资数据与方法.pdf"));
//
//            // create renderer
//            SimpleRenderer renderer = new SimpleRenderer();
//
//            // set resolution (in DPI)
//            renderer.setResolution(300);
//
//            // render
//            List<Image> images = renderer.render(document);
//
//            // write images to files to disk as PNG
//            String imagePath = "";
//            List<String> urls = new ArrayList<>();
//            try {
//                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                for (int i = 0; i < images.size(); i++) {
////                    ImageIO.write((RenderedImage) images.get(i), "png", new File((i + 1) + ".png"));
//                    ImageIO.write((RenderedImage) images.get(i), "png", os);
//                    imagePath = "upload/test/"+MD5Util.md5Encode(images.get(i).toString(),null)+"_"+ DateTime.now().toString()+".png";
//                    urls.add(imagePath);
//                    AliOssUtils.putObject(imagePath, new ByteArrayInputStream(os.toByteArray()));
//                }
//                urls.forEach(i -> {
//                    System.out.println(i);
//                });
//            } catch (IOException e) {
//                System.out.println("ERROR: " + e.getMessage());
//            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
