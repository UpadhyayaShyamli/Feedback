package com.aroha.demo.controller;

import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aroha.demo.model.RoleName;
import com.aroha.demo.model.Users;
import com.aroha.demo.payload.JwtAuthenticationResponse;
import com.aroha.demo.payload.SignInRequest;
import com.aroha.demo.payload.SignUpRequest;
import com.aroha.demo.security.JwtTokenProvider;
import com.aroha.demo.service.UserService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	/*
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest loginRequest) {
        Users user = userService.checkEmail(loginRequest);
        if (user != null) {
            int findRole = userService.findRoles(user.getUserId());
            switch (findRole) {
                case 1:
                    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                        loginRequest.setRoles(RoleName.ROLE_ADMIN);
                        loginRequest.setName(user.getUserName());
                        loginRequest.setStatus("logged In");
                        logger.info(user.getUserName() + " loggerd in successfully");
                        return ResponseEntity.ok(loginRequest);
                    }
                case 2:
                    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                        loginRequest.setRoles(RoleName.ROLE_USER);
                        loginRequest.setName(user.getUserName());
                        loginRequest.setStatus("logged In");
                        logger.info(user.getUserName() + " loggerd in successfully");
                        return ResponseEntity.ok(loginRequest);
                    }
                default:
                    loginRequest.setStatus("Password is wrong");
                    return ResponseEntity.ok(loginRequest);
            }
        } else {
            loginRequest.setStatus("Email Id not found");
            logger.error("Invalid email Id");
            return ResponseEntity.ok(loginRequest);
        }

    }
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest loginRequest) {

		 Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getUsernameOrEmail(),
							loginRequest.getPassword()    
							)
					);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> saveUser(@RequestBody SignUpRequest signup) {

		if (!userService.existsByEmail(signup.getUserEmailId())) {
			return ResponseEntity.ok(userService.saveUsers(signup));
		}
		return ResponseEntity.ok("User already present");
	}
}
