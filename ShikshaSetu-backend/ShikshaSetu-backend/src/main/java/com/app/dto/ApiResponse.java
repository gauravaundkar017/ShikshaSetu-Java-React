package com.app.dto;

//import java.time.LocalDateTime;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
////DTO :  resp DTO : to send API resp from rest server ---> rest clnt
//@NoArgsConstructor
//@Getter
//@Setter
//public class ApiResponse {
//	private LocalDateTime timeStamp;
//	private String message;
//	public ApiResponse(String message) {
//		super();
//		this.message = message;
//		this.timeStamp=LocalDateTime.now();
//	}
//	
//}

import java.time.LocalDateTime;

import com.app.entities.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private LocalDateTime timeStamp;
    private String message;
    private Object user; // Adjust this to match your user data type
    private boolean success;

    public ApiResponse(String message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(String message, boolean success, Object user) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.success = success;
        this.user = user;
    }
    public ApiResponse(String message, boolean success) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.success = success;
    }
}

