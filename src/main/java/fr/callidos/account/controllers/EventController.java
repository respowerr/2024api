package fr.callidos.account.controllers;

import fr.callidos.account.models.EventModel;
import fr.callidos.account.models.User;
import fr.callidos.account.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import fr.callidos.account.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
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
        if (eventDetails.getEventEnd() != null) {
            event.setEventEnd(eventDetails.getEventEnd());
        }
        if (eventDetails.getEventName() != null) {
            event.setEventName(eventDetails.getEventName());
        }
        if (eventDetails.getEventStart() != null) {
            event.setEventStart(eventDetails.getEventStart());
        }
        if (eventDetails.getEventType() != null) {
            event.setEventType(eventDetails.getEventType());
        }
        if (eventDetails.getLocation() != null) {
            event.setLocation(eventDetails.getLocation());
        }
        if (eventDetails.getDescription() != null) {
            event.setDescription(eventDetails.getDescription());
        }
        eventRepository.save(event);
        return ResponseEntity.ok("Event " + event_id + " was modified successfully.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{event_id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long event_id){
        eventRepository.deleteById(event_id);
        return ResponseEntity.ok("Event " + event_id + " was deleted sucessfully.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/{event_id}/join/{username}")
    public ResponseEntity<String> joinEvent(@PathVariable Long event_id, @PathVariable String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();

        if(!loggedInUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You shall not pass - Gandalf.");
        }

        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " was not found."));
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        if(existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            event.addMember(existingUser);
            eventRepository.save(event);
            return ResponseEntity.ok("User " + username + " joined event " + event_id + " successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + username + " was not found.");
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @DeleteMapping("/{event_id}/quit/{username}")
    public ResponseEntity<String> quiEvent(@PathVariable Long event_id, @PathVariable String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();

        if(!loggedInUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You shall not pass - Gandalf.");
        }

        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " was not found."));
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        if(existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            event.deleteMember(existingUser);
            eventRepository.save(event);
            return ResponseEntity.ok("User " + username + " quit event " + event_id + " successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + username + " was not found.");
        }
    }

}
