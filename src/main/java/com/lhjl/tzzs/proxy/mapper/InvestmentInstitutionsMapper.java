package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
@Mapper
public interface InvestmentInstitutionsMapper extends OwnerMapper<InvestmentInstitutions> {
    List<InvestmentInstitutionsDto> serachInstitution(@Param("shortName") String shortName, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
    List<Map<String, Object>>findByInvestmentShortNameAll(@Param("shortName") String shortName,@Param("userId") String userId);
    List<Map<String,Object>> findInvestment50(@Param("type") String type, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
    List<Map<String,Object>> findInvestmentall(@Param("type") String type, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
    List<InvestmentInstitutionsDto> screenInstitutionAll (@Param("workArray") Integer [] workArray,@Param("types") Integer [] types ,@Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
     List<Integer>serachSendProjectId(@Param("workArray1")Integer [] workArray1);
    List<InvestmentInstitutions> findeInvestmentByShortName(@Param("shortName") String shortName,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
    List<InvestmentInstitutionsDto> findInvestment50New(@Param("type") String type, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
    List<InvestmentInstitutionsDto> findInvestmentallNew(@Param("type") String type, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
    List<Integer>deliverySendProjectId(@Param("workArray1")Integer [] workArray1);
    List<InvestmentInstitutionsDto>findRecommendAll(@Param("workArray2")Integer [] workArray2);
    List<InvestmentInstitutionsDto>findRecommendInvestor(@Param("workArray3")String [] workArray3);

    /**
     * 根据投递记录id获取机构id数组
     * @param workArray1
     * @return
     */
    List<Integer>serachSendProjectIdZ(@Param("workArray1")List<Integer> workArray1);

    /**
     * 获取最近活跃机构
     * @param startPage 开始数量
     * @param pageSize 每页显示数量
     * @return
     */
    List<InvestmentInstitutionsDto> findRecentlyActiveInstitution(@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
    /**
     * 获取增加完之后的自增长id
     * @return
     */
    Integer getLastInsertId();

    /**
     * 筛选机构的方法
     * @param startPage 开始数量
     * @param pageSize 每页显示数量
     * @param domains 领域
     * @param stages 阶段
     * @param types 类型
     * @return
     */
    List<InvestmentInstitutions> filterInvestmentInstitution(@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize,
                                                             @Param("domains") String[] domains,@Param("stages") String[] stages,
                                                             @Param("types") Integer types);

    List<Integer> selectUserApprovalInstitution(@Param("userId") Integer userId);
}