package com.aroha.demo.payload;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private List<String> newEmployees=new ArrayList<>();
	private String message;

	public List<String> getNewEmployees() {
		return newEmployees;
	}

	public void setNewEmployees(List<String> newEmployees) {
		this.newEmployees = newEmployees;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
