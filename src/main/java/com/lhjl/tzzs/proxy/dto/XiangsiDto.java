package com.lhjl.tzzs.proxy.dto;

/**
 * Created by zyy on 2017/11/15.
 */
public class XiangsiDto {
    private Integer ID;
    private String short_name;
    private String kernel_desc;
    private String city;
    private String segementation;
    private String project_logo;
    private String stage;
    private String evaluation_recommend;
    
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getEvaluation_recommend() {
		return evaluation_recommend;
	}
	public void setEvaluation_recommend(String evaluation_recommend) {
		this.evaluation_recommend = evaluation_recommend;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getKernel_desc() {
		return kernel_desc;
	}
	public void setKernel_desc(String kernel_desc) {
		this.kernel_desc = kernel_desc;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSegementation() {
		return segementation;
	}
	public void setSegementation(String segementation) {
		this.segementation = segementation;
	}
	public String getProject_logo() {
		return project_logo;
	}
	public void setProject_logo(String project_logo) {
		this.project_logo = project_logo;
	}

}
