package com.aroha.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aroha.demo.payload.ApplicationListData;
import com.aroha.demo.payload.ApplicationPayloadResponse;
import com.aroha.demo.payload.ExternalAppData;
import com.aroha.demo.repository.ApplicationRepository;
import com.aroha.demo.repository.GroupRepository;

@Service
public class ExternalAppService {
	
	@Autowired
	private ApplicationRepository appRepo;
	@Autowired
	private GroupRepository groupRepo;
	
	public ExternalAppData getAllApplication() {
		ExternalAppData ex = new ExternalAppData();
		ApplicationListData ad = new ApplicationListData();
		List<ApplicationPayloadResponse> listObj = appRepo.getAllApplication();
		ad.setAppList(listObj);
		ex.setStatus(true);
		ex.setStatusMessage("message displayed succesfully");
		ex.setData(ad);
		return ex;
	}
}
