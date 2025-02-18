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
@RequestMapping("/doors")
public class OrdersDorsController {
    @Autowired
    private DoorsRepository doorsRepository;


    @GetMapping()
    public String createOrder(Model model) {
        List<Door> doors = doorsRepository.findAll();
        model.addAttribute("order", new Order());  // Добавляем пустой заказ
        model.addAttribute("doors", doors);
        return "doors";
    }

    @ModelAttribute("DoorsOrder")
    public DoorsOrder doorsOrder() {
        return new DoorsOrder();
    }

    @ModelAttribute("door")
    public Door door() {
        return new Door();
    }

    @PostMapping
    public String addDoors(@ModelAttribute DoorsOrder doorsOrder,
                              @Valid Door door, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "create";
        }
        doorsOrder.addDoor(door);
        System.out.println("adadasdadsd");
        sessionStatus.setComplete();
        return "redirect:/orders/create";
    }
}
