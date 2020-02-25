package com.aroha.demo.payload;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aroha.demo.model.Application;

public class ApplicationPayloadResponse {
	
	private int appId;
	private String appName;
	private int groupId;
	private String groupName;
	
	
	public ApplicationPayloadResponse(int appId, String appName, int groupId, String groupName) {
		super();
		this.appId = appId;
		this.appName = appName;
		this.groupId = groupId;
		this.groupName = groupName;
	}
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
	
}
