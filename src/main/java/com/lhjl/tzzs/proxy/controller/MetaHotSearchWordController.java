package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.MetaHotSearchWord;
import com.lhjl.tzzs.proxy.service.MetaHotSearchWordService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("hotsearchword/search/rhotword")
    public CommonDto<List<MetaHotSearchWord>> searchHotword(){
        CommonDto<List<MetaHotSearchWord>> result = new CommonDto<List<MetaHotSearchWord>>();

        try{
            result = metaHotSearchWordService.selectHotWord();

        }catch (Exception e){
            result.setMessage("数据读取失败");
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
            result.setMessage("更新失败！请检查id是否正确");
            result.setStatus(501);
            result.setData(null);
        }
        return result;

    }
}
