package Car.Service.App_NEW.service;

import Car.Service.App_NEW.model.CarOwner;
import Car.Service.App_NEW.repository.CarOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarOwnerService {

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    public CarOwner saveCarOwner(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }

    public List<CarOwner> getAllCarOwners() {
        return carOwnerRepository.findAll();
    }

    public CarOwner getCarOwnerById(Long id) {
        return carOwnerRepository.findById(id).orElse(null);
    }

    public void deleteCarOwner(Long id) {
        carOwnerRepository.deleteById(id);
    }
}
