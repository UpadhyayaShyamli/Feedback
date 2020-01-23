package com.aroha.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aroha.demo.model.Users;
import com.aroha.demo.payload.UserGroup;
import com.aroha.demo.service.AddUserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/addUser")
public class AddUserGroup {
   
    @Autowired
    private AddUserService addUserService;
    
    @PostMapping("/addUserInGroup")
    public ResponseEntity<?> addUser(@RequestBody UserGroup userGroup) {
        int appId = userGroup.getAppId();
        int groupId = userGroup.getGroupId();
        List<Users> userId=userGroup.getUserId();
        return ResponseEntity.ok(addUserService.addUserGroup(appId, groupId, userId));
    }
}
