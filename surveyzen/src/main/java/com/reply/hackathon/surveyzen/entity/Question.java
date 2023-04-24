package com.reply.hackathon.surveyzen.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Document(collection = "questions")
public class Question
{

	@Id
	private String id;


	@Field("question_text")
	private String questionText;

	@Field("category")
	private String category;


	@Field("type")
	private String type;

	@Field("surveyId")
	private String surveyId;

	@Field("answers")
	private List<Answer> answers;

	// Constructors, getters, and setters

	public Question()
	{
	}

	public Question(String questionText, String category, String type, List<Answer> answers, String surveyId)
	{
		this.questionText = questionText;
		this.category = category;
		this.type = type;
		this.answers = answers;
		this.surveyId = surveyId;
	}

	// Other methods

	public void addAnswer(Answer answer)
	{
		if (answers == null)
		{
			answers = new ArrayList<>();
		}
		answers.add(answer);
	}

	public void removeAnswer(Answer answer)
	{
		if (answers != null)
		{
			answers.remove(answer);
		}
	}
}
