package com.lhjl.tzzs.proxy.dto.angeltoken;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RedEnvelopeResDto {

    private String redEnvelopeID;
    private String token;
    private String neckName;
    private String headPic;
    private BigDecimal amount;
    private String message;
    private String status; //
    private Integer totalQuantity;
    private Integer quantity;
    private String description;
    private Date createTime;
    private BigDecimal totalAmount;
    private String companyName;
    private String companyDuties;
    private List<RedEnvelopeLogDto> redEnvelopeLogs;
    private String currency;


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRedEnvelopeID() {
        return redEnvelopeID;
    }

    public void setRedEnvelopeID(String redEnvelopeID) {
        this.redEnvelopeID = redEnvelopeID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNeckName() {
        return neckName;
    }

    public void setNeckName(String neckName) {
        this.neckName = neckName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<RedEnvelopeLogDto> getRedEnvelopeLogs() {
        return redEnvelopeLogs;
    }

    public void setRedEnvelopeLogs(List<RedEnvelopeLogDto> redEnvelopeLogs) {
        this.redEnvelopeLogs = redEnvelopeLogs;
    }
}
