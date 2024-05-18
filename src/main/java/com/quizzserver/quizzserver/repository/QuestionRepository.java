package com.quizzserver.quizzserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizzserver.quizzserver.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
