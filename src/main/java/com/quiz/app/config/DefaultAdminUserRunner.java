package com.quiz.app.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.quiz.app.entity.QuizUser;
import com.quiz.app.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultAdminUserRunner {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@PostConstruct
	public void defaultAdminUser() {
		
		if(userRepository.findByUsername("admin").isEmpty()) {
			
			QuizUser quizUser = new QuizUser();
			quizUser.setUsername("admin");
			quizUser.setPassword(bCryptPasswordEncoder.encode("admin"));
			quizUser.setRole("ROLE_ADMIN");
			
			userRepository.save(quizUser);
			
			log.info("Default admin user created/....");
		}
	}
}
