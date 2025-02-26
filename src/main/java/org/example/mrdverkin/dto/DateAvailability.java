package org.example.mrdverkin.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateAvailability {
    private LocalDate date;
    private Integer availableDoors;

    public DateAvailability(LocalDate date, Integer availableDoors) {
        this.date = date;
        this.availableDoors = availableDoors;
    }
}
