package com.lhjl.tzzs.proxy.dto.angeltoken;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RedEnvelopeResDto {

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
    private List<RedEnvelopeLogDto> redEnvelopeLogs;

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
