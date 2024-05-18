package com.quizzserver.quizzserver.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizzserver.quizzserver.dto.TokenResponse;
import com.quizzserver.quizzserver.dto.response.LoginResponse;
import com.quizzserver.quizzserver.dto.response.SignupResponse;
import com.quizzserver.quizzserver.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String password) {
		System.out.println("U Here");
		return ResponseEntity.ok(authService.login(email, password));
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<SignupResponse> signup(@RequestParam String email, @RequestParam String password,
			@RequestParam String fname, @RequestParam int age) {
		System.out.println("Signup here");
		return ResponseEntity.ok(authService.signup(email, password, fname, age));
	}
	
	@PostMapping("/forgotpassword")
	public TokenResponse forgotpassword(@RequestParam String email) {
		return authService.resetpassword(email);
	}

}