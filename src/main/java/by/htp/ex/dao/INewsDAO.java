package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.News;


public interface INewsDAO {
    List<News> getList() throws NewsDAOException;

    List<News> getLatestsList(int count) throws NewsDAOException;

    News fetchById(int id) throws NewsDAOException;

    void addNews(String title, String newsDate, String newsBrief, String newsContent, int usersId) throws NewsDAOException;

    void updateNews(int id, String title, String newsDate, String newsBrief, String newsContent) throws NewsDAOException;

    void deleteSelectedNewses(String[] idNewses) throws NewsDAOException;
}
