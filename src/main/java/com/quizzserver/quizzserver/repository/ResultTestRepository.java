package com.quizzserver.quizzserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizzserver.quizzserver.entity.ResultTest;

public interface ResultTestRepository extends JpaRepository<ResultTest, Long>{
	List<ResultTest> findByUser_Id(long id);
	List<ResultTest> findByTest_Id(long id);
	List<ResultTest> findByTest_IdAndUser_Id(long test_id, long user_id);
}
