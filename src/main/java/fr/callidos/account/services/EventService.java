package fr.callidos.account.services;

import fr.callidos.account.models.EventModel;
import fr.callidos.account.repository.EventTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventTypeRepository eventTypeRepository;

    @PostConstruct
    public void init() {
        if (eventTypeRepository.count() == 0) {
            createDefaultEventTypes();
        }
    }

    public void createDefaultEventTypes() {
        createEventType("maraude");
        createEventType("soutien_scolaire");
        createEventType("conference");
    }

    public void createEventType(String name) {
        EventModel.EventType eventType = new EventModel.EventType(name);
        eventTypeRepository.save(eventType);
    }
}