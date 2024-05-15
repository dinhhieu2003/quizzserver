package com.quizzserver.quizzserver.dto;

import com.quizzserver.quizzserver.entity.Role;

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
public class UserDTO {
	private long id;
    private String fname;
    private int age;
    private String email;
    private Role role;
}
