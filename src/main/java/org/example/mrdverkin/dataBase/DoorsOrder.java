package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "DOORS_ORDER")
public class DoorsOrder {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private int value;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Getter
    @Setter
    @ManyToMany
    private List<Door> doors = new ArrayList<>();

    public void addDoor(Door door) {
        this.doors.add(door);
    }
}
