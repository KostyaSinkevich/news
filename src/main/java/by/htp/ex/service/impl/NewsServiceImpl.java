package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;

public class NewsServiceImpl implements INewsService {

    private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

    @Override
    public void save() {
        // TODO Auto-generated method stub
    }

    @Override
    public void find() {
        // TODO Auto-generated method stub
    }

    @Override
    public void addNews(String title, String newsDate, String newsBrief, String newsContent) throws ServiceException {
        try {
            newsDAO.addNews(title, newsDate, newsBrief, newsContent);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public News update(int id, String title, String newsDate, String newsBrief, String newsContent) throws ServiceException {
        try {
            return newsDAO.updateNews(id, title, newsDate, newsBrief, newsContent);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> latestList(int count) throws ServiceException {
        try {
            return newsDAO.getLatestsList(5);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> list() throws ServiceException {
        try {
            return newsDAO.getList();
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public News findById(int id) throws ServiceException {
        try {
            return newsDAO.fetchById(id);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteSelectedNews(String[] idNewses) throws ServiceException {
        try {
            newsDAO.deleteSelectedNewses(idNewses);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }
}
