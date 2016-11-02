package com.jyy.bean;

public class ProjectBean {

	private int id;
	private String title;
	private String introduce;
	private String location;
	private String support;
	private String logo_url;
	private int logo;
	
	
	
	public ProjectBean(int id, String title, String introduce, String location, String support, String logo_url) {
		super();
		this.id = id;
		this.title = title;
		this.introduce = introduce;
		this.location = location;
		this.support = support;
		this.logo_url = logo_url;
	}
	public ProjectBean(int id, String title, String introduce, String location, String support, int logo) {
		super();
		this.id = id;
		this.title = title;
		this.introduce = introduce;
		this.location = location;
		this.support = support;
		this.logo = logo;
	}
	public ProjectBean() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public int getLogo() {
		return logo;
	}
	public void setLogo(int logo) {
		this.logo = logo;
	}
}
