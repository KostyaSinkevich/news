package by.htp.ex.service;

import by.htp.ex.util.validation.ValidationException;

public interface IUserService {

    String signIn(String login, String password) throws ServiceException;

    boolean registration(String login, String password, String name, String surname, String email, String birthday) throws ServiceException, ValidationException;

    int getUsersId(String login, String password) throws ServiceException;

}
