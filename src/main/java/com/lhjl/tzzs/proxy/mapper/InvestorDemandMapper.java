package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestorDemand;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InvestorDemandMapper extends OwnerMapper<InvestorDemand> {

    /**
     * 获取融资需求/投资风向标方法
     * @param startPage
     * @param pageSize
     * @param dataType
     * @param userId
     * @return
     */
    List<Map<String,Object>> getInvestorDemandList(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize,
                                                   @Param("status") Integer[] status, @Param("isUser") Integer isUser,
                                                   @Param("appid") Integer appid, @Param("dataType") Integer dataType,@Param("userId") Integer userId);

    /**
     * 获取融资需求/投资风向标方法
     * @param startPage
     * @param pageSize
     * @param dataType
     * @param userId
     * @return
     */
    List<Map<String,Object>> getInvestorDemandListBackStage(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize,
                                                   @Param("status") Integer[] status, @Param("isUser") Integer isUser,
                                                   @Param("appid") Integer appid, @Param("dataType") Integer dataType,@Param("userId") Integer userId);

    /**
     * 获取总数量
     * @param startPage
     * @param pageSize
     * @param dataType
     * @param userId
     * @return
     */
    Integer getInvestorDemandListCount(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize,
                                       @Param("status") Integer[] status, @Param("isUser") Integer isUser,
                                       @Param("appid") Integer appid, @Param("dataType") Integer dataType,@Param("userId") Integer userId);

    /**
     * 根据用户id获取用户融资需求信息
     * @param userId
     * @param appid
     * @return
     */
    Map<String,Object> selectDemandByUserId(@Param("userId") Integer userId,@Param("appid") Integer appid);

    InvestorDemand selectByInvestorId(@Param("investorId")Integer investorId);
}