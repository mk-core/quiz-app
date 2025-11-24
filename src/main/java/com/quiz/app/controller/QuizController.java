package com.quiz.app.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.Service.QuizService;
import com.quiz.app.entity.Question;
import com.quiz.app.entity.Quiz;
import com.quiz.app.exception.QuizAppException;
import com.quiz.app.model.request.CreateQuizRequest;
import com.quiz.app.model.response.CreateQuizResponse;
import com.quiz.app.model.response.QuizResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

	private final QuizService quizService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CreateQuizResponse> createQuiz(@RequestBody CreateQuizRequest quzRequest)
			throws QuizAppException {
		Quiz quiz = quizService.createQuiz(quzRequest);
		return ResponseEntity.ok().body(new CreateQuizResponse(quiz.getName(), "Quiz Created Successfully!",
				quiz.getId(), quiz.getQuestion().size()));
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{quizId}")
	public ResponseEntity<QuizResponse> startQuiz(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "1") Integer pageSize, @PathVariable Long quizId) throws QuizAppException {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<Question> question = quizService.getQuestions(pageable, quizId);

		Question q = question.get().findFirst().get();

		return ResponseEntity.ok().body(new QuizResponse(q.getId(), q.getQuest(), q.getLevel(), q.getOption1(),
				q.getOption2(), q.getOption3(), q.getOption4()));
	}
}
