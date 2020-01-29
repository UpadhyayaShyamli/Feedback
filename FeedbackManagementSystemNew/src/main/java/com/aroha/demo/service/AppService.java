package com.aroha.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aroha.demo.model.Application;
import com.aroha.demo.payload.AppPayload;
import com.aroha.demo.payload.AppUserPayload;
import com.aroha.demo.repository.ApplicationRepository;

@Service
public class AppService {

    @Autowired
    private ApplicationRepository appRepo;

    public boolean checkAppEx(String appName) {
        return appRepo.existsByappName(appName);
    }

    public AppPayload createApp(Application app) {
        //Instant now = Instant.now();
        AppPayload appObj = new AppPayload();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        Date date = new Date();
        String applicationcreating_date_time = sdf.format(date);
        app.setCreatedOn(applicationcreating_date_time);
        try {
        appRepo.save(app);
        appObj.setStatus("Application saved");
        appObj.setData(app);
        
        }catch (Exception e) {
        	appObj.setStatus(e.getMessage());
		}
        return appObj;
    }

    public List<Application> getAllApp() {
        return appRepo.findAll();
    }

    public boolean checkAppId(int appId) {
        return appRepo.existsByappId(appId);
    }

    public Optional<Application> findApplication(int appId) {
        return appRepo.findById(appId);
    }
    
    public List<AppUserPayload> getUserApp(long userId)
    {
    	List<Integer> app_id = appRepo.getappId(userId);
    	ArrayList<AppUserPayload> listObj = new ArrayList<AppUserPayload>();
    	//AppUserPayload appObj =new AppUserPayload();
    	if(app_id.size()>0) {
	    	for(Integer i:app_id) {
	    		Optional<Application> getApp = appRepo.findById(i);
	    		AppUserPayload appObj =new AppUserPayload();
	    		Application app = getApp.get();
	    		appObj.setAppId(app.getAppId());
	    		appObj.setAppName(app.getAppName());
	    		appObj.setStatusMessage("Application displayed successfully");	
	    		listObj.add(appObj);
	    	}
    	}
		return listObj;
    }
}
