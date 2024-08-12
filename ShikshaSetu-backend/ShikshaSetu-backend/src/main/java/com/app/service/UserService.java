package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.Signup;
import com.app.entities.UserEntity;

public interface UserService {
	//add signup method
	Signup userRegistration(Signup reqDTO, MultipartFile file) throws IOException;

	UserEntity getUserByEmail(String username);
}
