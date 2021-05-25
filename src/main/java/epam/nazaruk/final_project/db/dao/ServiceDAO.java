package epam.nazaruk.final_project.db.dao;

import epam.nazaruk.final_project.db.DBManager;
import epam.nazaruk.final_project.db.Fields;
import epam.nazaruk.final_project.db.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    private static final String SQL_FIND_TITLE_FROM_SERVICES = "SELECT title  FROM services";
    private static final String SQL_FIND_SERVICE_BY_NAME = "SELECT * FROM services where title = ?";

    public List<String> findServicesTitle() {
        List<String> services = new ArrayList<>();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_TITLE_FROM_SERVICES);
            while (resultSet.next()) {
                services.add(resultSet.getString(Fields.SERVICE_TITLE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return services;
    }

    public Service findServiceByName(String name){
        Service service = new Service();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_SERVICE_BY_NAME)){
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                service = mapRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return service;
    }

    private Service mapRow(ResultSet resultSet) throws SQLException {
            Service service = new Service();
            service.setId(resultSet.getInt(Fields.ENTITY__ID));
            service.setTitle(resultSet.getString(Fields.SERVICE_TITLE));
            service.setPrice(resultSet.getInt(Fields.SERVICE_PRICE));
            service.setDescription(resultSet.getString(Fields.SERVICE_DESCRIPTION));

            return service;
    }
}