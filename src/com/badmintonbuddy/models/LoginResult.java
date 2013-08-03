package com.badmintonbuddy.models;


public class LoginResult  {
	
	private Boolean success;
    private String auth_key;
    private String phone_number;
    private String name;
    private String email;
    private String message;
    
    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getAuth_key() {
		return auth_key;
	}
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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
}
