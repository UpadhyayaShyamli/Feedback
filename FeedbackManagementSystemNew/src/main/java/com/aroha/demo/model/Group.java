package com.aroha.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aroha.demo.config.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appGroup")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private String groupName;
    private String createdOn;

    @ManyToOne
    @JoinColumn(name = "applicationId")
    private Application app;
    
    @OneToMany(mappedBy = "group")
    private Collection<Feedback>feed=new ArrayList<>();

    public Group() {
        super();
    }

    public Group(int groupId, String groupName) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @ManyToMany(mappedBy = "groups")
    private Set<Users> user = new HashSet<>();

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }
    
   
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
   
    
    public Application getApp() {
        return app;
    }
  
    public void setApp(Application app) {
        this.app = app;
    }
    
    public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	@JsonIgnore
	public Set<Users> getUser() {
        return user;
    }
	@JsonIgnore
    public void setUser(Set<Users> user) {
        this.user = user;
    }

	@JsonIgnore
	public Collection<Feedback> getFeed() {
		return feed;
	}
	@JsonIgnore
	public void setFeed(Collection<Feedback> feed) {
		this.feed = feed;
	}
  
}
