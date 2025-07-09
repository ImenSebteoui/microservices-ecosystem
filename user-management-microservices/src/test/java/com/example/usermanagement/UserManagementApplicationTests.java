package com.example.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
class UserManagementApplicationTests {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
}