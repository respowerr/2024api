package fr.callidos.account.controllers;

import fr.callidos.account.models.EventModel;
import fr.callidos.account.models.User;
import fr.callidos.account.repository.EventRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import fr.callidos.account.repository.UserRepository;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<EventModel>> getAllEvents(){
        List<EventModel> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{event_id}")
    public ResponseEntity<EventModel> getEventById(@PathVariable Long event_id){
        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " not found."));
        return ResponseEntity.ok(event);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventModel event){
        eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body("Event created with success.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{event_id}")
    public ResponseEntity<String> putEvent(@PathVariable Long event_id, @RequestBody EventModel eventDetails){
        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " not found."));
        event.setEventEnd(eventDetails.getEventEnd());
        event.setEventName(eventDetails.getEventName());
        event.setEventStart(eventDetails.getEventStart());
        event.setEventType(eventDetails.getEventType());
        event.setLocation(eventDetails.getLocation());
        event.setDescription(eventDetails.getDescription());
        eventRepository.save(event);
        return ResponseEntity.ok("Event " + event_id + " was modified successfully.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{event_id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long event_id){
        eventRepository.deleteById(event_id);
        return ResponseEntity.ok("Event " + event_id + " was deleted sucessfully.");
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/{event_id}/join")
    public ResponseEntity<String> joinEvent(@PathVariable Long event_id, @RequestBody Long id){
        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " not found."));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found."));
        event.addMember(user);
        eventRepository.save(event);
        return ResponseEntity.ok("User N°" + id + " joined event N°" + event_id + ".");
    }
}
