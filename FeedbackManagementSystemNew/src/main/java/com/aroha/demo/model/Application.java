package com.aroha.demo.model;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.aroha.demo.config.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "appTable")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appId;
    private String appName;
    private String createdOn;

    @OneToMany(mappedBy = "app")
    private List<Group> group = new ArrayList<>();

    @ManyToMany(mappedBy = "applicationCollection", fetch = FetchType.LAZY)
    private Set<Users> userCollection = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appId", fetch = FetchType.LAZY)
    private Collection<Feedback> feedbackObj = new ArrayList<>();

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    @JsonIgnore
    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    @JsonIgnore
    public Set<Users> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Set<Users> userCollection) {
        this.userCollection = userCollection;
    }

    @JsonIgnore
    public Collection<Feedback> getFeedbackObj() {
        return feedbackObj;
    }

    public void setFeedbackObj(Collection<Feedback> feedbackObj) {
        this.feedbackObj = feedbackObj;
    }

}
