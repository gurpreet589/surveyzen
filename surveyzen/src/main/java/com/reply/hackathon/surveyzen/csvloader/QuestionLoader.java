package com.reply.hackathon.surveyzen.csvloader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.reply.hackathon.surveyzen.entity.Question;
import com.reply.hackathon.surveyzen.repository.QuestionRepository;


//@Component
public class QuestionLoader //implements CommandLineRunner
{
//
//	private final QuestionRepository questionRepository;
//
//	@Autowired
//	public QuestionLoader(QuestionRepository questionRepository) {
//		this.questionRepository = questionRepository;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Resource resource = new ClassPathResource("questions.csv");
//		InputStream inputStream = resource.getInputStream();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//		CsvToBean<Question> csvToBean = new CsvToBeanBuilder<Question>(reader)
//				.withType(Question.class)
//				.withIgnoreLeadingWhiteSpace(true)
//				.build();
//		List<Question> questions = csvToBean.parse();
//		questionRepository.saveAll(questions);
//		System.out.println("Questions uploaded successfully!");
//	}
}