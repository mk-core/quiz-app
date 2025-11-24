package com.quiz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.entity.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository< QuizAttempt,  Long>{

}
