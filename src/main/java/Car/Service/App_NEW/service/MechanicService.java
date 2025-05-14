package Car.Service.App_NEW.service;

import Car.Service.App_NEW.model.Mechanic;
import Car.Service.App_NEW.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicService {

    @Autowired
    private MechanicRepository mechanicRepository;

    public Mechanic saveMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    public Mechanic getMechanicById(Long id) {
        return mechanicRepository.findById(id).orElse(null);
    }

    public void deleteMechanic(Long id) {
        mechanicRepository.deleteById(id);
    }
}
