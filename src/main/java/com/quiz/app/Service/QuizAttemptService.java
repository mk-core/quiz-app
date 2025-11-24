package com.quiz.app.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quiz.app.entity.AttemptQuestion;
import com.quiz.app.entity.Question;
import com.quiz.app.entity.QuizAttempt;
import com.quiz.app.entity.QuizUser;
import com.quiz.app.model.request.QuizAttemptRequest;
import com.quiz.app.repository.QuestionRepository;
import com.quiz.app.repository.QuizAttemptRepository;
import com.quiz.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAttemptService {

	private final QuizAttemptRepository attemptRepository;
	private final UserRepository userRepository;
	private final QuestionRepository questionRepository;

	public QuizAttempt submitQuiz(QuizAttemptRequest attemptRequest, String username) {

		QuizUser quizUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!!. Invalid Test."));

		QuizAttempt quizAttempt = new QuizAttempt();
		quizAttempt.setUserId(quizUser.getId());

		quizAttempt.setAttemptQuestion(attemptRequest.questions().stream().map(qq -> {
			AttemptQuestion attemptQuestion = new AttemptQuestion();
			attemptQuestion.setAnswer(qq.answer());
			attemptQuestion.setQuestion(qq.question());
			attemptQuestion.setQuestionId(qq.questionId());

			attemptQuestion.setCorrect(checkAnswer(qq.questionId(), qq.answer()));

			attemptQuestion.setQuizAttempt(quizAttempt);

			return attemptQuestion;
		}).collect(Collectors.toSet()));

		return attemptRepository.save(quizAttempt);

	}

	private boolean checkAnswer(Long questionId, String answer) {

		Optional<Question> question = questionRepository.findById(questionId);

		if (question.isPresent()) {

			Question q = question.get();

			return q.getCorrectAnswer().equalsIgnoreCase(answer);

		}

		return false;
	}
}
