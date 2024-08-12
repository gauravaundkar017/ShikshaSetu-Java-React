package com.app.service;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ApiException;
import com.app.dto.Signup;
import com.app.entities.Avatar;
import com.app.entities.Subscription;
import com.app.entities.UserEntity;
import com.app.entities.UserRole;
import com.app.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.jsonwebtoken.io.IOException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
    private Cloudinary cloudinary;

	@Override
	public Signup userRegistration(Signup reqDTO, MultipartFile file) throws java.io.IOException {
		// TODO Auto-generated method U
		if(userRepository.existsByEmail(reqDTO.getEmail()))
			throw new ApiException("Email already exists !!!");

		UserEntity user = mapper.map(reqDTO, UserEntity.class);
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(reqDTO.getRole() != null ? reqDTO.getRole() : UserRole.USER); // Ensure role is set
	    user.setSubscription(reqDTO.getSubscription() != null ? reqDTO.getSubscription() : Subscription.INACTIVE); // Ensure subscription is set
	    
	    if (file != null && !file.isEmpty()) {
            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String publicId = uploadResult.get("public_id").toString();
                String secureUrl = uploadResult.get("secure_url").toString();

                // Create and set the Avatar entity
                Avatar avatar = new Avatar();
                avatar.setPublicId(publicId);
                avatar.setSecureUrl(secureUrl);
                user.setAvatar(avatar); // Set the avatar in the user entity
            } catch (IOException e) {
                throw new ApiException("File upload failed: " + e.getMessage());
            }
        }
		
		return mapper.map(userRepository.save(user), Signup.class);
	}
	//dep : dao layer i/f
			
	

}
