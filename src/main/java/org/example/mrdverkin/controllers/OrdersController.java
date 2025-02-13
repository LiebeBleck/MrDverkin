package org.example.mrdverkin.controllers;

import org.example.mrdverkin.dataBase.Door;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }
}
