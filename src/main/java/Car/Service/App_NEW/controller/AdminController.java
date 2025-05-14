package Car.Service.App_NEW.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // Главная страница — редирект на админку
    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/admin";
    }

    // Страница панели администратора
    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}
