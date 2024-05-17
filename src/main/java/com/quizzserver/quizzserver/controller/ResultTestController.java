package com.quizzserver.quizzserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizzserver.quizzserver.entity.ResultTest;
import com.quizzserver.quizzserver.service.ResultTestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class ResultTestController {
	private final ResultTestService resultTestService;
	
	@PostMapping
	private ResultTest addResultTest(@RequestBody ResultTest resultTest) {
		System.out.println("Submit");
		ResultTest body = resultTestService.save(resultTest);
		return body;
	}
	
	@GetMapping
	private List<ResultTest> getAll() {
		System.out.println("Get all result");
		return resultTestService.getAll(); 
	}
	
	@GetMapping("/{id}")
	private List<ResultTest> getAllByUserId(@PathVariable("id") long id) {
		return resultTestService.getAllByUserId(id);
	}
	
	@GetMapping("/test/{id}")
	private List<ResultTest> getAllByTestId(@PathVariable("id") long id) {
		System.out.println("Get all by test");
		return resultTestService.getAllByTestId(id);
	}
	
	@GetMapping("/test/{test_id}/{user_id}")
	private List<ResultTest> getAllByTestIdAndUserId(@PathVariable("test_id") long test_id, 
			@PathVariable("user_id") long user_id) {
		return resultTestService.getAllByTestIdAndUserId(test_id, user_id);
	}
}
