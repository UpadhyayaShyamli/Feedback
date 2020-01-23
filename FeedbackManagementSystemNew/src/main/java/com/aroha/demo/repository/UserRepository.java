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
}
