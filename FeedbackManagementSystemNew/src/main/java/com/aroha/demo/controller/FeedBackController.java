package com.aroha.demo.controller;

import com.aroha.demo.model.Feedback;
import com.aroha.demo.payload.FeedbackData;
import com.aroha.demo.payload.FeedbackPayload;
import com.aroha.demo.security.CurrentUser;
import com.aroha.demo.security.UserPrincipal;
import com.aroha.demo.service.FeedbackService;

import java.util.List;

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
	public ResponseEntity<?> saveFeedback(@RequestBody FeedbackPayload feedObj,@CurrentUser UserPrincipal user) {
		int appId=feedObj.getAppId();
		int groupId=feedObj.getGroupId();
		String email=user.getEmail();
		Feedback feedback=feedObj.getFeedback();
		return ResponseEntity.ok(feedService.saveFeedback(appId, groupId, email, feedback));
	}

//	@PostMapping("/showFeedbackforUser")
//    public ResponseEntity<?> showFeedbackForUser(@RequestBody FeedbackPayload feedObj, @CurrentUser UserPrincipal user) {
//        //String email=feedObj.getUserNameorEmail();
//        String email = user.getEmail();
//        int appId = feedObj.getAppId();
//        if (user.isAdminRole()) {
//            if (feedService.showFeedbackForadmin(appId).isEmpty()) {
//                return ResponseEntity.ok("No Feedback found");
//            }
//            return ResponseEntity.ok(feedService.showFeedbackForadmin(appId));
//        }
//        if (feedService.showFeedbackforUser(email).isEmpty()) {
//            return ResponseEntity.ok("No Feedback found");
//        }
//        return ResponseEntity.ok(feedService.showFeedbackforUser(email));
//    }
//
//	@PostMapping("/showFeedbackforAdmin")
//	public ResponseEntity<?>showFeedbackForAdmin(@RequestBody FeedbackPayload feedObj,@CurrentUser UserPrincipal user){
//		int appId = feedObj.getAppId();
//		List<FeedbackData>listObj=null;
//		if(user.isAdminRole()) {
//			listObj=feedService.showFeedbackForadmin(appId);
//		}
//		return ResponseEntity.ok(listObj);
//	}
	
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
            if (feedService.showFeedbackForadmin(appId).isEmpty()) {
                return ResponseEntity.ok("No Feedback found");
            }
            return ResponseEntity.ok(feedService.showFeedbackForadmin(appId));
        }
//        if (feedService.showFeedback(email,appId).isEmpty()) {
//            return ResponseEntity.ok("No Feedback found");
//        }
        return ResponseEntity.ok(feedService.showFeedback(email,appId));
    }
	
	@GetMapping("/showComent")
	public ResponseEntity<?>getComent(@RequestParam("feedbackId")int feedbackId)
	{
		return ResponseEntity.ok(feedService.getComent(feedbackId));
	}
}
