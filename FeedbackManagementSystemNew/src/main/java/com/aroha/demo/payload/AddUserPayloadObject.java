package com.aroha.demo.payload;

public class AddUserPayloadObject {
	
	private Boolean status;
	private Data data;
	private String exception;
	private String userDataUpdate;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getUserDataUpdate() {
		return userDataUpdate;
	}
	public void setUserDataUpdate(String userDataUpdate) {
		this.userDataUpdate = userDataUpdate;
	}

}
