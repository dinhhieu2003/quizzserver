package com.quizzserver.quizzserver.service;

import java.util.List;

import com.quizzserver.quizzserver.entity.Question;

public interface QuestionService {
	List<Question> addAll(List<Question> listQuestions);
}
