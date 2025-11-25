1. Assumptions & Constraints

This is a solo project, not an enterprise platform.
I prioritize speed of delivery and clarity over perfect architecture.
Quiz can be taken by USER roles only, and can be created by ADMIN role only
Admin/User authentication will be basic based on the database user authentication
A default admin user will be created at the start of the application.
Data persistence: H2 in-memory database for prototyping, easy switch to PostgreSQL/MySQL later.
All passwords are encrypted in db using BCryptEncoder
No search, categories, time limits, or anti-cheating measures in v1.
Quiz result/status is simple, based on the correct and incorrect answers. If user scores ore than 8% correct answer, User has passed the quiz
Target deployment: local or cheap VPS (Render, Railway, Fly.io).

2. Scope (MVP – what WILL be delivered)
Entities/DB Schema
Quiz (id, name, category, List of questions)
Question (id, quizId, question, level, option1, option2, option3, option4, isBoolean, isMcq, isText, correctAnswer)
QuizUser(id, username, password, role)
QuizAttempt (id, userId, list of AttemptQuestion)
AttemptQuestion (id, questionId, question, answer, isCorrect, quizAttempt_id)

EndPoints:
Public : 
  Register endpoint: where user can register themselves to take participate in quiz.
Private : 
  Create Quiz: (ADMIN only) Admin can create quizes and put questions along with options and correct answers.
  Start quiz: (USER only) User can start the quiz based on the quiz ID. User will get questions one by one with each get call.
  Submit Quiz: (USER only) User can see the score/quiz status after the submission of the quiz.


Tech Stack (locked in)
Spring Boot 3.5.8 (Java 17)
Spring Data JPA + Hibernate
Spring Security (basic auth for admin)(DAO implementaion for the user credentials)
H2 database (dev)
Maven
Lombok, Spring Boot DevTools

