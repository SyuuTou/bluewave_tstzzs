package com.lhjl.tzzs.proxy.controller.common;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.flow.FlowModel;
import com.lhjl.tzzs.proxy.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HeartController extends GenericController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/heart")
    public String heart(){
        return "ok";
    }

    @GetMapping("/test")
    public String test() {

        String url = "https://tws.idatavc.com/event/message/%s";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity<>( headers);
        url = String.format(url,"51cdc44f66049d4e14cd1ce2db4feca0");

        ResponseEntity<CommonDto<FlowModel>> commonDto = restTemplate.exchange(url, HttpMethod.GET, entity,new ParameterizedTypeReference<CommonDto<FlowModel>>(){} );
        return JsonUtils.toJson(commonDto.getBody());
    }
}
