package com.badmintonbuddy.models;

import java.io.Serializable;
import java.util.List;


public class AreaResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean success;
	private List<CourtArea> area;
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}


	public List<CourtArea> getArea() {
		return area;
	}


	public void setArea(List<CourtArea> area) {
		this.area = area;
	}
	
	public class CourtArea implements Serializable{
	
		public CourtArea(int id, String name) {
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
