package com.quiz.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.Service.UserService;
import com.quiz.app.entity.QuizUser;
import com.quiz.app.exception.QuizAppException;
import com.quiz.app.model.request.RegisterUserRequest;
import com.quiz.app.model.response.RegisterUserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
	
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest userRequest) throws QuizAppException{
		
		log.info(userRequest.toString());
		QuizUser quizUser = userService.registerUser(userRequest);
		
		return ResponseEntity.ok().body(new RegisterUserResponse(quizUser.getUsername(), "User Registered successfuly!!"));
	}
}
