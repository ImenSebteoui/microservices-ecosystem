package com.example.usermanagement.FeignClient;

import com.example.usermanagement.dto.ReclamationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "claim-management") // resolved by Eureka
public interface ReclamationClient {
    @GetMapping("/api/reclamations/user/{id}")
    List<ReclamationResponseDTO> getReclamationsByUserId(@PathVariable("id") Long userId);
}