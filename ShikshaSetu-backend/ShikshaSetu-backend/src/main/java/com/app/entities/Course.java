package com.app.entities;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "Title is required")
	    @Size(min = 5, max = 60, message = "Title must be between 5 and 60 characters")
	    @Column(nullable = false, length = 60)
	    private String title;

	    @NotBlank(message = "Description is required")
	    @Size(min = 5, max = 160, message = "Description must be between 5 and 160 characters")
	    @Column(nullable = false, length = 160)
	    private String description;

	    @NotBlank(message = "Category is required")
	    @Column(nullable = false)
	    private String category;

	    @Embedded
	    private Thumbnail thumbnail;

	    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Lecture> lectures;

//	    @Min(value = 0, message = "Number of lectures cannot be negative")
//	    @Column(name = "number_of_lectures", nullable = false)
//	    private int numberOfLectures = 0;

	    @NotBlank(message = "Created by is required")
	    @Column(name = "created_by", nullable = false)
	    private String createdBy;

	    @Column(name = "created_at", updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdAt;

	    @Column(name = "updated_at")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date updatedAt;

	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = new Date();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = new Date();
	    }
}
