package com.aroha.demo.payload;

/**
 * @author Shyamli | 02-Jan-2020
 *
 */
public class AppPayload {
	private String status;
	private Object data;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
