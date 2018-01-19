package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.ProInfoDto;
import com.lhjl.tzzs.proxy.dto.ProjectReqDto;
import com.lhjl.tzzs.proxy.dto.ProjectResDto;
import com.lhjl.tzzs.proxy.dto.ProjectsListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectsListOutputDto;
import com.lhjl.tzzs.proxy.dto.XiangsiDto;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ProjectsMapper extends OwnerMapper<Projects> {
    List<Projects> maxSerialNumber();
    List<Map<String, Object>> findProjectByUserId(@Param("userId") String userId, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
    List<Map<String, Object>> findProjectByShortName(@Param("shortName") String shortName,@Param("userId") String userId);
    List<Map<String, Object>> findProjectByShortNameAll(@Param("shortName") String shortName,@Param("userId") String userId,@Param("sizea") Integer sizea,@Param("froma") Integer froma);
    List<Map<String, Object>> findProjectBySview(@Param("types")int[] types,@Param("segmentations")String [] segmentations,
                                                 @Param("stages")String [] stages,@Param("cities")String [] cities,
                                                 @Param("working_background_descs")String [] working_background_descs,@Param("educational_background_descs")String [] educational_background_descs,
                                                 @Param("sizea") Integer sizea,@Param("froma") Integer froma

    );
    List<Projects> findByTodayProjects(Projects projects);

    List<Map<String,Object>> findProjectBySegmentation(@Param("userId") String userId, @Param("types") int[] types, @Param("segmentations") String[] segmentations,
                                                       @Param("stages") String[] stages,@Param("sizea") Integer sizea,@Param("froma") Integer froma);



    List<Map<String, Object>> findProjectBySviewA(@Param("types")int[] types,@Param("segmentations")String [] segmentations,
                                                  @Param("stages")String [] stages,@Param("cities")String [] cities,
                                                  @Param("working_background_descs")String [] working_background_descs,@Param("educational_background_descs")String [] educational_background_descs,
                                                  @Param("sizea") Integer sizea,@Param("froma") Integer froma,
                                                  @Param("beginTime") String beginTime ,@Param("endTime") String endTime

    );

    Integer findIvestmentTypeById(@Param("projectsId") Integer projectsId);

    List<Map<String,Object>> findProjectAmountOfZ();

    BigDecimal findProjectByRoundAndE(@Param("segmentation") String segmentation, @Param("stage") String stage);

    BigDecimal findProjectByRoundAndC(@Param("stage") String stage, @Param("city") String city);

    List<Map<String,Object>> findProjectAllAmountOfZ();

    BigDecimal findProjectAllByRoundAndE(@Param("segmentation") String segmentation, @Param("stage") String stage);

    BigDecimal findProjectAllByRoundAndC(@Param("stage") String stage, @Param("city") String city);
    List<Map<String,Object>> findLikes(@Param("educationArray") String [] educationArray, @Param("city") String city,@Param("pslArray") String [] pslArray, @Param("shortName") String shortName);
    List<XiangsiDto> findLikesall(@Param("educationArray") String [] educationArray,@Param("projectTagArry") String [] projectTagArry,@Param("shortName") String shortName,@Param("id") int  id,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);

    /**
     * 后台获取项目列表接口mapper,数据导入项目
     * @param startNum
     * @param pageSize
     * @param shortName
     * @param projectType
     * @return
     */
    List<Map<String,Object>> adminGetProjectListOne(@Param("startNum") Integer startNum,@Param("pageSize") Integer pageSize,@Param("shortName") String shortName,@Param("projectType") Integer projectType);

    /**
     * 后台获取项目列表接口mapper,用户提交项目
     * @param startNum
     * @param pageSize
     * @param shortName
     * @param projectType
     * @return
     */
    List<Map<String,Object>> adminGetProjectListTwo(@Param("startNum") Integer startNum,@Param("pageSize") Integer pageSize,@Param("shortName") String shortName,@Param("projectType") Integer projectType);

    /**
     * 获得机构的id
     * @param domains
     * @param stages
     * @return
     */
    List<Integer>screenInvestmentAll(@Param("domains")Integer[] domains,@Param("stages")Integer[] stages);

    /**
     * 获取用户创建项目编号最大值的接口
     * @return
     */
    List<Projects> projectLastSerialNumber();

    /**
     * 获取机构投资项目
     * @param institutionId 机构id
     * @param stage 轮次
     * @param segmentationName 领域
     * @param financingTime 融资时间
     * @param startNum 起始页码
     * @param pageSize 每页显示数量
     * @return
     */
    List<Map<String,Object>> findInstitutionProject(@Param("institutionId") Integer institutionId,@Param("stage") String stage,@Param("segmentationName") String[] segmentationName,@Param("financingTime") String[] financingTime,@Param("startNum") Integer startNum,@Param("pageSize") Integer pageSize);

    List<ProjectResDto> projectFilter(ProjectReqDto reqDto);

    List<ProjectResDto> projectFilterOne(ProjectReqDto reqDto);

    List<ProjectResDto> projectFilterTwo(ProjectReqDto reqDto);

    List<Map<String,Object>> relatedInvestmentInstitution(ProjectReqDto reqDto);

    List<HistogramList> projectSearchStatistics(ProjectReqDto reqDto);
    /**
     * 项目列表的实现
     * @return
     */
	List<ProjectsListOutputDto> findSplit(ProjectsListInputDto body);
	/**
	 * 获取是否经过模糊查询后的数据总数
	 * 经过模糊搜索之后仍然需要进行分组
	 * @return 返回的是最后经过分组之后的组数
	 */
	Long findSplitCount(ProjectsListInputDto body);

    /**
     *
     * @param projectId
     * @return
     */
	Map<String,Object> getLogoAndOtherInfoById(@Param("projectId") Integer projectId);

	Map<String,Object> getBaseInfoById(@Param("projectId") Integer projectId);
	/**
	 * 获取项目的简单相关信息
	 * @param projectId
	 * @return
	 */
	ProInfoDto getProjectsSimpleInfos(@Param("projectId")Integer projectId);
}


