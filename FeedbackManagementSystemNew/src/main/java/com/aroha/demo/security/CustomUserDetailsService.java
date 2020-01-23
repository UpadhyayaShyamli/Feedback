package com.aroha.demo.security;

import com.aroha.demo.exception.ResourceNotFoundException;
import com.aroha.demo.model.Users;
import com.aroha.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email

    	Users user = userRepository.findByuserEmailId(usernameOrEmail);
        return UserPrincipal.create(user);
    }

    @Transactional
	public UserDetails loadUserById(Long id) {
		Users user = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", id)
		);

		return UserPrincipal.create(user);
	}
}
