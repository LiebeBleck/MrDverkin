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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order = new Order();

}
