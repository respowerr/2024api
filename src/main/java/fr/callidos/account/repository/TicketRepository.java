package fr.callidos.account.repository;

import fr.callidos.account.models.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Long> {
    List<TicketModel> findBySenderAndResolved(String sender, boolean resolved);
}
