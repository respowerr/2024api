package fr.callidos.account.controllers;

import fr.callidos.account.models.MessageModel;
import fr.callidos.account.models.TicketModel;
import fr.callidos.account.repository.MessageRepository;
import fr.callidos.account.repository.TicketRepository;
import fr.callidos.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping("/mytickets")
    public List<TicketModel> myTickets(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtusername = authentication.getName();
        return ticketRepository.findBySenderAndResolved(jwtusername, false);
    }


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{ticketId}/resolve")
    public ResponseEntity<?> resolveTicket(@PathVariable Long ticketId) {
        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket N°" + ticketId + " was not found."));
        ticket.setResolved(true);
        ticketRepository.save(ticket);
        return ResponseEntity.ok().build();
    }

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtusername = authentication.getName();
        MessageModel newMessage = new MessageModel();

        TicketModel ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket N°" + ticketId + " not found."));

        newMessage.setSender(jwtusername);
        newMessage.setMessage(message.getMessage());
        newMessage.setTicket(ticket);

        return messageRepository.save(newMessage);
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<TicketModel> getAllTickets(){
        return ticketRepository.findAll();
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public TicketModel newTicket(@RequestBody TicketModel ticket){
        TicketModel newTicket = new TicketModel();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtusername = authentication.getName();

        newTicket.setSender(jwtusername);
        newTicket.setReceiver(ticket.getReceiver());
        newTicket.setTitle(ticket.getTitle());
        newTicket.setResolved(false);
        newTicket.setDesc(ticket.getDesc());
        return ticketRepository.save(newTicket);
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
