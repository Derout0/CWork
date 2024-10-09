package main.java.service;

import main.java.models.Client;
import main.java.repository.ClientRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(Connection connection) {
        this.clientRepository = new ClientRepository(connection);
    }

    public int getClientCount() throws SQLException {
        List<Client> clients = clientRepository.getAll();
        return clients.size();
    }

    public List<Client> getClientsByCountry(String country) throws SQLException {
        List<Client> clients = clientRepository.getClientsByTourCountry(country);

        if (clients.isEmpty()) {
            return null;
        }

        return clients;
    }
}
