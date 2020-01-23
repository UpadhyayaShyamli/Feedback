package com.aroha.demo.payload;

import com.aroha.demo.model.RoleName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class SignInRequest {
	
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String usernameOrEmail;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private RoleName roles;
	private String name;
//	private int companyId;
//	private String companyName;
	private String status;
	/**
	 * @return the usernameOrEmail
	 */
	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}
	/**
	 * @param usernameOrEmail the usernameOrEmail to set
	 */
	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the roles
	 */
	public RoleName getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(RoleName roles) {
		this.roles = roles;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
//	/**
//	 * @return the companyId
//	 */
//	public int getCompanyId() {
//		return companyId;
//	}
//	/**
//	 * @param companyId the companyId to set
//	 */
//	public void setCompanyId(int companyId) {
//		this.companyId = companyId;
//	}
//	/**
//	 * @return the companyName
//	 */
//	public String getCompanyName() {
//		return companyName;
//	}
//	/**
//	 * @param companyName the companyName to set
//	 */
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}	
	
	
}
