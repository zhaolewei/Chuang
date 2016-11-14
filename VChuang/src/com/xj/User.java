package com.xj;

public class User {
	private String username_user;// 用户名
	private String password_user;
	private int age_user;// 年龄
	private String sex_user;// 性别
	private String type_user;// 类型
	private String company_user;// 公司
	private String phonenum_user;// 手机话
	private String email_user;// email
	private String job_user;// 工作
	private String city_user;// 城市
	private String address_user;// 地址

	public String getUsername_user() {
		return username_user;
	}

	public void setUsername_user(String username_user) {
		this.username_user = username_user;
	}

	public String getPassword_user() {
		return password_user;
	}

	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}

	public int getAge_user() {
		return age_user;
	}

	public void setAge_user(int age_user) {
		this.age_user = age_user;
	}

	public String getSex_user() {
		return sex_user;
	}

	public void setSex_user(String sex_user) {
		this.sex_user = sex_user;
	}

	public String getType_user() {
		return type_user;
	}

	public void setType_user(String type_user) {
		this.type_user = type_user;
	}

	public String getCompany_user() {
		return company_user;
	}

	public void setCompany_user(String company_user) {
		this.company_user = company_user;
	}

	public String getPhonenum_user() {
		return phonenum_user;
	}

	public void setPhonenum_user(String phonenum_user) {
		this.phonenum_user = phonenum_user;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public String getJob_user() {
		return job_user;
	}

	public void setJob_user(String job_user) {
		this.job_user = job_user;
	}

	public String getCity_user() {
		return city_user;
	}

	public void setCity_user(String city_user) {
		this.city_user = city_user;
	}

	public String getAddress_user() {
		return address_user;
	}

	public void setAddress_user(String address_user) {
		this.address_user = address_user;
	}

	@Override
	public String toString() {
		return "User [username_user=" + username_user + ", password_user="
				+ password_user + ", age_user=" + age_user + ", sex_user="
				+ sex_user + ", type_user=" + type_user + ", company_user="
				+ company_user + ", phonenum_user=" + phonenum_user
				+ ", email_user=" + email_user + ", job_user=" + job_user
				+ ", city_user=" + city_user + ", address_user=" + address_user
				+ "]";
	}

	public User(String username_user, String password_user, int age_user,
			String sex_user, String type_user, String company_user,
			String phonenum_user, String email_user, String job_user,
			String city_user, String address_user) {
		super();
		this.username_user = username_user;
		this.password_user = password_user;
		this.age_user = age_user;
		this.sex_user = sex_user;
		this.type_user = type_user;
		this.company_user = company_user;
		this.phonenum_user = phonenum_user;
		this.email_user = email_user;
		this.job_user = job_user;
		this.city_user = city_user;
		this.address_user = address_user;
	}
	
	public User(String username_user){
		super();
		this.username_user = username_user;
	}

}
