package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogOutputDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadOutputDto;
import com.lhjl.tzzs.proxy.mapper.ProjectFinancingLogMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.ProjectFinancingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectFinancingLogServiceImpl implements ProjectFinancingLogService {

    @Value("${pageNum}")
    private Integer defalutPageNum;

    @Value("${pageSize}")
    private Integer defalutPageSize;

    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;
    @Autowired
    private ProjectsMapper projectsMapper;

    @Override
    public CommonDto<Map<String, Object>> getProjectFinancingLogList(ProjectFinancingLogInputDto body) {
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");

        Map<String,Object> map = new HashMap<>();
        List<ProjectFinancingLogOutputDto> list = new ArrayList<>();

        if (body.getSearchWord() == null){
            body.setSearchWord("");
        }

        if (body.getPageNum() == null){
            body.setPageNum(defalutPageNum);
        }

        if (body.getPageSize() == null){
            body.setPageSize(defalutPageSize);
        }
        //list转数组
        Integer[] dataSource = {};
        if(body.getDataSource() != null){
            Integer[] dataSourceInt =new  Integer[body.getDataSource().size()];
            for (int i = 0;i<body.getDataSource().size();i++){
                dataSourceInt[i] = body.getDataSource().get(i);
            }
            dataSource = dataSourceInt;
        }

        String[] stage = {};
        if (body.getStage() != null){
            String[] stageString = new String[body.getStage().size()];
            for (int i = 0; i < body.getStage().size();i++){
                stageString[i] = body.getStage().get(i);
            }
            stage = stageString;
        }

        Integer[] currency={};
        if (body.getCurrency() != null){
            Integer[] currencyInt = new Integer[body.getCurrency().size()];
            for (int i= 0;i<body.getCurrency().size();i++){
                currencyInt[i] = body.getCurrency().get(i);
            }
        }
        List<Map<String,Object>> projectFinancingLogList = projectFinancingLogMapper.getProjectFinancingLogList(body.getSearchWord(),body.getBegainTime(),
                body.getEndTime(),dataSource,stage,currency,body.getCreatTimeOrder(),body.getCreatTimeOrderDesc(),
                body.getUpdateTimeOrder(),body.getUpdateTimeOrderDesc(),body.getPageNum(),body.getPageSize());
        Integer totalcount = projectFinancingLogMapper.getProjectFinancingLogListCount(body.getSearchWord(),body.getBegainTime(),
                body.getEndTime(),dataSource,stage,currency,body.getCreatTimeOrder(),body.getCreatTimeOrderDesc(),
                body.getUpdateTimeOrder(),body.getUpdateTimeOrderDesc(),body.getPageNum(),body.getPageSize());

        //整理返回数据
        if (projectFinancingLogList.size() > 0){
            for (Map<String,Object> maps:projectFinancingLogList){
                ProjectFinancingLogOutputDto projectFinancingLogOutputDto = new ProjectFinancingLogOutputDto();
                
                if (maps.get("ID") != null){
                	projectFinancingLogOutputDto.setId((Integer) maps.get("ID"));
                }
                
                Integer serialNumber = 0;
                if (maps.get("serial_number") != null){
                    serialNumber = (Integer) maps.get("serial_number");
                }
                projectFinancingLogOutputDto.setSerialNumber(serialNumber);
                String typeName = "";
                if (maps.get("type_name") !=null){
                    typeName = (String)maps.get("type_name");
                }
                projectFinancingLogOutputDto.setTypeName(typeName);
                Integer investmentInstitutionId = 0;
                if (maps.get("investment_institution_id") != null){
                    investmentInstitutionId = (Integer) maps.get("investment_institution_id");
                }
                projectFinancingLogOutputDto.setInvestmentInstitutionId(investmentInstitutionId);
                String institutionName = "";
                if (maps.get("institution_name") !=null){
                    institutionName = (String)maps.get("institution_name");
                }
                projectFinancingLogOutputDto.setInstitutionName(institutionName);
                Integer projectId = 0;
                if (maps.get("project_id") != null){
                    projectId = (Integer) maps.get("project_id");
                }
                projectFinancingLogOutputDto.setProjectId(projectId);
                String projectName = "";
                if (maps.get("project_name") !=null){
                    projectName = (String)maps.get("project_name");
                }
                projectFinancingLogOutputDto.setProjectName(projectName);
                String segmentationName = "";
                if (maps.get("segmentation_name") !=null){
                    segmentationName = (String)maps.get("segmentation_name");
                }
                projectFinancingLogOutputDto.setSegmentationName(segmentationName);
                String stageS = "";
                if (maps.get("stage") !=null){
                    stageS = (String)maps.get("stage");
                }
                projectFinancingLogOutputDto.setStage(stageS);
                BigDecimal amount = BigDecimal.ZERO;
                if (maps.get("amount") !=null){
                    amount = (BigDecimal)maps.get("amount");
                }
                projectFinancingLogOutputDto.setAmount(amount);
                Integer currencys = 0;
                if (maps.get("currency") !=null){
                    currencys = (Integer)maps.get("currency");
                }
                projectFinancingLogOutputDto.setCurrency(currencys);
                String shareDivest = "";
                if (maps.get("share_divest") !=null){
                    shareDivest = (String) maps.get("share_divest");
                }
                projectFinancingLogOutputDto.setShareDivest(shareDivest);
                BigDecimal valuation = BigDecimal.ZERO;
                if (maps.get("valuation") !=null){
                    valuation = (BigDecimal)maps.get("valuation");
                }
                projectFinancingLogOutputDto.setValuation(valuation);
                BigDecimal totalAmount = BigDecimal.ZERO;
                if (maps.get("total_amount") !=null){
                    totalAmount = (BigDecimal)maps.get("total_amount");
                }
                projectFinancingLogOutputDto.setTotalAmount(totalAmount);
                BigDecimal prAmount = BigDecimal.ZERO;
                if (maps.get("pr_amount") !=null){
                    prAmount = (BigDecimal)maps.get("pr_amount");
                }
                projectFinancingLogOutputDto.setPrAmount(prAmount);
                String financingTime = "";
                if (maps.get("financing_time") !=null){
                    Date financingTimeD = (Date) maps.get("financing_time");
                    financingTime = sdf.format(financingTimeD);
                }
                projectFinancingLogOutputDto.setFinancingTime(financingTime);
                String investmentInstitutionsList = "";
                if (maps.get("Investment_institutions_list") !=null){
                    investmentInstitutionsList = (String)maps.get("Investment_institutions_list");
                }
                projectFinancingLogOutputDto.setInvestmentInstitutionsList(investmentInstitutionsList);
                String proportionList = "";
                if (maps.get("proportion_list") !=null){
                    proportionList = (String)maps.get("proportion_list");
                }
                projectFinancingLogOutputDto.setProportionList(proportionList);
                String createTime = "";
                if (maps.get("create_time") !=null){
                    Date createTimeD = (Date)maps.get("create_time");
                    createTime  = sdf.format(createTimeD);
                }
                projectFinancingLogOutputDto.setCreateTime(createTime);
                String updateTime = "";
                if (maps.get("update_time") !=null){
                    Date updateTimeD = (Date)maps.get("update_time");
                    updateTime  = sdf.format(updateTimeD);
                }
                projectFinancingLogOutputDto.setUpdateTime(updateTime);

                list.add(projectFinancingLogOutputDto);
            }
        }

        map.put("currentPage",body.getPageNum());
        map.put("pageSize",body.getPageSize());
        map.put("total",totalcount);

        map.put("list",list);

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

	@Override
	public CommonDto<ProjectFinancingLogHeadOutputDto> echoProjectFinancingLogHead(Integer appid,
			Integer projectFinancingLogId) {
		CommonDto<ProjectFinancingLogHeadOutputDto> result=new CommonDto<>();
		ProjectFinancingLogHeadOutputDto ProjectFinancingLogHead = projectFinancingLogMapper.echoProjectFinancingLogHead(projectFinancingLogId);
		result.setData(ProjectFinancingLogHead);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<List<Projects>> blurScanProjectByShortName(Integer appid, String keyword) {
		CommonDto<List<Projects>> result=new CommonDto<>();
		List<Projects> projects=new ArrayList<>();
		if(keyword != null && !(keyword.equals(""))) {
			projects = projectsMapper.blurScanProjectByShortName(keyword);
		}
		
		result.setData(projects==null || projects.size()==0 ? null : projects);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}
}
