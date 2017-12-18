package com.lhjl.tzzs.proxy.dto;

public class CommonTotal<T> extends CommonDto<T> {
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
