package com.aroha.demo.payload;

public class OwnFeedbackPayload {
	
	private int feedbackId;
	private String feedbackInfo;
	private String toWhom;
	private String dateAndtime;
	
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getFeedbackInfo() {
		return feedbackInfo;
	}
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}
	public String getToWhom() {
		return toWhom;
	}
	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}
	public String getDateAndtime() {
		return dateAndtime;
	}
	public void setDateAndtime(String dateAndtime) {
		this.dateAndtime = dateAndtime;
	}
}
