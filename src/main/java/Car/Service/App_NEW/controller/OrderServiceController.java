package Car.Service.App_NEW.controller;

import Car.Service.App_NEW.model.OrderService;
import Car.Service.App_NEW.service.MechanicService;
import Car.Service.App_NEW.service.OrderServiceService;
import Car.Service.App_NEW.service.ProductService;
import Car.Service.App_NEW.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderservice")
public class OrderServiceController {

    @Autowired
    private OrderServiceService orderServiceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MechanicService mechanicService;

    @Autowired
    private RepairOrderService repairOrderService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orderServices", orderServiceService.getAll());
        return "list-order-service";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("orderService", new OrderService());
        model.addAttribute("services", productService.getAllProducts());
        model.addAttribute("mechanics", mechanicService.getAllMechanics());
        model.addAttribute("repairOrders", repairOrderService.getAllRepairOrders());
        return "add-order-service";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute OrderService orderService) {
        orderServiceService.save(orderService);
        return "redirect:/orderservice";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("orderService", orderServiceService.getById(id));
        model.addAttribute("services", productService.getAllProducts());
        model.addAttribute("mechanics", mechanicService.getAllMechanics());
        model.addAttribute("repairOrders", repairOrderService.getAllRepairOrders());
        return "add-order-service";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderServiceService.delete(id);
        return "redirect:/orderservice";
    }
}
