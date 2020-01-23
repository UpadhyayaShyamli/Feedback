package com.aroha.demo.model;

import com.aroha.demo.config.DateAudit;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "feedback_details")
public class Feedback implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(name = "feedback_given_by")
    @NotNull
    private String feedbackGivenBy;
    @Column(name = "feedback_given_by_userId")
    @NotNull
    private Long feedbackGivenByuserId;
    @Column(name = "feedbackinfo")
    @NotNull
    private String feedbackinfo;
    @Column(name = "to_whom_feedbackgiven")
    @NotNull
    private String toWhomFeedbackgiven;
    
    @Column(name = "created_on")
    @NotNull
    private String createdOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appId")
    private Application appId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="groupId")
    private Group group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackGivenBy() {
        return feedbackGivenBy;
    }

    public void setFeedbackGivenBy(String feedbackGivenBy) {
        this.feedbackGivenBy = feedbackGivenBy;
    }

    public String getFeedbackinfo() {
        return feedbackinfo;
    }

    public void setFeedbackinfo(String feedbackinfo) {
        this.feedbackinfo = feedbackinfo;
    }

    public String getToWhomFeedbackgiven() {
        return toWhomFeedbackgiven;
    }

    public void setToWhomFeedbackgiven(String toWhomFeedbackgiven) {
        this.toWhomFeedbackgiven = toWhomFeedbackgiven;
    }

    public Application getAppId() {
        return appId;
    }

    public void setAppId(Application appId) {
        this.appId = appId;
    }

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Long getFeedbackGivenByuserId() {
		return feedbackGivenByuserId;
	}

	public void setFeedbackGivenByuserId(long feedbackGivenByuserId) {
		this.feedbackGivenByuserId = feedbackGivenByuserId;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
}
