package Car.Service.App_NEW.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Getter
@Setter

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseplate;
    private String brand;
    private String model;
    private int year;
    private Long ownerid;

    // Getters and setters
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private CarOwner owner;

    // Геттеры и сеттеры
    public CarOwner getOwner() {
        return owner;
    }

    public void setOwner(CarOwner owner) {
        this.owner = owner;
    }
}
