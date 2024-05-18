package com.quizzserver.quizzserver.service;

import java.util.List;

import com.quizzserver.quizzserver.entity.Test;

public interface TestService {
	List<Test> getAll();
	Test add(Test test);
}
