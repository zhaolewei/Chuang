package com.xj;

public class MyShouCang {
    private String picPath;

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	

	@Override
	public String toString() {
		return "MyShouCang [picPath=" + picPath + "]";
	}

	public MyShouCang(String picPath) {
		super();
		this.picPath = picPath;
	}
 
}
