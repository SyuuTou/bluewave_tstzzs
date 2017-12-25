
package com.lhjl.tzzs.proxy.service;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.UserFilterLog;

import java.util.List;
import java.util.Map;

public interface UserFilterLogService {
    CommonDto<String> userFilterAddLog(String investment_institutions_type,String investment_institutions_field,String financing_stage,String city,String education,String work,String user_id,String financingYear);
    CommonDto<Map<String,Object>> userFilterSearchLog(String id);
}