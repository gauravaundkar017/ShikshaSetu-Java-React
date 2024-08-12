package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
//derived query method
	Optional<UserEntity> findByEmail(String email);
	//derived query metho
//	existsByEmail(String email);
//	existsByE
	boolean existsByEmail(String email);

}
