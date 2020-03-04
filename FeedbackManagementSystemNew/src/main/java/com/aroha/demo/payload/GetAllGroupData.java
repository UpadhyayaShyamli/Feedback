package com.aroha.demo.payload;

import java.util.List;

import com.aroha.demo.model.Group;

public class GetAllGroupData {
	
	private int statusCode;
	private String Statusmessage;
	private boolean status;
	private List<Group> data;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusmessage() {
		return Statusmessage;
	}
	public void setStatusmessage(String statusmessage) {
		Statusmessage = statusmessage;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<Group> getData() {
		return data;
	}
	public void setData(List<Group> data) {
		this.data = data;
	}	
}
