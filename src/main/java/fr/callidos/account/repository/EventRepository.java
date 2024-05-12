package fr.callidos.account.repository;

import fr.callidos.account.models.EventModel;
import fr.callidos.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {
    List<EventModel> findByAcceptedTrue();
    List<EventModel> findByAcceptedFalse();
    List<EventModel> findByMembersAndEventStartBetweenOrMembersAndEventEndBetween(User existingUser, Date eventStart, Date eventEnd, User existingUser1, Date eventStart1, Date eventEnd1);
}
