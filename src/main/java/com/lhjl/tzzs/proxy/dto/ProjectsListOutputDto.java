package com.lhjl.tzzs.proxy.dto;

import java.util.Date;

public class ProjectsListOutputDto {
	/**
	 * 项目的唯一标识id
	 */
	private Integer id;
	/**
	 * 项目编号
	 */
	private Integer serialNumber;
	/**
	 * 项目简称
	 */
	private String shortName;
	/**
	 * 一句话介绍
	 */
	private String kernelDesc;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 项目来源
	 * 0表示创始人提交，1表示投资人提交
	 */
	private Integer projectSource;
	
	/**
	 * 项目等级
	 * 0表示D级，1表示C级，2表示B级，3表示A级，4表示S级'
	 */
	private Integer ratingStage;
	/**
	 * 融资状态
	 * 改值返回为null字段的时候表示正在融A轮
	 */
	private String stage;
	/**
	 * 关注量
	 */
	private Integer focusCount;
	/**
	 * 约谈量 
	 */
	private Integer interviewCount;
	/**
	 * 浏览量 暂时无**********
	 */
	private Integer viewCount;
	
	/**
	 * 跟进状态 暂时无**********
	 */
	private Integer followStatus;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	
}
