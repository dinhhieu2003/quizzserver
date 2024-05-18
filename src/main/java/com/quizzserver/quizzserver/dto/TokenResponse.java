package com.quizzserver.quizzserver.dto;

import java.io.Serializable;

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
public class TokenResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean error;
	private String message;
	private String data;
}
