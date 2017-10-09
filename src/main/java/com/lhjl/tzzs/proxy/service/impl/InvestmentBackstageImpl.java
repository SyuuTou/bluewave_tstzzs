package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.service.InvestmentBackstageService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.*;

@Service
public class InvestmentBackstageImpl implements InvestmentBackstageService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentBackstageImpl.class);

    @Resource
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private Environment environment;

    @Transactional
    @Override
    public CommonDto<String> adminAddInvestmentBackstage(InvestmentInstitutions body){
        CommonDto<String> result =new CommonDto<String>();

        String short_name = body.getShortName();
        String commet = body.getCommet();
        log.info("short_name={}",short_name);
        log.info("commet={}",commet);


        if (StringUtil.isEmpty(short_name)){
            result.setStatus(50001);
            result.setMessage("请输入机构简称");

            return result;
        }
        if (StringUtil.isEmpty(commet)){
            result.setStatus(50001);
            result.setMessage("请输入机构备注");

            return result;
        }

        int type = -1;
        if (body.getType() != null){
            type =body.getType();
            log.info("type={}",type);
        }else {
            result.setMessage("请选择机构类型");
            result.setStatus(50002);
            return result;
        }

        Date now =new Date();

        InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
        investmentInstitutions.setShortName(short_name);
        investmentInstitutions.setCreateTime(now);
        investmentInstitutions.setCommet(commet);
        investmentInstitutions.setType(type);

        investmentInstitutionsMapper.insert(investmentInstitutions);

        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 获取机构信息（50与非50）
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>>  findAllInvestment() {
        CommonDto<List<Map<String, Object>>> list = new CommonDto<List<Map<String, Object>>>();
        List<InvestmentInstitutions> listForfive = new ArrayList<InvestmentInstitutions>();
        List<InvestmentInstitutions> listNotfive = new ArrayList<InvestmentInstitutions>();

        //获取非50机构（所有）
        listNotfive = investmentInstitutionsMapper.selectAll();
        //获取50机构
        for(InvestmentInstitutions investmentInstitutions : listNotfive){
            if(investmentInstitutions.getType() == 1){
                listForfive.add(investmentInstitutions);
            }
        }

        //组装所有数据
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xiangmubiaoa", listForfive);
        map.put("xiangmubiaob", listNotfive);
        lists.add(map);

        //返回结果数据组装
        list.setStatus(200);
        list.setMessage("success");
        list.setData(lists);

        return list;
    }

    /**
     * 获取50机构信息（分页）
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findFiveInvestment(Integer pageNum, Integer pageSize) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        //初始化分页信息
        if(pageNum == null){
            pageNum = Integer.parseInt(environment.getProperty("pageNum"));
        }
        if(pageSize == null){
            pageSize = Integer.parseInt(environment.getProperty("pageSize"));
        }
        //计算查询起始记录
        Integer beginNum = (pageNum-1)*pageSize;

        List<Map<String, Object>> list = investmentInstitutionsMapper.findFiveInvestment(beginNum, pageSize);
        //判断是否还有查询结果
        if(list.size() <= 0){
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 获取非50机构信息（分页）
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findNotFiveInvestment(Integer pageNum, Integer pageSize) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        //初始化分页信息
        if(pageNum == null){
            pageNum = Integer.parseInt(environment.getProperty("pageNum"));
        }
        if(pageSize == null){
            pageSize = Integer.parseInt(environment.getProperty("pageSize"));
        }
        //计算查询起始记录
        Integer beginNum = (pageNum-1)*pageSize;

        List<Map<String, Object>> list = investmentInstitutionsMapper.findNotFiveInvestment(beginNum, pageSize);
        //判断是否还有查询结果
        if(list.size() <= 0){
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }
}
