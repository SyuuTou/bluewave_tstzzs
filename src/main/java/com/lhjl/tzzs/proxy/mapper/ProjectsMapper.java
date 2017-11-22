package com.lhjl.tzzs.proxy.mapper;

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
    List<Map<String, Object>> findProjectBySview(@Param("userId")String userId,@Param("types")int[] types,@Param("segmentations")String [] segmentations,
                                                 @Param("stages")String [] stages,@Param("cities")String [] cities,
                                                 @Param("working_background_descs")String [] working_background_descs,@Param("educational_background_descs")String [] educational_background_descs,
                                                 @Param("sizea") Integer sizea,@Param("froma") Integer froma

    );
    List<Projects> findByTodayProjects(Projects projects);

    List<Map<String,Object>> findProjectBySegmentation(@Param("userId") String userId, @Param("types") int[] types, @Param("segmentations") String[] segmentations,
                                                       @Param("stages") String[] stages,@Param("sizea") Integer sizea,@Param("froma") Integer froma);



    List<Map<String, Object>> findProjectBySviewA(@Param("userId")String userId,@Param("types")int[] types,@Param("segmentations")String [] segmentations,
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
    List<XiangsiDto> findLikesall(@Param("educationArray") String [] educationArray,@Param("projectTagArry") String [] projectTagArry,@Param("shortName") String shortName);

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
    List<Integer>screenInvestmentAll(@Param("domains")String [] domains,@Param("stages")String [] stages);
}


