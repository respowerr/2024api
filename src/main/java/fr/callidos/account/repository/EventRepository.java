package fr.callidos.account.repository;

import fr.callidos.account.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {
    List<EventModel> findByAcceptedTrue();
    List<EventModel> findByAcceptedFalse();

}
