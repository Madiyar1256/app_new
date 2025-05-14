package Car.Service.App_NEW.config;

import Car.Service.App_NEW.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final CustomSuccessHandler successHandler;

    // Внедрение зависимостей
    public SecurityConfig(CustomUserDetailsService userDetailsService, CustomSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    // Конфигурация безопасности
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/api/register", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/").hasAnyRole("USER", "ADMIN")

                        .requestMatchers("/cars").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/cars/new").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/cars/{id}/edit").hasAnyRole("ADMIN", "DIRECTOR")

                        .requestMatchers("/carowners").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/carowners/new").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/carowners/{id}/edit").hasAnyRole("ADMIN", "DIRECTOR")

                        .requestMatchers("/mechanics").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/mechanics/new").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/mechanics/{id}/edit").hasAnyRole("ADMIN", "DIRECTOR")

                        .requestMatchers("/orderservice").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/orderservice/new").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/orderservice/{id}/edit").hasAnyRole("ADMIN", "DIRECTOR")

                        .requestMatchers("/products").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/products/new").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/products/{id}/edit").hasAnyRole("ADMIN", "DIRECTOR")

                        .requestMatchers("/repairorders").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/repairorders/new").hasAnyRole("ADMIN", "DIRECTOR")
                        .requestMatchers("/repairorders/{id}/edit").hasAnyRole("ADMIN", "DIRECTOR")

                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler) // <- подключаем наш кастомный handler
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // Бин для AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Бин для шифрования паролей с использованием BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
