package Car.Service.App_NEW.repository;

import Car.Service.App_NEW.model.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
}
