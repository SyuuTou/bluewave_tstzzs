package com.lhjl.tzzs.proxy.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lhjl.tzzs.proxy.dto.ChangePrincipalInputDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.DistributedCommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.investorDto.InvestorsOutputDto;
import com.lhjl.tzzs.proxy.mapper.DatasOperationManageMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandCharacterMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandSpeedwayMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandStageMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorInvestmentCaseMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.mapper.MetaFinancingMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.model.Users;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Service(value = "investorServiceImpl")
public class InvestorServiceImpl implements InvestorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestorServiceImpl.class);

    @Autowired
    private InvestorsMapper investorsMapper;
    @Autowired
    private InvestorDemandMapper investorDemandMapper;
    @Autowired
    private InvestorDemandCharacterMapper investorDemandCharacterMapper;
    @Autowired
    private InvestorDemandSegmentationMapper investorDemandSegmentationMapper;
    @Autowired
    private InvestorDemandSpeedwayMapper investorDemandSpeedwayMapper;
    @Autowired
    private InvestorDemandStageMapper investorDemandStageMapper;
    @Autowired
    private InvestorInvestmentCaseMapper investorInvestmentCaseMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private DatasOperationManageMapper datasOperationManageMapper;
    
    
    @Value("${pageNum}")
    private Integer pageNumDefault ;

    @Value("${pageSize}")
    private Integer pageSizeDefault;
    
    @Transactional(readOnly = true)
	@Override
	public CommonDto<Map<String,Object>> listInvestorsInfos(Integer appid, InvestorListInputDto body) {
		CommonDto<Map<String,Object>> result =new CommonDto<>();
		Map<String,Object> map=new HashMap<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        
        try{
        	if(body.getStartTimeStr() !=null) {
            	body.setStartTime(sdf.parse(body.getStartTimeStr()));
            }
            if(body.getEndTimeStr() !=null) {
            	body.setEndTime(sdf.parse(body.getEndTimeStr()));
            }
        }catch(Exception e){
        	result.setData(null);
            result.setStatus(200);
            result.setMessage("日期字符串输入格式不正确");
    		return result;  
        }
        System.err.println(body);
        List<InvestorsOutputDto> list = investorsMapper.listInvestorsInfos(body);
        //进行时间字符串的转换
        list.forEach((e)->{  
        	if(e.getUpdateTime() !=null) {
        		e.setUpdateTimeStr(sdf.format(e.getUpdateTime()));
        	}
        	if(e.getCreateTime() !=null) {
        		e.setCreateTimeStr(sdf.format(e.getCreateTime()));
        	}
        });
        Long total = investorsMapper.getInvestorsListCount(body);
        
        //规范数据封装格式，便于前台接受数据
        map.put("list", list);
        map.put("total", total);
        map.put("currentPage", body.getCurrentPage());
        map.put("pageSize", body.getPageSize());
        
        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<List<Users>> matchUsers(Integer appid, String keyword) {
		CommonDto<List<Users>> result =new CommonDto<>();
		
		List<Users> matchUserList = usersMapper.matchUsersExcepteBlackList(keyword);
		
		result.setData(matchUserList);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<Boolean> changeIrPrincipalBatch(Integer appid, ChangePrincipalInputDto body) {
		CommonDto<Boolean> result =new CommonDto<>();
		DatasOperationManage dom=new DatasOperationManage();
		dom.setUpdateTime(new Date());
		//删除所有选中投资人的记录信息
		if(body.getInvestorIds() !=null && body.getInvestorIds().size()!=0) {
			body.getInvestorIds().forEach((e)->{
				dom.setDataId(e);
				dom.setDataType("投资人");  
				dom.setIrPrincipal(body.getIrPrincipal());
				if(datasOperationManageMapper.findInvestor(dom) ==null) {//不存在相关的投资人，执行插入设置
					//新增插入时间
					dom.setCreateTime(new Date());
					datasOperationManageMapper.addInvestorIrPrincipal(dom);  
				}else {//执行相关的更新操作
					datasOperationManageMapper.changeInvestorIrPrincipal(dom);
				}
				
			});
		}
			
		result.setData(true);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

    
}
