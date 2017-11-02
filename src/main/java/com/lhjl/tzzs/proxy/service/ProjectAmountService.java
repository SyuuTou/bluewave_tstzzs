package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import org.springframework.stereotype.Service;

@Service
public interface ProjectAmountService {
    CommonDto<String> projectAmoutAdd();
    CommonDto<String> projetcAllAmountAdd();
}
