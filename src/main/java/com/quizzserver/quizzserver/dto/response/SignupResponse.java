package com.quizzserver.quizzserver.dto.response;

import com.quizzserver.quizzserver.dto.UserDTO;

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
public class SignupResponse {
	private Boolean error;
    private String message;
    private UserDTO user;
}
