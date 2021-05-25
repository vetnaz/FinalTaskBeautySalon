package epam.nazaruk.final_project.db.dao;

import epam.nazaruk.final_project.db.entity.ServiceRecord;
import epam.nazaruk.final_project.db.DBManager;
import epam.nazaruk.final_project.db.exception.AlreadyExistRecordException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRecordDAO {
    private static final String SQL_INSERT_INTO_SERVICE_RECORD ="insert into  service_records(date, status_id, users_id, users_roles_id, services_id, masters_id, timeslot) values (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_STATUS = "update service_records set  status_id = ? where id = ?";
    private static final String SQL_DELETE_RECORD = "delete from service_records where id = ?";
    private static final String SQL_UPDATE_TIMESLOT_BY_ID = "update service_records set timeslot = ? where id = ?";
    private static final String SQL_SELECT_SERVICE_RECORD_BY_TIMESLOT = "select * from service_records where date = ? and timeslot = ? and masters_id = ?";

    public void insertIntoServiceRecords(ServiceRecord serviceRecord) throws AlreadyExistRecordException {
        Connection connection;
        connection = DBManager.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_SERVICE_RECORD);
             PreparedStatement checkPreparedStatement = connection.prepareStatement(SQL_SELECT_SERVICE_RECORD_BY_TIMESLOT)
             ){
            checkPreparedStatement.setString(1, serviceRecord.getDate());
            checkPreparedStatement.setString(2, serviceRecord.getTimeslot());
            checkPreparedStatement.setInt(3,serviceRecord.getMastersId());

            ResultSet resultSet = checkPreparedStatement.executeQuery();

            if (resultSet.next()){
                throw new SQLException();
            }

            preparedStatement.setString(1,serviceRecord.getDate());
            preparedStatement.setInt(2,serviceRecord.getStatusId());
            preparedStatement.setInt(3,serviceRecord.getUserId());
            preparedStatement.setInt(4,serviceRecord.getUserRolesId());
            preparedStatement.setInt(5,serviceRecord.getServiceId());
            preparedStatement.setInt(6,serviceRecord.getMastersId());
            preparedStatement.setString(7,serviceRecord.getTimeslot());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AlreadyExistRecordException();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
    }

    public void updateStatusOfRecords(int recordId,int newStatus){
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
            statement.setInt(1,newStatus);
            statement.setInt(2,recordId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
    }

    public void deleteRecordById(int recordId){
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RECORD)) {
            statement.setInt(1,recordId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
    }

    public void updateTimeslotById(int recordId,String newTimeslot){
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TIMESLOT_BY_ID)) {
            statement.setString(1,newTimeslot);
            statement.setInt(2,recordId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
    }

}
