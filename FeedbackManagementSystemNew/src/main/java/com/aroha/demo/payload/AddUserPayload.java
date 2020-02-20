package com.aroha.demo.payload;

import java.util.List;

import com.aroha.demo.model.Role;
import com.aroha.demo.model.Users;

public class AddUserPayload {
	
	private int appId;
	private int groupId;
	private List<Users>user;
	
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

}
