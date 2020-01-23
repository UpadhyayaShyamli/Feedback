package com.aroha.demo.payload;

import java.util.List;

import com.aroha.demo.model.Users;

public class UserGroup {

    private int appId;
    private int groupId;
    private List<Users> userId;
    private String successStatus;
    private String unSuccessStatus;

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

	public List<Users> getUserId() {
		return userId;
	}

	public void setUserId(List<Users> userId) {
		this.userId = userId;
	}

	public String getSuccessStatus() {
		return successStatus;
	}

	public void setSuccessStatus(String successStatus) {
		this.successStatus = successStatus;
	}

	public String getUnSuccessStatus() {
		return unSuccessStatus;
	}

	public void setUnSuccessStatus(String unSuccessStatus) {
		this.unSuccessStatus = unSuccessStatus;
	}	
}
