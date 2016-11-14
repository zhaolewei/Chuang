package com.xj;

public class MyInfo {
	private String username;// 用户名
	private int age;// 年龄
	private String sex;// 性别
	private String type;// 类型
	private String company;// 公司
	private String phonenum;// 手机话
	private String email;// email
	private String job;// 工作
	private String city;// 城市
	private String address;// 地址

	@Override
	public String toString() {
		return "MyInfo [username=" + username + ", age=" + age + ", sex=" + sex
				+ ", type=" + type + ", company=" + company + ", phonenum="
				+ phonenum + ", email=" + email + ", job=" + job + ", city="
				+ city + ", address=" + address + "]";
	}

	public MyInfo(String username, int age, String sex, String type,
			String company, String phonenum, String email, String job,
			String city, String address) {
		super();
		this.username = username;
		this.age = age;
		this.sex = sex;
		this.type = type;
		this.company = company;
		this.phonenum = phonenum;
		this.email = email;
		this.job = job;
		this.city = city;
		this.address = address;
	}

	public MyInfo() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
