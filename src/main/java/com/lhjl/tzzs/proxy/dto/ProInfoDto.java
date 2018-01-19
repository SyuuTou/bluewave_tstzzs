package com.lhjl.tzzs.proxy.dto;

/**
 * report相关的项目信息
 * @author IdataVC
 *
 */
public class ProInfoDto {
	/**
	 * 项目id
	 */
	private Integer id;
	/**
	 * 项目简称
	 */
	private String shortName;
	/**
	 * 项目城市
	 */
	private String city;
	/**
	 * 一句话简介
	 */
	private String kernelDesc;
	/**
	 * 项目领域
	 */
	private String segmentation;  
	/**
	 * 项目logo
	 */
	private String projectLogo;
	/**
	 * 醒目融资状态
	 */
	private String stage;
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getKernelDesc() {
		return kernelDesc;
	}
	public void setKernelDesc(String kernelDesc) {
		this.kernelDesc = kernelDesc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getSegmentation() {
		return segmentation;
	}
	public void setSegmentation(String segmentation) {
		this.segmentation = segmentation;
	}
	public String getProjectLogo() {
		return projectLogo;
	}
	public void setProjectLogo(String projectLogo) {
		this.projectLogo = projectLogo;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	@Override
	public String toString() {
		return "ProInfoDto [id=" + id + ", shortName=" + shortName + ", kernelDesc=" + kernelDesc + ", segmentation="
				+ segmentation + ", projectLogo=" + projectLogo + ", stage=" + stage + "]";
	}
	
}
