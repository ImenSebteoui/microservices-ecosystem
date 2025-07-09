package com.example.usermanagement.controller;

import com.example.usermanagement.dto.ReclamationResponseDTO;
import com.example.usermanagement.dto.UserCreateDto;
import com.example.usermanagement.dto.UserDto;
import com.example.usermanagement.dto.UserUpdateDto;
import com.example.usermanagement.model.User;
import com.example.usermanagement.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(id, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @GetMapping("/{id}/reclamations")
    public ResponseEntity<List<ReclamationResponseDTO>> getReclamationsByUserId(@PathVariable Long id) {
        // Fetch reclamations from the service, which will call the Feign client
        List<ReclamationResponseDTO> reclamations = userService.getReclamationsByUserId(id);
        return ResponseEntity.ok(reclamations);
    }
}