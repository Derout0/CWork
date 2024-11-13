package main.java.application;

import main.java.models.Client;
import main.java.models.Manager;
import main.java.service.ClientService;
import main.java.service.ManagerService;
import main.java.service.TourService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

interface ApplicationInterface {
    void listManagers() throws SQLException;
    void countClients() throws SQLException;
    void listClientsByCountry() throws SQLException;
    void averageTourCost() throws SQLException;
    void averageTourDuration() throws SQLException;
    void mostPopularCountry() throws SQLException;
}

public class SwingApplication extends JFrame implements ApplicationInterface {
    private final ManagerService managerService;
    private final ClientService clientService;
    private final TourService tourService;
    private JTextArea outputArea;

    public SwingApplication(ManagerService managerService, ClientService clientService, TourService tourService) {
        this.managerService = managerService;
        this.clientService = clientService;
        this.tourService = tourService;

        setTitle("Туристичний додаток");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
    }

    private void setupUI() {
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        JButton listManagersButton = new JButton("Менеджери");
        JButton countClientsButton = new JButton("Кількість клієнтів");
        JButton listClientsByCountryButton = new JButton("Клієнти по країні");
        JButton averageTourCostButton = new JButton("Середня вартість туру");
        JButton averageTourDurationButton = new JButton("Середня тривалість туру");
        JButton mostPopularCountryButton = new JButton("Найпопулярніша країна");
        JButton exitButton = new JButton("Вихід");

        listManagersButton.addActionListener(e -> {
            try {
                listManagers();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        });

        countClientsButton.addActionListener(e -> {
            try {
                countClients();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        });

        listClientsByCountryButton.addActionListener(e -> {
            try {
                listClientsByCountry();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        });

        averageTourCostButton.addActionListener(e -> {
            try {
                averageTourCost();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        });

        averageTourDurationButton.addActionListener(e -> {
            try {
                averageTourDuration();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        });

        mostPopularCountryButton.addActionListener(e -> {
            try {
                mostPopularCountry();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(listManagersButton);
        panel.add(countClientsButton);
        panel.add(listClientsByCountryButton);
        panel.add(averageTourCostButton);
        panel.add(averageTourDurationButton);
        panel.add(mostPopularCountryButton);
        panel.add(exitButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Помилка", JOptionPane.ERROR_MESSAGE);
    }

    public void listManagers() throws SQLException {
        List<Manager> managers = managerService.getAllManagers();
        if (managers == null || managers.isEmpty()) {
            outputArea.setText("Менеджерів не знайдено");
        } else {
            StringBuilder sb = new StringBuilder("Менеджери:\n");
            for (Manager manager : managers) {
                sb.append(manager.displayInformation()).append("\n");
            }
            outputArea.setText(sb.toString());
        }
    }

    public void countClients() throws SQLException {
        int count = clientService.getClientCount();
        outputArea.setText("Усього клієнтів: " + count);
    }

    public void listClientsByCountry() throws SQLException {
        String country = JOptionPane.showInputDialog(this, "Введіть назву країни:");
        if (country == null || country.isEmpty()) return;

        List<Client> clients = clientService.getClientsByCountry(country);
        if (clients == null || clients.isEmpty()) {
            outputArea.setText("Клієнтів не знайдено для країни: " + country);
        } else {
            StringBuilder sb = new StringBuilder("Клієнти в країні " + country + ":\n");
            for (Client client : clients) {
                sb.append(client.displayInformation()).append("\n");
            }
            outputArea.setText(sb.toString());
        }
    }

    public void averageTourCost() throws SQLException {
        double averageCost = tourService.getAverageTourCost();
        outputArea.setText("Середня вартість туру: " + averageCost);
    }

    public void averageTourDuration() throws SQLException {
        double averageDuration = tourService.getAverageTourDuration();
        outputArea.setText("Середня тривалість туру: " + averageDuration);
    }

    public void mostPopularCountry() throws SQLException {
        String country = tourService.getMostPopularCountry();
        outputArea.setText("Найпопулярніша країна: " + country);
    }
}
