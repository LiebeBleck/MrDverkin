package org.example.mrdverkin.dataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Door {
    @Id
    private int id;
    private String doorName;
    private long price;
}
