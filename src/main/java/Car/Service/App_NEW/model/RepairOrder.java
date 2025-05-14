package Car.Service.App_NEW.model;

import jakarta.persistence.*;

@Entity
@Table(name = "repairorder")
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderdate;
    private String status;
    private Double totalcost;

    @ManyToOne
    @JoinColumn(name = "carid", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "serviceid", nullable = false)
    private Product service;


    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Double totalcost) {
        this.totalcost = totalcost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Product getService() {
        return service;
    }

    public void setService(Product service) {
        this.service = service;
    }


}
