package com.quiz.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.entity.Question;
import com.quiz.app.entity.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	Page<Question> findByQuiz(Quiz quiz, Pageable pageable);
}
