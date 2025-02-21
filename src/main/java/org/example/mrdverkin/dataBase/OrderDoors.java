package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@ToString(exclude = "order")
public class OrderDoors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Door> door = new ArrayList<>(); // Связываем через ID

    @ElementCollection
    private List<Integer> countDors = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
