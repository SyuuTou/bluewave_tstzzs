package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.dto.SereachDto;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
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
                                                 @Param("sizea") Integer sizea,@Param("froma") Integer froma

    );

    Integer findIvestmentTypeById(@Param("projectsId") Integer projectsId);
}


