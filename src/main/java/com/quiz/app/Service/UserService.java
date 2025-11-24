package com.quiz.app.Service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quiz.app.entity.QuizUser;
import com.quiz.app.exception.QuizAppException;
import com.quiz.app.model.request.RegisterUserRequest;
import com.quiz.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public QuizUser registerUser(RegisterUserRequest userRequest) throws QuizAppException {

		try {

			if (userRepository.findByUsername(userRequest.username()).isPresent()) {
				throw new QuizAppException("User already registered.");
			}

			QuizUser quizUser = new QuizUser();
			quizUser.setUsername(userRequest.username());
			quizUser.setPassword(bCryptPasswordEncoder.encode(userRequest.password()));
			quizUser.setRole("ROLE_USER");

			return userRepository.save(quizUser);

		} catch (Exception e) {
			throw new QuizAppException(e, e.getMessage());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		QuizUser quizUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("%s Username not found", username)));

		return new User(quizUser.getUsername(), quizUser.getPassword(),
				List.of(new SimpleGrantedAuthority(quizUser.getRole())));
	}
}
