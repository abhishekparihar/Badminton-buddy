package com.badmintonbuddy.models;

import java.io.Serializable;
import java.util.List;

import com.badmintonbuddy.models.AreaResult.CourtArea;


public class BuddyResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean success;
	private CourtArea area;
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}


	public CourtArea getArea() {
		return area;
	}


	public void setArea(CourtArea area) {
		this.area = area;
	}
	
	public class User implements Serializable{
	
		public User(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
	private int id;
	private String name;
	
	
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
	}
	
}
