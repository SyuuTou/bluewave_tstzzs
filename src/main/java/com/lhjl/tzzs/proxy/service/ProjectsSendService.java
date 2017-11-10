package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectsSendDto;
import com.lhjl.tzzs.proxy.dto.TeamDto;
import com.lhjl.tzzs.proxy.dto.TeamDto1;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by 蓝海巨浪 on 2017/10/23.
 */
public interface ProjectsSendService {
    /**
     * 项目投递
     * @param params 投递参数
     * @param userId 用户ID
     * @return
     */
    CommonDto<String> ctuisongsecond(ProjectsSendDto params, int userId);

    /**
     * 项目投递回显
     * @param userId 用户ID
     * @param tsid 投递项目ID
     * @return
     */
    CommonDto<Map<String, Object>> rtuisonghuixian(int userId, String tsid);

    /**
     * 融资历史记录
     * @param tsid 项目ID
     * @param rongzilishi 融资历史信息
     * @return
     */
    CommonDto<String> ctuisongthird(String tsid, String rongzilishi);

    /**
     * 融资历史回显
     * @param tsid 投递项目ID
     * @param userId 用户ID
     * @return
     */
    CommonDto<Map<String, Object>> rtuisongthird(String tsid, int userId);
    
    
    CommonDto<String> saveTeam(TeamDto body);

    CommonDto<String> deleteTeam(Integer id );
    CommonDto<Map<String, Object>> teamRecord(Integer id);
    CommonDto<String> saveTeam1( TeamDto1 body);
}
