package com.lhjl.tzzs.proxy.controller.weixin;


import com.lhjl.tzzs.proxy.dto.ImageHandlerDto;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class QRcodeController extends GenericController {

    @Autowired
    private InvestmentInstitutionsService investmentInstitutionsService;

    @PostMapping("jg/qrcode/generate")
    public byte[] imageHandler(@RequestBody ImageHandlerDto reqDto) throws IOException {

        InputStream in = null;
        try {
            in = investmentInstitutionsService.imageHandler(reqDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IOUtils.toByteArray(in);
    }
}
