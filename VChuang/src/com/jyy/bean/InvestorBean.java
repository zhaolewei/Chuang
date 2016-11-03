package com.jyy.bean;

public class InvestorBean {

	private int id;
	private String name;
	private String introduce;
	private String harvestnum;
	private int icon;
	private String icon_url;
	
	
	
	public InvestorBean(int id, String name, String introduce, String harvestnum, int icon) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.harvestnum = harvestnum;
		this.icon = icon;
	}
	public InvestorBean(int id, String name, String introduce,  String harvestnum, String icon_url) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.harvestnum = harvestnum;
		this.icon_url = icon_url;
	}
	public InvestorBean(){
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getHarvestnum() {
		return harvestnum;
	}
	public void setHarvestnum(String harvestnum) {
		this.harvestnum = harvestnum;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	
}
