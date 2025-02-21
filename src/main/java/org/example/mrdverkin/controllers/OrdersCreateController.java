package org.example.mrdverkin.controllers;

import jakarta.validation.Valid;
import org.example.mrdverkin.dataBase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersCreateController {
    @Autowired
    private OrderDoorsDoorRepository orderDoorsDoorRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/create")
    public String createOrder(Model model) {
        List<Door> doors = orderDoorsDoorRepository.findAllDoors();
        model.addAttribute("order", new Order());  // Добавляем пустой заказ
        model.addAttribute("doors", doors);
        return "create";
    }
    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @PostMapping
    public String createOrder(@Valid @ModelAttribute Order order,
                              Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "create";
        }
        OrderDoorsDoor lastOrderDoors =orderDoorsDoorRepository.findLastOrderDoors();
        order.setOrderDoors(lastOrderDoors);
        order.setOrderDoors(lastOrderDoors);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "done";
//        return "redirect:/orders/create/done";
    }
}
