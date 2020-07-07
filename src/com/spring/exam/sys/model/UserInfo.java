package com.spring.exam.sys.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserInfo {
	private String fullname;
	private String email;
	private String phone;
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	private String username;
	private String password;
	private String avata;

	public UserInfo() {
		super();
	}

	public UserInfo(String fullname, String email, String phone, String address, Date birthday, String username,
			String password, String avata) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
		this.avata = avata;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getAvata() {
		return avata;
	}

	public void setAvata(String avata) {
		this.avata = avata;
	}

	@Override
	public String toString() {
		return "UserInfo [fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", birthday=" + birthday + ", username=" + username + ", password=" + password + ", avata=" + avata
				+ "]";
	}

	

}
