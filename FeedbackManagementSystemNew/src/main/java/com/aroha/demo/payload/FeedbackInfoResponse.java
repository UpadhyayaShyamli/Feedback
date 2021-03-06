package com.aroha.demo.payload;

public class FeedbackInfoResponse {
	
	private boolean status;
	private String statusMessage;
	private int statusCode;
	private FeedbackResponseData data;
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
	public FeedbackResponseData getData() {
		return data;
	}
	public void setData(FeedbackResponseData data) {
		this.data = data;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
