package epam.nazaruk.final_project.service;

import epam.nazaruk.final_project.db.dao.UserDAO;
import epam.nazaruk.final_project.db.entity.User;
import epam.nazaruk.final_project.db.exception.AlreadyExistRecordException;
import epam.nazaruk.final_project.db.exception.DAOException;
import epam.nazaruk.final_project.db.exception.NoSuchRecordException;
import epam.nazaruk.final_project.service.encrypt.Encrypt;
import epam.nazaruk.final_project.service.exception.AlreadyExistRecordExceptionService;
import epam.nazaruk.final_project.service.exception.EntityNotFoundExceptionService;
import epam.nazaruk.final_project.service.exception.ServiceException;

import java.security.NoSuchAlgorithmException;

public class AuthorizationsService {

    public static User getUser(String login, String password) throws ServiceException {
        User user;

        try {
            user = new UserDAO().findUserByLogin(login);
            String encryptPassword = Encrypt.encrypt(password);
            if (user.getPassword().equals(encryptPassword)) {
                return user;
            }else {
                throw new NoSuchAlgorithmException();
            }
        }catch (NoSuchRecordException e) {
            throw new EntityNotFoundExceptionService("No such user");
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Wrong password");
        }
    }


    public static void createUser(User user) throws ServiceException {
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.insertIntoUser(user);
        }catch (AlreadyExistRecordException e){
            throw new AlreadyExistRecordExceptionService();
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }
}
