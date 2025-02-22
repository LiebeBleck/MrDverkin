package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_doors")
public class OrderDoors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Door> door = new ArrayList<>();

    @ElementCollection
    private List<Integer> quantity = new ArrayList<>();
}
