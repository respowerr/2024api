package fr.callidos.account.controllers;

import fr.callidos.account.models.TicketModel;
import fr.callidos.account.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public List<TicketModel> getAllTickets(){
        return ticketRepository.findAll();
    }

    @PostMapping
    public TicketModel newTicket(@RequestBody TicketModel ticket){
        return ticketRepository.save(ticket);
    }

    @GetMapping("/{id}")
    public TicketModel getTicketById(@PathVariable(value = "id") Long ticketId){
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket N°" + ticketId + " was not found."));
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable(value = "id") Long ticketId) {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket N°" + ticketId + " was not found."));

        ticketRepository.delete(ticket);
    }

}
