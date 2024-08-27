package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long>{

}
