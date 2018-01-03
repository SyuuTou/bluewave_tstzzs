package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendFinancingHistoryBDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendInvestorDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendSearchCommenDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendFinancingHistoryBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendInvestorBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendFinancingHistoryB;
import com.lhjl.tzzs.proxy.model.ProjectSendInvestorB;
import com.lhjl.tzzs.proxy.service.ProjectSendFinancingHistoryBService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectSendFinancingHistoryBImpl implements ProjectSendFinancingHistoryBService{

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(ProjectSendFinancingHistoryBImpl.class);

    @Resource
    private ProjectSendFinancingHistoryBMapper projectSendFinancingHistoryBMapper;

    @Resource
    private ProjectSendInvestorBMapper projectSendInvestorBMapper;

    /**
     * 保存项目融资历史信息接口
     * @param body
     * @param appid
     * @return
     */
    @Override
    public CommonDto<String> creatProjectSendFinancingHistoryB(List<ProjectSendFinancingHistoryBDto> body, Integer appid) {
        CommonDto<String> result  = new CommonDto<>();

        if (body == null){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("请传入融资历史信息");

            return result;
        }

        if (body.size() > 0){
            for (ProjectSendFinancingHistoryBDto psf:body){
                if (psf.getAmount() == null){
                    result.setStatus(502);
                    result.setMessage("融资金额不能为空");
                    result.setData(null);

                    return result;
                }
                if (psf.getStage() == null || "".equals(psf.getStage()) || "undefined".equals(psf.getStage())){
                    result.setData(null);
                    result.setMessage("融资轮次不能为空");
                    result.setStatus(502);

                    return result;
                }
                if (psf.getTotalAmount() == null){
                    result.setStatus(502);
                    result.setMessage("估值金额不能为空");
                }
            }
        }

        if (body.size()>0){
            Integer projectSendBId = body.get(0).getProjectSendBId();
            ProjectSendFinancingHistoryB projectSendFinancingHistoryB = new ProjectSendFinancingHistoryB();
            projectSendFinancingHistoryB.setProjectSendBId(projectSendBId);

            List<ProjectSendFinancingHistoryB> projectSendFinancingHistoryBList = projectSendFinancingHistoryBMapper.select(projectSendFinancingHistoryB);
            if (projectSendFinancingHistoryBList.size() > 0){
                //先删除
                deleteFinancingHistory(projectSendBId,appid);
                //创建
                for (ProjectSendFinancingHistoryBDto psfd: body){
                    createFinancingOne(psfd,appid);
                }
            }else {
                //创建
                for (ProjectSendFinancingHistoryBDto psfd: body){
                    createFinancingOne(psfd,appid);
                }
            }
        }

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 读取提交项目融资历史信息接口
     * @param projectSendBId
     * @param appid
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public CommonDto<List<ProjectSendFinancingHistoryBDto>> readProjectSendFinancingHistoryB(Integer projectSendBId, Integer appid) {
        CommonDto<List<ProjectSendFinancingHistoryBDto>> result  = new CommonDto<>();
        List<ProjectSendFinancingHistoryBDto> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (projectSendBId == null){
            result.setMessage("提交项目id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        ProjectSendFinancingHistoryB projectSendFinancingHistoryB = new ProjectSendFinancingHistoryB();
        projectSendFinancingHistoryB.setProjectSendBId(projectSendBId);
        projectSendFinancingHistoryB.setAppid(appid);

        List<ProjectSendFinancingHistoryB> projectSendFinancingHistoryBList = projectSendFinancingHistoryBMapper.select(projectSendFinancingHistoryB);
        if (projectSendFinancingHistoryBList.size() > 0){
            for (ProjectSendFinancingHistoryB psf:projectSendFinancingHistoryBList){
                Integer projectFinancingHistoryId = psf.getId();
                ProjectSendInvestorB projectSendInvestorB = new ProjectSendInvestorB();
                projectSendInvestorB.setPsFinancingHistoryBId(projectFinancingHistoryId);
                List<ProjectSendInvestorB> projectSendInvestorBS = new ArrayList<>();

                List<ProjectSendInvestorB> projectSendInvestorBList = projectSendInvestorBMapper.select(projectSendInvestorB);
                if (projectSendInvestorBList.size()>0){
                    projectSendInvestorBS = projectSendInvestorBList;
                }

                ProjectSendFinancingHistoryBDto projectSendFinancingHistoryBDto = new ProjectSendFinancingHistoryBDto();
                projectSendFinancingHistoryBDto.setStage(psf.getStage());
                projectSendFinancingHistoryBDto.setAmount(psf.getAmount());
                projectSendFinancingHistoryBDto.setCurrency(psf.getCurrency());
                projectSendFinancingHistoryBDto.setTotalAmount(psf.getTotalAmount());
                projectSendFinancingHistoryBDto.setTotalAmountCurrency(psf.getTotalAmountCurrency());
                projectSendFinancingHistoryBDto.setProjectSendBId(projectSendBId);
                String financingTime = "";
                if (psf.getFinancingTime() != null){
                    try {
                        financingTime = sdf.format(psf.getFinancingTime());
                    }catch (Exception e){
                        log.error(e.getMessage());
                        log.info("解析时间失败");
                    }
                }
                projectSendFinancingHistoryBDto.setFinancingTime(financingTime);
                List<ProjectSendInvestorDto> projectSendInvestorDtoList = new ArrayList<>();
                if (projectSendInvestorBS.size() > 0){
                    for (ProjectSendInvestorB pb:projectSendInvestorBS){
                        ProjectSendInvestorDto projectSendInvestorDto = new ProjectSendInvestorDto();
                        projectSendInvestorDto.setInvestorName(pb.getInvestorName());
                        projectSendInvestorDto.setProjectFinancingHistoryId(pb.getPsFinancingHistoryBId());
                        projectSendInvestorDto.setShareRatio(pb.getStockRatio());

                        projectSendInvestorDtoList.add(projectSendInvestorDto);
                    }
                }
                projectSendFinancingHistoryBDto.setInvestor(projectSendInvestorDtoList);

                list.add(projectSendFinancingHistoryBDto);
            }
        }

        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 复制提交项目融资历史信息的方法
     * @param newprojectSendId
     * @param projectSendBId
     * @param appid
     */
    @Override
    public void copyProjectSendFinancingHistoryB(Integer newprojectSendId,Integer projectSendBId,Integer appid) {

        if (projectSendBId == null ){
            return;
        }
        if (appid == null){
            return;
        }
        ProjectSendFinancingHistoryB projectSendFinancingHistoryB = new ProjectSendFinancingHistoryB();
        projectSendFinancingHistoryB.setProjectSendBId(projectSendBId);
        projectSendFinancingHistoryB.setAppid(appid);

        List<ProjectSendFinancingHistoryB> projectSendFinancingHistoryBList = projectSendFinancingHistoryBMapper.select(projectSendFinancingHistoryB);
        if (projectSendFinancingHistoryBList.size() > 0){
            for (ProjectSendFinancingHistoryB psf:projectSendFinancingHistoryBList){
                ProjectSendSearchCommenDto projectSendSearchCommenDto = new ProjectSendSearchCommenDto();
                projectSendSearchCommenDto.setOldid(psf.getId());
                projectSendSearchCommenDto.setNewid(newprojectSendId);

                projectSendFinancingHistoryBMapper.copyProjectSendFinancingHistory(projectSendSearchCommenDto);

                Integer historyId = projectSendSearchCommenDto.getId();
                ProjectSendSearchCommenDto projectSendSearchCommenDto1 = new ProjectSendSearchCommenDto();
                projectSendSearchCommenDto1.setNewid(historyId);
                projectSendSearchCommenDto1.setOldid(psf.getId());

                projectSendInvestorBMapper.copyProjectSendInvestorB(projectSendSearchCommenDto1);
            }
        }

    }

    /**
     * 创建单个融资历史信息的方法
     * @param projectSendFinancingHistoryBDto
     * @param appid
     */
    @Transactional
    public void createFinancingOne(ProjectSendFinancingHistoryBDto projectSendFinancingHistoryBDto,Integer appid){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ProjectSendFinancingHistoryB projectSendFinancingHistoryB = new ProjectSendFinancingHistoryB();
        projectSendFinancingHistoryB.setStage(projectSendFinancingHistoryBDto.getStage());
        projectSendFinancingHistoryB.setAmount(projectSendFinancingHistoryBDto.getAmount());
        projectSendFinancingHistoryB.setCurrency(projectSendFinancingHistoryBDto.getCurrency());
        projectSendFinancingHistoryB.setTotalAmount(projectSendFinancingHistoryBDto.getTotalAmount());
        projectSendFinancingHistoryB.setTotalAmountCurrency(projectSendFinancingHistoryBDto.getTotalAmountCurrency());
        projectSendFinancingHistoryB.setProjectSendBId(projectSendFinancingHistoryBDto.getProjectSendBId());
        projectSendFinancingHistoryB.setAppid(appid);
        if (projectSendFinancingHistoryBDto.getFinancingTime() != null){
            try {
                Date financingTime = sdf.parse(projectSendFinancingHistoryBDto.getFinancingTime());
                projectSendFinancingHistoryB.setFinancingTime(financingTime);
            }catch (Exception e){
             log.error(e.getMessage());
             log.info("解析时间失败");
            }
        }


        projectSendFinancingHistoryBMapper.insertSelective(projectSendFinancingHistoryB);

        Integer projectHistoryId = projectSendFinancingHistoryB.getId();
        if (projectSendFinancingHistoryBDto.getInvestor() != null){
            if (projectSendFinancingHistoryBDto.getInvestor().size() > 0){
                List<ProjectSendInvestorDto> projectSendInvestorDtoList = projectSendFinancingHistoryBDto.getInvestor();
                createFinancingInvestors(projectSendInvestorDtoList,projectHistoryId,appid);
            }
        }


    }

    /**
     * 创建融资历史投资方信息的方法
     * @param projectSendInvestorDtoList
     * @param projectHistoryId
     * @param appid
     */
    @Transactional
    public void createFinancingInvestors(List<ProjectSendInvestorDto> projectSendInvestorDtoList,Integer projectHistoryId,Integer appid){
        if (projectSendInvestorDtoList.size() > 0){
            for (ProjectSendInvestorDto psi:projectSendInvestorDtoList){
                ProjectSendInvestorB projectSendInvestorB = new ProjectSendInvestorB();
                projectSendInvestorB.setAppid(appid);
                projectSendInvestorB.setInvestorName(psi.getInvestorName());
                projectSendInvestorB.setPsFinancingHistoryBId(projectHistoryId);
                projectSendInvestorB.setStockRatio(psi.getShareRatio());

                projectSendInvestorBMapper.insertSelective(projectSendInvestorB);
            }
        }
    }

    /**
     * 删除融资历史信息的方法
     * @param projectSendBId
     * @param appid
     */
    @Transactional
    public void deleteFinancingHistory(Integer projectSendBId,Integer appid){
        if (projectSendBId == null){
            return;
        }
        if (appid == null){
            return;
        }

        ProjectSendFinancingHistoryB projectSendFinancingHistoryB = new ProjectSendFinancingHistoryB();
        projectSendFinancingHistoryB.setProjectSendBId(projectSendBId);

        List<ProjectSendFinancingHistoryB> projectSendFinancingHistoryBList = projectSendFinancingHistoryBMapper.select(projectSendFinancingHistoryB);
        if (projectSendFinancingHistoryBList.size() > 0){
            for (ProjectSendFinancingHistoryB psf:projectSendFinancingHistoryBList){
                ProjectSendInvestorB projectSendInvestorB = new ProjectSendInvestorB();
                projectSendInvestorB.setPsFinancingHistoryBId(psf.getId());

                projectSendInvestorBMapper.delete(projectSendInvestorB);
            }
            projectSendFinancingHistoryBMapper.delete(projectSendFinancingHistoryB);
        }


    }
}
