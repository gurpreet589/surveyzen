package com.reply.hackathon.surveyzen.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Document(collection = "answers")
public class Answer
{

	@Id
	private String id;

	@Field("question_id")
	private String questionId;

	@Field("answer_text")
	private String answerText;



	// Constructors, getters, and setters

	public Answer()
	{
	}

	public Answer(String answerText, String questionId)
	{
		this.answerText = answerText;
		this.questionId = questionId;
	}

	// Other methods
}
