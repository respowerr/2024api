package fr.callidos.account.repository;

import fr.callidos.account.models.Egrades;
import fr.callidos.account.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByName(Egrades name);
}
