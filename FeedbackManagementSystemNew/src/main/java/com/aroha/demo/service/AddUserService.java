package com.aroha.demo.service;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Group;
import com.aroha.demo.model.Role;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.UserGroup;
import com.aroha.demo.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {

	@Autowired
	private AppService appServie;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

//	@Autowired
//	private RoleService roleService;
	
	@Autowired
	private GroupRepository groupRepo;
	

	public UserGroup addUserGroup(int appId, int groupId, List<Users>userId) {
		
		UserGroup ugObj = new UserGroup();
		ArrayList<Users>user = userService.getUsers(userId);
		ArrayList<String>userlist=new ArrayList<>();
		ArrayList<String>unsecc=new ArrayList<>();
		for(Users userObj:user) {
			Integer userExists = groupRepo.checkUserExists(userObj.getUserId(), appId, groupId);
			System.out.println("hhhhhh"+userObj.getUserId());
			if(!(userExists>0))
			{
				
				userlist.add(userObj.getUserName());
				
				
				Optional<Application> app = appServie.findApplication(appId);
				Application appObj = app.get();
				if (!app.isPresent()) {
					throw new RuntimeException("Application " + appObj.getAppName() + " not found");
				} else {
					userObj.getApplicationCollection().add(appObj);
				}
				Optional<Group> group = groupService.getGroup(groupId);
				Group groupObj = group.get();
				if (!group.isPresent()) {
					throw new RuntimeException("Group " + groupObj.getGroupName() + " not found");
				} else {
					userObj.getGroups().add(groupObj);
				}
				try {
					userService.saveUser(userObj);
//					ugObj.setAppId(appId);
//					ugObj.setGroupId(groupId);
//					ugObj.setUserId(userId);
					ugObj.setSuccessStatus("User "+userlist.toString()+" added successfully");
					
				} catch (Exception ex) 
				{
					ugObj.setSuccessStatus(ex.getMessage());
					return ugObj;
				}
			}
			else
			{
				unsecc.add(userObj.getUserName());
//				ugObj.setAppId(appId);
//				ugObj.setGroupId(groupId);
//				ugObj.setUserId(userId);
				ugObj.setUnSuccessStatus("user "+unsecc.toString()+" already exists in same group and app");
				continue;
			}
		}
		return ugObj;
	}
}
