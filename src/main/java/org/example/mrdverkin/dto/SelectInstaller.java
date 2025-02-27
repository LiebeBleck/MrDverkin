package org.example.mrdverkin.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SelectInstaller {
    private String fullNameInstaller;
    private String address;
    private String orderPhone;
    private LocalDate dateOrder;
    private int quantity;
}
