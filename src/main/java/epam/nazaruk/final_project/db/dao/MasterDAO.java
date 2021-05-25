package epam.nazaruk.final_project.db.dao;

import epam.nazaruk.final_project.db.Fields;
import epam.nazaruk.final_project.db.DBManager;
import epam.nazaruk.final_project.db.entity.Master;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDAO {
    private static final String SQL_FIND_ALL_MASTERS = "SELECT master_name,master_surname FROM masters";
    private static final String SQL_FIND_MASTER_BY_NAME = "SELECT * FROM masters where master_name=? and master_surname=?";

    public List<Master> findAllMasters(){
        List<Master> masters = new ArrayList<>();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_MASTERS);
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

    public  Master findMasterByName(String name,String surname){
        Master master = new Master();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_MASTER_BY_NAME)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                master = mapRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return master;
    }

    private Master mapRow(ResultSet resultSet) throws SQLException {
        Master master = new Master();
        master.setId(resultSet.getInt(Fields.ENTITY__ID));
        master.setMasterName(resultSet.getString(Fields.MASTER_NAME));
        master.setMasterSurname(resultSet.getString(Fields.MASTER_NAME));
        master.setRating(resultSet.getInt(Fields.MASTER_RATING));

        return master;
    }

}
