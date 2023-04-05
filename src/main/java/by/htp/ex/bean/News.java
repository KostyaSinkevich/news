package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idNews;
    private String title;
    private String briefNews;
    private String content;
    private String newsDate;
    private boolean status;

    public News(int idNews, String title, String newsDate, String briefNews, String content) {
        super();
        this.idNews = idNews;
        this.title = title;
        this.newsDate = newsDate;
        this.briefNews = briefNews;
        this.content = content;
        this.status = true;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefNews() {
        return briefNews;
    }

    public void setBriefNews(String briefNews) {
        this.briefNews = briefNews;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		News news = (News) o;
		return idNews.equals(news.idNews)
				&& Objects.equals(title, news.title)
				&& Objects.equals(briefNews, news.briefNews)
				&& Objects.equals(content, news.content)
				&& Objects.equals(newsDate, news.newsDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNews, title, briefNews, content, newsDate);
	}
}
