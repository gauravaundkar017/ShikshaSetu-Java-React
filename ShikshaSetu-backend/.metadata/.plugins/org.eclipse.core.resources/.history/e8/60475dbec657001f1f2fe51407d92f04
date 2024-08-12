package com.app.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.SignInRequest;
import com.app.dto.SigninResponse;
import com.app.dto.Signup;
import com.app.entities.UserEntity;
import com.app.security.JwtUtils;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins =  "http://localhost:5173")
public class UserSignInSignupController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr; 
	
//    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
	@PostMapping("/register")
	public ResponseEntity<?> userSignup(@RequestPart("dto") @Valid Signup dto,@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("in sign up " + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegistration(dto, file));
	}
    
//    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid SignInRequest request){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		
		Authentication verifiedToken = authMgr.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(verifiedToken);

//	    UserEntity user = (UserEntity) verifiedToken.getPrincipal();

		SigninResponse resp = new SigninResponse(jwtUtils.generateJwtToken(verifiedToken), "Successful Auth!!!!");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);

	}
	
}
