package by.htp.ex.dao.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;

public class NewsDAO implements INewsDAO {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public List<News> getLatestsList(int count) throws NewsDAOException {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM news WHERE status_active=1 ORDER BY publication_date DESC LIMIT ?")) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();
            return getNews(newsList, resultSet);
        } catch (SQLException e) {
            throw new NewsDAOException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException("failed to connection");
        }
    }

    @Override
    public List<News> getList() throws NewsDAOException {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM news WHERE status_active=true ORDER BY publication_date DESC");
             ResultSet resultSet = statement.executeQuery()) {
            return getNews(newsList, resultSet);
        } catch (SQLException e) {
            throw new NewsDAOException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException("failed to connection");
        }
    }

    private List<News> getNews(List<News> newsList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Date publicationDate = resultSet.getDate("publication_date");
            String date = publicationDate.toString();
            newsList.add(new News(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    date,
                    resultSet.getString("brief"),
                    resultSet.getString("content")));
        }
        return newsList;
    }

    @Override
    public News fetchById(int id) throws NewsDAOException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM news WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            Date publicationDate = resultSet.getDate("publication_date");
            String date = publicationDate.toString();
            return new News(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    date,
                    resultSet.getString("brief"),
                    resultSet.getString("content"));
        } catch (SQLException e) {
            throw new NewsDAOException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException("failed to connection");
        }
    }

    @Override
    public void addNews(String title, String newsDate, String newsBrief, String newsContent, int usersId) throws NewsDAOException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO news(title, brief, content, publication_date, status_active, users_id) VALUES (?,?,?,?,?,?)")) {
            statement.setString(1, title);
            statement.setString(2, newsBrief);
            statement.setString(3, newsContent);
            statement.setDate(4, Date.valueOf(LocalDate.parse(newsDate)));
            statement.setBoolean(5, true);
            statement.setInt(6, usersId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NewsDAOException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException("failed to connection");
        }
    }

    @Override
    public void updateNews(int id, String title, String newsDate, String newsBrief, String newsContent) throws NewsDAOException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE news SET title=?, brief=?, content =?, publication_date=? WHERE id=?")) {
            statement.setString(1, title);
            statement.setString(2, newsBrief);
            statement.setString(3, newsContent);
            statement.setDate(4, Date.valueOf(newsDate));
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new NewsDAOException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException("failed to connection");
        }
    }

    @Override
    public void deleteSelectedNewses(String[] idNewses) throws NewsDAOException {
        try (Connection connection = CONNECTION_POOL.takeConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE news SET status_active=? WHERE id=?")) {

            for (String idNews : idNewses) {
                int id = Integer.parseInt(idNews);
                statement.setBoolean(1, false);
                statement.setInt(2, id);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new NewsDAOException("problem while working with the database");
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException("failed to connection");
        }
    }
}
