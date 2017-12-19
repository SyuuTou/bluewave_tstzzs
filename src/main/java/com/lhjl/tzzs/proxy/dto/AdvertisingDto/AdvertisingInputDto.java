package com.lhjl.tzzs.proxy.dto.AdvertisingDto;

public class AdvertisingInputDto {
    /**
     * 位置id
     */
    private Integer positionId;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 编辑完成状态
     */
    private Integer editStatus;
    /**
     * 隐藏状态
     */
    private Integer hides;
    /**
     * 是否验证时间是否符合当前时间
     */
    private Integer timeYn;
    /**
     * 开始时间上限
     */
    private String startTime;
    /**
     * 开始时间下限
     */
    private String endTime;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(Integer editStatus) {
        this.editStatus = editStatus;
    }

    public Integer getHides() {
        return hides;
    }

    public void setHides(Integer hides) {
        this.hides = hides;
    }

    public Integer getTimeYn() {
        return timeYn;
    }

    public void setTimeYn(Integer timeYn) {
        this.timeYn = timeYn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
