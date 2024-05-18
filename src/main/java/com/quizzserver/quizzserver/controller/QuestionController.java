package com.quizzserver.quizzserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizzserver.quizzserver.dto.ListQuestionTestDTO;
import com.quizzserver.quizzserver.entity.Question;
import com.quizzserver.quizzserver.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
	private final QuestionService questionService;
	
	@PostMapping
	private List<Question> addAll(@RequestBody ListQuestionTestDTO listQuestionTestDTO) {
		for(Question question: listQuestionTestDTO.getListQuestion()) {
			question.setTest(listQuestionTestDTO.getTest());
		}
		return questionService.addAll(listQuestionTestDTO.getListQuestion());
	}
}
