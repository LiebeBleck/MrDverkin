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
    private OrderDorsRepository orderDorsRepository;


    @GetMapping()
    public String createOrder(Model model) {
        List<Door> doors = doorsRepository.findAll();
        model.addAttribute("order", new OrderDoors());  // Добавляем пустой заказ
        model.addAttribute("doors", doors);
        return "doors";
    }

    @ModelAttribute("orderDoors")
    public OrderDoors doorsOrder() {
        return new OrderDoors();
    }

    @ModelAttribute("door")
    public Door door() {
        return new Door();
    }

    @PostMapping
    public String addDoors(@ModelAttribute OrderDoors orderDoors, SessionStatus sessionStatus) {
        List<Long> doorIds = orderDoors.getDoorIds(); // Получаем список ID дверей из объекта
        List<Integer> count = orderDoors.getCount();

        if (doorIds == null || doorIds.isEmpty()) {
            return "redirect:/doors?error=NoDoorsSelected"; // Обработай случай, если ничего не выбрано
        }

        List<Door> selectedDoors = doorsRepository.findAllById(doorIds); // Загружаем двери по ID

        for (Door door : selectedDoors) {
            OrderDoors newOrderDoors = new OrderDoors();
            newOrderDoors.setDoor(door);
            newOrderDoors.setCountDors(count.get(0));
            count.remove(0);
            orderDorsRepository.save(newOrderDoors);
        }

        sessionStatus.setComplete();
        return "redirect:/orders/create";
    }


}
