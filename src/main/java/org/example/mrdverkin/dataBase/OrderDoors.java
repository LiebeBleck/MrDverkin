package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class OrderDoors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "door_id", nullable = false) // Внешний ключ
    private Door door; // Связываем через ID

    private int countDors;
}
