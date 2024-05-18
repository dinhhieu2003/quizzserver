package com.quizzserver.quizzserver.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizzserver.quizzserver.entity.Question;
import com.quizzserver.quizzserver.repository.QuestionRepository;
import com.quizzserver.quizzserver.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
	
	private final QuestionRepository questionRepository;
	@Override
	public List<Question> addAll(List<Question> listQuestions) {
		return questionRepository.saveAll(listQuestions);
	}

}
