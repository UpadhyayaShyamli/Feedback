package com.aroha.demo.payload;

import java.util.List;

public class GetOnlyGroupDataResponse {
	
	private int statusCode;
	private String statusMessage;
	private boolean status;
	private List<GetOnlyGroupData> data;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<GetOnlyGroupData> getData() {
		return data;
	}
	public void setData(List<GetOnlyGroupData> data) {
		this.data = data;
	}
	
}
