package com.quizzserver.quizzserver.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.quizzserver.quizzserver.entity.User;

public interface UserService {
	User save(User user);
	UserDetailsService userDetailsService();
}
