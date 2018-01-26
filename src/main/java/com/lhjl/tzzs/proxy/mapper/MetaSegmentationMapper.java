package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MetaSegmentationMapper extends OwnerMapper<MetaSegmentation> {
    List<Integer> findMetaSegmentationBySegmentation(@Param("domains") String[] domains);
    
    /**
     * 获取所有的领域对象
     * @return list存储
     */
    List<MetaSegmentation> findAll();

    List<Map<String,Object>> findInstitutionTop(@Param("institutionId") Integer institutionId);

    List<Map<String,Object>> findUserFocusSegmentation(@Param("institutionId") Integer institutionId);

    List<MetaSegmentation> selectAll();

    Integer findSegmentationIdByName(@Param("segmentation") String segmentation);

    List<MetaSegmentation> selectBySegmentationIds(@Param("segmentationIds")List<Integer> segmentationIds);
}