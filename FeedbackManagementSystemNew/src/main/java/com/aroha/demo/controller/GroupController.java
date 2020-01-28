package com.aroha.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aroha.demo.model.Group;
import com.aroha.demo.payload.CreateGroup;
import com.aroha.demo.payload.FeedbackPayload;
import com.aroha.demo.payload.GroupDataRequest;
import com.aroha.demo.service.GroupService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/createGroup")
    public ResponseEntity<?> createGrop(@RequestBody CreateGroup group) {
        int appId = group.getAppId();
        Group gObj = group.getGroupObj();
        group=groupService.createGroup(appId, gObj);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/showAllGroup")
    public ResponseEntity<?> getAllGroup() {
        if (groupService.getAllGroup().isEmpty()) {
            return ResponseEntity.ok("No group found");
        }
        return ResponseEntity.ok(groupService.getAllGroup());
    }

    @PostMapping("/showGroup")
    public ResponseEntity<?> showGroup(@RequestBody GroupDataRequest dataRequest) {
        int appId = dataRequest.getAppId();
        System.out.println(appId);
        return ResponseEntity.ok(groupService.showGroupbyAppId(appId));
    }
    
    @PostMapping("/checkUserExistsInGroup")
    public ResponseEntity<?>checkUserEsistsInGroup(@RequestBody FeedbackPayload feedObj)
    {
    	return ResponseEntity.ok(groupService.checkuserExistsInGroup(feedObj.getGroupId()));
    }
}
