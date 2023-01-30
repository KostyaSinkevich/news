package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.NewsDAOException;

public interface INewsService {
    void save();

    void find();

    List<News> latestList(int count) throws ServiceException;

    List<News> list() throws ServiceException;

    News findById(int id) throws ServiceException;

    void deleteSelectedNews(String[] idNewses) throws ServiceException;

    News update(int id, String title, String newsDate, String newsBrief, String newsContent) throws ServiceException;

    void addNews(String title, String newsDate, String newsBrief, String newsContent) throws ServiceException;

}
