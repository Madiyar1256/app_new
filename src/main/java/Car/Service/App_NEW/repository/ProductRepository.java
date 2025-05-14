package Car.Service.App_NEW.repository;

import Car.Service.App_NEW.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Можно добавить дополнительные методы, если необходимо
}
