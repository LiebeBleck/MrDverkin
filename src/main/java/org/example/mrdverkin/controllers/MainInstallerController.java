package org.example.mrdverkin.controllers;

import org.example.mrdverkin.dataBase.Entitys.Installer;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dataBase.Mapping.OrderAttribute;
import org.example.mrdverkin.dataBase.Repository.InstallerRepository;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home/mainInstaller")
public class MainInstallerController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InstallerRepository installerRepository;

    @GetMapping
    public String mainInstaller(Model model) {
        List<Order> ordes = orderRepository.findAll();
        List<Installer> installers = installerRepository.findAll();

        List<OrderAttribute> orderAttributes = OrderAttribute.fromOrderList(ordes);
        model.addAttribute("orders", orderAttributes);
        model.addAttribute("installers", installers);
        return "mainInstaller";
    }
}
