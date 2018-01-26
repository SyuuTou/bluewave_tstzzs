package com.lhjl.tzzs.proxy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.DistributedCommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.mapper.MetaFinancingMapper;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.InvestorService;
import com.lhjl.tzzs.proxy.utils.ComparatorHistogramList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@Service(value = "investorServiceImpl")
public class InvestorServiceImpl implements InvestorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestorServiceImpl.class);

//    @Autowired
//    private MetaFinancingMapper financingMapper;

    @Value("${pageNum}")
    private Integer pageNumDefault ;

    @Value("${pageSize}")
    private Integer pageSizeDefault;

	@Override
	public CommonDto<Object> listInvestorsInfos(Integer appid, InvestorListInputDto body) {
		CommonDto<Object> result =new CommonDto<>();
		//验证、格式化参数信息
        //默认设置为10条记录
        if (body.getPageSize() == null){
            body.setPageSize(pageSizeDefault);
        }
        //默认设置为第一页
        if (body.getCurrentPage() == null){
            body.setCurrentPage(pageNumDefault);
        }
        //设置开始索引
        body.setStart((long) ((body.getCurrentPage()-1) * body.getPageSize())) ;
		return result;
	}

    
}
