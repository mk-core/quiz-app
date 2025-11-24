package com.quiz.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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
@Table(name = "question")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "quiz")
@ToString(exclude = "quiz")
public class Question {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String quest;
	
	private String level;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	private boolean isMcq;
	private boolean isBoolean;
	private boolean isText;
	
	@Column(nullable = false)
	private String correctAnswer;
	
	@ManyToOne
	@JoinColumn(name = "quiz_id")
	@JsonBackReference
	private Quiz quiz;
}
