package fr.callidos.account.controllers;

import fr.callidos.account.models.MessageModel;
import fr.callidos.account.models.TicketModel;
import fr.callidos.account.repository.MessageRepository;
import fr.callidos.account.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{ticketId}/messages")
    public List<Object> getAllMessagesOfTicket(@PathVariable Long ticketId) {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket N°" + ticketId + " was not found."));

        return ticket.getMessages().stream()
                .map(message -> {
                    return Map.of(
                            "id", message.getId(),
                            "sender", message.getSender(),
                            "message", message.getMessage()
                    );
                })
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{ticketId}/messages")
    public MessageModel addMessageToTicket(@PathVariable Long ticketId,
                                           @RequestBody MessageModel message) {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + ticketId));
        message.setTicket(ticket);
        return messageRepository.save(message);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<TicketModel> getAllTickets(){
        return ticketRepository.findAll();
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public TicketModel newTicket(@RequestBody TicketModel ticket){
        return ticketRepository.save(ticket);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public TicketModel getTicketById(@PathVariable(value = "id") Long ticketId){
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket N°" + ticketId + " was not found."));
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable(value = "id") Long ticketId) {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket N°" + ticketId + " was not found."));

        ticketRepository.delete(ticket);
    }

}
