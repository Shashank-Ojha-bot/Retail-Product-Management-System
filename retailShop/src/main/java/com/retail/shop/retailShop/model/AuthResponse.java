package com.retail.shop.retailShop.model;



public class AuthResponse {

	private String userId;

	private String userName;

	private boolean isValid;

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

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "AuthResponse [userId=" + userId + ", userName=" + userName + ", isValid=" + isValid + "]";
	}

	
}
