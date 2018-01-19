package com.lhjl.tzzs.proxy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lhjl.tzzs.proxy.model.MetaColumn;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

    private String eventName; // 事件名称
    private String eventType; // LIKE_IT ：点赞, TURN_AROUND ： 约谈, RECOMMEND ： 推荐, CONCERN :关注, COMMENT:评论，GET_HISTORY：获取历史信息
    private String fromUser;
    private String toUser;
    private List<Integer> projectIds;
    private String projectLevel;
    private List<Integer> investmentInstitutionsIds;
    private String message;  // 点赞：项目ID，
    private Integer startIndex;
    private Integer endIndex;
    private Date dateTime;
    private List<MetaColumn> columnList;


    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<Integer> getInvestmentInstitutionsIds() {
        return investmentInstitutionsIds;
    }

    public void setInvestmentInstitutionsIds(List<Integer> investmentInstitutionsIds) {
        this.investmentInstitutionsIds = investmentInstitutionsIds;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public List<Integer> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Integer> projectIds) {
        this.projectIds = projectIds;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public List<MetaColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<MetaColumn> columnList) {
        this.columnList = columnList;
    }

	@Override
	public String toString() {
		return "EventDto [eventName=" + eventName + ", eventType=" + eventType + ", fromUser=" + fromUser + ", toUser="
				+ toUser + ", projectIds=" + projectIds + ", projectLevel=" + projectLevel
				+ ", investmentInstitutionsIds=" + investmentInstitutionsIds + ", message=" + message + ", startIndex="
				+ startIndex + ", endIndex=" + endIndex + ", dateTime=" + dateTime + ", columnList=" + columnList + "]";
	}
    
}
