package com.app.dto;

import javax.validation.constraints.*;

import com.app.entities.Thumbnail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 60, message = "Title must be between 5 and 60 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 160, message = "Description must be between 5 and 160 characters")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    private String createdBy;
    
    private Thumbnail thumbnail;
    
   

    
}