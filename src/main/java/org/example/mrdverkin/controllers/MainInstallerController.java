package org.example.mrdverkin.controllers;

import org.example.mrdverkin.dataBase.Entitys.Installer;
import org.example.mrdverkin.dataBase.Entitys.Order;
import org.example.mrdverkin.dataBase.Mapping.OrderAttribute;
import org.example.mrdverkin.dataBase.Repository.InstallerRepository;
import org.example.mrdverkin.dataBase.Repository.OrderRepository;
import org.example.mrdverkin.dto.DateAvailability;
import org.example.mrdverkin.dto.SelectInstaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home/mainInstaller")
public class MainInstallerController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InstallerRepository installerRepository;

    @ModelAttribute("selectInstaller")
    public SelectInstaller selectInstaller() {
        return new SelectInstaller();
    }

    @PostMapping()
    public String addInstaller(@ModelAttribute SelectInstaller selectInstaller) {
        System.out.println(selectInstaller);
//        orderRepository.updateInstaller(installerRepository.findByName(selectInstaller.getFullName()));
        return "redirect:/home/mainInstaller";
    }


    @GetMapping
    public String mainInstaller(Model model) {
        List<Order> ordes = orderRepository.findByInstallerNull();
        LocalDate today = LocalDate.now();
        List<DateAvailability> availabilityList = new ArrayList<>();
        for (int i = 0; i < 7; i++ ){
            DateAvailability availability = new DateAvailability(today, orderRepository.numberOfDoorsToInstallation(today));
            availabilityList.add(availability);
            today = today.plusDays(1);
        }

        List<OrderAttribute> orderAttributes = OrderAttribute.fromOrderList(ordes);
        model.addAttribute("orders", orderAttributes);
        model.addAttribute("installers", installerRepository.findAll());
        model.addAttribute("availabilityList", availabilityList);
        return "mainInstaller";
    }
}
