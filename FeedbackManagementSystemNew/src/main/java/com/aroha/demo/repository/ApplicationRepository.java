package com.aroha.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aroha.demo.model.Application;
import com.aroha.demo.payload.ApplicationPayloadResponse;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	Boolean existsByappName(String appName);
	
	Boolean existsByappId(int appId);
	
	@Query(value="select app_id from app_users where user_id=?1",nativeQuery=true)
	public List<Integer> getappId(long userId);
	
//	@Query(value="select a.app_id,a.app_name,ag.group_id,ag.group_name from app_table a \r\n" + 
//			"inner join app_group ag on a.app_id=ag.application_id",nativeQuery = true)
//	public List<ApplicationPayloadResponse> getAllApplication(); 
	
	@Query("select new com.aroha.demo.payload.ApplicationPayloadResponse(a.appId,a.appName,g.groupId,g.groupName)"
			+ "from Application a inner join a.group g")
	public List<ApplicationPayloadResponse> getAllApplication();
	
}
