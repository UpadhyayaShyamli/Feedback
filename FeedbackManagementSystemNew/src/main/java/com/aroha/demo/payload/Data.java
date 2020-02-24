package com.aroha.demo.payload;

import java.util.HashSet;
import java.util.Set;

public class Data {
	
	private Set<String> newEmployees=new HashSet<>();
	private String message;
	
	public Set<String> getNewEmployees() {
		return newEmployees;
	}

	public void setNewEmployees(Set<String> newEmployees) {
		this.newEmployees = newEmployees;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
