package com.reply.hackathon.surveyzen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.reply.hackathon.surveyzen.entity.Answer;
import com.reply.hackathon.surveyzen.entity.Question;
import com.reply.hackathon.surveyzen.repository.AnswerRepository;
import com.reply.hackathon.surveyzen.repository.QuestionRepository;


@RestController
@RequestMapping("/survey")
@CrossOrigin(origins = "http://localhost:4200")
public class SurveyController implements WebMvcConfigurer
{

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE");
	}

	@GetMapping("/questions")
	public List<Question> getAllQuestions() {
		return questionRepository.findAll();


	}

	@PostMapping("/questions")
	public Question createQuestion(@RequestBody Question question) {
		return questionRepository.save(question);
	}


	@DeleteMapping("/surveys/{surveyId}/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		Optional<Question> question = questionRepository.findByIdAndSurveyId(questionId, surveyId);
		if (!question.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		questionRepository.deleteByIdAndSurveyId(questionId, surveyId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/surveys/questions")
	public ResponseEntity<?> deleteAllQuestions() {
		questionRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}


	@PostMapping("/answers")
	public ResponseEntity<String> saveAnswers(@RequestBody List<Answer> answers) {
		try {
			for (Answer answer : answers) {
				answerRepository.save(answer);
			}
			return ResponseEntity.ok().body("Answers saved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving answers");
		}
	}


	@GetMapping("/answers")
	public List<Answer> getAllAnswers() {
		return answerRepository.findAll();
	}


	@DeleteMapping("/surveys/answers")
	public ResponseEntity<?> deleteAllAnswers() {
		answerRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}

}
