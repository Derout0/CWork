package main.java.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.models.Manager;
import main.java.shared.composition.AgencyDetails;
import main.java.shared.composition.PersonDetails;

public class ManagerRepository extends BaseRepository<Manager> {
    public ManagerRepository(Connection connection) {
        super(connection, "managers");
    }

    @Override
    protected Manager mapping(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String agencyName = resultSet.getString("agency_name");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");

        PersonDetails personDetails = new PersonDetails(firstName, lastName, phone);
        AgencyDetails agencyDetails = new AgencyDetails(agencyName);

        return new Manager(id, agencyDetails, personDetails);
    }
}
