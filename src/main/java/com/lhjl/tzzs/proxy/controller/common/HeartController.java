package com.lhjl.tzzs.proxy.controller.common;

import com.lhjl.tzzs.proxy.controller.GenericController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartController extends GenericController {

    @GetMapping("/heart")
    public String heart(){
        return "ok";
    }
}
