package com.app.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


public class CloudnaryServiceImpl implements CloudinaryService {
	@Autowired
	public Cloudinary cloudinary;

	@Override
	public Map<String, String> uploadImage(File file) throws IOException {
		// TODO Auto-generated method stub
		return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
	}

}
