package epam.nazaruk.final_project.db.dao;

import epam.nazaruk.final_project.db.bean.MasterRecordsBean;
import epam.nazaruk.final_project.db.DBManager;
import epam.nazaruk.final_project.db.Fields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterRecordsDAO {
    private static final String SQL_SELECT_RECORDS_BY_MASTER_AND_DAY = "select service_records.id as id,title,users.name,surname,date,price,status.name as status_name,timeslot from service_records,services,status,users,masters where services_id = services.id and status_id = status.id and users_id = users.id and masters_id = masters.id  and master_name=? and master_surname=? and date = ?";
    private static final String SQL_SELECT_WORK_DATE = "select distinct date from service_records,masters where masters.id = service_records.masters_id and master_name = ? and master_surname = ? and date>=curdate() order by date;";
    private static final String SQL_SELECT_COUNT_OF_ROWS = "select  count(service_records.id) as count from service_records,services,status,users,masters where services_id = services.id and status_id = status.id and users_id = users.id and masters_id = masters.id and date>=curdate()";
    private static final String SQL_SELECT_ALL_RECORDS_BY_PAGE = "select service_records.id as id,title,users.name,surname,date,price,status.name as status_name,master_surname,master_name ,timeslot from service_records,services,status,users,masters where services_id = services.id and status_id = status.id and users_id = users.id and masters_id = masters.id  and date>=curdate() order by date limit  ? ,?";
    private static final String SQL_SELECT_DONE_RECORDS_BY_USER = "SELECT service_records.id as id,timeslot,title,status.name as status_name,date,price, master_surname,master_name,comment FROM  status,services,masters,service_records LEFT JOIN feedback f on service_records.id = f.service_records_id WHERE services.id = service_records.services_id and masters_id = masters.ID and status.id = status_id and users_id = ? and status_id = ? order by date";
    private static final String SQL_SELECT_RECORD_BY_ID = "select service_records.id as id,title,date,price, master_surname,master_name from service_records,services,status,users,masters where services_id = services.id and status_id = status.id and users_id = users.id and masters_id = masters.id and service_records.id = ? order by date";
    private static final String SQL_SELECT_CONFIRMED_RECORDS_BY_USER = "SELECT service_records.id as id,timeslot,title,status.name as status_name,date,price, master_surname,master_name,comment FROM  status,services,masters,service_records LEFT JOIN feedback f on service_records.id = f.service_records_id WHERE services.id = service_records.services_id and date>=curdate() and  masters_id = masters.ID and status.id = status_id and users_id = ? and status_id = ? order by date";

    public List<MasterRecordsBean> findRecordsByMasterAndDay(String name, String surname, String date){
        List<MasterRecordsBean> masterRecords = new ArrayList<>();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_RECORDS_BY_MASTER_AND_DAY)) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.setString(3,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                MasterRecordsBean masterRecordsBean = new MasterRecordsBean();
                masterRecordsBean.setRecordId(resultSet.getInt(Fields.ENTITY__ID));
                masterRecordsBean.setService(resultSet.getString(Fields.SERVICE_TITLE));
                masterRecordsBean.setClientName(resultSet.getString(Fields.USER_NAME));
                masterRecordsBean.setClientSurname(resultSet.getString(Fields.USER_SURNAME));
                masterRecordsBean.setDate(resultSet.getString(Fields.SERVICE_RECORDS_DATE));
                masterRecordsBean.setPrice(resultSet.getInt(Fields.SERVICE_PRICE));
                masterRecordsBean.setStatus(resultSet.getString(Fields.STATUS_NAME));
                masterRecordsBean.setTimeslot(resultSet.getString(Fields.SERVICE_RECORDS_TIMESLOT));
                masterRecords.add(masterRecordsBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }

        return masterRecords;
    }


    public List<String> selectWorkDays(String name,String surname){
        List<String> workDate = new ArrayList<>();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_WORK_DATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                workDate.add(resultSet.getString(Fields.SERVICE_RECORDS_DATE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return workDate;
    }


    public List<MasterRecordsBean> selectAllRecords(int start,int end){
        List<MasterRecordsBean> masterRecords = new ArrayList<>();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_RECORDS_BY_PAGE)) {
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,end);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                MasterRecordsBean masterRecordsBean = new MasterRecordsBean();
                masterRecordsBean.setRecordId(resultSet.getInt(Fields.ENTITY__ID));
                masterRecordsBean.setService(resultSet.getString(Fields.SERVICE_TITLE));
                masterRecordsBean.setClientName(resultSet.getString(Fields.USER_NAME));
                masterRecordsBean.setClientSurname(resultSet.getString(Fields.USER_SURNAME));
                masterRecordsBean.setDate(resultSet.getString(Fields.SERVICE_RECORDS_DATE));
                masterRecordsBean.setPrice(resultSet.getInt(Fields.SERVICE_PRICE));
                masterRecordsBean.setStatus(resultSet.getString(Fields.STATUS_NAME));
                masterRecordsBean.setTimeslot(resultSet.getString(Fields.SERVICE_RECORDS_TIMESLOT));
                masterRecordsBean.setMasterName(resultSet.getString(Fields.MASTER_NAME));
                masterRecordsBean.setMasterSurname(resultSet.getString(Fields.MASTER_SURNAME));
                masterRecords.add(masterRecordsBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return masterRecords;
    }

    public MasterRecordsBean selectRecordById(int recordId){
        MasterRecordsBean masterRecord = new MasterRecordsBean();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_RECORD_BY_ID)) {
            preparedStatement.setInt(1,recordId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                masterRecord.setRecordId(resultSet.getInt(Fields.ENTITY__ID));
                masterRecord.setService(resultSet.getString(Fields.SERVICE_TITLE));
                masterRecord.setDate(resultSet.getString(Fields.SERVICE_RECORDS_DATE));
                masterRecord.setPrice(resultSet.getInt(Fields.SERVICE_PRICE));
                masterRecord.setMasterName(resultSet.getString(Fields.MASTER_NAME));
                masterRecord.setMasterSurname(resultSet.getString(Fields.MASTER_SURNAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return masterRecord;
    }

    public int selectCountOfRows(){
        int count = 0;
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COUNT_OF_ROWS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                count = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return count;
    }

    public List<MasterRecordsBean> selectRecordsByUser(int userId,int statusId){
        List<MasterRecordsBean> masterRecords = new ArrayList<>();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DONE_RECORDS_BY_USER)) {
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,statusId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                MasterRecordsBean masterRecordsBean = new MasterRecordsBean();
                masterRecordsBean.setRecordId(resultSet.getInt(Fields.ENTITY__ID));
                masterRecordsBean.setService(resultSet.getString(Fields.SERVICE_TITLE));
                masterRecordsBean.setDate(resultSet.getString(Fields.SERVICE_RECORDS_DATE));
                masterRecordsBean.setPrice(resultSet.getInt(Fields.SERVICE_PRICE));
                masterRecordsBean.setMasterName(resultSet.getString(Fields.MASTER_NAME));
                masterRecordsBean.setMasterSurname(resultSet.getString(Fields.MASTER_SURNAME));
                masterRecordsBean.setComment(resultSet.getString(Fields.FEEDBACK_COMMENT));
                masterRecordsBean.setStatus(resultSet.getString(Fields.STATUS_NAME));
                masterRecordsBean.setTimeslot(resultSet.getString(Fields.SERVICE_RECORDS_TIMESLOT));
                masterRecords.add(masterRecordsBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return masterRecords;
    }


    public List<MasterRecordsBean> selectConfirmedRecordsByUSer(int userId,int statusId){
        List<MasterRecordsBean> masterRecords = new ArrayList<>();
        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CONFIRMED_RECORDS_BY_USER)) {
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,statusId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                MasterRecordsBean masterRecordsBean = new MasterRecordsBean();
                masterRecordsBean.setService(resultSet.getString(Fields.SERVICE_TITLE));
                masterRecordsBean.setDate(resultSet.getString(Fields.SERVICE_RECORDS_DATE));
                masterRecordsBean.setPrice(resultSet.getInt(Fields.SERVICE_PRICE));
                masterRecordsBean.setMasterName(resultSet.getString(Fields.MASTER_NAME));
                masterRecordsBean.setMasterSurname(resultSet.getString(Fields.MASTER_SURNAME));
                masterRecordsBean.setStatus(resultSet.getString(Fields.STATUS_NAME));
                masterRecordsBean.setTimeslot(resultSet.getString(Fields.SERVICE_RECORDS_TIMESLOT));
                masterRecords.add(masterRecordsBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return masterRecords;
    }


}
