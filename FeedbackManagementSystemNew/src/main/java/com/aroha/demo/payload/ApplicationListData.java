package com.aroha.demo.payload;

import java.util.ArrayList;
import java.util.List;

public class ApplicationListData {
	
	private List<ApplicationPayloadResponse> appList = new ArrayList<>();

	public List<ApplicationPayloadResponse> getAppList() {
		return appList;
	}

	public void setAppList(List<ApplicationPayloadResponse> appList) {
		this.appList = appList;
	}
	
}
