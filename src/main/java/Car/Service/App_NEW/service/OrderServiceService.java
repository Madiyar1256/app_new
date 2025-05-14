package Car.Service.App_NEW.service;

import Car.Service.App_NEW.model.OrderService;
import Car.Service.App_NEW.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceService {

    @Autowired
    private OrderServiceRepository orderServiceRepository;

    public List<OrderService> getAll() {
        return orderServiceRepository.findAll();
    }

    public OrderService getById(Long id) {
        return orderServiceRepository.findById(id).orElse(null);
    }

    public void save(OrderService orderService) {
        orderServiceRepository.save(orderService);
    }

    public void delete(Long id) {
        orderServiceRepository.deleteById(id);
    }
}
