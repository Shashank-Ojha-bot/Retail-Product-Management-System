package com.cognizant.authorizationService.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "userdata")
public class UserData {

	@Id
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "userName")
	private String userName;

	@Column(name = "userPassword")
	private String password;

	
	@Transient
	private String authToken;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAuthToken() {
		return authToken;
	}


	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}


	public UserData(String userId, String userName, String password, String authToken) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.authToken = authToken;
	}

	public UserData() {
		
	}


	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", userName=" + userName + ", password=" + password + ", authToken="
				+ authToken + "]";
	}
	
	
}
