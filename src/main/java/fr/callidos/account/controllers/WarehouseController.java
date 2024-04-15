package fr.callidos.account.controllers;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouse")
public class WarehouseController {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "item")
    private String item;

    public WarehouseController(Long id, String location, int capacity, String item) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.item = item;
    }

    public WarehouseController() {}

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getItem() {
        return item;
    }
}
