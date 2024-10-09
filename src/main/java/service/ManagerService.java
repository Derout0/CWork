package main.java.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import main.java.models.Manager;
import main.java.repository.ManagerRepository;

public class ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerService(Connection connection) {
        this.managerRepository = new ManagerRepository(connection);
    }

    public List<Manager> getAllManagers() throws SQLException {
        List<Manager> managers = managerRepository.getAll();

        if (managers.isEmpty()) {
            return null;
        }

        return managerRepository.getAll();
    }
}
