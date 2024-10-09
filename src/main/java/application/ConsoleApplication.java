package main.java.application;

import main.java.models.Client;
import main.java.models.Manager;
import main.java.service.ClientService;
import main.java.service.ManagerService;
import main.java.service.TourService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

interface ApplicationInterface {
    void listManagers() throws SQLException;
    void countClients() throws SQLException;
    void listClientsByCountry() throws SQLException;
    void averageTourCost() throws SQLException;
    void averageTourDuration() throws SQLException;
    void mostPopularCountry() throws SQLException;
}

public class ConsoleApplication implements ApplicationInterface {
    private final ManagerService managerService;
    private final ClientService clientService;
    private final TourService tourService;

    public ConsoleApplication(ManagerService managerService, ClientService clientService, TourService tourService) {
        this.managerService = managerService;
        this.clientService = clientService;
        this.tourService = tourService;
    }

    public void listManagers() throws SQLException {
        List<Manager> managers = managerService.getAllManagers();

        if (managers == null) {
            System.out.println("Менеджерів не знайдено");
            return;
        }

        for (Manager manager : managers) {
            System.out.println(manager.displayInformation());
        }
    }

    public void countClients() throws SQLException {
        int count = clientService.getClientCount();
        System.out.println("Total clients: " + count);
    }

    public void listClientsByCountry() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву країни: ");
        String country = scanner.nextLine();

        List<Client> clients = clientService.getClientsByCountry(country);
        if (clients == null) {
            System.out.println("Клієнтів не знайдено для країни: " + country);
        } else {
            System.out.println("Клієнти в країні " + country + ":");
            for (Client client : clients) {
                System.out.println(client.displayInformation());
            }
        }
    }

    public void averageTourCost() throws SQLException {
        double averageCost = tourService.getAverageTourCost();
        System.out.println("Average tour cost: " + averageCost);
    }

    public void averageTourDuration() throws SQLException {
        double averageDuration = tourService.getAverageTourDuration();
        System.out.println("Average tour duration: " + averageDuration);
    }

    public void mostPopularCountry() throws SQLException {
        String country = tourService.getMostPopularCountry();
        System.out.println("Most popular country: " + country);
    }

    public void showMenu() {
        System.out.println("Виберіть варіант:");
        System.out.println("1. Менеджери");
        System.out.println("2. Кількість клієнтів");
        System.out.println("3. Список клієнтів за країною");
        System.out.println("4. Середня вартість туру");
        System.out.println("5. Середня тривалість туру");
        System.out.println("6. Найпопулярніша країна");
        System.out.println("0. Вихід");
    }

    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Помилка: введіть цифру!");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    listManagers();
                    break;
                case 2:
                    countClients();
                    break;
                case 3:
                    listClientsByCountry();
                    break;
                case 4:
                    averageTourCost();
                    break;
                case 5:
                    averageTourDuration();
                    break;
                case 6:
                    mostPopularCountry();
                    break;
                case 0:
                    System.out.println("Вихід");
                    return;
                default:
                    System.out.println("Неправильний вибір! Спробуйте ще раз.");
            }
        }
    }
}
