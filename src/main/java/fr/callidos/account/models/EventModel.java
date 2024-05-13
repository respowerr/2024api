package fr.callidos.account.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
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

    @Column(name = "event_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventStart;

    @Column(name = "event_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventEnd;

    @Column(name = "location")
    @NotBlank
    @Size(min = 5, max = 50)
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "creator")
    private String creator;

    @Column(name = "accepted")
    private Boolean accepted;

    @ManyToMany
    @JoinTable(
            name = "event_members",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<User> members = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "event_vehicles",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    @JsonIgnore
    private List<VehicleModel> vehicles = new ArrayList<>();

    public void addVehicle(VehicleModel vehicle) {
        vehicles.add(vehicle);
        vehicle.getEvents().add(this);
    }

    public void removeVehicle(VehicleModel vehicle) {
        vehicles.remove(vehicle);
        vehicle.getEvents().remove(this);
    }

    public List<VehicleModel> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleModel> vehicles) {
        this.vehicles = vehicles;
    }

    public void addMember(User user) {
        members.add(user);
        user.getEvents().add(this);
    }

    public void deleteMember(User user) {
        members.remove(user);
        user.getEvents().add(this);
    }

    public EventModel(Long id, String eventName, String eventType, Date eventStart, Date eventEnd, String location, String description) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.location = location;
        this.description = description;
    }


    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public boolean isMember(User user) {
        return members.contains(user);
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Long getId() {
        return id;
    }

    public Boolean getAccepted() {
        return accepted;
    }

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

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public String getLocation() {
        return location;
    }

    public EventModel() {}

    public String getEventStartFormattedDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(eventStart);
    }
    public String getEventEndFormattedDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(eventEnd);
    }
}
