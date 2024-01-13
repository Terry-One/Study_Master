package com.ddb.entity;

import java.util.Date;

/**
*@author 段道博
*@date 2021年1月18日下午2:22:08
*
*/
public class User {
	
	private int user_id;
	
	private String name;
	
	private String email;
	
	private String passwd;
	
	private String nick_name;
	
	private String sex;
	
	private Integer age;
	
	private String phone;
	
	private int is_admin;
	
	private Date create_time;
	
	private Date update_time;
	
	private int status;

	public int getId() {
		return user_id;
	}

	public void setId(int id) {
		this.user_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nick_name;
	}

	public void setNickname(String nickname) {
		this.nick_name = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsadmin() {
		return is_admin;
	}

	public void setIsadmin(int isadmin) {
		this.is_admin = isadmin;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + user_id + ", name=" + name + ", email=" + email + ", passwd=" + passwd + ", nickname="
				+ nick_name + ", sex=" + sex + ", age=" + age + ", phone=" + phone + ", isadmin=" + is_admin
				+ ", create_time=" + create_time + ", update_time=" + update_time + ", status=" + status + "]";
	}
	
}
