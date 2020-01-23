package com.aroha.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "created_on")
    private String createdOn;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "user_email_id")
    private String userEmailId;
    @Column(name = "user_name")
    private String userName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_id"))
    private Set<Application> applicationCollection = new HashSet<Application>();

    public Users() {
    }

    public Users(Long userId) {
        this.userId = userId;
    }

    public Users(String password, String userEmailId, String userName) {
        super();
        this.password = password;
        this.userEmailId = userEmailId;
        this.userName = userName;
    }
    
    public Users(Long userId,String userName,String userEmailId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmailId = userEmailId;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }
    @JsonIgnore
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @JsonIgnore
    public Set<Group> getGroups() {
        return groups;
    }
    @JsonIgnore
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
    
    public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@JsonIgnore
    public Set<Application> getApplicationCollection() {
        return applicationCollection;
    }
    @JsonIgnore
    public void setApplicationCollection(Set<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", createdOn=" + createdOn + ", mobileNumber=" + mobileNumber + ", password="
				+ password + ", userEmailId=" + userEmailId + ", userName=" + userName + ", roles=" + roles
				+ ", groups=" + groups + ", applicationCollection=" + applicationCollection + "]";
	}
}
