package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaSegmentationMapper extends OwnerMapper<MetaSegmentation> {
    List<Integer> findMetaSegmentationBySegmentation(@Param("domains") String[] domains);
    
    /**
     * 获取所有的领域对象
     * @return list存储
     */
    List<MetaSegmentation> findAll();
    
}