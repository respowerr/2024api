package fr.callidos.account.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles", uniqueConstraints = { @UniqueConstraint(columnNames = "id_plate")})
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_plate")
    @Size(min = 9, max = 10)
    private String id_plate;

    @Column(name = "fret_capacity")
    private int fret_capacity;

    @Column(name = "human_capacity")
    private int human_capacity;

    @Column(name = "model")
    private String model;

    @ManyToMany(mappedBy = "vehicles")
    private List<EventModel> events = new ArrayList<>();

    public VehicleModel() {
    }

    public VehicleModel(Long id, String id_plate, int fret_capacity, int human_capacity, String model) {
        this.id = id;
        this.id_plate = id_plate;
        this.fret_capacity = fret_capacity;
        this.human_capacity = human_capacity;
        this.model = model;
    }

    public void addEvent(EventModel event) {
        events.add(event);
        event.getVehicles().add(this);
    }

    public void removeEvent(EventModel event) {
        events.remove(event);
        event.getVehicles().remove(this);
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getId_plate() {
        return id_plate;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getFret_capacity() {
        return fret_capacity;
    }

    public int getHuman_capacity() {
        return human_capacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId_plate(String id_plate) {
        this.id_plate = id_plate;
    }

    public void setFret_capacity(int fret_capacity) {
        this.fret_capacity = fret_capacity;
    }

    public void setHuman_capacity(int human_capacity) {
        this.human_capacity = human_capacity;
    }
}



