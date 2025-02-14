package org.example.mrdverkin.controllers;

import jakarta.validation.Valid;
import org.example.mrdverkin.dataBase.Order;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/orders")
public class OrdersCreateController {

    @GetMapping("/create")
    public String createOrder() {
        return "create";
    }
    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }
    @PostMapping
    public String createOrder(@Valid Order order,  Errors errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "create";
        }
        return "redirect:/orders/create";
    }
}
