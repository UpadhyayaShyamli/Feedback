package com.aroha.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.aroha.demo.model.Application;
import com.aroha.demo.payload.AppPayload;
import com.aroha.demo.service.AppService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/app")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/registerApp")
    public ResponseEntity<?> createApp(@RequestBody Application app) {
        System.out.println("------I am here--------");

        Boolean isExists = !appService.checkAppEx(app.getAppName());
        String err = "";
        try {
            if (isExists) {
                return ResponseEntity.ok(appService.createApp(app));
            }
            AppPayload ap=new AppPayload();
            ap.setStatus("Application " + app.getAppName() + " is already present");
            return ResponseEntity.ok(ap);
        } catch (Exception ex) {
            err = ex.getMessage();
        }
        return ResponseEntity.ok(err);
    }

    @GetMapping("/getApp")
    public ResponseEntity<?> getAllApp() {
        if (appService.getAllApp().isEmpty()) {
            return ResponseEntity.ok("No Application found");
        }
        return ResponseEntity.ok(appService.getAllApp());
    }
}
