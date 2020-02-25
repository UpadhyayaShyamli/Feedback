package com.aroha.demo.payload;

import java.util.ArrayList;
import java.util.List;

public class ExternalAppData {
	
	private boolean status;
	private String statusMessage;
	private ApplicationListData data;
	
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
	public ApplicationListData getData() {
		return data;
	}
	public void setData(ApplicationListData data) {
		this.data = data;
	}
	
}
