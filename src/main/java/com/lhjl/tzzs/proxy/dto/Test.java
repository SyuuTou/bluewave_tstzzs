package com.lhjl.tzzs.proxy.dto;

import java.util.Arrays;

public class Test{
	private String name;
	private Integer[] segs;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getSegs() {
		return segs;
	}

	public void setSegs(Integer[] segs) {
		this.segs = segs;
	}

	@Override
	public String toString() {
		return "Test [name=" + name + ", segs=" + Arrays.toString(segs) + "]";
	}


}