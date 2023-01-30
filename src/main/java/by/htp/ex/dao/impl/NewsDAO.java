package by.htp.ex.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;

public class NewsDAO implements INewsDAO {

    List<News> result = new ArrayList<>();

    private static int id;

    {
        result.add(new News(1, "title1", "brief1brief1brief1brief1brief1brief1brief1", "content1", "11/11/22"));
        result.add(new News(2, "title2", "brief2brief2brief2brief2brief2brief2brief2", "content2", "11/11/22"));
        result.add(new News(3, "title3", "brief3brief3brief3brief3brief3brief3brief3", "content3", "11/11/22"));
        result.add(new News(4, "title4", "brief4brief4brief4brief4brief4brief4brief4", "content4", "11/11/22"));
        result.add(new News(5, "title5", "brief5brief5brief5brief5brief5brief5brief5", "content5", "11/11/22"));
        id = 5;
    }

    @Override
    public List<News> getLatestsList(int count) throws NewsDAOException {
        return result;
    }

    @Override
    public List<News> getList() throws NewsDAOException {
        return result;
    }

    @Override
    public News fetchById(int id) throws NewsDAOException {
        for (News news : result) {
            if (id == news.getIdNews()) {
                return news;
            }
        }
        // TODO
        return null;
    }

    @Override
    public void addNews(String title, String newsDate, String newsBrief, String newsContent) throws NewsDAOException {
        id += 1;
        News news = new News(id, title, newsDate, newsBrief, newsContent);
        result.add(news);
    }

    @Override
    public News updateNews(int id, String title, String newsDate, String newsBrief, String newsContent) throws NewsDAOException {
        News news = fetchById(id);
        news.setTitle(title);
        news.setNewsDate(newsDate);
        news.setBriefNews(newsBrief);
        news.setContent(newsContent);
        return news;
    }

    @Override
    public void deleteSelectedNewses(String[] idNewses) throws NewsDAOException {
        for (String idNews : idNewses) {
            int id = Integer.parseInt(idNews);
            result.removeIf(news -> id == news.getIdNews());
        }
    }
}
