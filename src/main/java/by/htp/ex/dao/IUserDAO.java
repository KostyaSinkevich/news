package by.htp.ex.dao;

import by.htp.ex.bean.UserInfo;

public interface IUserDAO {

    boolean logination(String login, String password) throws DaoException;

    boolean registration(String login, String password, String name, String email) throws DaoException;

    String getRole(String login, String password) throws DaoException;

    void addNewUser(String login, String password, String name, String email) throws DaoException;

}
