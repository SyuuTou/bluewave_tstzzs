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
    List<Projects> findProjectByUserId(String userId);
    List<Map<String, Object>> findProjectByShortName(@Param("shortName") String shortName,@Param("userId") String userId);
    List<Map<String, Object>> findProjectByShortNameAll(@Param("shortName") String shortName,@Param("userId") String userId);
    List<Map<String, Object>> findProjectBySview(SereachDto sereachDto);
}
