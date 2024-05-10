package fr.callidos.account.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.List;

@Entity
@Table(name = "warehouses")
public class WarehouseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long warehouse_id;

    @Column(name = "location")
    private String location;

    @Column(name = "total_capacity") // En rack
    @Min(0)
    private int rack_capacity;

    @Column(name = "utilization") // Pourcentage de capacité utilisée
    @Min(0)
    private double utilization;

    @Column(name = "current_stock") // En rack, MAX la total_capacity, MIN 0
    @Min(0)
    private int current_stock;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRack_capacity(int rack_capacity) {
        this.rack_capacity = rack_capacity;
    }

    public void setUtilization(double utilization) {
        this.utilization = utilization;
    }

    public void setCurrent_stock(int current_stock) {
        this.current_stock = current_stock;
        calculUtilization();
    }

    public Long getWarehouse_id() {
        return warehouse_id;
    }

    public String getLocation() {
        return location;
    }

    public int getRack_capacity() {
        return rack_capacity;
    }

    public double getUtilization() {
        return utilization;
    }

    public int getCurrent_stock() {
        return current_stock;
    }

    public WarehouseModel(Long warehouse_id, String location, int rack_capacity, int current_stock) {
        this.warehouse_id = warehouse_id;
        this.location = location;
        this.rack_capacity = rack_capacity;
        this.current_stock = current_stock;
        calculUtilization();
    }

    public WarehouseModel() {}

    private void calculUtilization(){
        if (rack_capacity != 0){
            utilization = ((double) current_stock / rack_capacity) * 100;
        } else {
            utilization = 0.0;
        }
    }
}
