package com.lhjl.tzzs.proxy.dto;

public class CommentAndHighlightsOutputDto {
	/**
	 * 简介
	 */
	private String comment;
	/**
	 * 投资亮点
	 */
	private String investmentHighlights;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getInvestmentHighlights() {
		return investmentHighlights;
	}
	public void setInvestmentHighlights(String investmentHighlights) {
		this.investmentHighlights = investmentHighlights;
	}
	
}
