package main.java.main;

import java.sql.Connection;
import java.sql.SQLException;

import main.java.application.ConsoleApplication;
import main.java.database.DatabaseConfig;
import main.java.service.ClientService;
import main.java.service.ManagerService;
import main.java.service.TourService;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = DatabaseConfig.getConnection();
            System.out.println("Connected to the database!");

            ManagerService managerService = new ManagerService(connection);
            ClientService clientService = new ClientService(connection);
            TourService tourService = new TourService(connection);

            ConsoleApplication app = new ConsoleApplication(managerService, clientService, tourService);
            app.run();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
