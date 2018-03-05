package com.lhjl.tzzs.proxy.controller.data;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ElegantServiceService;
import com.lhjl.tzzs.proxy.service.ImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by caochuangui on 2018/2/11.
 */
@RestController
public class dataController extends GenericController{

    @Resource
    private ImportDataService importDataService;

    @GetMapping("/importdata")
    public CommonDto<String> importData(){
        CommonDto<String> result = new CommonDto<>();

        try {
            result = importDataService.importData();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }

}
