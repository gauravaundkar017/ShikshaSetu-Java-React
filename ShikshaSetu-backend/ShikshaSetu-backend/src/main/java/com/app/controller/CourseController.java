package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CourseDTO;
import com.app.entities.Course;
import com.app.service.CourseService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;


	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> createCourse(@RequestPart("course") CourseDTO courseDTO,
			@RequestParam("file") MultipartFile file)
			throws IOException, java.io.IOException {
		CourseDTO createdCourse = courseService.createCourse(courseDTO, file);
		return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
	}
}
