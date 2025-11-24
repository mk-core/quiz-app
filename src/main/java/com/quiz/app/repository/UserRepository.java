package com.quiz.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.entity.QuizUser;

public interface UserRepository extends JpaRepository<QuizUser, Long>{

	Optional<QuizUser> findByUsername(String username);
}
