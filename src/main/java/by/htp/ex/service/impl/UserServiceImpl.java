package by.htp.ex.service.impl;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.IUserService;
import by.htp.ex.util.validation.ValidationException;
import by.htp.ex.util.validation.impl.UserValidationImpl;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

    @Override
    public String signIn(String login, String password) throws ServiceException {
        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getRole(login);
            } else {
                return "guest";
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getUsersId(String login, String password) throws ServiceException {
        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getUsersId(login);
            }
            return -1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(String login, String password, String name, String surname, String email, String birthday) throws ServiceException, ValidationException {

        UserValidationImpl.UserValidationBuilder builder = new UserValidationImpl.UserValidationBuilder();
        UserValidationImpl userValidation = builder.checkLogin(login).checkPassword(password).checkName(name).checkSurname(surname).checkEmail(email).checkBirthday(birthday).validateAll();

        if (!userValidation.getErrors().isEmpty()) {
            throw new ValidationException(userValidation.errorMessage());
        }
        try {
            return userDAO.registration(login, password, name, surname, email, birthday);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
