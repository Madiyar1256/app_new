package Car.Service.App_NEW.service;

import Car.Service.App_NEW.model.RepairOrder;
import Car.Service.App_NEW.repository.RepairOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairOrderService {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    public List<RepairOrder> getAllRepairOrders() {
        return repairOrderRepository.findAll();
    }

    public RepairOrder saveRepairOrder(RepairOrder repairOrder) {
        return repairOrderRepository.save(repairOrder);
    }

    public RepairOrder getRepairOrderById(Long id) {
        return repairOrderRepository.findById(id).orElse(null);
    }

    public void deleteRepairOrder(Long id) {
        repairOrderRepository.deleteById(id);
    }
}
