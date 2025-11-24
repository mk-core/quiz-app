package com.quiz.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "attempt_question")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "quizAttempt")
@ToString(exclude = "quizAttempt")
public class AttemptQuestion {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	private Long questionId;
	
	private String question;
	
	private String answer;
	
	private boolean isCorrect;
	
	@ManyToOne
	@JoinColumn(name = "quizAttempt_id")
	@JsonBackReference
	private QuizAttempt quizAttempt;
}
