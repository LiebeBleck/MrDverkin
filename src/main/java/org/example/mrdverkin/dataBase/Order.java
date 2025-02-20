package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String address;
    private String phone;
    private String email;


    @Temporal(TemporalType.DATE)
    private Date dateOrder;

    @NotNull(message = "Время доставки должно быть указано")
    @DateTimeFormat(pattern = "HH:mm")
    private Time timeOrder;

    private Float price;
    private Date placeAt = new Date();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDoors> orderDoors = new ArrayList<>();

    public void addDoor(OrderDoors door) {
        this.orderDoors.add(door);
    }
}
