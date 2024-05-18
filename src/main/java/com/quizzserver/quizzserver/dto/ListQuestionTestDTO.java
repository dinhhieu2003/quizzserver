package com.quizzserver.quizzserver.dto;

import java.io.Serializable;
import java.util.List;

import com.quizzserver.quizzserver.entity.Question;
import com.quizzserver.quizzserver.entity.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListQuestionTestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Question> listQuestion;
    private Test test;

}
