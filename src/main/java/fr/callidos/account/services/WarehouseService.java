package fr.callidos.account.services;

import fr.callidos.account.models.WarehouseModel;
import fr.callidos.account.repository.WarehouseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @PostConstruct
    public void init(){
        if (warehouseRepository.count() == 0){
            WarehouseModel SQ = new WarehouseModel(null, "Saint-Quentin", 90, 0);
            WarehouseModel LAON = new WarehouseModel(null, "Laon", 59, 0);
            warehouseRepository.save(SQ);
            warehouseRepository.save(LAON);
        }
    }
}
