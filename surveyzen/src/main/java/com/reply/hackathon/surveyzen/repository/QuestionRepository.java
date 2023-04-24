package com.reply.hackathon.surveyzen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reply.hackathon.surveyzen.entity.Question;


@Repository
public interface QuestionRepository extends MongoRepository<Question, String>
{

	List<Question> findBySurveyId(String surveyId);

	Optional<Question> findByIdAndSurveyId(String id, String surveyId);

	Question save(Question question);

	void deleteByIdAndSurveyId(String id, String surveyId);
}