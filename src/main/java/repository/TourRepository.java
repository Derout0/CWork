package main.java.repository;

import main.java.models.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TourRepository extends BaseRepository<Tour> {

    public TourRepository(Connection connection) {
        super(connection, "tours");
    }

    @Override
    protected Tour mapping(ResultSet resultSet) throws SQLException {
        return new Tour(
                resultSet.getInt("id"),
                resultSet.getString("country"),
                resultSet.getString("city"),
                resultSet.getBigDecimal("cost"),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate()
        );
    }

    public String findMostPopularCountry() throws SQLException {
        String query = "SELECT country, COUNT(*) AS country_count " +
                "FROM tours " +
                "GROUP BY country " +
                "ORDER BY country_count DESC " +
                "LIMIT 1";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("country");
            }

            return null;
        }
    }
}
