package by.htp.ex.dao.impl;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.concurrent.locks.ReentrantLock;

public class UserDAO implements IUserDAO {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final ReentrantLock locker = new ReentrantLock();

    @Override
    public boolean logination(String login, String password) throws DaoException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=?")) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return BCrypt.checkpw(password, resultSet.getString("password"));
            }
            return false;
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        }
    }

    @Override
    public String getRole(String login) throws DaoException {
        String role = "guest";

        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT role_name FROM roles INNER JOIN users ON users.roles_id=roles.id WHERE login=?")) {

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role_name");
            }
            return role;
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        }
    }

    @Override
    public int getUsersId(String login) throws DaoException {
        int id = -1;

        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM users WHERE login=?")) {

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        }
    }

    @Override
    public boolean registration(String login, String password, String name, String surname, String email, String birthday) throws DaoException {
        int id;
        try (Connection connection = CONNECTION_POOL.takeConnection()) {
            connection.setAutoCommit(false);
            locker.lock();
            if (checkLogin(login)) {
                return false;
            }
            id = insertNewUser(login, password);
            insertNewUserDetails(id, name, surname, email, birthday);

            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        } finally {
            locker.unlock();
        }
        return true;
    }

    private int insertNewUser(String login, String password) throws DaoException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users(login, password, roles_id) VALUES(?, ?, ?)")) {
            statement.setString(1, login);
            statement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            statement.setInt(3, 3);
            statement.executeUpdate();
            return getIdByLogin(login);
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        }
    }

    private void insertNewUserDetails(int id, String name, String surname, String email, String birthday) throws DaoException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user_details(user_id, uname, surname, email, birthday) VALUES(?, ?, ?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, email);
            statement.setDate(5, Date.valueOf(birthday));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        }
    }

    private boolean checkLogin(String login) throws DaoException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=?")) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        }
    }

    private int getIdByLogin(String login) throws DaoException {
        int id = 0;
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=?")) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new DaoException("failed to connection");
        }
    }
}
