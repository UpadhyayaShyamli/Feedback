package com.aroha.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aroha.demo.payload.UserSummary;
import com.aroha.demo.security.CurrentUser;
import com.aroha.demo.security.UserPrincipal;
import com.aroha.demo.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/me")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')") // just for example
	public ResponseEntity<UserSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getAuthorities());
		return ResponseEntity.ok(userSummary);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<?> getUser()
	{
		if(userService.getAllUsers().isEmpty())
		{
			return ResponseEntity.ok("No User Found");
		}
		return ResponseEntity.ok( userService.getUser());
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?>getAllUsers()
	{
		if(userService.getAllUsers().isEmpty()){
			return ResponseEntity.ok("No User Found");
		}
		return ResponseEntity.ok(userService.getAllUsers());		
	}
}
