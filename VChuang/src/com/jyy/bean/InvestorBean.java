package com.jyy.bean;

public class InvestorBean {
	/**
	 * 投资人信息
	 */
	private int id;
	private String name;  //投资人名字
	private String introduce;  //投资人介绍
	private int harvest_num;  //收货的项目数
	private String icon_url;  //头像
	
	private String information; //投资人信息 （手机等）  

	
	public InvestorBean() {
		super();
	}
	public InvestorBean(int id, String name, String introduce,
			int harvest_num, String icon_url, String information) {
		super();
		this.id = id;
		this.name = name;
		this.introduce = introduce;
		this.harvest_num = harvest_num;
		this.icon_url = icon_url;
		this.information = information;
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

	

	public int getHarvest_num() {
		return harvest_num;
	}
	public void setHarvest_num(int harvest_num) {
		this.harvest_num = harvest_num;
	}
	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	
}
