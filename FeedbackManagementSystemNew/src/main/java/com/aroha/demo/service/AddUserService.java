package com.aroha.demo.service;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Group;
import com.aroha.demo.model.Role;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.AddUserPayload;
import com.aroha.demo.payload.AddUserPayloadObject;
import com.aroha.demo.payload.Data;
import com.aroha.demo.payload.UserGroup;
import com.aroha.demo.repository.GroupRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {

	@Autowired
	private AppService appServie;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private GroupRepository groupRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public AddUserPayloadObject addUserdata(AddUserPayload addUserPayload) 
	{	
		int appId = addUserPayload.getAppId();
		int groupId = addUserPayload.getGroupId();
		List<Users>list=addUserPayload.getUser();
		Data d=new Data();
		boolean flag=false;
		AddUserPayloadObject load=new AddUserPayloadObject();
		for(Users user :list) {
			Boolean isUsersExists=userService.existsByEmail(user.getUserEmailId());
			if(!isUsersExists) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
				Date date = new Date();
				user.setCreatedOn(sdf.format(date));
				user.setPassword(passwordEncoder.encode(user.getMobileNumber()));
				Application app=appServie.findApplication(appId).get();
				Group group=groupService.getGroup(groupId).get();
				Role role=roleService.findRoles(2).get();
				user.getApplicationCollection().add(app);
				user.getGroups().add(group);
				user.getRoles().add(role);				
				try {
					flag=true;
					userService.saveUser(user);
					load.setStatus(true);
					Set<String>listObject=d.getNewEmployees();
					listObject.add(user.getUserName());
					d.setNewEmployees(listObject);
					d.setMessage("Data Saved");
					load.setData(d);
				}catch(Exception e) {
					load.setStatus(false);
					load.setException(e.getMessage());
				}

			}else {
				Users getUserObj=userService.findUsers(user.getUserEmailId());
				Integer isInApp = userService.isUserPresentInApp(getUserObj.getUserId(), appId);
				if(isInApp==0) {
					try {
						flag=true;
						userService.saveUserApp(getUserObj.getUserId(), appId);
						Set<String>listObject=d.getNewEmployees();
						listObject.add(user.getUserName());
						d.setNewEmployees(listObject);
						d.setMessage("User succesfully added into group and app");
						load.setData(d);
						load.setStatus(true);
						load.setUserDataUpdate("Data Updated");
					}catch(Exception e) {
						load.setUserDataUpdate(e.getMessage());
					}
				}

				else {
					if(!flag) {
					d.setMessage("User already exist in same App and Group");
					load.setData(d);
					load.setStatus(false);
					load.setUserDataUpdate("No Upadated data");
					}
				}
				Integer isInGroup = userService.isUserPresentInGroup(getUserObj.getUserId(), groupId);
				if(isInGroup==0) {
					try {
						flag=true;
						userService.saveUserGroup(getUserObj.getUserId(), groupId);
						Set<String>listObject=d.getNewEmployees();
						listObject.add(user.getUserName());
						d.setNewEmployees(listObject);
						d.setMessage("User succesfully added into group and app");
						load.setData(d);
						load.setStatus(true);
						load.setUserDataUpdate("Data Updated");
					}catch(Exception e) {
						load.setUserDataUpdate(e.getMessage());
					}
				}
				
				else {
					if(!flag) {
					d.setMessage("User already exist in same group and app");
					load.setData(d);
					load.setStatus(false);
					load.setUserDataUpdate("No Upadated data");
					}
				}
			}
		}
		return load;
	}
}
