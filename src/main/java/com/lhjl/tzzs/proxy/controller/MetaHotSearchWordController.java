package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaHotSearchWord;
import com.lhjl.tzzs.proxy.service.MetaHotSearchWordService;
import io.swagger.annotations.*;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MetaHotSearchWordController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MetaHotSearchWordController.class);

    @Resource
    private MetaHotSearchWordService metaHotSearchWordService;

    /**
     * 查询10条热门搜索，按热度排序
     * @return
     */

    @ApiOperation(value = "查询热门搜索接口", notes = "查询热门搜索信息，按热度排序")
    @RequestMapping(method = RequestMethod.GET, path="/hotsearchword/search/rhotword", produces = "application/json")


    @GetMapping("hotsearchword/search/rhotword")
    public CommonDto<List<MetaHotSearchWord>> searchHotword(){
        CommonDto<List<MetaHotSearchWord>> result = new CommonDto<List<MetaHotSearchWord>>();

        try{
            result = metaHotSearchWordService.selectHotWord();

        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            result.setData(null);
            log.error(e.getMessage(),e.fillInStackTrace());
        }

        return result;
    }

    /**
     * 更新热门搜索的热度
     * @param id
     * @return
     */
    @GetMapping("hotsearchword/search/uhotword/{id}")
    public CommonDto<String> updateHotWordAmount(@PathVariable Integer id){
        CommonDto<String> result = new CommonDto<String>();

        try {
            result = metaHotSearchWordService.updateHotWordAmount(id);
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            result.setData(null);
        }
        return result;

    }
}
