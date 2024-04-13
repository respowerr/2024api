package fr.callidos.account.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.TimeZoneColumn;

import java.sql.Date;

@Entity
@Table(name = "events")
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "event_name")
    @Size(min = 4, max = 20)
    private String eventName;

    @Column(name = "event_type")
    @Size(min = 3, max = 20)
    private String eventType;

    @TimeZoneColumn(name = "event_start")
    private Date eventStart;

    @TimeZoneColumn(name = "event_end")
    private Date eventEnd;

    @Column(name = "members")
    private String members;

    @Column(name = "location")
    @Size(min = 5, max = 50)
    private String location;

    @Column(name = "description")
    private String description;

    public EventModel(String eventName, String eventType, Date eventStart, Date eventEnd, String members, String location, String description) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.members = members;
        this.location = location;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public EventModel() {}

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public String getMembers() {
        return members;
    }

    public String getLocation() {
        return location;
    }
}
