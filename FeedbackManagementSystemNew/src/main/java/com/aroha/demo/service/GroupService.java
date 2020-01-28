package com.aroha.demo.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aroha.demo.model.Application;
import com.aroha.demo.model.Group;
import com.aroha.demo.payload.CreateGroup;
import com.aroha.demo.payload.GroupDataRequest;
import com.aroha.demo.payload.GroupPayload;
import com.aroha.demo.payload.UserGroupResponse;
import com.aroha.demo.repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private AppService appService;
    @Autowired
    private GroupRepository groupRepo;

    public CreateGroup createGroup(int appId, Group group) {

        Boolean isExists = appService.checkAppId(appId);
        //Boolean isGroupName = groupRepo.existsBygroupName(group.getGroupName());
        Integer groupExists=groupRepo.checkGroupExists(group.getGroupName(), appId);
        CreateGroup cgObj= new CreateGroup();
        if (isExists) {
            if (!(groupExists>0)) {
                Optional<Application> app = appService.findApplication(appId);
                Application appObj = app.get();
                group.setApp(appObj);
                appObj.getGroup().add(group);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        		Date date = new Date();
        		String groupcreating_date_time = sdf.format(date);
        		group.setCreatedOn(groupcreating_date_time);
                //Instant instant=Instant.ofEpochMilli(new Date().getTime());
        		try {
	                groupRepo.save(group);
	                cgObj.setStatus("Group Created");
	                cgObj.setAppId(appId);
	                cgObj.setGroupObj(group);
                }catch(Exception ex) {
                	cgObj.setStatus(ex.getMessage());
                }
                return cgObj;
            } else {
            	cgObj.setStatus("Group " + group.getGroupName() + " already present");
                return cgObj;
            }
        }
        cgObj.setStatus("Application with id " + appId + " not present");
        return cgObj;
    }

    public List<Group> getAllGroup() {
        return groupRepo.findAll();
    }

    public List<GroupPayload> showGroupbyAppId(int appId) {

        List<Group> group = groupRepo.showAllGroup(appId);
        ArrayList<GroupPayload> list = new ArrayList<>();
//        if(group.size()<=0)
//        {
//        	GroupPayload groupObj = new GroupPayload();
//        	groupObj.setStatus("no group exists in that appId");
//        	//groupObj.setData(groupObj);
//        	list.add(groupObj);
//        }
        Iterator<Group> itr = group.iterator();
        while (itr.hasNext()) {
            Group gObj = itr.next();
            GroupPayload groupObj = new GroupPayload();
            groupObj.setGroupId(gObj.getGroupId());
            groupObj.setGroupName(gObj.getGroupName());
            groupObj.setStatus("Show group by appId displayed successfully");
            //groupObj.setData(groupObj);
            list.add(groupObj);
        }
        return list;
    }
    
    public Optional<Group> getGroup(int groupId){
        return groupRepo.findById(groupId);
    }
    
    public List<GroupDataRequest> findGroup(long userId){
    	return groupRepo.showGroup(userId);
    }
    
//    public int isUserExistsinGroup(int groupId) {
//    	int userCount = groupRepo.checkUserExists(groupId);
//    	return userCount;
//    }
    public UserGroupResponse checkuserExistsInGroup(int groupId)
    {
    	int userCount = groupRepo.checkUserExists(groupId);
    	UserGroupResponse ugResponse = new UserGroupResponse();
    	
    	if(userCount==0)
    	{
    		ugResponse.setStatus(false);
    		ugResponse.setStatusMessage("No User Exists");
    	}
    	
    	else
    	{
    		ugResponse.setStatus(true);
    		ugResponse.setStatusMessage("User Exists");
    	}
    	
    	return ugResponse;
    }
  
}
