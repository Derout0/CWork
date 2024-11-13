package main.java.main;
import java.sql.Connection;
import java.sql.SQLException;
import main.java.application.SwingApplication;
import main.java.database.DatabaseConfig;
import main.java.service.ClientService;
import main.java.service.ManagerService;
import main.java.service.TourService;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            System.out.println("Connected to the database!");

            ManagerService managerService = new ManagerService(connection);
            ClientService clientService = new ClientService(connection);
            TourService tourService = new TourService(connection);

            SwingApplication app = new SwingApplication(managerService, clientService, tourService);
            app.setVisible(true);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                        System.out.println("Database connection closed.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
