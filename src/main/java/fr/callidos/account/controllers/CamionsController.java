package fr.callidos.account.controllers;

import fr.callidos.account.models.CamionsModel;
import fr.callidos.account.repository.CamionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/camions")
public class CamionsController {

    @Autowired
    private CamionRepository camionRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CamionsModel> getCamionById(@PathVariable Long id) {
        CamionsModel camion = camionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Camion introuvable avec l'ID : " + id));
        return ResponseEntity.ok(camion);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<CamionsModel>> getCamions() {
        List<CamionsModel> camions = camionRepository.findAll();
        return ResponseEntity.ok(camions);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> ajouterCamion(@RequestBody CamionsModel camion) {
        camionRepository.save(camion);
        return ResponseEntity.status(HttpStatus.CREATED).body("Le camion a été ajouté avec succès.");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> modifierCamion(@PathVariable Long id, @RequestBody CamionsModel camionDetails) {
        CamionsModel camion = camionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Camion introuvable avec l'ID : " + id));
        camion.setPlaqueImmatriculation(camionDetails.getPlaqueImmatriculation());
        camion.setCapacite(camionDetails.getCapacite());
        camion.setTourneeId(camionDetails.getTourneeId());
        camionRepository.save(camion);
        return ResponseEntity.ok("Le camion a été modifié avec succès.");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerCamion(@PathVariable Long id) {
        camionRepository.deleteById(id);
        return ResponseEntity.ok("Le camion a été supprimé avec succès.");
    }
}
