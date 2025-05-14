package Car.Service.App_NEW.repository;

import Car.Service.App_NEW.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    List<RepairOrder> findByCarOwnerId(Long carOwnerId);
}
