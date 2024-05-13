package fr.callidos.account.payload.request;

import fr.callidos.account.models.EventModel;

import java.util.List;

public class EventRequest {
    private EventModel event;
    private List<Long> vehicleIds;

    // Getters and Setters

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    public List<Long> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<Long> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
