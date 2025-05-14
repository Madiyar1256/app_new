package Car.Service.App_NEW.controller;

import Car.Service.App_NEW.model.Mechanic;
import Car.Service.App_NEW.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mechanics")
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;

    @GetMapping
    public String getMechanics(Model model) {
        model.addAttribute("mechanics", mechanicService.getAllMechanics());
        return "list-mechanic";
    }

    @GetMapping("/add")
    public String addMechanicForm(Model model) {
        model.addAttribute("mechanic", new Mechanic());
        return "add-mechanic";
    }

    @PostMapping("/save")
    public String saveMechanic(@ModelAttribute Mechanic mechanic) {
        mechanicService.saveMechanic(mechanic);
        return "redirect:/mechanics";
    }

    @GetMapping("/edit/{id}")
    public String editMechanic(@PathVariable Long id, Model model) {
        model.addAttribute("mechanic", mechanicService.getMechanicById(id));
        return "add-mechanic";
    }

    @GetMapping("/delete/{id}")
    public String deleteMechanic(@PathVariable Long id) {
        mechanicService.deleteMechanic(id);
        return "redirect:/mechanics";
    }
}
