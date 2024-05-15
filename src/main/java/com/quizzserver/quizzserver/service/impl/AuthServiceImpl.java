package com.quizzserver.quizzserver.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizzserver.quizzserver.dto.response.LoginResponse;
import com.quizzserver.quizzserver.dto.response.SignupResponse;
import com.quizzserver.quizzserver.entity.Role;
import com.quizzserver.quizzserver.entity.User;
import com.quizzserver.quizzserver.mapper.UserMapper;
import com.quizzserver.quizzserver.repository.UserRepository;
import com.quizzserver.quizzserver.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;

	@Override
	public LoginResponse login(String email, String password) {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password));
		} catch (AuthenticationException e) {
			authentication = null;
		}

		LoginResponse loginResponse = new LoginResponse();
		// if authentication ok
		if (authentication != null && authentication.isAuthenticated()) {
			Optional<User> user = userRepository.findByEmail(email);
			loginResponse.setError(false);
			loginResponse.setMessage("Login success");
			loginResponse.setUser(UserMapper.convertUserToUserDTO(user.get()));
		} else {
			loginResponse.setError(true);
			loginResponse.setMessage("Invalid email or password");
			loginResponse.setUser(null);
		}
		return loginResponse;
	}

	@Override
	public SignupResponse signup(String email, String password, String fname, int age) {
		SignupResponse signupResponse = new SignupResponse();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(userOptional.isPresent()) {
			signupResponse.setError(true);
			signupResponse.setMessage("Email existed");
			signupResponse.setUser(null);
		} else {
			String hashedPassword = passwordEncoder.encode(password);
			User newUser = User.builder()
					.email(email)
					.fname(fname)
					.age(age)
					.role(Role.USER)
					.password(hashedPassword)
					.build();
			newUser = userRepository.save(newUser);
			signupResponse.setError(false);
			signupResponse.setMessage("sign up success");
			signupResponse.setUser(UserMapper.convertUserToUserDTO(newUser));
		}
		return signupResponse;
	}

}
