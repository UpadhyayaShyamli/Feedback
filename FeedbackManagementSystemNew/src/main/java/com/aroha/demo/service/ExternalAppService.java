package com.aroha.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aroha.demo.payload.ApplicationListData;
import com.aroha.demo.payload.ApplicationPayloadResponse;
import com.aroha.demo.payload.ExternalAppData;
import com.aroha.demo.repository.ApplicationRepository;

@Service
public class ExternalAppService {
	
	@Autowired
	private ApplicationRepository appRepo;
	
	public ExternalAppData getAllApplication() {
		ExternalAppData ex = new ExternalAppData();
		ApplicationListData ad = new ApplicationListData();
		try {
			List<ApplicationPayloadResponse> listObj = appRepo.getAllApplication();
			ad.setAppList(listObj);
			ex.setStatus(true);
			ex.setStatusCode(HttpStatus.OK.value());
			ex.setStatusMessage("message displayed succesfully");
			ex.setData(ad);
		}
		catch (Exception e) {
			ex.setStatus(false);
			ex.setStatusCode(HttpStatus.BAD_REQUEST.value());
			ex.setStatusMessage(e.getMessage());
		}
		return ex;
	}
}
