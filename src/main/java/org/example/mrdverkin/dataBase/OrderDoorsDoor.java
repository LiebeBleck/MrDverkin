package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_doors_door")
public class OrderDoorsDoor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "door_id", nullable = false)
    private List<Door> door = new ArrayList<>();

    @ElementCollection
    private List<Integer> quantity = new ArrayList<>();
}
