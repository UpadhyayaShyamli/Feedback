package com.aroha.demo.payload;

public class UserGroupResponse 
{
	private boolean status;
	private String statusMessage;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}	
}
