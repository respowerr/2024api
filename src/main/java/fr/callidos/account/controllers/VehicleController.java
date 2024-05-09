package fr.callidos.account.controllers;

import fr.callidos.account.models.VehicleModel;
import fr.callidos.account.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<VehicleModel> getVehicleById(@PathVariable Long id) {
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle N°" + id + " was not found."));
        return ResponseEntity.ok(vehicle);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<VehicleModel>> getVehicle() {
        List<VehicleModel> vehicle = vehicleRepository.findAll();
        return ResponseEntity.ok(vehicle);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody VehicleModel vehicle) {
        vehicleRepository.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vehicle was added successfully.");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> putVehicle(@PathVariable Long id, @RequestBody VehicleModel vehicleDetails) {
        VehicleModel vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle N°" + id + " was not found."));

        if (vehicleDetails.getId_plate() != null) {
            vehicle.setId_plate(vehicleDetails.getId_plate());
        }
            vehicle.setFret_capacity(vehicleDetails.getFret_capacity());
            vehicle.setHuman_capacity(vehicleDetails.getHuman_capacity());
        if (vehicleDetails.getModel() != null) {
            vehicle.setModel(vehicleDetails.getModel());
        }

        vehicleRepository.save(vehicle);
        return ResponseEntity.ok("Vehicle modified successfully.");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return ResponseEntity.ok("Vehicle N°" + id + " was successfully deleted.");
    }
}
