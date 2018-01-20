package com.lhjl.tzzs.proxy.dto;

public class ProjectsUpdateInputDto {
	/**
	 * 项目id
	 */
	private Integer id;
	/**
	 * 项目的跟进状态
	 */
	private Integer status;

	/**跟进状态描述*/
	private String description;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProjectsUpdateInputDto [id=" + id + ", status=" + status + "]";
	}
	
}
