package com.example.web.control.total;

import org.hibernate.validator.constraints.NotEmpty;



public class LoginForm {

	@NotEmpty
	private String userid;
	@NotEmpty
	private String passwd;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userID) {
		this.userid = userID;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
