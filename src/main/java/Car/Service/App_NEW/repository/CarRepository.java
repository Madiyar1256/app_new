package Car.Service.App_NEW.repository;

import Car.Service.App_NEW.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}