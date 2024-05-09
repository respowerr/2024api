package fr.callidos.account.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events", uniqueConstraints = {
        @UniqueConstraint(columnNames = "event_name")
})
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long event_id;

    @Column(name = "event_name")
    @NotBlank
    @Size(min = 4, max = 20)
    private String eventName;

    @Column(name = "event_type")
    @NotBlank
    @Size(min = 3, max = 20)
    private String eventType;

    @Column(name = "event_start", columnDefinition = "DATETIME")
    private Date eventStart;

    @Column(name = "event_end", columnDefinition = "DATETIME")
    private Date eventEnd;

    @Column(name = "location")
    @NotBlank
    @Size(min = 5, max = 50)
    private String location;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "event_members",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<User> members = new ArrayList<>();

    public void addMember(User user) {
        members.add(user);
        user.getEvents().add(this);
    }

    public void deleteMember(User user) {
        members.remove(user);
        user.getEvents().add(this);
    }

    public EventModel(String eventName, String eventType, Date eventStart, Date eventEnd, String location, String description) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.location = location;
        this.description = description;
    }
    public EventModel() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return event_id;
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

    public String getLocation() {
        return location;
    }

}
