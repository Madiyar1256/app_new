package Car.Service.App_NEW.repository;

import Car.Service.App_NEW.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    // Можно добавить дополнительные методы, если необходимо
}
