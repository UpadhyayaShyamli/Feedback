package com.aroha.demo.payload;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Feedback;
import com.aroha.demo.model.Group;

public class FeedbackPayload {

    private int appId;
    private int groupId;
    private String userNameorEmail;
    private Feedback feedback;
    private String feedbackinfo;
    private Application application;
    private Group group;
    private String feedbackGivenBy;
    private String createdOn;
    private String statusMessage;
    
    public FeedbackPayload(String feedbackinfo, String feedbackGivenBy,String createdOn) {
		super();
		this.feedbackinfo = feedbackinfo;
		this.feedbackGivenBy = feedbackGivenBy;
		this.createdOn = createdOn;
	}
    
	public FeedbackPayload() {
		super();
	}

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

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getUserNameorEmail() {
        return userNameorEmail;
    }

    public void setUserNameorEmail(String userNameorEmail) {
        this.userNameorEmail = userNameorEmail;
    }

	public String getFeedbackinfo() {
		return feedbackinfo;
	}

	public void setFeedbackinfo(String feedbackinfo) {
		this.feedbackinfo = feedbackinfo;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getFeedbackGivenBy() {
		return feedbackGivenBy;
	}

	public void setFeedbackGivenBy(String feedbackGivenBy) {
		this.feedbackGivenBy = feedbackGivenBy;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
}
