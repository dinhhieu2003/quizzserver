package com.quizzserver.quizzserver.service;

import java.util.List;

import com.quizzserver.quizzserver.entity.ResultTest;

public interface ResultTestService {
	ResultTest save(ResultTest resultTest);
	List<ResultTest> getAll();
	List<ResultTest> getAllByUserId(long id);
	List<ResultTest> getAllByTestId(long id);
}
