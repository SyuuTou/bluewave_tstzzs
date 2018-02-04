package com.lhjl.tzzs.proxy.dto.angeltoken;

import java.math.BigDecimal;

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
