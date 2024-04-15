package fr.callidos.account.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehouse")
public class WarehouseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long warehouse_id;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<ItemQuantity> items;

    public WarehouseModel(Long warehouse_id, String location, int capacity, List<ItemQuantity> items) {
        this.warehouse_id = warehouse_id;
        this.location = location;
        this.capacity = capacity;
        this.items = items;
    }

    public void setWarehouse_id(Long warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setItems(List<ItemQuantity> items) {
        this.items = items;
    }

    public Long getWarehouse_id() {
        return warehouse_id;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<ItemQuantity> getItems() {
        return items;
    }

    public WarehouseModel() {}

    @Entity
    @Table(name = "item_quantity")
    public static class ItemQuantity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(name = "item_name")
        private String itemName;

        @Column(name = "count")
        private int count;

        @ManyToOne
        @JoinColumn(name = "warehouse_id")
        private WarehouseModel warehouse;

        public ItemQuantity(Long id, String itemName, int count, WarehouseModel warehouse) {
            this.id = id;
            this.itemName = itemName;
            this.count = count;
            this.warehouse = warehouse;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setWarehouse(WarehouseModel warehouse) {
            this.warehouse = warehouse;
        }

        public Long getId() {
            return id;
        }

        public String getItemName() {
            return itemName;
        }

        public int getCount() {
            return count;
        }

        public WarehouseModel getWarehouse() {
            return warehouse;
        }

        public ItemQuantity() {}

    }
}
