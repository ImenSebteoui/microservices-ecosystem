package com.example.usermanagement.service.impl;
import com.example.usermanagement.mappers.UserMapper;
import com.example.usermanagement.FeignClient.ReclamationClient;
import com.example.usermanagement.dto.ReclamationResponseDTO;
import com.example.usermanagement.dto.UserCreateDto;
import com.example.usermanagement.dto.UserDto;
import com.example.usermanagement.dto.UserUpdateDto;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserRepository userRepository;
    private final ReclamationClient reclamationClient;
    public UserDto createUser(UserCreateDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ValidationException("Email already exists");
        }

        User user = UserMapper.toEntity(dto);
        return UserMapper.toDto(userRepository.save(user));
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDto(user);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(Long id, UserUpdateDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserMapper.updateEntity(user, dto);
        return UserMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<ReclamationResponseDTO> getReclamationsByUserId(Long userId) {
        return reclamationClient.getReclamationsByUserId(userId);  // Calls the Feign client
    }
}