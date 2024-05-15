package com.quizzserver.quizzserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizzserver.quizzserver.entity.Test;
import com.quizzserver.quizzserver.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {
	private final TestService testService;
	@GetMapping("")
	private List<Test> getAll() {
		return testService.getAll();
	}
}