package org.example.mrdverkin.controllers;

import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dto.OrderAttribute;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
@RequestMapping("/home/adminPanel")
public class AdminPanelController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String adminPanel(Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderAttribute> adminMapping = OrderAttribute.fromOrderList(orders);
        model.addAttribute("orders",adminMapping);
        return "adminPanel";
    }
}
