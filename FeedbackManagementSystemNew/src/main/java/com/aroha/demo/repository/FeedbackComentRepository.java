package com.aroha.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aroha.demo.model.FeedbackComent;
@Repository
public interface FeedbackComentRepository extends JpaRepository<FeedbackComent, Long> {
	
	public List<FeedbackComent> findByfeedbackId(Integer id);
	
	@Query(value="select * from feedback_coment where feedback_id=?1",nativeQuery=true)
	public List<FeedbackComent> showComent(int feedbackId);

}
