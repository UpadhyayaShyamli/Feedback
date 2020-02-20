package com.aroha.demo.service;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Group;
import com.aroha.demo.model.Role;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.AddUserPayload;
import com.aroha.demo.payload.UserGroup;
import com.aroha.demo.repository.GroupRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	@Autowired
	private RoleService roleService;

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


	public String addUserdata(AddUserPayload addUserPayload) 
	{	
		int appId = addUserPayload.getAppId();
		int groupId = addUserPayload.getGroupId();
		List<Users>list=addUserPayload.getUser();
		//ArrayList<Users>userObj = userService.getUsers(list);
		System.out.println("App id: "+appId+" \n groupId "+groupId);
		for(Users user:list) {
			Boolean inExists=userService.existsByEmail(user.getUserEmailId());
			System.out.println("Is user exists:  "+inExists);
			Users getUser = userService.findUsers(user.getUserEmailId());
			Integer userExists = groupRepo.checkUserExistsinAppGroup(user.getUserEmailId(), appId, groupId);
			System.out.println("------------------"+userExists);
			if(!inExists) {
				System.out.println("----------  i am here 1 ---------");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
				Date date = new Date();
				String userAddingDatetime = sdf.format(date);
				user.setCreatedOn(userAddingDatetime);
				user.setPassword(user.getMobileNumber());
				Optional<Role>roleObj=roleService.findRoles(2);
				Role r=roleObj.get();
				user.getRoles().add(r);
				Optional<Application> app = appServie.findApplication(appId);
				Application appObj = app.get();
				if (!app.isPresent()) {
					throw new RuntimeException("Application " + appObj.getAppName() + " not found");
				} else {
					user.getApplicationCollection().add(appObj);
				}
				Optional<Group> group = groupService.getGroup(groupId);
				Group groupObj = group.get();
				if (!group.isPresent()) {
					throw new RuntimeException("Group " + groupObj.getGroupName() + " not found");
				} else {
					user.getGroups().add(groupObj);
				}
				try {
					userService.saveUser(user);	
					
					//ugObj.setSuccessStatus("User "+userlist.toString()+" added successfully");

				} catch (Exception ex) 
				{
					return ex.getMessage();
					//ugObj.setSuccessStatus(ex.getMessage());
					//return ugObj;
				}
				
			}
			
			else if(inExists && !(userExists>0))
			{
				//userlist.add(userObj.getUserName());


				Optional<Application> app = appServie.findApplication(appId);
				Application appObj = app.get();
				if (!app.isPresent()) {
					throw new RuntimeException("Application " + appObj.getAppName() + " not found");
				} else {
					user.getApplicationCollection().add(appObj);
				}
				Optional<Group> group = groupService.getGroup(groupId);
				Group groupObj = group.get();
				if (!group.isPresent()) {
					throw new RuntimeException("Group " + groupObj.getGroupName() + " not found");
				} else {
					user.getGroups().add(groupObj);
				}
				try {
					
					//					ugObj.setAppId(appId);
					//					ugObj.setGroupId(groupId);
					//					ugObj.setUserId(userId);
					//ugObj.setSuccessStatus("User "+userlist.toString()+" added successfully");

				} catch (Exception ex) 
				{
					return ex.getMessage();
				}
			}
		}
		return "saved";
	}
}
