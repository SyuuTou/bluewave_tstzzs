package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.AdminCreatProjectDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ProjectRatingService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectRatingServiceImpl implements ProjectRatingService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAuditServiceImpl.class);

    @Autowired
    private AdminProjectRatingLogMapper adminProjectRatingLogMapper;

    @Autowired
    private ProjectSendLogsMapper projectSendLogsMapper;

    @Autowired
    private ProjectFinancingInvestmentInstitutionRelationshipMapper projectFinancingInvestmentInstitutionRelationshipMapper;

    @Autowired
    private AdminProjectApprovalLogMapper adminProjectApprovalLogMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public CommonDto<String> projectRating(ProjectRatingDto body){
        CommonDto<String> result = new CommonDto<>();
        Date now =new Date();

        if (body.getProjectId() == null){
            result.setStatus(50001);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.getRatingStage() == null){
            result.setData(null);
            result.setMessage("评级级别不能为空");
            result.setStatus(50001);
            return result;
        }
        //先判断项目是否已经评过等级了
        AdminProjectRatingLog adminProjectRatingLogForSearch = new AdminProjectRatingLog();
        adminProjectRatingLogForSearch.setProjectId(body.getProjectId());

        List<AdminProjectRatingLog> adminProjectRatingLogList = adminProjectRatingLogMapper.select(adminProjectRatingLogForSearch);

        if (adminProjectRatingLogList.size() > 0){
            Integer aprlid  = adminProjectRatingLogList.get(0).getId();

            AdminProjectRatingLog adminProjectRatingLogForUpdate = new AdminProjectRatingLog();
            adminProjectRatingLogForUpdate.setId(aprlid);
            adminProjectRatingLogForUpdate.setRatingStage(body.getRatingStage());
            if (body.getRatingAdminName() != null){
                adminProjectRatingLogForUpdate.setRatingAdminName(body.getRatingAdminName());
            }
            if (body.getRatingDiscription() != null){
                adminProjectRatingLogForUpdate.setRatingDescription(body.getRatingDiscription());
            }
            adminProjectRatingLogForUpdate.setRatingTime(now);

            //更新数据
            adminProjectRatingLogMapper.updateByPrimaryKeySelective(adminProjectRatingLogForUpdate);
        }else{
            AdminProjectRatingLog adminProjectRatingLog = new AdminProjectRatingLog();
            adminProjectRatingLog.setProjectId(body.getProjectId());
            adminProjectRatingLog.setRatingStage(body.getRatingStage());
            if (body.getRatingAdminName() != null){
                adminProjectRatingLog.setRatingAdminName(body.getRatingAdminName());
            }
            if (body.getRatingDiscription() != null){
                adminProjectRatingLog.setRatingDescription(body.getRatingDiscription());
            }
            adminProjectRatingLog.setRatingTime(now);

            //插入数据
            adminProjectRatingLogMapper.insertSelective(adminProjectRatingLog);
        }



        result.setStatus(200);
        result.setMessage("success");
        result.setData(null);

        return result;
    }

    /**
     * 后台管理员审核后，获取事件接受的机构id信息接口
     * @param sourceId 源数据id
     * @param idType id类型，0表示项目提交记录表，1表示项目表信息
     * @return
     */
    @Override
    public CommonDto<AdminCreatProjectDto> adminCreateEvent(Integer sourceId, Integer idType){
        CommonDto<AdminCreatProjectDto> result = new CommonDto<>();

        if (sourceId == null ){
            result.setStatus(502);
            result.setMessage("源数据id不能为空");
            result.setData(null);

            return result;
        }

        if (idType == null ){
            result.setMessage("源数据id类型不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        //判断项目源类型是提交项目记录表的处理情况
        if (idType == 0){
            result = getInfoFromProjectSendLogs(sourceId);
        }else if (idType == 1){
            result = getInfoFromProjects(sourceId);
        }else {
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目源类型未知，请检查");
        }

        return result;
    }

    /**
     * 去项目提交记录表找，提交人id,选择机构列表；
     * @param soruceId 提交项目记录表id
     * @return
     */
    private CommonDto<AdminCreatProjectDto> getInfoFromProjectSendLogs(Integer soruceId){
        CommonDto<AdminCreatProjectDto> result  = new CommonDto<>();

        //先获取到用户提交记录信息
        ProjectSendLogs projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(soruceId);

        if (projectSendLogs == null ){
            result.setMessage("项目提交记录表id非法，没找到对应提交记录");
            result.setData(null);
            result.setStatus(502);
        }else {
            AdminCreatProjectDto adminCreatProjectDto = new AdminCreatProjectDto();

            //获取到用户token;
            Example userTokenExample = new Example(UserToken.class);
            userTokenExample.and().andEqualTo("userId",projectSendLogs.getUserid());

            List<UserToken> userTokenList = userTokenMapper.selectByExample(userTokenExample);

            String userToken = "";
            if (userTokenList.size() > 0){
                userToken = userTokenList.get(0).getToken();
            }else {
                result.setStatus(502);
                result.setMessage("没有找到用户id对应的token");
                result.setData(null);

                return result;
            }

            //找到推荐的机构信息，然后放到返回数据里面去
            Example pfiirExample = new Example(ProjectFinancingInvestmentInstitutionRelationship.class);
            pfiirExample.and().andEqualTo("projectSendLogId",soruceId);
            List<ProjectFinancingInvestmentInstitutionRelationship> projectFinancingInvestmentInstitutionRelationshipList =  projectFinancingInvestmentInstitutionRelationshipMapper.selectByExample(pfiirExample);
            if (projectFinancingInvestmentInstitutionRelationshipList.size() > 0){
                //将机构id放到数组里
                List<Integer> list = new ArrayList<>();
                for (ProjectFinancingInvestmentInstitutionRelationship pfii : projectFinancingInvestmentInstitutionRelationshipList){
                   list.add(pfii.getInvestmentInstitutionId());
                }
                adminCreatProjectDto.setInvestmentInstitutionIds(list);
                adminCreatProjectDto.setProjectCreaterId(userToken);

                result.setStatus(200);
                result.setData(adminCreatProjectDto);
                result.setMessage("success");
            }else {
                //没有机构的时候返回空数组
                List<Integer> list = new ArrayList<>();
                adminCreatProjectDto.setProjectCreaterId(userToken);
                adminCreatProjectDto.setInvestmentInstitutionIds(list);

                result.setMessage("success");
                result.setData(adminCreatProjectDto);
                result.setStatus(200);
            }
        }

        return result;
    }

    /**
     * 去项目表找，提交人id，选择机构信息；
     * @param soruceId 项目表id
     * @return
     */
    private CommonDto<AdminCreatProjectDto> getInfoFromProjects(Integer soruceId){
        CommonDto<AdminCreatProjectDto> result = new CommonDto<>();
        AdminCreatProjectDto adminCreatProjectDto = new AdminCreatProjectDto();

        //用项目id去获取项目审核记录里面最近一条的提交项目表id
        Example apaExample = new Example(AdminProjectApprovalLog.class);
        apaExample.and().andEqualTo("projectId",soruceId);
        apaExample.setOrderByClause("approvaled_time desc");
        List<AdminProjectApprovalLog> adminProjectApprovalLogList = adminProjectApprovalLogMapper.selectByExample(apaExample);

        if (adminProjectApprovalLogList.size() > 0){
            //有审核记录的时候，获取提交记录id进而找到机构信息
            Integer projectSoruce = adminProjectApprovalLogList.get(0).getProjectSourceType();
            Integer projectSendLogId = adminProjectApprovalLogList.get(0).getProjectSourceId();
            //当项目是创始人提交的时候才发信息流事件
            if (projectSoruce == 0){
                ProjectSendLogs projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(projectSendLogId);
                if (projectSendLogs == null){
                    //没找到提交记录信息
                    List<Integer> list =new ArrayList<>();
                    adminCreatProjectDto.setInvestmentInstitutionIds(list);
                    adminCreatProjectDto.setProjectCreaterId("");

                    result.setData(adminCreatProjectDto);
                    result.setMessage("没有找到该项目的提交记录");
                    result.setStatus(502);

                    return result;

                }else {
                    //找到后根据项目提交记录找到，提交的机构记录
                    List<Integer> list =new ArrayList<>();

                    //获取到用户token;
                    Example userTokenExample = new Example(UserToken.class);
                    userTokenExample.and().andEqualTo("userId",projectSendLogs.getUserid());

                    List<UserToken> userTokenList = userTokenMapper.selectByExample(userTokenExample);

                    String userToken = "";
                    if (userTokenList.size() > 0){
                        userToken = userTokenList.get(0).getToken();
                    }else {
                        result.setStatus(502);
                        result.setMessage("没有找到用户id对应的token");
                        result.setData(null);

                        return result;
                    }

                    //查询机构信息
                    Example pfiirExample = new Example(ProjectFinancingInvestmentInstitutionRelationship.class);
                    pfiirExample.and().andEqualTo("projectSendLogId",projectSendLogs.getId());
                    List<ProjectFinancingInvestmentInstitutionRelationship> projectFinancingInvestmentInstitutionRelationshipList = projectFinancingInvestmentInstitutionRelationshipMapper.selectByExample(pfiirExample);
                    if (projectFinancingInvestmentInstitutionRelationshipList.size() > 0){
                        for(ProjectFinancingInvestmentInstitutionRelationship pfi : projectFinancingInvestmentInstitutionRelationshipList){
                            list.add(pfi.getInvestmentInstitutionId());
                        }
                        adminCreatProjectDto.setInvestmentInstitutionIds(list);
                        adminCreatProjectDto.setProjectCreaterId(userToken);

                        result.setStatus(200);
                        result.setMessage("success");
                        result.setData(adminCreatProjectDto);

                    }else {
                        adminCreatProjectDto.setProjectCreaterId(userToken);
                        adminCreatProjectDto.setInvestmentInstitutionIds(list);

                        result.setData(adminCreatProjectDto);
                        result.setMessage("success");
                        result.setStatus(200);
                    }
                }
            }else {
                List<Integer> list = new ArrayList<>();
                adminCreatProjectDto.setInvestmentInstitutionIds(list);
                adminCreatProjectDto.setProjectCreaterId(null);

                result.setStatus(201);
                result.setMessage("机构提交项目不发信息流");
                result.setData(adminCreatProjectDto);
            }

        }else {
            List<Integer> list =new ArrayList<>();

            adminCreatProjectDto.setProjectCreaterId(null);
            adminCreatProjectDto.setInvestmentInstitutionIds(list);

            result.setData(adminCreatProjectDto);
            result.setMessage("项目没有审核记录不发信息流");
            result.setStatus(201);
        }

        return result;
    }
}
