package com.lhjl.tzzs.proxy.service;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;



public interface ProjectRatingService {
    CommonDto<String> projectRating(ProjectRatingDto body);
}
