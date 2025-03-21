package org.example.mrdverkin.controllers;

import org.example.mrdverkin.dto.OrderAttribute;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/home/adminPanel")
public class AdminPanelController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String adminPanel(Model model) {
        List<OrderAttribute> adminMapping = OrderAttribute.fromOrderList(orderRepository.findAll());
        model.addAttribute("orders",adminMapping);
        return "adminPanel";
    }
}
