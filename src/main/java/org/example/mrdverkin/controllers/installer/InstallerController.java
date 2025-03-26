package org.example.mrdverkin.controllers.installer;

import org.example.mrdverkin.dataBase.Entitys.Installer;
import org.example.mrdverkin.services.InstallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/installer")
public class InstallerController {
    @Autowired
    private InstallerService installerService;

    @GetMapping("/{id}")
    public String installer(@PathVariable Long id, Model model) {
        Installer installer = installerService.findInstallerById(id);
        model.addAttribute("installer", installer);
        return "installerEdit";
    }

    @PostMapping("/{id}")
    public String updateInstaller(@PathVariable Long id, @RequestParam String fullName, @RequestParam String phone) {
        installerService.updateInstaller(id, fullName, phone);
        return "redirect:/home/mainInstaller/listInstallers";
    }
}
