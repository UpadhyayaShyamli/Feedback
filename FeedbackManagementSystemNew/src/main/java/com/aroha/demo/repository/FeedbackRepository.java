package com.aroha.demo.repository;

import com.aroha.demo.model.Application;
import com.aroha.demo.model.Feedback;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.FeedbackPayload;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	
	@Query("select new com.aroha.demo.payload.FeedbackPayload(f.feedbackinfo,f.feedbackGivenBy,f.createdOn)from Feedback f left join f.group g where g.groupId=?1 order by f.createdOn desc")
	public List<FeedbackPayload>showFeedback(int groupId);
	
//	@Query("select new com.aroha.demo.payload.FeedbackPayload(f.feedbackinfo,f.feedbackGivenBy,f.createdOn)from Feedback f left join f.appId a where a.appId=?1")
//	public List<FeedbackPayload>showFeedbackforAdmin(int appId);
	
	@Query(value="select * from feedback_details where app_id=?1 order by created_on desc",nativeQuery=true)
	public List<Feedback>showFeedbackForAdmin(int appId);
}
