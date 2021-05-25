package epam.nazaruk.final_project.db.dao;

import epam.nazaruk.final_project.db.DBManager;
import epam.nazaruk.final_project.db.Fields;
import epam.nazaruk.final_project.db.bean.ServiceMasterBean;
import epam.nazaruk.final_project.db.entity.Master;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceMasterDAO {

    private static final String SQL_FIND_ALL_SERVICES_AND_MASTER = "SELECT  title, description, price, master_name,master_surname, rating FROM services,masters,services_has_masters where services_id = services.id and masters_id = masters.id ORDER BY master_name ";
    private static final String SQL_FIND_MASTERS_BY_SERVICE = "SELECT DISTINCT master_name,master_surname FROM services,masters,services_has_masters where services_id = services.id and masters_id = masters.id and title = ?";

    public List<ServiceMasterBean> findServicesAndMasterSortByName() {
        List<ServiceMasterBean> serviceMasterBeans = null;
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_SERVICES_AND_MASTER);
            serviceMasterBeans = mapRow(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceMasterBeans;
    }

    public List<Master> findMastersByService(String service) {
        Connection connection;
        List<Master> masters = new ArrayList<>();
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_MASTERS_BY_SERVICE)){
            preparedStatement.setString(1,service);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Master master = new Master();
                master.setMasterName(resultSet.getString(Fields.MASTER_NAME));
                master.setMasterSurname(resultSet.getString(Fields.MASTER_SURNAME));
                masters.add(master);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }

        return masters;
    }

    private List<ServiceMasterBean> mapRow(ResultSet resultSet) throws SQLException {
        List<ServiceMasterBean> serviceMasterBeans = new ArrayList<>();
        while (resultSet.next()){
            ServiceMasterBean serviceMasterBean = new ServiceMasterBean();
            serviceMasterBean.setTitle(resultSet.getString(Fields.SERVICE_TITLE));
            serviceMasterBean.setDescription(resultSet.getString(Fields.SERVICE_DESCRIPTION));
            serviceMasterBean.setPrice(resultSet.getInt(Fields.SERVICE_PRICE));
            serviceMasterBean.setMasterName(resultSet.getString(Fields.MASTER_NAME));
            serviceMasterBean.setMasterSurname(resultSet.getString(Fields.MASTER_SURNAME));
            serviceMasterBean.setRating(resultSet.getInt(Fields.MASTER_RATING));
            serviceMasterBeans.add(serviceMasterBean);
        }
        return serviceMasterBeans;
    }


}
