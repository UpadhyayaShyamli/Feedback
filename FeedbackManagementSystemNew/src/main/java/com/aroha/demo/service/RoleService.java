package com.aroha.demo.service;

import com.aroha.demo.model.Role;
import com.aroha.demo.repository.RoleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepo;
    
    public Optional<Role> findRoles(long roleId){
        return roleRepo.findById(roleId);
    }
    
    public long findRole(long userId){
        return roleRepo.getRoles(userId);
    }
}
