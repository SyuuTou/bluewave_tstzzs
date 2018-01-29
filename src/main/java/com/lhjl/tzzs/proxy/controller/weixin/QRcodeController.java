package com.lhjl.tzzs.proxy.controller.weixin;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ImageHandlerDto;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class QRcodeController extends GenericController {

    @Autowired
    private InvestmentInstitutionsService investmentInstitutionsService;

    @PostMapping("jg/qrcode/generate")
    public void imageHandler(@RequestBody ImageHandlerDto reqDto,HttpServletResponse response) throws IOException {

        InputStream in = null;
        try {
            in = investmentInstitutionsService.imageHandler(reqDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return IOUtils.toByteArray(in);
//        return ResponseEntity.ok()
//                .contentLength(in.read())
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(new InputStreamResource(in));

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());

    }

    public CommonDto<String> generateBase64(){



        return null;
    }
}
