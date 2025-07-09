package tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Dto.ReclamationRequestDTO;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Dto.ReclamationResponseDTO;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.Entities.Reclamation;
import tn.esprit.pi.reclamationmanagement.reclamationmanagementmicroservice.services.ReclamationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamations")

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ReclamationController {
    @Autowired
    private final ReclamationService reclamationService;

    @Autowired
    public ReclamationController(ReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

   @PostMapping("/{userId}")
   public ReclamationResponseDTO create(@PathVariable Long userId,
                                        @Valid @RequestBody ReclamationRequestDTO dto) {
       return reclamationService.createReclamation(userId, dto);
   }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        reclamationService.deleteReclamation(id);
    }
    @GetMapping("/{id}")
    public ReclamationResponseDTO getById(@PathVariable String id) {
        return reclamationService.getById(id);
    }
    @GetMapping
    public List<ReclamationResponseDTO> getAll() {
        return reclamationService.getAll();
    }

    @PutMapping("/{id}")
    public ReclamationResponseDTO update(@PathVariable String id,
                                         @Valid @RequestBody ReclamationRequestDTO dto) {
        return reclamationService.update(id, dto);
    }

    @GetMapping("/user/{id}")
    public List<ReclamationResponseDTO> getByUserId(@PathVariable Long id) {
        return reclamationService.getByUserId(id);
    }
}
