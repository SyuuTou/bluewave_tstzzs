package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectFinancingLogMapper extends OwnerMapper<ProjectFinancingLog> {
    List<ProjectFinancingLog> selectProjectFinancingLogList(@Param("projectId") Integer projectId,@Param("projectStatus") Integer projectStatus);
    List<String> selectInvestors(@Param("pflid") Integer pflid);
    /**
     * 获取所有的融资状态
     * @return
     */
	List<String> fetchFinancingStatus();

    /**
     * 项目融资历史查询
     * @param searchWord
     * @param begainTime
     * @param endTime
     * @param dataSource
     * @param stage
     * @param currency
     * @param creatTimeOrder
     * @param creatTimeOrderDesc
     * @param updateTimeOrder
     * @param updateTimeOrderDesc
     * @param startPage
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> getProjectFinancingLogList(@Param("searchWord") String searchWord,@Param("begainTime") String begainTime,
                                                        @Param("endTime") String endTime,@Param("dataSource") Integer[] dataSource,
                                                        @Param("stage") String[] stage,@Param("currency") Integer[] currency,
                                                        @Param("creatTimeOrder") Integer creatTimeOrder,@Param("creatTimeOrderDesc") Integer creatTimeOrderDesc,
                                                        @Param("updateTimeOrder") Integer updateTimeOrder,@Param("updateTimeOrderDesc") Integer updateTimeOrderDesc,
                                                        @Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
    Integer getProjectFinancingLogListCount(@Param("searchWord") String searchWord,@Param("begainTime") String begainTime,
                                                        @Param("endTime") String endTime,@Param("dataSource") Integer[] dataSource,
                                                        @Param("stage") String[] stage,@Param("currency") Integer[] currency,
                                                        @Param("creatTimeOrder") Integer creatTimeOrder,@Param("creatTimeOrderDesc") Integer creatTimeOrderDesc,
                                                        @Param("updateTimeOrder") Integer updateTimeOrder,@Param("updateTimeOrderDesc") Integer updateTimeOrderDesc,
                                                        @Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
}