package tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReclamationResponseDTO {
    private String id;
    private String title;
    private String description;
    private String status;
    private Long userId;
    private String createdAt;
    private String modifiedAt;
    private String image;
}
