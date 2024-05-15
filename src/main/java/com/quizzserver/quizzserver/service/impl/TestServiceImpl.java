package com.quizzserver.quizzserver.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizzserver.quizzserver.entity.Test;
import com.quizzserver.quizzserver.repository.TestRepository;
import com.quizzserver.quizzserver.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{

	private final TestRepository testRepository;
	@Override
	public List<Test> getAll() {
		return testRepository.findAll();
	}

}
