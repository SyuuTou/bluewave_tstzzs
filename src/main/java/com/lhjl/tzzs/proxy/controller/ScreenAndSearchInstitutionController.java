package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import com.lhjl.tzzs.proxy.dto.SaveScreenDto;
import com.lhjl.tzzs.proxy.dto.ScreenDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.SearchInvestmentRecord;
import com.lhjl.tzzs.proxy.service.ScreenAndSearchInstitutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by lmy on 2017/11/20.
 */
@RestController
public class ScreenAndSearchInstitutionController {
    private static final Logger log = LoggerFactory.getLogger(ScreenAndSearchInstitutionController.class);


    @Autowired
    private ScreenAndSearchInstitutionService screenAndSearchInstitutionService;





    /**
     * 搜索机构列表
     *
     * @param body
     * @return
     */
    @PostMapping("search/institution/all")
    public CommonDto<List<InvestmentInstitutionsDto>> searchInstitution(@RequestBody ScreenDto body) {
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
        String shortName = body.getShortName();
        Integer pageNum = body.getPageNum();
        String token = body.getToken();
        if (null != body.getToken() || !"".equals(body.getToken())) {

        } else {
            result.setStatus(5101);
            result.setMessage("用户token为空");
        }
        Integer pageSize = body.getPageSize();

        try {
            //初始化分页信息
            if (pageNum == null) {
                pageNum = 1;
            }
            if (pageSize == null) {
                pageSize = 20;
            }

            result = screenAndSearchInstitutionService.searchInstitution(shortName, pageNum, pageSize, token);

            if (result.getStatus() == null) {
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("搜索异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 搜索机构的记录
     * @param body
     * @return
     */

    @PostMapping("search/institution/record")
    public CommonDto<List<SearchInvestmentRecord>> searchInstitutionRecord(@RequestBody ScreenDto body) {
        CommonDto<List<SearchInvestmentRecord>> result = new CommonDto<List<SearchInvestmentRecord>>();
        String token = body.getToken();
        if (null != body.getToken() || !"".equals(body.getToken())) {

        } else {
            result.setStatus(5101);
            result.setMessage("用户token为空");
        }
        try {
            result = screenAndSearchInstitutionService.searchInstitutionRecord(token);

            if (result.getStatus() == null) {
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("搜索异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 搜索热门的记录
     * @return
     */
    @PostMapping("search/institution/hot")
    public CommonDto<List<SearchInvestmentRecord>> searchInstitutionHot(){
        CommonDto<List<SearchInvestmentRecord>> result = new CommonDto<List<SearchInvestmentRecord>>();
        try {
            result = screenAndSearchInstitutionService.searchInstitutionHot();

            if (result.getStatus() == null) {
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("搜索异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 筛选页面
     * @return
     */
    @PostMapping("search/institution")
    public CommonDto<Map<String, Object>> screenInstitution(@RequestBody ScreenDto body){
    	CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        String token = body.getToken();
        if (null != body.getToken() || !"".equals(body.getToken())) {

        } else {
            result.setStatus(5101);
            result.setMessage("用户token为空");
        }
        
        try {
            result =screenAndSearchInstitutionService.screenInstitution(token);

            if (result.getStatus() == null) {
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("搜索异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 保存筛选记录
     * @param body
     * @return
     */
    @PostMapping("save/institution/record")
    public CommonDto<String>  savescreenInstitution(@RequestBody SaveScreenDto body){
        CommonDto<String> result =new CommonDto<String>();

        try {
            result =screenAndSearchInstitutionService.savescreenInstitution(body);

            if (result.getStatus() == null) {
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("服务器端异常");
            log.error(e.getMessage());
        }

        return result;
    }

    /**
     * 筛选结果
     * @param body
     * @return
     */
    @PostMapping("screen/institution/all")
    public CommonDto<List<InvestmentInstitutionsDto>> screenInstitutionAll(@RequestBody SaveScreenDto body) {
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();


        try {
            //初始化分页信息
            result = screenAndSearchInstitutionService.screnInstitutionAllNew(body);
            if (result.getStatus() == null) {
                result.setStatus(200);
                result.setMessage("success");
            }
        } catch (Exception e) {
            result.setStatus(5103);
            result.setMessage("服务器异常，请稍后再试");
            log.error(e.getMessage());
        }
        return result;
    }
}