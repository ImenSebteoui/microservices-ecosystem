package tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Dto.ReclamationRequestDTO;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Dto.ReclamationResponseDTO;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Entities.Reclamation;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Enums.ReclamationStatus;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Mappers.ReclamationMapper;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.repository.IReclamationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReclamationService {

    private final IReclamationRepository reclamrepository;
/*
    private final UserClient userClient;
*/

/*
    public UserDto fetchUserInfo(String userId) {
        return userClient.getUserById(userId);
    }
*/

    public ReclamationResponseDTO createReclamation(Long userId, ReclamationRequestDTO dto) {
        Reclamation reclamation = ReclamationMapper.toEntity(dto, userId);
        return ReclamationMapper.toDTO(reclamrepository.save(reclamation));
    }

    public void deleteReclamation(String id) {
        reclamrepository.deleteById(id);
    }

    public ReclamationResponseDTO getById(String id) {
        return reclamrepository.findById(id)
                .map(ReclamationMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));
    }

    public List<ReclamationResponseDTO> getAll() {
        return reclamrepository.findAll()
                .stream()
                .map(ReclamationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReclamationResponseDTO update(String id, ReclamationRequestDTO dto) {
        Reclamation existing = reclamrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reclamation not found"));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setImage(dto.getImage());
        existing.setModifiedAt(LocalDateTime.now());

        if (dto.getStatus() != null) {
            existing.setStatus(ReclamationStatus.valueOf(dto.getStatus()));
        }

        return ReclamationMapper.toDTO(reclamrepository.save(existing));
    }


    public List<ReclamationResponseDTO> getByUserId(Long userId) {
        List<Reclamation> reclamations = reclamrepository.findByUserId(userId);
        return reclamations.stream()
                .map(ReclamationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
