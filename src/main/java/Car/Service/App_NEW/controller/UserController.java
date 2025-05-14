package Car.Service.App_NEW.controller;

import Car.Service.App_NEW.model.Product;
import Car.Service.App_NEW.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import Car.Service.App_NEW.model.RepairOrder;
import Car.Service.App_NEW.repository.RepairOrderRepository;


import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RepairOrderRepository repairOrderRepository;


    // Домашняя страница пользователя (можно поменять на дашборд)
    @GetMapping("/user/home")
    public String userHome(Model model) {
        List<Product> services = productRepository.findAll();
        model.addAttribute("services", services);
        return "user_services"; // этот HTML уже содержит карточки
    }

    @GetMapping("/user/orders")
    public String viewAllOrders(Model model) {
        List<RepairOrder> orders = repairOrderRepository.findAll();
        model.addAttribute("orders", orders);
        return "user_orders";
    }




    // Заготовка для формы создания заявки:
    // @GetMapping("/user/order")
    // public String createOrder(@RequestParam("serviceId") Long serviceId, Model model) {
    //     Product product = productRepository.findById(serviceId).orElse(null);
    //     model.addAttribute("service", product);
    //     return "create_order_form"; // Форма для создания заявки
    // }
}
