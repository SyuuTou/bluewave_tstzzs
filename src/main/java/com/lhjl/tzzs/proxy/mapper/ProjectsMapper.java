package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ProjectsMapper extends OwnerMapper<Projects> {
    List<Projects> maxSerialNumber();
    List<Projects> findProjectByUserId(String userId);
    List<Map<String, Object>>findProjectByShortName(@Param("shortName") String shortName,@Param("userId") String userId);
    List<Map<String, Object>>findProjectByShortNameAll(@Param("shortName") String shortName,@Param("userId") String userId);
    List<Map<String, Object>>findProjectBySview(@Param("type") Integer  type,@Param("segmentation") String segmentation,
                                                @Param("stage") String stage,@Param("city") String city,@Param("userId") String userId,
                                                @Param("working_background_desc") String working_background_desc,@Param("educational_background_desc") String educational_background_desc
    );
}