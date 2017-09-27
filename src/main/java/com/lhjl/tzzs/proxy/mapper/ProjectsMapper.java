package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

import java.util.List;

public interface ProjectsMapper extends OwnerMapper<Projects> {
    List<Projects> maxSerialNumber();
}