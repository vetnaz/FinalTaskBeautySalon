package epam.nazaruk.final_project.db.dao;

import epam.nazaruk.final_project.db.DBManager;
import epam.nazaruk.final_project.db.entity.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {

    private static final String SQL_INSERT_NEW_COMMENT = "insert into feedback(feedback_date, comment, service_records_id) VALUES (?,?,?)";

    public void insertIntoFeedback(Feedback feedback){
        Connection connection;
        connection = DBManager.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_COMMENT)){
            preparedStatement.setString(1,feedback.getFeedbackDate());
            preparedStatement.setString(2,feedback.getComment());
            preparedStatement.setInt(3,feedback.getServiceRecordId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
    }

}
