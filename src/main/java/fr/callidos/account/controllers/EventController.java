package fr.callidos.account.controllers;

import fr.callidos.account.models.EventModel;
import fr.callidos.account.models.User;
import fr.callidos.account.models.VehicleModel;
import fr.callidos.account.payload.request.EventRequest;
import fr.callidos.account.repository.EventRepository;
import fr.callidos.account.repository.EventTypeRepository;
import fr.callidos.account.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import fr.callidos.account.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @PostMapping("/request")
    public ResponseEntity<String> newRequest(@RequestBody EventModel event) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtusername = authentication.getName();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        event.setEventStart(dateFormat.parse(event.getEventStartFormattedDate()));
        event.setEventEnd(dateFormat.parse(event.getEventEndFormattedDate()));

        event.setCreator(jwtusername);
        event.setAccepted(false);

        eventRepository.save(event);

        return ResponseEntity.status(HttpStatus.CREATED).body("The request to create your event has been registered.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/request")
    public ResponseEntity<List<EventModel>> getAllRequests(){
        List<EventModel> requests = eventRepository.findByAcceptedFalse();
        return ResponseEntity.ok(requests);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @GetMapping
    public ResponseEntity<List<EventModel>> getAllEvents(){
        List<EventModel> events = eventRepository.findByAcceptedTrue();
        return ResponseEntity.ok(events);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @GetMapping("/types")
    public ResponseEntity<List<EventModel.EventType>> getAllTypes(){
        List<EventModel.EventType> types = eventTypeRepository.findAll();
        return ResponseEntity.ok(types);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @GetMapping("/types/{id}")
    public ResponseEntity<EventModel.EventType> getTypeById(@PathVariable Long id){
        EventModel.EventType type = eventTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found."));
        return ResponseEntity.ok(type);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @GetMapping("/types/{id}")
    public ResponseEntity<String> deleteTypeById(@PathVariable Long id){
        eventTypeRepository.deleteById(id);
        return ResponseEntity.ok("Type " + id + " was deleted sucessfully.");
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @GetMapping("/{event_id}")
    public ResponseEntity<EventModel> getEventById(@PathVariable Long event_id){
        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " not found."));
        return ResponseEntity.ok(event);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/accept/{request_id}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long request_id) {
        EventModel event = eventRepository.findById(request_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request " + request_id + " not found."));

        event.setAccepted(true);
        eventRepository.save(event);

        return ResponseEntity.ok("Request " + request_id + " was accepted successfully.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventRequest eventRequest) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtusername = authentication.getName();

        EventModel event = eventRequest.getEvent();
        List<Long> vehicleIds = eventRequest.getVehicleIds();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        event.setEventStart(dateFormat.parse(event.getEventStartFormattedDate()));
        event.setEventEnd(dateFormat.parse(event.getEventEndFormattedDate()));

        event.setCreator(jwtusername);
        event.setAccepted(true);

        User creator = userRepository.findByUsername(jwtusername)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        event.addMember(creator);

        List<VehicleModel> vehicles = new ArrayList<>();
        if (vehicleIds != null && !vehicleIds.isEmpty()) {
            for (Long vehicleId : vehicleIds) {
                Optional<VehicleModel> optionalVehicle = vehicleRepository.findById(vehicleId);
                optionalVehicle.ifPresent(vehicles::add);
            }
        }
        event.setVehicles(vehicles);

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

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @PostMapping("/{event_id}/join")
    public ResponseEntity<String> joinEvent(@PathVariable Long event_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtusername = authentication.getName();

        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " was not found."));
        Optional<User> existingUserOptional = userRepository.findByUsername(jwtusername);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            if (event.isMember(existingUser)) {
                return ResponseEntity.badRequest().body("User " + jwtusername + " is already a member of event " + event_id + ".");
            }

            List<EventModel> userEvents = eventRepository.findByMembersAndEventStartBetweenOrMembersAndEventEndBetween(existingUser,
                    event.getEventStart(),
                    event.getEventEnd(),
                    existingUser,
                    event.getEventStart(),
                    event.getEventEnd());
            if (!userEvents.isEmpty()) {
                return ResponseEntity.badRequest().body("User " + jwtusername + " already has an event scheduled during this time.");
            }

            event.addMember(existingUser);
            eventRepository.save(event);
            return ResponseEntity.ok("User " + jwtusername + " joined event " + event_id + " successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + jwtusername + " was not found.");
        }
    }


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_BENEFICIAIRE') or hasRole('ROLE_PARTENAIRE')")
    @DeleteMapping("/{event_id}/quit")
    public ResponseEntity<String> quitEvent(@PathVariable Long event_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtusername = authentication.getName();

        EventModel event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event " + event_id + " was not found."));
        Optional<User> existingUserOptional = userRepository.findByUsername(jwtusername);

            User existingUser = existingUserOptional.get();
            event.deleteMember(existingUser);
            eventRepository.save(event);
            return ResponseEntity.ok("User " + jwtusername + " quit event " + event_id + " successfully.");
    }

}
