package main.java.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tour extends Entity {
    private String country;
    private String city;
    private BigDecimal cost;
    private LocalDate startDate;
    private LocalDate endDate;

    public Tour(int id, String country, String city, BigDecimal cost, LocalDate startDate, LocalDate endDate) {
        super(id);
        this.country = country;
        this.city = city;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String displayInformation() {
        return "Tour [ID: " + id + ", Country: " + country + ", City: " + city + ", Cost: " + cost + ", Start Date: " + startDate + ", End Date: " + endDate + "]";
    }
}
