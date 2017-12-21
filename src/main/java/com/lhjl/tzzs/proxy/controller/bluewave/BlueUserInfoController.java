package com.lhjl.tzzs.proxy.controller.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.UserHeadPicOutputDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.BlueUserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class BlueUserInfoController extends GenericService{

    @Resource
    private BlueUserInfoService blueUserInfoService;

    @GetMapping("/v{appid}/headpic")
    public CommonDto<UserHeadPicOutputDto> getUserHeadpic( String token,@PathVariable Integer appid){
        CommonDto<UserHeadPicOutputDto> result = new CommonDto<>();

        try {
            result = blueUserInfoService.getUserHeadpic(appid, token);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setStatus(502);
            result.setMessage("服务器端发生错误");
        }

        return result;
    }

    @PostMapping("/v{appid}/update/headpic")
    public CommonDto<String> updateUserHeadpic(@PathVariable Integer appid, @RequestBody Map<String,Object> body){
        CommonDto<String> result = new CommonDto<>();
        try {
            result = blueUserInfoService.updateUserHeadpic(appid, body);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }
}
