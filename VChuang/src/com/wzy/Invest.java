package com.wzy;

public class Invest {
	
	private String title;
	private String create_time;
	private String city;
	private String imgurl;
	private String end_time;
	private String start_time;
	private String master;
	private String introduction;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "Invest [title=" + title + ", create_time=" + create_time
				+ ", city=" + city + ", imgurl=" + imgurl + ", end_time="
				+ end_time + ", start_time=" + start_time + ", master="
				+ master + ", introduction=" + introduction + "]";
	}
	public Invest(String title, String create_time, String city, String imgurl,
			String end_time, String start_time, String master,
			String introduction) {
		super();
		this.title = title;
		this.create_time = create_time;
		this.city = city;
		this.imgurl = imgurl;
		this.end_time = end_time;
		this.start_time = start_time;
		this.master = master;
		this.introduction = introduction;
	}
	public Invest() {
		super();
	}

	
	
	
	

}
