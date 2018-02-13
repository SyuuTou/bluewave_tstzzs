package com.lhjl.tzzs.proxy.dto.angeltoken;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties
public class RedEnvelopeDto {

    /** 单个可领取金额 */
    private BigDecimal amount;
    /** 总金额 */
    private BigDecimal totalAmount;
    /** 可领取数量 */
    private Integer quantity;
    /** 红包描述 */
    private String message;
    /** 当前用户token */
    private String token;
    /** 红包类型；0：为定额红包，1：为随机红包 */
    private Integer redEnvelopeType;
    /** 红包类型描述；INVITATIONED：邀请红包 ，QUOTAED：定额红包，RANDOMED：随机红包*/
    private String description;
    /**   */
    private Integer currency;

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRedEnvelopeType() {
        return redEnvelopeType;
    }

    public void setRedEnvelopeType(Integer redEnvelopeType) {
        this.redEnvelopeType = redEnvelopeType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
