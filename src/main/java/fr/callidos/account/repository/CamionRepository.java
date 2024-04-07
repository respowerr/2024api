package fr.callidos.account.repository;

import fr.callidos.account.models.CamionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends JpaRepository<CamionsModel, Long> {
}
