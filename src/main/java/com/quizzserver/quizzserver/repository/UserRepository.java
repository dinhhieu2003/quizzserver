package com.quizzserver.quizzserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizzserver.quizzserver.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);

}
