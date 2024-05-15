package com.quizzserver.quizzserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizzserver.quizzserver.entity.Test;

public interface TestRepository extends JpaRepository<Test, Long>{
	
}
