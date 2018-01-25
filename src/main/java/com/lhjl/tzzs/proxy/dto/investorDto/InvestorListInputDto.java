package com.lhjl.tzzs.proxy.dto.investorDto;

public class InvestorListInputDto {
	/**
     * 模糊搜索关键字
     */
    private String keyWords;

    /**
     * 此处根据实际情况可考虑将列值传递过来
     * 但是应该考虑到一个问题，就是如何将该列和排序的标志位结合起来
     * private String column;
     */

    /**
     * 排序方式：升序asc;降序desc
     * 如果单纯的传递这一个值的话 那肯定是根据某列进行排序，则Mapper.xml直接将该字段写死
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
    /**
     * 定义其他的搜索查询字段
     */
}
