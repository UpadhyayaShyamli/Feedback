package com.aroha.demo.payload;

import java.util.List;

public class FeedbackData {
	private String feedback;
	private String feedbackSender;
	private String dateAndtime;
	private String statusMessage;
	private List<String> comment;
    private String feedbackSenderName;
	
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getFeedbackSender() {
		return feedbackSender;
	}
	public void setFeedbackSender(String feedbackSender) {
		this.feedbackSender = feedbackSender;
	}
	public String getDateAndtime() {
		return dateAndtime;
	}
	public void setDateAndtime(String dateAndtime) {
		this.dateAndtime = dateAndtime;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<String> getComment() {
		return comment;
	}
	public void setComment(List<String> comment) {
		this.comment = comment;
	}
	public String getFeedbackSenderName() {
		return feedbackSenderName;
	}
	public void setFeedbackSenderName(String feedbackSenderName) {
		this.feedbackSenderName = feedbackSenderName;
	}	
}
