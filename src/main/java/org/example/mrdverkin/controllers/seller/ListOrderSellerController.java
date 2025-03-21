package org.example.mrdverkin.controllers.seller;

import org.example.mrdverkin.dataBase.Entitys.User;
import org.example.mrdverkin.dto.OrderAttribute;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/listOrdersSeller")
public class ListOrderSellerController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String listOrders(@AuthenticationPrincipal User user, Model model) {
        List<Order> ordes = orderRepository.findOrdersByUser(user);
        List<OrderAttribute> orderAttributes = OrderAttribute.fromOrderList(ordes);
        model.addAttribute("orders", orderAttributes);
        return "listOrdersSeller";
    }
}
