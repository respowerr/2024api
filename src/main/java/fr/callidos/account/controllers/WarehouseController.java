package fr.callidos.account.controllers;

import fr.callidos.account.models.WarehouseModel;
import fr.callidos.account.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<WarehouseModel>> getAllWarehouses(){
        List<WarehouseModel> warehouses = warehouseRepository.findAll();
        return ResponseEntity.ok(warehouses);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{warehouse_id}")
    public ResponseEntity<WarehouseModel> getWarehouseById(@PathVariable Long warehouse_id){
        WarehouseModel warehouse = warehouseRepository.findById(warehouse_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse " + warehouse_id + " not found."));
        return ResponseEntity.ok(warehouse);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{warehouse_id}")
    public ResponseEntity<String> putCurrentStock(@PathVariable Long warehouse_id, @RequestBody WarehouseModel warehouseDetails){
        WarehouseModel currentstock = warehouseRepository.findById(warehouse_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse " + warehouse_id + " was not found."));
        currentstock.setCurrent_stock(warehouseDetails.getCurrent_stock());
        warehouseRepository.save(currentstock);
        return ResponseEntity.ok("Current stock was successfully updated.");
    }

}
