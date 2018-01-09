package com.lhjl.tzzs.proxy.dto;

public class InterviewCommentDto {
	//约谈记录的id
	private Integer id;
	
	//约谈的批注信息
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "InterviewCommentDto [id=" + id + ", comment=" + comment + "]";
	}
	
	
}
