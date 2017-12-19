package com.lhjl.tzzs.proxy.controller;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import com.lhjl.tzzs.proxy.dto.SaveInformationDto;
import com.lhjl.tzzs.proxy.service.InvesmentInformationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lmy on 2017/11/22.
 */
@RestController
public class InvesmentInformationController {
        private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentDataController.class);

        @Resource
        private InvesmentInformationService invesmentInformationService;

        @Value("${pageNum}")
        private String defaultPageNum;

        @Value("${pageSize}")
        private String defaultPageSize;


        /**
         * 查询50机构（分页）
         * @return
         */
        @PostMapping("find/investment/five/new")
        public CommonDto<List<InvestmentInstitutionsDto>> findFiveInvestmentNew(@RequestBody SaveInformationDto body){
            CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
            Integer pageNum = body.getPageNum();
            Integer pageSize = body.getPageSize();
            String token =body.getToken();
            try {
                //初始化分页信息
                if("".equals(token)){
                    result.setStatus(5002);
                    result.setMessage("token不存在");
                }else {
                    if (pageNum == null) {
                        pageNum = Integer.parseInt(defaultPageNum);
                    }
                    if (pageSize == null) {
                        pageSize = Integer.parseInt(defaultPageSize);
                    }
                    result = invesmentInformationService.findFiveInvestmentNew(pageNum, pageSize,token);
                }
            }catch (Exception e){
                result.setMessage(e.getMessage());
                result.setStatus(501);
                log.error(e.getMessage(),e.fillInStackTrace());
            }
            return result;
        }

        /**
         * 查询非50机构（分页）
         * @return
         */
        @PostMapping("find/investment/notfive/new")
        public CommonDto<List<InvestmentInstitutionsDto>> findNotFiveInvestmentNew(@RequestBody SaveInformationDto  body){
            CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
            Integer pageNum = body.getPageNum();
            Integer pageSize = body.getPageSize();
            String token =body.getToken();
            try {
                if("".equals(token)){
                    result.setStatus(5002);
                    result.setMessage("token不存在");
                }else {
                    if (pageNum == null) {
                        pageNum = Integer.parseInt(defaultPageNum);
                    }
                    if (pageSize == null) {
                        pageSize = Integer.parseInt(defaultPageSize);
                    }
                    result = invesmentInformationService.findNotFiveInvestmentNew(pageNum, pageSize,token);
                }
            }catch (Exception e){
                result.setMessage(e.getMessage());
                result.setStatus(501);
                log.error(e.getMessage(),e.fillInStackTrace());
            }
            return result;
        }

    /**
     * 查询 已投递/推荐的机构 创始人
     * @return
     */
    @PostMapping("find/recommend/creater")
    public CommonDto<List<InvestmentInstitutionsDto>> findRecommendCreater(@RequestBody SaveInformationDto body){
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
        String token =body.getToken();
        try {
            //初始化分页信息
            if("".equals(token)){
                result.setStatus(5002);
                result.setMessage("token不存在");
            }else {
                result = invesmentInformationService.findRecommendCreater(token);
            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }
    /**
     * 查询 已投递/推荐的机构 投资人
     * @return
     */
    @PostMapping("find/recommend/investor")
    public CommonDto<List<InvestmentInstitutionsDto>> findRecommendInvestor(@RequestBody SaveInformationDto body){
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<List<InvestmentInstitutionsDto>>();
        String token =body.getToken();
        try {
            //初始化分页信息
            if("".equals(token)){
                result.setStatus(5002);
                result.setMessage("token不存在");
            }else {
                result = invesmentInformationService.findRecommendInvestor(token);
            }
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setStatus(501);
            log.error(e.getMessage(),e.fillInStackTrace());
        }
        return result;
    }

    /**
     * 获取最近活跃的机构
     * @param body
     * @return
     */
    @PostMapping("find/investment/active")
    public CommonDto<List<InvestmentInstitutionsDto>> findRecentlyActiveInvestment(@RequestBody SaveInformationDto body){
        CommonDto<List<InvestmentInstitutionsDto>> result = new CommonDto<>();

        try {
            result = invesmentInformationService.findRecentlyInstitution(body);
        }catch (Exception e){
            log.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务端发生错误");
            result.setStatus(502);
            result.setData(null);
        }

        return result;
    }
}