package com.app.dto;

import java.util.Date;
import java.util.List;

import com.app.entities.Thumbnail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DisplayCoursesDTO {
	
	private Long id;
    private String title;
    private String description;
    private String category;
    private Thumbnail thumbnail;
//    private int numberOfLectures;
    private List<LectureDTO> lectures;
    
    private String createdBy;
    private Date createdAt;
    private Date updatedAt;
}
