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

//	public UserGroup addUserGroup(int appId, int groupId, List<Users>userId) {
//
//		UserGroup ugObj = new UserGroup();
//		ArrayList<Users>user = userService.getUsers(userId);
//		ArrayList<String>userlist=new ArrayList<>();
//		ArrayList<String>unsecc=new ArrayList<>();
//		for(Users userObj:user) {
//			Integer userExists = groupRepo.checkUserExists(userObj.getUserId(), appId, groupId);
//			System.out.println("hhhhhh"+userObj.getUserId());
//			if(!(userExists>0))
//			{
//
//				userlist.add(userObj.getUserName());
//
//
//				Optional<Application> app = appServie.findApplication(appId);
//				Application appObj = app.get();
//				if (!app.isPresent()) {
//					throw new RuntimeException("Application " + appObj.getAppName() + " not found");
//				} else {
//					userObj.getApplicationCollection().add(appObj);
//				}
//				Optional<Group> group = groupService.getGroup(groupId);
//				Group groupObj = group.get();
//				if (!group.isPresent()) {
//					throw new RuntimeException("Group " + groupObj.getGroupName() + " not found");
//				} else {
//					userObj.getGroups().add(groupObj);
//				}
//				try {
//					userService.saveUser(userObj);
//					//					ugObj.setAppId(appId);
//					//					ugObj.setGroupId(groupId);
//					//					ugObj.setUserId(userId);
//					ugObj.setSuccessStatus("User "+userlist.toString()+" added successfully");
//
//				} catch (Exception ex) 
//				{
//					ugObj.setSuccessStatus(ex.getMessage());
//					return ugObj;
//				}
//			}
//			else
//			{
//				unsecc.add(userObj.getUserName());
//				//				ugObj.setAppId(appId);
//				//				ugObj.setGroupId(groupId);
//				//				ugObj.setUserId(userId);
//				ugObj.setUnSuccessStatus("user "+unsecc.toString()+" already exists in same group and app");
//				continue;
//			}
//		}
//		return ugObj;
//	}
	
	
	public AddUserPayloadObject addUserdata(AddUserPayload addUserPayload) 
	{	
		int appId = addUserPayload.getAppId();
		int groupId = addUserPayload.getGroupId();
		List<Users>list=addUserPayload.getUser();
		Data d=new Data();
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
					userService.saveUser(user);
					load.setStatus(true);
					List<String>listObject=d.getNewEmployees();
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
						userService.saveUserApp(getUserObj.getUserId(), appId);
						load.setUserDataUpdate("Data Updated");
					}catch(Exception e) {
						load.setUserDataUpdate(e.getMessage());
					}
				}
				Integer isInGroup = userService.isUserPresentInGroup(getUserObj.getUserId(), groupId);
				if(isInGroup==0) {
					try {
						userService.saveUserGroup(getUserObj.getUserId(), groupId);
						load.setUserDataUpdate("Data Updated");
					}catch(Exception e) {
						load.setUserDataUpdate(e.getMessage());
					}
				}
			}
		}
		return load;
	}


//	public AddUserPayload addUserdata(AddUserPayload addUserPayload) 
//	{
//		AddUserPayload ap = new AddUserPayload();
//		int appId = addUserPayload.getAppId();
//		int groupId = addUserPayload.getGroupId();
//		List<Users>list=addUserPayload.getUser();
//		ArrayList<String>userlist=new ArrayList<>();
//		ArrayList<String>unsecc=new ArrayList<>();``````
//		//ArrayList<Users>userObj = userService.getUsers(list);
//		System.out.println("App id: "+appId+" \n groupId "+groupId);
//		for(Users user:list) {
//			Boolean inExists=userService.existsByEmail(user.getUserEmailId());
//			System.out.println("Is user exists:  "+inExists);
//			Users getUser = userService.findUsers(user.getUserEmailId());
//			//Integer userExists = groupRepo.checkUserExistsinAppGroup(user.getUserEmailId(), appId, groupId);
//			//System.out.println("------------------"+userExists);
//			if(!inExists) {
//				System.out.println("----------  i am here 1 ---------");
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//				Date date = new Date();
//				String userAddingDatetime = sdf.format(date);
//				user.setCreatedOn(userAddingDatetime);
//				user.setPassword(user.getMobileNumber());
//				Optional<Role>roleObj=roleService.findRoles(2);
//				Role r=roleObj.get();
//				user.getRoles().add(r);
//				Optional<Application> app = appServie.findApplication(appId);
//				Application appObj = app.get();
//				if (!app.isPresent()) {
//					throw new RuntimeException("Application " + appObj.getAppName() + " not found");
//				} else {
//					user.getApplicationCollection().add(appObj);
//				}
//				Optional<Group> group = groupService.getGroup(groupId);
//				Group groupObj = group.get();
//				if (!group.isPresent()) {
//					throw new RuntimeException("Group " + groupObj.getGroupName() + " not found");
//				} else {
//					user.getGroups().add(groupObj);
//				}
//				try {
//					userService.saveUser(user);	
//					
//					//ugObj.setSuccessStatus("User "+userlist.toString()+" added successfully");
//
//				} catch (Exception ex) 
//				{
//					ap.setSuccessStatus(ex.getMessage());
//					return ap;
//				}
//				
//			}
//			
//			else if(inExists)
//			{
//				Integer userExists = groupRepo.checkUserExistsinAppGroup(user.getUserEmailId(), appId, groupId);
//				if(!(userExists>0)) {
//				userlist.add(user.getUserName());
//				Optional<Application> app = appServie.findApplication(appId);
//				Application appObj = app.get();
//				if (!app.isPresent()) {
//					throw new RuntimeException("Application " + appObj.getAppName() + " not found");
//				} else {
//					user.getApplicationCollection().add(appObj);
//				}
//				Optional<Group> group = groupService.getGroup(groupId);
//				Group groupObj = group.get();
//				if (!group.isPresent()) {
//					throw new RuntimeException("Group " + groupObj.getGroupName() + " not found");
//				} else {
//					user.getGroups().add(groupObj);
//				}
//				try {
//					userService.saveUser(user);
//					ap.setSuccessStatus("User "+userlist.toString()+" added successfully");
//
//				} catch (Exception ex) 
//				{
//					ap.setSuccessStatus(ex.getMessage());
//					return ap;
//				}
//				}
//			}
//			
//			else
//			{
//				unsecc.add(user.getUserName());
//				//				ugObj.setAppId(appId);
//				//				ugObj.setGroupId(groupId);
//				//				ugObj.setUserId(userId);
//				ap.setUnSuccessStatus("user "+unsecc.toString()+" already exists in same group and app");
//				continue;
//			}
//		}
//		return ap;
//	}
}
