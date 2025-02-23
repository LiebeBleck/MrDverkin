package org.example.mrdverkin.dataBase.Entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "installer")
public class Installer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phone;

    @OneToMany(mappedBy = "installer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}
