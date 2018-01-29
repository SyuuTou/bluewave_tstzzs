package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/25.
 */
@RestController
public class MetaController extends GenericController{

    @Autowired
    private MetaService metaService;

    /**
     * 得到所有投资类型
     * @return
     */
    @GetMapping("/getsegmentations")
    public CommonDto<List<MetaSegmentation>> getSegmentations( ){
        CommonDto<List<MetaSegmentation>> result = new CommonDto<>();
        try {
            result = metaService.getSegmentations();
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setStatus(502);
            result.setData(null);
            result.setMessage("服务器端发生错误");
        }
        return result;
    }
}
