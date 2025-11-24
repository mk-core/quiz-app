package com.quiz.app.Service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quiz.app.entity.Question;
import com.quiz.app.entity.Quiz;
import com.quiz.app.exception.QuizAppException;
import com.quiz.app.model.request.CreateQuizRequest;
import com.quiz.app.repository.QuestionRepository;
import com.quiz.app.repository.QuizReppository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizReppository quizrepository;
	private final QuestionRepository questionRepository;

	public Quiz createQuiz(CreateQuizRequest quizRequest) throws QuizAppException {

		try {
			Quiz quiz = new Quiz();
			quiz.setCategory(quizRequest.category());
			quiz.setName(quizRequest.name());

			Set<Question> questions = quizRequest.questions().stream().map(qq -> {
				Question q = new Question();

				q.setQuest(qq.question());
				q.setLevel(qq.level());
				q.setOption1(qq.option1());
				q.setOption2(qq.option2());
				q.setOption3(qq.option3());
				q.setOption4(qq.option4());

				q.setMcq(qq.isMcq());
				q.setBoolean(qq.isBoolean());
				q.setText(qq.isText());

				q.setCorrectAnswer(qq.correctAnswer());

				q.setQuiz(quiz);

				return q;
			}).collect(Collectors.toSet());

			quiz.setQuestion(questions);

			return quizrepository.save(quiz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QuizAppException(e, e.getMessage());
		}

	}

	public Page<Question> getQuestions(Pageable pageable, Long quizId) throws QuizAppException {

		Quiz quiz = quizrepository.findById(quizId).orElseThrow(() -> new QuizAppException("Quiz not found!"));
		
		return questionRepository.findByQuiz(quiz, pageable);
	}
}
