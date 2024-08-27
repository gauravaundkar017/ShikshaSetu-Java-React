package com.app.dto;

import com.app.entities.LectureFile;

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
public class LectureDTO {
	private Long id;
    private String title;
    private String description;
    private LectureFile lectureFile;

}
