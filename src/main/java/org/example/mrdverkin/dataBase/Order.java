package org.example.mrdverkin.dataBase;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "order_doors")
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

    @Temporal(TemporalType.DATE)
    private Date dateOrdered;

    @NotNull(message = "Время доставки должно быть указано")
    @DateTimeFormat(pattern = "HH:mm")
    private Time timeOrdered;

    @NotNull(message = "Сумма должна быть указана ")
    @Min(value = 0, message = "Сумма не может быть отрицательной")
    private Float price;

    @OneToMany
    @JoinColumn(name = "order_id")
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
    public String getTimeOrdered() {
        return String.valueOf(timeOrdered);
    }
    public Float getPrice() {
        return price;
    }
    public List<Door> getDoors() {
        return doors;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDateOrdered(String dateString) {
        if (dateString != null) {
            try {
                // Преобразование строки в объект Date
                this.dateOrdered = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            } catch (ParseException e) {}
        }
    }

    public void setTimeOrdered(String timeString) {
        if (timeString != null) {
            // Добавляем секунды, чтобы строка стала в формате HH:mm:ss
            timeString = timeString + ":00";  // Добавляем секунд
            this.timeOrdered = Time.valueOf(timeString);  // Конвертируем в Time
        }
    }

    public void setPrice(Float price){
        this.price = price;
    }
    public void setDoors(List<Door> doors){
        this.doors = doors;
    }
}
