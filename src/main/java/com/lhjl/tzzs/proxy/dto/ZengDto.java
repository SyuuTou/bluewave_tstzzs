package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

public class ZengDto {
	private String uuids;
	private String sKey;
	private BigDecimal qj;
	public String getUuids() {
		return uuids;
	}
	public void setUuids(String uuids) {
		this.uuids = uuids;
	}
	public String getsKey() {
		return sKey;
	}
	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

	public BigDecimal getQj() {
		return qj;
	}

	public void setQj(BigDecimal qj) {
		this.qj = qj;
	}
}
