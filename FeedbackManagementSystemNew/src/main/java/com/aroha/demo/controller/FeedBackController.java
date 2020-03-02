package com.aroha.demo.controller;

import com.aroha.demo.model.Feedback;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.FeedbackPayload;
import com.aroha.demo.security.CurrentUser;
import com.aroha.demo.security.UserPrincipal;
import com.aroha.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/feedback")
public class FeedBackController {

	@Autowired
	private FeedbackService feedService;

	@PostMapping("/saveFeedback")
	public ResponseEntity<?> saveFeedback(@RequestBody FeedbackPayload feedObj) {
		int appId=feedObj.getAppId();
		int groupId=feedObj.getGroupId();
		//String email=user.getEmail();
		String emailId = feedObj.getUserNameorEmail();
		Feedback feedback=feedObj.getFeedback();
		return ResponseEntity.ok(feedService.saveFeedback(appId, groupId, emailId, feedback));
	}
	
	@PostMapping("/saveFeedbackdata")
	public ResponseEntity<?> saveFeedbackdata(@RequestBody FeedbackPayload feedObj) {
		int appId=feedObj.getAppId();
		int groupId=feedObj.getGroupId();
		//String email=user.getEmail();
		String emailId = feedObj.getUserNameorEmail();
		Feedback feedback=feedObj.getFeedback();
		Users user = feedObj.getUser();
		return ResponseEntity.ok(feedService.saveFeedbackDetails(appId, groupId, emailId, feedback,user));
	}
	
	@PostMapping("/saveFeedbackComent")
	public ResponseEntity<?>saveFeedbackComent(@RequestParam("feedbackId") int feedbackId,
			@RequestParam("coment") String coment)
	{
		return ResponseEntity.ok(feedService.savefeedbackComent(feedbackId, coment));
	}
	
	@PostMapping("/showFeedback")
	public ResponseEntity<?>showFeedback(@RequestBody FeedbackPayload feedObj,@CurrentUser UserPrincipal user)
	{
		String email = user.getEmail();
        int appId = feedObj.getAppId();
        if (user.isAdminRole()) {
//            if (feedService.showFeedbackForadmin(appId).isEmpty()) {
//                return ResponseEntity.ok("No Feedback found");
//            }
            return ResponseEntity.ok(feedService.showFeedbackForadmin(appId));
        }
//        if (feedService.showFeedback(email,appId).isEmpty()) {
//            return ResponseEntity.ok("No Feedback found");
//        }
        return ResponseEntity.ok(feedService.showFeedback(email,appId));
    }
	
	@PostMapping("/showGivenFeedback")
	public ResponseEntity<?>showOwnFeedback(@RequestBody FeedbackPayload feedObj,@CurrentUser UserPrincipal user)
	{
		String email = user.getEmail();
        int appId = feedObj.getAppId();
        if (user.isAdminRole()) {
            if (feedService.showOwnFeedbackForadmin(appId).isEmpty()) {
                return ResponseEntity.ok("No Feedback found");
            }
            return ResponseEntity.ok(feedService.showOwnFeedbackForadmin(appId));
        }
//        if (feedService.showFeedback(email,appId).isEmpty()) {
//            return ResponseEntity.ok("No Feedback found");
//        }
        return ResponseEntity.ok(feedService.showOwnGivenFeedback(email,appId));
    }
	
	
	@PostMapping("/showComent")
	public ResponseEntity<?>getComent(@RequestParam("feedbackId")int feedbackId)
	{
		return ResponseEntity.ok(feedService.getComent(feedbackId));
	}
}
