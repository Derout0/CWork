package main.java.service;

import main.java.models.Tour;
import main.java.repository.TourRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TourService {
    private final TourRepository tourRepository;

    public TourService(Connection connection) {
        this.tourRepository = new TourRepository(connection);
    }

    public double getAverageTourCost() throws SQLException {
        List<Tour> tours = tourRepository.getAll();

        double totalCost = 0;

        for (Tour tour : tours) {
            BigDecimal cost = tour.getCost();
            totalCost += cost.doubleValue();
        }

        return totalCost / tours.size();
    }

    public double getAverageTourDuration() throws SQLException {
        List<Tour> tours = tourRepository.getAll();

        double totalDuration = 0;

        for (Tour tour : tours) {
            // Обчислення кількості днів між двома датами
            long days = ChronoUnit.DAYS.between(tour.getStartDate(), tour.getEndDate());
            totalDuration += days;
        }

        return totalDuration / tours.size();
    }

    public String getMostPopularCountry() throws SQLException {
        return tourRepository.findMostPopularCountry();
    }
}
