package com.quizzserver.quizzserver.service;

import com.quizzserver.quizzserver.dto.response.LoginResponse;
import com.quizzserver.quizzserver.dto.response.SignupResponse;

public interface AuthService {
	LoginResponse login(String email, String password);
	SignupResponse signup(String email, String password, String fname, int age);
}
