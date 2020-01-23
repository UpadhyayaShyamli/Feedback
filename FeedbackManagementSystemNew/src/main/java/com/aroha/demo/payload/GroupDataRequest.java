package com.aroha.demo.payload;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Group;
import com.aroha.demo.model.Users;

public class GroupDataRequest {

	private int appId;
	private int groupId;
	private Application application;
	private Group group;
	private String groupName;
	private Users user;

	public GroupDataRequest(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public GroupDataRequest(int groupId) {
		super();
		this.groupId = groupId;
	}

	public GroupDataRequest() {
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
