package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "door")
public class Door {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String doorName;
    private float price;
    private String doorType;
    private String city;
    private String color;
}
