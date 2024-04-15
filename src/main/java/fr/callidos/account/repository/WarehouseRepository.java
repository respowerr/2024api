package fr.callidos.account.repository;

import fr.callidos.account.models.WarehouseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<WarehouseModel, Long> {
}
