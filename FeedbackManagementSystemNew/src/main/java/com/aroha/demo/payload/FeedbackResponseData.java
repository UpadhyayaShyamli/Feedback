package com.aroha.demo.payload;

public class FeedbackResponseData {
	
	private int appId;
	private int groupId;
	private String feedbackGivenBy;
	private long feedbackGivenByuserId;
	private String feedbackInfo;
	
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getFeedbackGivenBy() {
		return feedbackGivenBy;
	}
	public void setFeedbackGivenBy(String feedbackGivenBy) {
		this.feedbackGivenBy = feedbackGivenBy;
	}
	public String getFeedbackInfo() {
		return feedbackInfo;
	}
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}
	public long getFeedbackGivenByuserId() {
		return feedbackGivenByuserId;
	}
	public void setFeedbackGivenByuserId(long userId) {
		this.feedbackGivenByuserId = userId;
	}
	
}
