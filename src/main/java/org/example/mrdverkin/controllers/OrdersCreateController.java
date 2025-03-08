package org.example.mrdverkin.controllers;

import jakarta.validation.Valid;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dataBase.Entitys.User;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.example.mrdverkin.dataBase.Repository.UserRepository;
import org.example.mrdverkin.dto.DateAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersCreateController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create")
    public String createOrder(Model model) {
        List<DateAvailability> availabilityList = DateAvailability.fromDates(orderRepository);
        model.addAttribute("availabilityList", availabilityList);
        return "create";
    }
    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @PostMapping
    public String createOrder(@Valid @ModelAttribute Order order,
                              @AuthenticationPrincipal User user,
                              Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "create";
        }
        if ((orderRepository.numberOfFrontDoorsToInstallation(order.getDateOrder()) - order.getFrontDoorQuantity()) <= 0 || (orderRepository.numberOfInDoorsToInstallation(order.getDateOrder()) - order.getInDoorQuantity()) <= 0) {
            return "create";
        }
            order.setUser(user);
            orderRepository.save(order);
            userRepository.save(user);
            sessionStatus.setComplete();

        return "done";
    }
}
