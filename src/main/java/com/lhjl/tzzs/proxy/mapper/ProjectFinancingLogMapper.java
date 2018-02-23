package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogOutputDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadOutputDto;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectFinancingLogMapper extends OwnerMapper<ProjectFinancingLog> {
    List<ProjectFinancingLog> selectProjectFinancingLogList(@Param("projectId") Integer projectId,@Param("projectStatus") Integer projectStatus);
    List<String> selectInvestors(@Param("pflid") Integer pflid);
    /**
     * 获取投资事件所有的实体list
     * @param body
     * @return
     */
    List<ProjectFinancingLogOutputDto> getProjectFinancingLogLists(ProjectFinancingLogInputDto body);
    /**
     * 获取投资事件记录数
     * @param body
     * @return
     */
    Integer getProjectFinancingLogListCount(ProjectFinancingLogInputDto body);
    /**
     * 获取所有的融资状态
     * @return
     */
	List<String> fetchFinancingStatus();

    ProjectFinancingLog selectByProjectId(@Param("projectId") Integer projectId);
    /**
     * 投资事件头部信息的输出
     * @return
     */
	ProjectFinancingLogHeadOutputDto echoProjectFinancingLogHead(@Param("projectFinancingLogId") Integer projectFinancingLogId);
	
}