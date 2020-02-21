package com.aroha.demo.payload;

import java.util.List;

import com.aroha.demo.model.Role;
import com.aroha.demo.model.Users;

public class AddUserPayload {
	
	private int appId;
	private int groupId;
	private List<Users>user;
	
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
	public List<Users> getUser() {
		return user;
	}
	public void setUser(List<Users> user) {
		this.user = user;
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
