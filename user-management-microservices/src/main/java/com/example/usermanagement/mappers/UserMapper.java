package com.example.usermanagement.mappers;

import com.example.usermanagement.dto.UserCreateDto;
import com.example.usermanagement.dto.UserDto;
import com.example.usermanagement.dto.UserUpdateDto;
import com.example.usermanagement.model.User;

public class UserMapper {
    public static User toEntity(UserCreateDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .build();
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static void updateEntity(User user, UserUpdateDto dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
    }

}
