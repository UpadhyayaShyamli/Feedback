package com.aroha.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aroha.demo.model.Group;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.FeedbackPayload;
import com.aroha.demo.payload.GroupDataRequest;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query(value = "select * from app_group where application_id=?1", nativeQuery = true)
    public List<Group> showAllGroup(int app);

    Boolean existsBygroupName(String groupName);

    @Query(value = "select count(group_id) from app_group where group_name=?1 and application_id=?2", nativeQuery = true)
    public Integer checkGroupExists(String groupName, int appId);
    
    @Query("select new com.aroha.demo.payload.GroupDataRequest(g.groupId)from Group g left join g.user u where u.userId=?1")
    public List<GroupDataRequest> showGroup(long userId);
    
    @Query(value="select count(u.user_id)from users u inner join app_users a on u.user_id=a.user_id "
    		+ "inner join group_user g on u.user_id=g.user_id where\r\n" + 
    		"u.user_id=?1 and a.app_id=?2  and  g.group_id=?3",nativeQuery = true)
    public Integer checkUserExists(long userId,int appId,int groupId);
}
