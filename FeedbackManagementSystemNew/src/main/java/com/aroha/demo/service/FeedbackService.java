package com.aroha.demo.service;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Feedback;
import com.aroha.demo.model.FeedbackComent;
import com.aroha.demo.model.Group;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.FeedbackComentPayload;
import com.aroha.demo.payload.FeedbackData;
import com.aroha.demo.payload.FeedbackPayload;
import com.aroha.demo.payload.GroupDataRequest;
import com.aroha.demo.repository.FeedbackComentRepository;
import com.aroha.demo.repository.FeedbackRepository;
import com.aroha.demo.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

    public FeedbackPayload saveFeedback(int appId, int groupId, String email, Feedback feedback) {
    	FeedbackPayload feedPayload = new FeedbackPayload();
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
        feedPayload.setAppId(appId);
        feedPayload.setGroupId(groupId);
        //feedPayload.setFeedback(feedback);
        feedPayload.setFeedbackGivenBy(feedback.getFeedbackGivenBy());
        feedPayload.setFeedbackinfo(feedback.getFeedbackinfo());
        feedPayload.setStatusMessage("Feedback Given successfully to-> "+groupObj.getGroupName());
        }catch (Exception e) {
        	 feedPayload.setStatusMessage(e.getMessage());
		}
        return feedPayload;
    }
    
    public List<FeedbackData> showFeedback(String email) {
    	Users user=userService.findUsers(email);
    	long userId=user.getUserId();
    	System.out.println("User Id: "+userId);
    	List<GroupDataRequest>groupObj=groupService.findGroup(userId);
    	ArrayList<FeedbackData>list=new ArrayList<>();
    	for(GroupDataRequest g:groupObj) {
//    		System.out.println("GroupId is: "+g.getGroupId());
    		List<FeedbackPayload>feedObj=feedRepo.showFeedback(g.getGroupId());
    		Iterator<FeedbackPayload>itr=feedObj.iterator();
    		while(itr.hasNext()) {
    			FeedbackPayload obj=itr.next();
    			System.out.println("------------"+obj.getCreatedOn());
    			FeedbackData feedback=new FeedbackData();
    			feedback.setFeedback(obj.getFeedbackinfo());
    			feedback.setFeedbackSender(obj.getFeedbackGivenBy());
    			feedback.setDateAndtime(obj.getCreatedOn());
    			list.add(feedback);	
    		}
    	}
    	return list;	
    }
    
    public ArrayList<FeedbackData> showFeedbackForadmin(int appId) {
        List<Feedback> list = feedRepo.showFeedbackForAdmin(appId);
        ArrayList<FeedbackData> listObj = new ArrayList<>();
//        if(list.size()==0)
//        {
//        	FeedbackData feedback = new FeedbackData();
//        	feedback.setStatusMessage("No feedback Found");
//        	listObj.add(feedback);
//        }
        Iterator<Feedback> itr = list.iterator();
        while (itr.hasNext()) {
            Feedback obj = itr.next();
            FeedbackData feedback = new FeedbackData();
            feedback.setFeedback(obj.getFeedbackinfo());
            feedback.setFeedbackSender(obj.getFeedbackGivenBy());
            feedback.setDateAndtime(obj.getCreatedOn());
            feedback.setStatusMessage("Feedback displayed successfully");
            listObj.add(feedback);
        }
        return listObj;
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
    
//    public ArrayList<feedbackData>showfeedbackforAdmin(int appId)
//    {
//    	List<Feedback>list=feedRepo.findAll();
//    	ArrayList<FeedbackData>listData=new ArrayList<>();
//    	for(Feedback f:list) {
//    		Application app = new Application();
//    		List<FeedbackPayload>listObj = feedRepo.showFeedbackforAdmin(app.getAppId()); 
//    		
//    	}
//    }   
}
