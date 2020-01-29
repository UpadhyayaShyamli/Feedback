package com.aroha.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aroha.demo.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	Boolean existsByappName(String appName);
	
	Boolean existsByappId(int appId);
	
	@Query(value="select app_id from app_users where user_id=?1",nativeQuery=true)
	public List<Integer> getappId(long userId);
	
}
