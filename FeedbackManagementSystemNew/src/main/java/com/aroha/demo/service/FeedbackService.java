package com.aroha.demo.service;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Feedback;
import com.aroha.demo.model.FeedbackComent;
import com.aroha.demo.model.Group;
import com.aroha.demo.model.Role;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.FeedbackComentPayload;
import com.aroha.demo.payload.FeedbackData;
import com.aroha.demo.payload.FeedbackInfoResponse;
import com.aroha.demo.payload.FeedbackPayload;
import com.aroha.demo.payload.FeedbackResponseData;
import com.aroha.demo.payload.OwnFeedbackPayload;
import com.aroha.demo.repository.FeedbackComentRepository;
import com.aroha.demo.repository.FeedbackRepository;
import com.aroha.demo.repository.UserRepository;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedRepo;
    @Autowired
    private AppService appService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService; 
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private FeedbackComentRepository feedComentRepo;
    @Autowired
	private RoleService roleService;
    @Autowired
	PasswordEncoder passwordEncoder;

    public FeedbackInfoResponse saveFeedback(int appId, int groupId, String email, Feedback feedback) {
    	//FeedbackPayload feedPayload = new FeedbackPayload();
    	FeedbackResponseData frd = new FeedbackResponseData();
    	FeedbackInfoResponse feedInfoRes = new FeedbackInfoResponse();
    	Users user=userRepo.findByuserEmailId(email);
    	long userId=user.getUserId();
        Optional<Application> app = appService.findApplication(appId);
        Optional<Group> group = groupService.getGroup(groupId);
        if (!app.isPresent()) {
            throw new RuntimeException("Application with id " + appId + " not found");
        }
        if (!group.isPresent()) {
            throw new RuntimeException("Group with id " + groupId + " not found");
        }
        Application appObj = app.get();
        Group groupObj = group.get();
        String toWhomFeedbackgiven=groupObj.getGroupName();
        //Instant instant=Instant.ofEpochMilli(new Date().getTime());
        //feedback.setCreatedAt(instant);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        Date date = new Date();
        String feedbackgiving_date_time = sdf.format(date);
        feedback.setCreatedOn(feedbackgiving_date_time);
        feedback.setFeedbackGivenBy(email);
        feedback.setAppId(appObj);
        feedback.setGroup(groupObj);
        feedback.setFeedbackGivenByuserId(userId);
        feedback.setToWhomFeedbackgiven(toWhomFeedbackgiven);
        groupObj.getFeed().add(feedback);
        appObj.getFeedbackObj().add(feedback);
        try {
	        feedRepo.save(feedback);
	        frd.setAppId(appId);
	        frd.setGroupId(groupId);
	        frd.setFeedbackGivenBy(feedback.getFeedbackGivenBy());
	        frd.setFeedbackGivenByuserId(feedback.getFeedbackGivenByuserId());
	        frd.setFeedbackInfo(feedback.getFeedbackinfo());
	        feedInfoRes.setStatus(true);
	        feedInfoRes.setStatusMessage("Feedback Given successfully to-> "+groupObj.getGroupName());
	        feedInfoRes.setData(frd);
        }catch (Exception e) {
        	feedInfoRes.setStatusMessage(e.getMessage());
		}
        return feedInfoRes;
    }
    
//    public List<FeedbackData> showFeedbackforUser(String email) {
//        Users user = userService.findUsers(email);
//        long userId = user.getUserId();
//        System.out.println("User Id: " + userId);
//        List<GroupDataRequest> groupObj = groupService.findGroup(userId);
//        FeedbackComent comment = new FeedbackComent();
//        ArrayList<FeedbackData> list = new ArrayList<>();
//        List<String>listObj=new ArrayList<>();
//        
//        for (GroupDataRequest g : groupObj) {
//            //    		System.out.println("GroupId is: "+g.getGroupId());
//            List<FeedbackPayload> feedObj = feedRepo.showFeedback(g.getGroupId());
//            Iterator<FeedbackPayload> itr = feedObj.iterator();
//            while (itr.hasNext()) {
//                FeedbackPayload obj = itr.next();
//                System.out.println("--------Feedback Id:--------" + obj.getId());
//                FeedbackData feedback = new FeedbackData();
//                List<FeedbackComent> commentObj = feedComentRepo.findByfeedbackId(obj.getId());
//                if (!commentObj.isEmpty()) {
//                	for(FeedbackComent fcObj:commentObj) {
//                   // FeedbackComent comObj = commentObj.get(fcObj);
//                		listObj.add(fcObj.getComent());
//                	}
//                	
//                	feedback.setComment(listObj);
//                }
//                Users getUser = userService.findUsers(obj.getFeedbackGivenBy());
//                feedback.setFeedback(obj.getFeedbackinfo());
//                feedback.setFeedbackSender(obj.getFeedbackGivenBy());
//                feedback.setDateAndtime(obj.getCreatedOn());
//                feedback.setFeedbackSenderName(getUser.getUserName());
//                list.add(feedback);
//            }
//        }
//        return list;
//    }
//    
    public ArrayList<FeedbackData> showFeedbackForadmin(int appId) {
        List<Feedback> list = feedRepo.showFeedbackForAdmin(appId);
        ArrayList<FeedbackData> listObj = new ArrayList<>();
        Iterator<Feedback> itr = list.iterator();
        while (itr.hasNext()) {
            Feedback obj = itr.next();
            System.out.println("Feedback id is: " + obj.getId());
            FeedbackData feedback = new FeedbackData();
            Users getUser = userService.findUsers(obj.getFeedbackGivenBy());
            feedback.setFeedbackId(obj.getId());
            feedback.setFeedback(obj.getFeedbackinfo());
            feedback.setFeedbackSender(obj.getFeedbackGivenBy());
            feedback.setDateAndtime(obj.getCreatedOn());
            feedback.setFeedbackSenderName(getUser.getUserName());
            //feedback.setStatusMessage("Feedback displayed successfully");
            listObj.add(feedback);
        }
        return listObj;
    }
    
    public List<FeedbackData>showFeedback(String email,int appId)
    {
    	Users user = userService.findUsers(email);
        long userId = user.getUserId();
        System.out.println("User Id: " + userId);
        List<Integer> getgroupObj = groupService.getGroupId(appId, userId);
        //FeedbackComent comment = new FeedbackComent();
        ArrayList<FeedbackData> list = new ArrayList<>();
        //List<String>listObj=new ArrayList<>();
        for (Integer i : getgroupObj) {
            //    		System.out.println("GroupId is: "+g.getGroupId());
            List<FeedbackPayload> feedObj = feedRepo.showFeedback(i);
            Iterator<FeedbackPayload> itr = feedObj.iterator();
          
            while (itr.hasNext()) {
                FeedbackPayload obj = itr.next();
                System.out.println("Feedback Id:" + obj.getId());
                FeedbackData feedback = new FeedbackData();
                Users getUser = userService.findUsers(obj.getFeedbackGivenBy());
                feedback.setFeedbackId(obj.getId());
                feedback.setFeedback(obj.getFeedbackinfo());
                feedback.setFeedbackSender(obj.getFeedbackGivenBy());
                feedback.setDateAndtime(obj.getCreatedOn());
                feedback.setFeedbackSenderName(getUser.getUserName());
                list.add(feedback);
            }
        }
        return list;
    }
    
    public FeedbackComentPayload savefeedbackComent(int feedbackId,String coment)
    {
    	Optional<Feedback> feed = feedRepo.findById(feedbackId);
    	Feedback feedObj = feed.get();
    	int feedId = feedObj.getId();
    	FeedbackComent feedComent = new FeedbackComent();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		Date date = new Date();
		String date_time = sdf.format(date);
    	feedComent.setFeedbackId(feedId);
    	feedComent.setComent(coment);
    	feedComent.setDateAndTime(date_time);
    	FeedbackComentPayload feedComentPayload = new FeedbackComentPayload();
    	try {
	    	feedComentRepo.save(feedComent);
	    	feedComentPayload.setStatusMessage("coment saved");
    	}catch (Exception e) {
			feedComentPayload.setStatusMessage(e.getMessage());
		}
    	return feedComentPayload;
    }
    
   
    
    public List<FeedbackComent>getComent(int feedbackId)
    {
    	return feedComentRepo.showComent(feedbackId);
    }
    
    public List<OwnFeedbackPayload> showOwnGivenFeedback(String email,int appId)
    {
    	List<Feedback> getOwnGivenfeedback = feedRepo.showOwnFeedback(email, appId);
    	ArrayList<OwnFeedbackPayload> ofPayloadList = new ArrayList<>();
    	for(Feedback f:getOwnGivenfeedback)
    	{
    		OwnFeedbackPayload ofPayload = new OwnFeedbackPayload();
    		ofPayload.setFeedbackId(f.getId());
    		ofPayload.setFeedbackInfo(f.getFeedbackinfo());
    		ofPayload.setToWhom(f.getToWhomFeedbackgiven());
    		ofPayload.setDateAndtime(f.getCreatedOn());
    		ofPayloadList.add(ofPayload);
    	}
    	
    	return ofPayloadList;
    }
    
    public ArrayList<OwnFeedbackPayload> showOwnFeedbackForadmin(int appId) {
        List<Feedback> list = feedRepo.showFeedbackForAdmin(appId);
        ArrayList<OwnFeedbackPayload> ofPayloadList = new ArrayList<>();
        Iterator<Feedback> itr = list.iterator();
        while (itr.hasNext()) {
            Feedback f = itr.next();
            System.out.println("Feedback id is: " + f.getId());
            OwnFeedbackPayload ofPayload = new OwnFeedbackPayload();
            ofPayload.setFeedbackId(f.getId());
    		ofPayload.setFeedbackInfo(f.getFeedbackinfo());
    		ofPayload.setToWhom(f.getToWhomFeedbackgiven());
    		ofPayload.setDateAndtime(f.getCreatedOn());
    		ofPayloadList.add(ofPayload);
        }
        return ofPayloadList;
    }

    public FeedbackPayload saveFeedbackDetails(int appId, int groupId, String email, Feedback feedback,Users userObj) {
    	FeedbackPayload feedPayload = new FeedbackPayload();
    	Boolean isUserExists = userService.existsByEmail(email);
    	if(isUserExists) {
    		userObj=userRepo.findByuserEmailId(email);
    		long userId=userObj.getUserId();
        Optional<Application> app = appService.findApplication(appId);
        Optional<Group> group = groupService.getGroup(groupId);
        if (!app.isPresent()) {
            throw new RuntimeException("Application with id " + appId + " not found");
        }
        if (!group.isPresent()) {
            throw new RuntimeException("Group with id " + groupId + " not found");
        }
        Application appObj = app.get();
        Group groupObj = group.get();
        String toWhomFeedbackgiven=groupObj.getGroupName();
        //Instant instant=Instant.ofEpochMilli(new Date().getTime());
        //feedback.setCreatedAt(instant);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        Date date = new Date();
        String feedbackgiving_date_time = sdf.format(date);
        feedback.setCreatedOn(feedbackgiving_date_time);
        feedback.setFeedbackGivenBy(email);
        feedback.setAppId(appObj);
        feedback.setGroup(groupObj);
        feedback.setFeedbackGivenByuserId(userId);
        feedback.setToWhomFeedbackgiven(toWhomFeedbackgiven);
        groupObj.getFeed().add(feedback);
        appObj.getFeedbackObj().add(feedback);
        try {
        feedRepo.save(feedback);
        feedPayload.setAppId(appId);
        feedPayload.setGroupId(groupId);
        //feedPayload.setFeedback(feedback);
        feedPayload.setFeedbackGivenBy(feedback.getFeedbackGivenBy());
        feedPayload.setFeedbackinfo(feedback.getFeedbackinfo());
        feedPayload.setStatusMessage("Feedback Given successfully to-> "+groupObj.getGroupName());
        }catch (Exception e) {
        	 feedPayload.setStatusMessage(e.getMessage());
		}
    	}
    	else {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			Date date = new Date();
			userObj.setCreatedOn(sdf.format(date));
			userObj.setPassword(passwordEncoder.encode(userObj.getMobileNumber()));
			Role role=roleService.findRoles(2).get();
			userObj.getRoles().add(role);
			userService.saveUser(userObj);
			Optional<Application> app = appService.findApplication(appId);
	        Optional<Group> group = groupService.getGroup(groupId);
	        if (!app.isPresent()) {
	            throw new RuntimeException("Application with id " + appId + " not found");
	        }
	        if (!group.isPresent()) {
	            throw new RuntimeException("Group with id " + groupId + " not found");
	        }
	        Application appObj = app.get();
	        Group groupObj = group.get();
	        String toWhomFeedbackgiven=groupObj.getGroupName();
	        //Instant instant=Instant.ofEpochMilli(new Date().getTime());
	        //feedback.setCreatedAt(instant);
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
	        Date date1 = new Date();
	        String feedbackgiving_date_time = sdf1.format(date1);
	        feedback.setCreatedOn(feedbackgiving_date_time);
	        feedback.setFeedbackGivenBy(email);
	        feedback.setAppId(appObj);
	        feedback.setGroup(groupObj);
	        feedback.setFeedbackGivenByuserId(userObj.getUserId());
	        feedback.setToWhomFeedbackgiven(toWhomFeedbackgiven);
	        groupObj.getFeed().add(feedback);
	        appObj.getFeedbackObj().add(feedback);
	        try {
	        feedRepo.save(feedback);
	        feedPayload.setAppId(appId);
	        feedPayload.setGroupId(groupId);
	        //feedPayload.setFeedback(feedback);
	        feedPayload.setFeedbackGivenBy(feedback.getFeedbackGivenBy());
	        feedPayload.setFeedbackinfo(feedback.getFeedbackinfo());
	        feedPayload.setStatusMessage("Feedback Given successfully to-> "+groupObj.getGroupName());
	        }catch (Exception e) {
	        	 feedPayload.setStatusMessage(e.getMessage());
			}
    	}
        return feedPayload;
    }
    }