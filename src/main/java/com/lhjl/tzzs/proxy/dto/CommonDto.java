package com.lhjl.tzzs.proxy.dto;


public class CommonDto<T> {

    private String message;
    private Integer status ;
    private T data;

    public CommonDto(){

    }

    public CommonDto(T data, String message, Integer status){
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "CommonDto [message=" + message + ", status=" + status + ", data=" + data + "]";
	}
    
}
