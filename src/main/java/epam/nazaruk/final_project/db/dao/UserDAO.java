package epam.nazaruk.final_project.db.dao;

import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.db.DBManager;
import epam.nazaruk.final_project.db.Fields;
import epam.nazaruk.final_project.db.exception.AlreadyExistRecordException;
import epam.nazaruk.final_project.db.exception.DAOException;
import epam.nazaruk.final_project.db.exception.NoSuchRecordException;

import java.sql.*;

public class UserDAO {
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_INSERT_NEW_USER = "insert into users(login, password, name, surname, roles_id, email) values (?,MD5(?),?,?,3,?)";
    private static final String SQL_FIND_USER_BY_RECORD = "select name,surname,email from service_records,users where users_id = users.id and service_records.id = ?";

    public User findUserByLogin(String login) throws NoSuchRecordException {
        User user;
        Connection connection;
        connection = DBManager.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)){
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new SQLException();
            }

            user = mapRow(resultSet);
        } catch (SQLException e) {
            throw new NoSuchRecordException("There are not such user",e);
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return user;
    }

    public void insertIntoUser(User user) throws DAOException {
        Connection connection;
        connection = DBManager.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_USER)){
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getSurname());
            preparedStatement.setString(5,user.getEmail());

            preparedStatement.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new AlreadyExistRecordException();
        }catch (SQLException e) {
            throw new DAOException();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
    }

    public User findUserByOrder(int recordId){
        User user = new User();
        Connection connection;
        connection = DBManager.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_RECORD)){
            preparedStatement.setInt(1,recordId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user.setName(resultSet.getString(Fields.USER_NAME));
                user.setSurname(resultSet.getString(Fields.USER_SURNAME));
                user.setEmail(resultSet.getString(Fields.USER_EMAIL));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return user;
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
            user.setId(resultSet.getInt(Fields.ENTITY__ID));
            user.setLogin(resultSet.getString(Fields.USER_LOGIN));
            user.setPassword(resultSet.getString(Fields.USER_PASSWORD));
            user.setName(resultSet.getString(Fields.USER_NAME));
            user.setSurname(resultSet.getString(Fields.USER_SURNAME));
            user.setRoleId(resultSet.getInt(Fields.USER_ROLES_ID));
        return user;
    }
}
