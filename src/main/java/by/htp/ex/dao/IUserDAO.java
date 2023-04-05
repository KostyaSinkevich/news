package by.htp.ex.dao;

public interface IUserDAO {

    boolean logination(String login, String password) throws DaoException;

    boolean registration(String login, String password, String name, String surname, String email, String birthday) throws DaoException;

    String getRole(String login) throws DaoException;

    int getUsersId(String login) throws DaoException;

}
