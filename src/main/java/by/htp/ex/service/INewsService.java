package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.util.validation.ValidationException;

public interface INewsService {

    List<News> latestList(int count) throws ServiceException;

    List<News> list() throws ServiceException;

    News findById(int id) throws ServiceException;

    void deleteSelectedNews(String[] idNewses) throws ServiceException;

    void update(int id, String title, String newsDate, String newsBrief, String newsContent) throws ServiceException, ValidationException;

    void addNews(String title, String newsDate, String newsBrief, String newsContent, int usersId) throws ServiceException, ValidationException;

}
