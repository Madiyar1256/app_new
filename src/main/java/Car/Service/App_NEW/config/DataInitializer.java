package Car.Service.App_NEW.config;

import Car.Service.App_NEW.model.Car;
import Car.Service.App_NEW.model.Mechanic;
import Car.Service.App_NEW.model.Product;
import Car.Service.App_NEW.model.RepairOrder;
import Car.Service.App_NEW.model.OrderService;
import Car.Service.App_NEW.model.CarOwner;
import Car.Service.App_NEW.repository.CarRepository;
import Car.Service.App_NEW.repository.MechanicRepository;
import Car.Service.App_NEW.repository.ProductRepository;
import Car.Service.App_NEW.repository.RepairOrderRepository;
import Car.Service.App_NEW.repository.OrderServiceRepository;
import Car.Service.App_NEW.repository.CarOwnerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final CarRepository carRepository;
    private final ProductRepository productRepository;
    private final RepairOrderRepository repairOrderRepository;
    private final OrderServiceRepository orderServiceRepository;
    private final MechanicRepository mechanicRepository;
    private final CarOwnerRepository carOwnerRepository;

    @PostConstruct
    public void initData() {
        initCars();        // Инициализация автомобилей
        initProducts();    // Инициализация услуг
        initRepairOrders(); // Инициализация заказов
        initMechanics();   // Инициализация механиков
        initOrderServices(); // Инициализация заказов на услуги
        initCarOwners();   // Инициализация владельцев автомобилей
    }

    // Метод для инициализации автомобилей
    private void initCars() {
        if (carRepository.count() > 0) {
            return; // Если в таблице уже есть записи, не добавляем новые
        }

        List<Car> cars = Arrays.asList(
                createCar("ABC1234", "Toyota", "Camry", 2015, 1L),
                createCar("XYZ5678", "Honda", "Civic", 2018, 2L),
                createCar("LMN9876", "Ford", "Focus", 2017, 3L),
                createCar("QWE4567", "BMW", "X5", 2019, 4L),
                createCar("JKL2345", "Audi", "A4", 2020, 5L),
                createCar("MNO6789", "Nissan", "Altima", 2016, 6L),
                createCar("PQR3456", "Chevrolet", "Malibu", 2018, 7L),
                createCar("STU1234", "Hyundai", "Sonata", 2021, 8L),
                createCar("VWX2345", "Kia", "Optima", 2017, 9L),
                createCar("YZA5678", "Mercedes", "C-Class", 2022, 10L)
        );

        carRepository.saveAll(cars); // Сохраняем автомобили в базе данных
    }

    // Метод для создания объектов Car
    private Car createCar(String licensePlate, String brand, String model, int year, Long ownerId) {
        Car car = new Car();
        car.setLicenseplate(licensePlate);
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setOwnerid(ownerId);
        return car;
    }

    // Метод для инициализации услуг
    private void initProducts() {
        if (productRepository.count() > 0) {
            return; // Если в таблице уже есть записи, не добавляем новые
        }

        List<Product> products = Arrays.asList(
                createProduct("Oil Change", 50.0),
                createProduct("Tire Replacement", 100.0),
                createProduct("Brake Repair", 150.0),
                createProduct("Engine Check", 200.0)
        );

        productRepository.saveAll(products); // Сохраняем услуги в базе данных
    }

    // Метод для создания объектов Product
    private Product createProduct(String name, Double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    // Метод для инициализации заказов
    private void initRepairOrders() {
        if (repairOrderRepository.count() > 0) {
            return; // Если в таблице уже есть записи, не добавляем новые
        }

        List<RepairOrder> repairOrders = Arrays.asList(
                createRepairOrder(1L, "2025-05-13", "In Progress", 250.0),
                createRepairOrder(2L, "2025-05-14", "Completed", 100.0),
                createRepairOrder(3L, "2025-05-15", "Pending", 150.0)
        );

        repairOrderRepository.saveAll(repairOrders); // Сохраняем заказы в базе данных
    }

    // Метод для создания объектов RepairOrder
    private RepairOrder createRepairOrder(Long carId, String orderDate, String status, Double totalCost) {
        RepairOrder repairOrder = new RepairOrder();

        // Получаем автомобиль по ID
        Car car = carRepository.findById(carId).orElse(null);
        if (car == null) {
            System.out.println("Car with ID " + carId + " not found!");
            return null; // Пропускаем создание RepairOrder, если Car не найден
        }

        // Получаем услугу по ID (например, первая услуга с ID 1L)
        Product service = productRepository.findById(1L).orElse(null);
        if (service == null) {
            System.out.println("Service with ID 1 not found!");
            return null; // Пропускаем создание RepairOrder, если Product не найден
        }

        repairOrder.setCar(car);
        repairOrder.setService(service);
        repairOrder.setOrderdate(orderDate);
        repairOrder.setStatus(status);
        repairOrder.setTotalcost(totalCost);

        return repairOrder;
    }

    // Метод для инициализации механиков
    private void initMechanics() {
        if (mechanicRepository.count() > 0) {
            return; // Если в таблице уже есть записи, не добавляем новые
        }

        List<Mechanic> mechanics = Arrays.asList(
                createMechanic("John Doe", "Engine Specialist"),
                createMechanic("Jane Smith", "Brake Specialist"),
                createMechanic("Alan Brown", "Transmission Specialist")
        );

        mechanicRepository.saveAll(mechanics); // Сохраняем механиков в базе данных
    }

    // Метод для создания объектов Mechanic
    private Mechanic createMechanic(String name, String specialization) {
        Mechanic mechanic = new Mechanic();
        mechanic.setName(name);
        mechanic.setSpecialization(specialization);
        return mechanic;
    }

    // Метод для инициализации заказов на услуги
    private void initOrderServices() {
        if (orderServiceRepository.count() > 0) {
            return; // Если в таблице уже есть записи, не добавляем новые
        }

        List<OrderService> orderServices = Arrays.asList(
                createOrderService(1L, 1L, 1L, 5, 200.0),
                createOrderService(2L, 2L, 2L, 3, 100.0),
                createOrderService(3L, 3L, 3L, 4, 150.0)
        );

        orderServiceRepository.saveAll(orderServices); // Сохраняем заказы на услуги в базе данных
    }

    // Метод для создания объектов OrderService
    private OrderService createOrderService(Long repairOrderId, Long serviceId, Long mechanicId, int hoursWorked, double cost) {
        OrderService orderService = new OrderService();

        // Получаем заказ по ID
        RepairOrder repairOrder = repairOrderRepository.findById(repairOrderId).orElse(null);
        if (repairOrder == null) {
            System.out.println("RepairOrder with ID " + repairOrderId + " not found!");
            return null; // Пропускаем создание OrderService, если RepairOrder не найден
        }

        // Получаем услугу по ID
        Product service = productRepository.findById(serviceId).orElse(null);
        if (service == null) {
            System.out.println("Product with ID " + serviceId + " not found!");
            return null; // Пропускаем создание OrderService, если Product не найден
        }

        // Получаем механика по ID
        Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
        if (mechanic == null) {
            System.out.println("Mechanic with ID " + mechanicId + " not found!");
            return null; // Пропускаем создание OrderService, если Mechanic не найден
        }

        orderService.setRepairOrder(repairOrder);
        orderService.setService(service);
        orderService.setMechanic(mechanic);
        orderService.setHoursworked(hoursWorked);
        orderService.setCost(cost);

        return orderService;
    }

    // Метод для инициализации владельцев автомобилей
    private void initCarOwners() {
        if (carOwnerRepository.count() > 0) {
            return; // Если в таблице уже есть записи, не добавляем новые
        }

        List<CarOwner> carOwners = Arrays.asList(
                createCarOwner("John", "Doe", "123-456-7890"),
                createCarOwner("Jane", "Smith", "987-654-3210"),
                createCarOwner("Alan", "Brown", "555-123-4567")
        );

        carOwnerRepository.saveAll(carOwners); // Сохраняем владельцев автомобилей в базе данных
    }

    // Метод для создания объектов CarOwner
    private CarOwner createCarOwner(String firstName, String lastName, String phone) {
        CarOwner carOwner = new CarOwner();
        carOwner.setFirstname(firstName);
        carOwner.setLastname(lastName);
        carOwner.setPhone(phone);
        return carOwner;
    }
}
