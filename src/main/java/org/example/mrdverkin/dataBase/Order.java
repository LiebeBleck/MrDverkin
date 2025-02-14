package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "order_doors")
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "ФИО должно быть указано ")
    private String name;

    @NotBlank(message = "Адрес должен быть указано ")
    private String address;

    @NotBlank(message = "Номер телефона должен быть указан")
    @Pattern(regexp = "^\\+?\\d{10,12}$", message = "Неверный формат номера")
    private String phone;

    private String email;

    @NotNull(message = "Дата доставки должна быть указана ")
    @Temporal(TemporalType.DATE)
    private Date dateOrdered;

    @NotNull(message = "Время доставки должно быть указано ")
    @Temporal(TemporalType.TIME)
    private Date timeOrdered;

    @NotNull(message = "Сумма должна быть указана ")
    @Min(value = 0, message = "Сумма не может быть отрицательной")
    private Float price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Door> doors = new ArrayList<>();

    private Date placedAt = new Date();

    public void addDoor(Door door) {
        this.doors.add(door);
    }
    public String getName(){
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public Date getDateOrdered() {
        return dateOrdered;
    }
    public Date getTimeOrdered() {
        return timeOrdered;
    }
    public Float getPrice() {
        return price;
    }
    public List<Door> getDoors() {
        return doors;
    }
}
