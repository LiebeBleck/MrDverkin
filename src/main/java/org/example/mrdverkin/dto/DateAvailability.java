package org.example.mrdverkin.dto;

import lombok.Data;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DateAvailability {
    private LocalDate date;
    private Integer availableDoors;

    public DateAvailability(LocalDate date, Integer availableDoors) {
        this.date = date;
        this.availableDoors = availableDoors;
    }

    public static List<DateAvailability> fromDates(OrderRepository orderRepository) {
        List<DateAvailability> availabilityList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 14; i++ ){
            DateAvailability availability = new DateAvailability(today, orderRepository.numberOfDoorsToInstallation(today));
            availabilityList.add(availability);
            today = today.plusDays(1);
        }

        return availabilityList;
    }
}
