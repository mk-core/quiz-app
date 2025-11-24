package com.quiz.app.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "quiz_attempt")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "attemptQuestion")
@ToString(exclude = "attemptQuestion")
public class QuizAttempt {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userId;
	
	@OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL, orphanRemoval = false)
	@JsonManagedReference
	private Set<AttemptQuestion> attemptQuestion;
	
}
