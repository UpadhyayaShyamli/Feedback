package com.aroha.demo.payload;

import com.aroha.demo.model.Group;

public class CreateGroup {
	
	private int appId;
	private Group groupObj;
	private String status;
	
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public Group getGroupObj() {
		return groupObj;
	}
	public void setGroupObj(Group groupObj) {
		this.groupObj = groupObj;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
