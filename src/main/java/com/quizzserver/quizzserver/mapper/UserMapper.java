package com.quizzserver.quizzserver.mapper;

import com.quizzserver.quizzserver.dto.UserDTO;
import com.quizzserver.quizzserver.entity.User;

public class UserMapper {
	public static UserDTO convertUserToUserDTO(User user) {
		UserDTO userDTO = UserDTO.builder().id(user.getId())
						.email(user.getEmail())
						.fname(user.getFname())
						.age(user.getAge())
						.role(user.getRole())
						.build();
		return userDTO;
	}
}
