package com.jyy.bean;

public class ProjectBean {

	/**
	 * 项目bean
	 */
	private int id;
	private String title;   //项目的标题
	private String introduce;  //项目的介绍
	private String location;  //项目人所在地
	private int zan_count;  //点赞人数
	private String logo_url;  //logo url
	
	private String content; //项目详细内容
	
	
	
	public ProjectBean() {
		super();
	}
	public ProjectBean(int id, String title, String introduce, String location,
			int zan_count, String logo_url, String content) {
		super();
		this.id = id;
		this.title = title;
		this.introduce = introduce;
		this.location = location;
		this.zan_count = zan_count;
		this.logo_url = logo_url;
		this.content = content;
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
	public int getZan_count() {
		return zan_count;
	}
	public void setZan_count(int zan_count) {
		this.zan_count = zan_count;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
