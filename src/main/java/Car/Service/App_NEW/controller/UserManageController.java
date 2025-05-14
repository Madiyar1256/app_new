package Car.Service.App_NEW.controller;

import Car.Service.App_NEW.model.User;
import Car.Service.App_NEW.service.UserService;
import Car.Service.App_NEW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usermanage")
public class UserManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Страница для отображения всех пользователей
    @GetMapping
    public String userListPage(Model model) {
        List<User> users = userService.getAllUsers(); // Получаем всех пользователей
        model.addAttribute("users", users); // Добавляем их в модель
        return "userlist"; // Страница для отображения списка пользователей
    }

    // Страница для управления пользователем (редактирование данных)
    @GetMapping("/{id}")
    public String userManagePage(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user); // Добавляем данные пользователя в модель
        return "usermanage"; // Страница редактирования пользователя
    }

    // Обновление данных пользователя (логин, роль)
    // Обновление данных пользователя (логин, роль)
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        System.out.println("Updating user: " + user.getUsername() + " with role " + user.getRole());
        try {
            userService.updateUser(user); // Обновляем данные пользователя
            return "redirect:/usermanage"; // Возвращаемся к списку пользователей
        } catch (Exception e) {
            model.addAttribute("error", "Произошла ошибка при сохранении данных. Попробуйте снова.");
            return "usermanage";
        }
    }

    // Удаление пользователя
    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId); // Удаляем пользователя
        return "redirect:/usermanage"; // Перенаправляем на страницу списка пользователей
    }
}