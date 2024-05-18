package com.quizzserver.quizzserver.service;

import com.quizzserver.quizzserver.dto.TokenResponse;
import com.quizzserver.quizzserver.dto.response.LoginResponse;
import com.quizzserver.quizzserver.dto.response.SignupResponse;
import com.quizzserver.quizzserver.entity.User;

public interface AuthService {
	LoginResponse login(String email, String password);
	SignupResponse signup(String email, String password, String fname, int age);
	TokenResponse resetpassword(String email);
	TokenResponse verify(String token);
	User changepassword(String password, long id);
}
