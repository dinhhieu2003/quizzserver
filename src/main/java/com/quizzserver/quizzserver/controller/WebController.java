package com.quizzserver.quizzserver.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quizzserver.quizzserver.dto.TokenResponse;
import com.quizzserver.quizzserver.entity.User;
import com.quizzserver.quizzserver.repository.UserRepository;
import com.quizzserver.quizzserver.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class WebController {
	private final AuthService authService;
	private final UserRepository userRepository;
	
	@GetMapping("/verify/{token}")
	public String verifyToken(@PathVariable("token") String token, Model model) {
		System.out.println(token);
		TokenResponse tokenResponse = authService.verify(token);
		if(tokenResponse.isError()) {
			return "tokenExpired";
		}
		Optional<User> user = this.userRepository.findByEmail(tokenResponse.getData());
		if(user.isPresent()) {
			System.out.println("Có vào đây");
			model.addAttribute("id", user.get().getId());
		}
		model.addAttribute("id", user.get().getId());
		return "resetpassword";
	}
	
	@PostMapping("/resetpassword")
	public String resetpassword(@RequestParam long id, @RequestParam String password) {
		System.out.println("Vao trang reset");
		if(authService.changepassword(password, id) != null) {
			return "success";
		} 
		return "failed";
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}
}
