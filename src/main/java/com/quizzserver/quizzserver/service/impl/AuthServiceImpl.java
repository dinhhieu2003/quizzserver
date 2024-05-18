package com.quizzserver.quizzserver.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizzserver.quizzserver.dto.TokenResponse;
import com.quizzserver.quizzserver.dto.response.LoginResponse;
import com.quizzserver.quizzserver.dto.response.SignupResponse;
import com.quizzserver.quizzserver.email.EmailSender;
import com.quizzserver.quizzserver.entity.Role;
import com.quizzserver.quizzserver.entity.User;
import com.quizzserver.quizzserver.mapper.UserMapper;
import com.quizzserver.quizzserver.repository.UserRepository;
import com.quizzserver.quizzserver.service.AuthService;
import com.quizzserver.quizzserver.service.JwtService;
import com.quizzserver.quizzserver.service.UserService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final EmailSender emailSender;

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

	@Override
	public TokenResponse resetpassword(String email) {
		System.out.println("Email: " + email);
		TokenResponse tokenResponse = new TokenResponse();
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(userOptional.isPresent()) {
			String activateToken = jwtService.generateActivateToken(userOptional.get());
			tokenResponse.setError(false);
			tokenResponse.setData(activateToken);
			try {
				emailSender.sendActivationLink(userOptional.get(), "https://quizzserver-ol4w.onrender.com", activateToken);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			tokenResponse.setError(true);
			tokenResponse.setMessage("Không tìm thấy email");
		}
		return tokenResponse;
	}

	@Override
	public TokenResponse verify(String token) {
		TokenResponse tokenResponse = new TokenResponse();
		String username = jwtService.extractUsername(token);
		if(!username.isEmpty()) {
			UserDetails userDetails = this.userService.userDetailsService().loadUserByUsername(username);
			if (jwtService.isTokenValid(token, userDetails)) {
				tokenResponse.setError(false);
				tokenResponse.setData(username);
			} else {
				tokenResponse.setError(true);
				tokenResponse.setMessage("Link hết hạn");
				tokenResponse.setData(null);
			}
		} else {
			tokenResponse.setError(true);
			tokenResponse.setMessage("Không tìm thấy người dùng");
			tokenResponse.setData(null);
		}
		return tokenResponse;
	}

	@Override
	public User changepassword(String password, long id) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent()) {
			User newUser = user.get();
			newUser.setPassword(hashedPassword);
			return userRepository.save(newUser);
		} 
		return null;
	}

}
