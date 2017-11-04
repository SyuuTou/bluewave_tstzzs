package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.DistributedCommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.mapper.ProjectFinancingLogMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.ProjectAmountService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectAmountImpl implements ProjectAmountService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAmountService.class);

    @Autowired
    ProjectsMapper projectsMapper;

    @Autowired
    ProjectFinancingLogMapper projectFinancingLogMapper;

    @Resource
    private EvaluateService evaluateService;



    @Override
    public CommonDto<String> projectAmoutAdd(){
        List<Map<String,Object>> chaxunjieguo = new ArrayList<Map<String, Object>>();
        chaxunjieguo = projectsMapper.findProjectAmountOfZ();

        if (chaxunjieguo.size() > 0) {
            for (Map<String,Object> map : chaxunjieguo) {

                String segmentation = String.valueOf(map.get("segmentation_name"));
                String stage = String.valueOf( map.get("stage"));
                String city = String.valueOf(map.get("city"));
                int id = Integer.valueOf(String.valueOf(map.get("ID")));

                BigDecimal avgamount = projectsMapper.findProjectByRoundAndE(segmentation, stage);

                if (avgamount == null){
                    avgamount = projectsMapper.findProjectByRoundAndC(stage,city);
                    if(avgamount == null){
                        avgamount = projectsMapper.findProjectByRoundAndC(segmentation,null);
                    }

                }

                ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
                projectFinancingLog.setAmount(avgamount);
                projectFinancingLog.setCalculationAmountStatus(1);
                projectFinancingLog.setId(id);

                projectFinancingLogMapper.updateByPrimaryKeySelective(projectFinancingLog);

            }

            log.info("执行完毕");
        }
        return null;
    }

    @Override
    public CommonDto<String> projetcAllAmountAdd(){

        try {
            List<Map<String,Object>> chaxunjieguo = new ArrayList<Map<String, Object>>();
            chaxunjieguo = projectsMapper.findProjectAllAmountOfZ();
            DistributedCommonDto<List<HistogramList>> result = null;
            BigDecimal amount = null;
            BigDecimal avgallamount = null;
            if (chaxunjieguo.size() > 0){
                for(Map<String,Object> mapall:chaxunjieguo){
                    String segmentation = String.valueOf(mapall.get("segmentation_name"));
                    String stage = String.valueOf( mapall.get("stage"));
                    if ("".equals(stage)){
                        continue;
                    }
                    String city = String.valueOf(mapall.get("city"));
                    amount = new BigDecimal(String.valueOf(mapall.get("amount")));
                    int id = Integer.valueOf(String.valueOf(mapall.get("ID")));



                        result = evaluateService.financingAmount("-1" ,stage,segmentation,null,null,null,0,10);

                        avgallamount = new BigDecimal(result.getAvgMoney());
                    if (avgallamount == null){
                        result = evaluateService.financingAmount("-1" ,stage,null,city,null,null,0,10);
                        avgallamount = new BigDecimal(result.getAvgMoney());
                        if (avgallamount == null){
                            result = evaluateService.financingAmount("-1" ,stage,null,null,null,null,0,10);
                            avgallamount = new BigDecimal(result.getAvgMoney());

                            if (null != avgallamount && avgallamount.compareTo(amount)== -1){
                                avgallamount = amount;
                            }
                        }else if (avgallamount.compareTo(amount)== -1){
                            avgallamount = amount;
                        }
                    }else if (avgallamount.compareTo(amount)== -1){
                        avgallamount = amount;
                    }

                    ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();

                    projectFinancingLog.setTotalAmount(avgallamount);
                    projectFinancingLog.setCalculationAmountStatus(1);
                    projectFinancingLog.setId(id);

                    projectFinancingLogMapper.updateByPrimaryKeySelective(projectFinancingLog);

                }
                log.info("执行完毕");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommonDto<String> valuaionRecommend() {



        return null;
    }
}
