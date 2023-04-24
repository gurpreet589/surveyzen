package com.reply.hackathon.surveyzen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reply.hackathon.surveyzen.entity.Answer;


@Repository
public interface AnswerRepository extends MongoRepository<Answer, String>
{

	List<Answer> findByQuestionId(String questionId);

	Optional<Answer> findByIdAndQuestionId(String id, String questionId);

	Answer save(Answer answer);

	void deleteByIdAndQuestionId(String id, String questionId);
}
