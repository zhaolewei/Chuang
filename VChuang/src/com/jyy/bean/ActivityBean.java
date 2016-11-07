package com.jyy.bean;

public class ActivityBean {
	/**
	 * 活动bean
	 */
	private int id;  //id
	private String title;  //活动的标题
	private String location;  //活动的地点
	private String time;  //活动时间
	private String pic_url;  //活动海报url
	
	private String content; //活动内容
	
	
	public ActivityBean() {
		super();
	}
	public ActivityBean(int id, String title, String location, String time,
			String pic_url, String content) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.time = time;
		this.pic_url = pic_url;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
