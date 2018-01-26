package com.lhjl.tzzs.proxy.dto.investorDto;

import java.util.Date;

public class InvestorListInputDto {
	/**
     * 模糊搜索关键字
     */
    private String keyWords;
    /**
     * 开始时间字符串
     */
    private String startTimeStr;
    /**
     * 开始时间
     * 用于接受开始时间字符串的转换
     */
    private Date startTime;
    /**
     * 结束时间字符串
     */
    private String endTimeStr;
    /**
     * 结束时间
     * 用于结束时间字符串的转换
     */
    private Date endTime;
    /**
     * 单笔投资金额最低值（人民币）
     */
    private Integer amountBeginRmb;
    /**
     * 单笔投资金额最高值（人民币）
     */
    private Integer amountEndRmb;
    /**
     * 单笔投资金额最低值（美元）
     */
    private Integer amountBeginDollar;
    /**
     * 单笔投资金额最高值（美元）
     */
    private Integer amountEndDollar;
    
  //以下是筛选字段
    /**
     * 负责人
     */
    /**
     * 来源类型
     */
    /**
     * 合作等级
     */
    /**
     * 机构分类
     */
    /**
     * 基金币种
     */
    /**
     * 默认按照更新时间进行排序
     * 此处根据实际情况可考虑将列值传递过来
     * 但是应该考虑到一个问题，就是如何将该列和排序的标志位结合起来
     */
    private String column;

    /**
     * 排序方式：升序asc;降序desc
     * 如果单纯的传递这一个值的话 那肯定是根据某列进行排序，则Mapper.xml直接将排序字段写死即可
     * 
     */
    private String order;
    
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
   
    
}
