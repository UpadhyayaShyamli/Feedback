package com.aroha.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aroha.demo.model.Application;
import com.aroha.demo.payload.AppPayload;
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
        appRepo.save(app);
        appObj.setStatus("Application saved");
        appObj.setData(app);
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

}
