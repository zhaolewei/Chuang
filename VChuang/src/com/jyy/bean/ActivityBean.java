package com.jyy.bean;

public class ActivityBean {

	private int id;
	private String title;
	private String location;
	private String time;
	private String pic_url;
	private int pic;
	
	public ActivityBean(int id, String title, String location, String time, String icon_url) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.time = time;
		this.pic_url = icon_url;
		
	}
	public ActivityBean(int id, String title, String location, String time, int pic) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.time = time;
		this.pic = pic;
		
	}
	public ActivityBean(){
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
	public void setPic_url(String icon_url) {
		this.pic_url = icon_url;
	}
	public int getPic() {
		return pic;
	}
	public void setPic(int pic) {
		this.pic = pic;
	}
	
}
