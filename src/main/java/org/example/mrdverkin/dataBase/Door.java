package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Door {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String doorName;
    private float price;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDoorName() {
        return doorName;
    }
    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
