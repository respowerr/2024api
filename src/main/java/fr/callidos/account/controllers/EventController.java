package fr.callidos.account.controllers;

import fr.callidos.account.models.EventModel;
import fr.callidos.account.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<EventModel>> getAllEvents(){
        List<EventModel> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EventModel> getEventById(@PathVariable Long id){
        EventModel event = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + id + " not found."));
        return ResponseEntity.ok(event);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventModel event){
        eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body("Event created with success.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> putEvent(@PathVariable Long id, @RequestBody EventModel eventDetails){
        EventModel event = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + id + " not found."));
        event.setEventEnd(eventDetails.getEventEnd());
        event.setEventName(eventDetails.getEventName());
        event.setEventStart(eventDetails.getEventStart());
        event.setEventType(eventDetails.getEventType());
        event.setLocation(eventDetails.getLocation());
        event.setMembers(eventDetails.getMembers());
        event.setDescription(eventDetails.getDescription());
        eventRepository.save(event);
        return ResponseEntity.ok("Event " + id + " was modified successfully.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id){
        eventRepository.deleteById(id);
        return ResponseEntity.ok("Event " + id + " was deleted sucessfully.");
    }


}
