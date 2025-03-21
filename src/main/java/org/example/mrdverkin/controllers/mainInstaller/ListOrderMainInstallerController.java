package org.example.mrdverkin.controllers.mainInstaller;

import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dto.OrderAttribute;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/listOrdersMainInstaller")
public class ListOrderMainInstallerController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String listOrders(Model model) {
        List<Order> ordes = orderRepository.findAll();
        List<OrderAttribute> orderAttributes = OrderAttribute.fromOrderList(ordes);
        model.addAttribute("orders", orderAttributes);
        return "listOrdersMainInstaller";
    }
}
