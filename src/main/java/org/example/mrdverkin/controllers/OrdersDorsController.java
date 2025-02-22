package org.example.mrdverkin.controllers;

import jakarta.validation.Valid;
import org.example.mrdverkin.dataBase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/doors")
public class OrdersDorsController {
    @Autowired
    private DoorsRepository doorsRepository;
    @Autowired
    private OrderDoorsDoorRepository orderDoorsDoorRepository;


    @GetMapping()
    public String createOrder(Model model) {
        List<Door> doors = doorsRepository.findAll();
        model.addAttribute("order", new OrderDoors());  // Добавляем пустой заказ
        model.addAttribute("doors", doors);
        return "doors";
    }

    @ModelAttribute("door")
    public Door door() {
        return new Door();
    }

    @PostMapping
    public String addDoors(@ModelAttribute OrderDoors orderDoors, SessionStatus sessionStatus) {
//        if (orderDoors.getOrderDoorsDoors() == null || orderDoors.getOrderDoorsDoors().isEmpty()) {
//            return "redirect:/doors?error=NoDoorsSelected"; // Обработай случай, если ничего не выбрано
//        }
        orderDoorsDoorRepository.save(orderDoors);
        sessionStatus.setComplete();
        return "redirect:/orders/create";
    }


}
