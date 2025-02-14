package org.example.mrdverkin.controllers;

import jakarta.validation.Valid;
import org.example.mrdverkin.dataBase.Order;
import org.example.mrdverkin.dataBase.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/orders")
public class OrdersCreateController {
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/create")
    public String createOrder() {
        return "create";
    }
    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }
    @PostMapping
    public String createOrder(@Valid Order order,  Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "create";
        }
        ordersRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/orders/create";
    }
}
