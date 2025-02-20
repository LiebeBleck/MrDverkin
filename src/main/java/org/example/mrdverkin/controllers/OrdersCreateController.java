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
//    @Autowired
//    private OrdersRepository ordersRepository;
//    @Autowired
//    private DoorsRepository doorsRepository;

    @GetMapping("/create")
    public String createOrder(Model model) {
//        List<Door> doors = doorsRepository.findAll();
//        model.addAttribute("order", new Order());  // Добавляем пустой заказ
//        model.addAttribute("doors", doors);
        return "create";
    }
//    @ModelAttribute("order")
//    public Order order() {
//        return new Order();
//    }
//
//    @PostMapping
//    public String createOrder(@Valid Order order, @ModelAttribute DoorsOrder doorsOrder,
//                              Errors errors, SessionStatus sessionStatus) {
//        if (errors.hasErrors()) {
//            return "create";
//        }
//        order.addDoor(doorsOrder);
//        ordersRepository.save(order);
//        sessionStatus.setComplete();
//        return "redirect:/orders/create";
//    }
}
