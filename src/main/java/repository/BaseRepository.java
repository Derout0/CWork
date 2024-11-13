package main.java.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository<T> {
    final Connection connection;
    private final String tableName;

    public BaseRepository(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    protected abstract T mapping(ResultSet resultSet) throws SQLException;

    public List<T> getAll() throws SQLException {
        List<T> results = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

             while (resultSet.next()) {
                results.add(mapping(resultSet));
            }
        }

        return results;
    }

    public List<T> findBy(Map<String, Object> criteria) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
        List<Object> params = new ArrayList<>();

        for (String key : criteria.keySet()) {
            query.append(key).append(" = ? AND ");
            params.add(criteria.get(key));
        }

        query.setLength(query.length() - 5); // Удалить последний " AND "

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            ResultSet resultSet = statement.executeQuery();
            List<T> resultList = new ArrayList<>();

            while (resultSet.next()) {
                resultList.add(mapping(resultSet));
            }

            return resultList;
        }
    }
}
