package Car.Service.App_NEW.controller;

import Car.Service.App_NEW.model.User;
import Car.Service.App_NEW.repository.UserRepository;
import Car.Service.App_NEW.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    // Обычная форма логина и регистрации (одна страница)
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "auth"; // файл auth.html
    }
    @PostMapping("/api/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody Map<String, String> userMap) {
        String username = userMap.get("username");
        String password = userMap.get("password");
        String role = userMap.get("role");

        if (username == null || password == null || role == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Все поля обязательны"));
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Пользователь уже существует"));
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role.toUpperCase()); // USER или ADMIN

        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Пользователь успешно зарегистрирован"));
    }


    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";  // Название вашего HTML-шаблона
    }

}
