package com.example.usermanagement.dto;

import lombok.Data;

@Data
public class ReclamationResponseDTO {
    private String id;
    private String title;
    private String description;
    private String status;
    private Long userId;
}
