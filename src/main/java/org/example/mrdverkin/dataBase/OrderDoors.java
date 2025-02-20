package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class OrderDoors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "door_id", nullable = false) // Внешний ключ
    private Door door; // Связываем через ID

    private int countDors;
    @Transient
    private List<Integer> count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Transient // Это поле не сохраняется в БД, оно временное для формы
    private List<Long> doorIds;
}
