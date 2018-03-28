package com.lhjl.tzzs.proxy.dto;

import java.util.List;
/**
 * 分页输出DTO模板
 * @author IdataVC
 *
 * @param <T>
 */
public class PagingOutputDto<T> {
	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 每页显示的记录数
	 */
	private Integer pageSize;
	/**
	 * 总记录数
	 */
	private Long total;
	/**
	 * 返回的具体列表数据
	 */
	private List<T> list;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
