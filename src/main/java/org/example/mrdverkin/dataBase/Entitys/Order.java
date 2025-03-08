package org.example.mrdverkin.dataBase.Entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
    private String messageSeller;
    private String messageMainInstaller;

    @Column(name = "date_order")
    private LocalDate dateOrder;

    private Date placeAt = new Date();

    @NotNull(message = "Колличество дверей не указано")
    @Column(name = "FRONTDOORQUANTITY")
    private int frontDoorQuantity;
    @Column(name = "INDOORQUANTITY")
    private int inDoorQuantity;

    @ManyToOne
    private Installer installer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
