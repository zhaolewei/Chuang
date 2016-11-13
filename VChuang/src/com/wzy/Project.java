package com.wzy;

import java.util.Arrays;

public class Project {
	
	private String name;                 //项目名称
	private String url;                  //项目网址
	private String introduction;         //项目简介
	private String stage;                //项目阶段
	private String area;                 //项目领域
	private String city;                 //项目所在城市
	private String description;          //项目详细描述
    private byte[] picPath;              //项目logo地址
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public byte[] getPicPath() {
		return picPath;
	}
	public void setPicPath(byte[] picPath) {
		this.picPath = picPath;
	}
	@Override
	public String toString() {
		return "Project [name=" + name + ", url=" + url + ", introduction="
				+ introduction + ", stage=" + stage + ", area=" + area
				+ ", city=" + city + ", description=" + description
				+ ", picPath=" + Arrays.toString(picPath) + "]";
	}
	public Project(String name, String url, String introduction, String stage,
			String area, String city, String description, byte[] picPath) {
		super();
		this.name = name;
		this.url = url;
		this.introduction = introduction;
		this.stage = stage;
		this.area = area;
		this.city = city;
		this.description = description;
		this.picPath = picPath;
	}
	public Project() {
		super();
	}
	
	
	
}
