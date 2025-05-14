package Car.Service.App_NEW.controller;

import Car.Service.App_NEW.model.CarOwner;
import Car.Service.App_NEW.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carowners")
public class CarOwnerController {

    @Autowired
    private CarOwnerService carOwnerService;

    @GetMapping
    public String getCarOwners(Model model) {
        model.addAttribute("carOwners", carOwnerService.getAllCarOwners());
        return "carowner-list";
    }

    @GetMapping("/add")
    public String addCarOwnerForm(Model model) {
        model.addAttribute("carOwner", new CarOwner());
        return "add-carowner";
    }

    @PostMapping("/save")
    public String saveCarOwner(@ModelAttribute CarOwner carOwner) {
        carOwnerService.saveCarOwner(carOwner);
        return "redirect:/carowners";
    }

    @GetMapping("/edit/{id}")
    public String editCarOwner(@PathVariable Long id, Model model) {
        model.addAttribute("carOwner", carOwnerService.getCarOwnerById(id));
        return "add-carowner";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarOwner(@PathVariable Long id) {
        carOwnerService.deleteCarOwner(id);
        return "redirect:/carowners";
    }
}
