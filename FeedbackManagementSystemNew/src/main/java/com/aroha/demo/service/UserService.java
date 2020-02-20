package com.aroha.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.aroha.demo.model.Role;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.SignInRequest;
import com.aroha.demo.payload.SignUpRequest;
import com.aroha.demo.repository.RoleRepository;
import com.aroha.demo.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    public Users checkEmail(SignInRequest loginRequest) {
        return userRepo.findByuserEmailId(loginRequest.getUsernameOrEmail());
    }

    public long findRoles(long userId) {
        return userRepo.findRoles(userId);
    }

    public Users saveUser(Users user) {
        return userRepo.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByuserEmailId(email);
    }

    public ArrayList<Users> getUsers(List<Users>userId) {
    	//Optional<Users> list2 =null;
    	ArrayList<Users>list2=new ArrayList<>();
    	for(int i=0;i<userId.size();i++)
    	{
    		Users userObj = userId.get(i);
    		Users list = userRepo.findById(userObj.getUserId()).orElseThrow(()->
    		new RuntimeException("User with Id "+userObj.getUserId()+" not found"));
    		System.out.println("------uSERiD-------"+userObj.getUserId());
    		//return list;
    		list2.add(list);
    	}
		return list2;
    }
    
    public Users findUsers(String email) {
    	return userRepo.findByuserEmailId(email);
    }
    
    public String saveUsers(SignUpRequest signup) {

		//int appId=signup.getAppId();
		//System.out.println("AppId: "+appId);
		long roleId=signup.getRoleId();
		Users userObj=new Users();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		Date date = new Date();
		String usercreating_date_time = sdf.format(date);
		//int userId = apppayload.getUserId();
		userObj.setCreatedOn(usercreating_date_time);
		userObj.setUserEmailId(signup.getUserEmailId());
		userObj.setPassword(passwordEncoder.encode(signup.getPassword()));
		userObj.setMobileNumber(signup.getMobileNumber());
		userObj.setUserName(signup.getUserName());
		//Optional<Application> app=appRepo.findById(appId);
		Optional<Role> role=roleRepo.findById(roleId);
//		if(!app.isPresent()) {
//			throw new RuntimeException("Application with id "+appId+" is not found");
//		}
//		else
//		{
//			Application appObj=app.get();
//			userObj.getApplicationCollection().add(appObj);
//		}
		
		if(!role.isPresent()) {
			throw new RuntimeException("Role with id "+roleId+" is not found");
		}
		else
		{
			Role roleObj=role.get();
			userObj.getRoles().add(roleObj);
		}
		
		userRepo.save(userObj);
		return "User added successfully";
	}
    
    public Users getAllUser(String email){
       return userRepo.findByuserEmailId(email);
    }
    
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    } 
    
   public List<Users> getUser()
   {
	   List<Users> u = userRepo.getUsers();
	   System.out.println("size is "+u.size());
	   return u;
	   //return 
   }
}
