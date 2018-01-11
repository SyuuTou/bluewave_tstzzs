package com.lhjl.tzzs.proxy.dto;

public class UpdateModifyInputDto {
	//约谈id
	private Integer id;
	//约谈状态
	private Integer status;
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
	@Override
	public String toString() {
		return "UpdateModifyInputDto [id=" + id + ", status=" + status + "]";
	}
	
}
