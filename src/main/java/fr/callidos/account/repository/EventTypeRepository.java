package fr.callidos.account.repository;

import fr.callidos.account.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTypeRepository extends JpaRepository<EventModel.EventType, Long> {
    EventModel.EventType findByName(String name);
}
