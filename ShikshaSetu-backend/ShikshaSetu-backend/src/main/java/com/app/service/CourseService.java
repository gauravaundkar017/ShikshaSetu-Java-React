package com.app.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CourseDTO;
import com.app.dto.DisplayCoursesDTO;
import com.app.dto.LectureDTO;
import com.app.entities.Course;

public interface CourseService {
	CourseDTO createCourse(CourseDTO courseDTO, MultipartFile thumbnailFile) throws java.io.IOException;

	List<DisplayCoursesDTO> getAllCourses();

	LectureDTO addLectureToCourse(Long courseId, LectureDTO lectureDTO, MultipartFile videoFile) throws IOException;
	
	void deleteLectureFromCourseById(Long courseId, Long lectureId);
	List<LectureDTO> getLecturesByCourseId(Long courseId);

	CourseDTO updateCourse(Long courseId, CourseDTO courseDTO, MultipartFile thumbnailFile) throws IOException;
	void removeCourse(Long courseId);


}
