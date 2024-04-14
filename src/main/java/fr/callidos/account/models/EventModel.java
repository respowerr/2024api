package fr.callidos.account.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events", uniqueConstraints = {
        @UniqueConstraint(columnNames = "event_name")
})
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "event_name")
    @NotBlank
    @Size(min = 4, max = 20)
    private String eventName;

    @Column(name = "event_type")
    @NotBlank
    @Size(min = 3, max = 20)
    private String eventType;

    @Column(name = "event_start", columnDefinition = "DATETIME")
    @NotBlank
    private Date eventStart;

    @Column(name = "event_end", columnDefinition = "DATETIME")
    @NotBlank
    private Date eventEnd;

    @ManyToMany
    @JoinTable(
            name = "members",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @Column(name = "location")
    @NotBlank
    @Size(min = 5, max = 50)
    private String location;

    @Column(name = "description")
    private String description;

    public EventModel(Set<User> users) {
        this.users = users;
    }

    public EventModel(String eventName, String eventType, Date eventStart, Date eventEnd, Set<User> users, String location, String description) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.users = users;
        this.location = location;
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLocation() {
        return location;
    }
}
