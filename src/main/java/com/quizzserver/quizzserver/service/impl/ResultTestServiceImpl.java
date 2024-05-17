package com.quizzserver.quizzserver.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizzserver.quizzserver.entity.ResultTest;
import com.quizzserver.quizzserver.repository.ResultTestRepository;
import com.quizzserver.quizzserver.service.ResultTestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultTestServiceImpl implements ResultTestService{

	private final ResultTestRepository resultTestRepository;
	@Override
	public ResultTest save(ResultTest resultTest) {
		return resultTestRepository.save(resultTest);
	}
	@Override
	public List<ResultTest> getAll() {
		return resultTestRepository.findAll();
	}
	@Override
	public List<ResultTest> getAllByUserId(long id) {
		return resultTestRepository.findByUser_Id(id);
	}
	@Override
	public List<ResultTest> getAllByTestId(long id) {
		return resultTestRepository.findByTest_Id(id);
	}
	@Override
	public List<ResultTest> getAllByTestIdAndUserId(long test_id, long user_id) {
		return resultTestRepository.findByTest_IdAndUser_Id(test_id, user_id);
	}

}
