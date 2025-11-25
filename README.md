API: 
(Register User) Public
POST: api/auth/register
Request body: {
  "username": "mani",
  "password": "mani"
}

Response: {
  "username": "mani",
  "message": "User Registered successfuly!!"
}

Creating Quiz (Admin only)
POST: /api/quiz
Request Body:{
  "name": "General",
  "category": "Mix",
  "questions": [
    {
      "question": "2+2?",
      "level": "EASY",
      "option1": "2",
      "option2": "4",
      "option3": "6",
      "option4": "8",
      "isBoolean": false,
      "isMcq": true,
      "isText": false,
      "correctAnswer": "4"
    },
    {
      "question": "India's capital?",
      "level": "MEDIUM",
      "option1": "Chandigarh",
      "option2": "New Delhi",
      "option3": "Mumbai",
      "option4": "Chennai",
      "isBoolean": false,
      "isMcq": true,
      "isText": false,
      "correctAnswer": "New Delhi"
    }
  ]
}

Response: {
  "name": "General",
  "message": "Quiz Created Successfully!",
  "id": 1,
  "numberOfQuestions": 4
}

QuizAttempt (USER only)
GET: /api/quiz/{quizId}/questions?pageNumber=1&pageSize=1
Response: {
  "quizId": 1,
  "questionId": 2,
  "question": "2+2?",
  "level": "EASY",
  "option1": "2",
  "option2": "4",
  "option3": "6",
  "option4": "8"
}

QuizAttempt (USER only)
GET: /api/quiz/{quizId}/questions?pageNumber=1&pageSize=1

Response: {
    "quizId": 1,
    "questionId": 2,
    "question": "2+2?",
    "level": "EASY",
    "option1": "2",
    "option2": "4",
    "option3": "6",
    "option4": "8"
}

POST:
Submit quiz
Request Body : {
    "quizId": "1",
    "questions": [
        {
            "questionId": "2",
            "question": "2+2?",
            "answer": "4"
        },
        {
            "questionId": "3",
            "question": "Sun rises in east. true or false?",
            "answer": "false"
        },
        {
            "questionId": "4",
            "question": "India's capital?",
            "answer": "New Delhi"
        }
    ]
}

Response: {
    "correctAnswers": 2,
    "wrongAnswers": 1,
    "quizAttemptStatus": "FAIL"
}
POST:Submit quizRequest Body : { "quizId": "1", "questions": [ { "questionId": "2", "question": "2+2?", "answer": "4" }, { "questionId": "3", "question": "Sun rises in east. true or false?", "answer": "false" }, { "questionId": "4", "question": "India's capital?", "answer": "New Delhi" } ]}
Response: { "correctAnswers": 2, "wrongAnswers": 1, "quizAttemptStatus": "FAIL"}
