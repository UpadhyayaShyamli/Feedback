package com.aroha.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aroha.demo.model.Role;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByrolename(String email);

    @Query(value = "select role_id from user_roles r left outer join users u on u.user_id=r.user_id where u.user_id=?1", nativeQuery = true)
    public long getRoles(long userId);
}
