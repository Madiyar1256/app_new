package Car.Service.App_NEW.repository;

import Car.Service.App_NEW.model.CarOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {
    // Можно добавить кастомные запросы, если необходимо
}
