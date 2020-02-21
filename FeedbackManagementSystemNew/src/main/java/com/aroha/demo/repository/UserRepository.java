package com.aroha.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aroha.demo.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>  
{
	public Users findByuserEmailId(String email);

	@Query(value="select role_id from user_roles where user_id=?1",nativeQuery = true)
	public long findRoles(long userId);

	Boolean existsByuserEmailId(String email);
	
	@Query("select new com.aroha.demo.model.Users(u.userId,u.userName,u.userEmailId) from Users u")
	public List<Users>getUsers();
	
	@Query(value="select count(user_id)from app_users where user_id=?1 and app_id=?2",nativeQuery = true)
	public Integer ifUserPresentInApp(long userId,int appId);
	
	@Query(value="select count(user_id)from group_user where user_id=?1 and group_id=?2",nativeQuery = true)
	public Integer ifUserPresentInGroup(long userId,int groupId);
	
	@Query(value="Insert into app_users(user_id,app_id)values(?1,?2)",nativeQuery = true)
	public void saveUserInApp(long userId,int appId);
	
	@Query(value="Insert into group_user(user_id,group_id)values(?1,?2)",nativeQuery = true)
	public void saveUserInGroup(long userId,int groupId);
}
