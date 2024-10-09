package main.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.models.Client;
import main.java.shared.composition.AgencyDetails;
import main.java.shared.composition.PersonDetails;

public class ClientRepository extends BaseRepository<Client> {
    public ClientRepository(Connection connection) {
        super(connection, "clients");
    }

    @Override
    protected Client mapping(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");
        String agencyName = resultSet.getString("agency_name");
        String address = resultSet.getString("address");
        Integer tourId = resultSet.getInt("tour_id");

        PersonDetails personDetails = new PersonDetails(firstName, lastName, phone);
        AgencyDetails agencyDetails = new AgencyDetails(agencyName);

        return new Client(id, tourId, address, personDetails, agencyDetails);
    }

    public List<Client> getClientsByTourCountry(String country) throws SQLException {
        String query = "SELECT client.* FROM clients client " +
                "JOIN tours tour ON client.tour_id = tour.id " +
                "WHERE tour.country = ?";

        List<Client> clients = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, country);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(mapping(resultSet));
            }
        }

        return clients;
    }
}
