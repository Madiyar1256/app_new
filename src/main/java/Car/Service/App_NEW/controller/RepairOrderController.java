package Car.Service.App_NEW.controller;

import Car.Service.App_NEW.model.RepairOrder;
import Car.Service.App_NEW.service.ProductService;
import Car.Service.App_NEW.service.RepairOrderService;
import Car.Service.App_NEW.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/repairorders")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private CarService carService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listRepairOrders(Model model) {
        model.addAttribute("repairOrders", repairOrderService.getAllRepairOrders());
        return "repair-order-list";  // Страница списка заказов
    }

    @GetMapping("/add")
    public String addRepairOrderForm(Model model) {
        model.addAttribute("repairOrder", new RepairOrder());
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("services", productService.getAllProducts());
        return "add-repair-order";  // Страница добавления нового заказа
    }

    @PostMapping("/save")
    public String saveRepairOrder(@ModelAttribute RepairOrder repairOrder) {
        repairOrderService.saveRepairOrder(repairOrder);
        return "redirect:/repairorders";  // Перенаправление на список заказов
    }

    @GetMapping("/edit/{id}")
    public String editRepairOrderForm(@PathVariable Long id, Model model) {
        model.addAttribute("repairOrder", repairOrderService.getRepairOrderById(id));
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("services", productService.getAllProducts());
        return "add-repair-order";  // Форма редактирования заказа
    }

    @GetMapping("/delete/{id}")
    public String deleteRepairOrder(@PathVariable Long id) {
        repairOrderService.deleteRepairOrder(id);
        return "redirect:/repairorders";  // Удаление и возврат на страницу списка
    }
}
