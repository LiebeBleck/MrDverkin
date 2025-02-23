package org.example.mrdverkin.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/mainInstaller")
public class MainInstallerController {

    @GetMapping
    public String mainInstaller(Model model) {
        return "mainInstaller";
    }
}
