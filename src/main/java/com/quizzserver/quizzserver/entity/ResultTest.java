package com.quizzserver.quizzserver.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name="result_test")
public class ResultTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int score;
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private Test test;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
