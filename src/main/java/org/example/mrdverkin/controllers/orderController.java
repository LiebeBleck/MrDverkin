package org.example.mrdverkin.controllers;

import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dto.OrderAttribute;
import org.example.mrdverkin.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders/edit")
public class orderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Order order = orderService.findOrderById(id);
        OrderAttribute orderAttribute = new OrderAttribute().fromOrder(order);
        model.addAttribute("orderAttribute", orderAttribute);
        return "editOrder";
    }

    @PostMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute("orderAttribute") OrderAttribute orderAttribute, BindingResult bindingResult) {
        if (orderService.updateOrder(id, orderAttribute,bindingResult).hasErrors()){
            return "editOrder";
        }
        return "redirect:/listOrdersSeller"; // Перенаправляем на список заказов после сохранения
    }
}
