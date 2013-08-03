package com.badmintonbuddy.models;

import java.io.Serializable;
import java.util.List;


public class CourtResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean success;
	private List<Court> courts;
	
	

	public List<Court> getCourts() {
		return courts;
	}

	public void setCourts(List<Court> courts) {
		this.courts = courts;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}


	
	public class Court implements Serializable{
	
		
	public Court(String ph, String name, String area) {
			super();
			this.ph = ph;
			this.name = name;
			this.area = area;
		}
	private String ph;
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	private String name;
	private String area;
	
	
	}
	
}
