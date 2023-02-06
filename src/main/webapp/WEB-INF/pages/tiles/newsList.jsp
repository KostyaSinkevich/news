<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="locale.jsp"%>

<div class="body-title">
    <a href="">${newses} >> </a> ${news_list}
</div>

<form action="controller?command=delete_news" method="post">
    <c:forEach var="news" items="${requestScope.news}">
        <div class="single-news-wrapper">
            <div class="single-news-header-wrapper">
                <div class="news-title">
                    <c:out value="${news.title}"/>
                </div>
                <div class="news-date">
                    <c:out value="${news.newsDate}"/>
                </div>

                <div class="news-content">
                    <c:out value="${news.briefNews}"/>
                </div>
                <div class="news-link-to-wrapper">
                    <div class="link-position">
                        <c:if test="${sessionScope.role eq 'admin'}">
                            <a href="controller?command=go_to_edit_news&id=${news.idNews}">${edit} </a>
                        </c:if>

                        <a href="controller?command=go_to_view_news&id=${news.idNews}">${view} </a>

                        <c:if test="${sessionScope.role eq 'admin'}">
                            <input type="checkbox" name="id" value="${news.idNews}"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>
    <div class="single-news-wrapper">
        <div class="link-position">
            <c:if test="${sessionScope.role eq 'admin'}">
                <input type="submit" name="delete" value="${delete}"/>
            </c:if>
        </div>
    </div>

    <!-- 	<logic:notEmpty name="newsForm" property="newsList">
		<div class="delete-button-position">
			<html:submit>
				<bean:message key="locale.newslink.deletebutton" />
			</html:submit>
		</div>
	</logic:notEmpty> -->

    <div class="no-news">
        <c:if test="${requestScope.news eq null}">
            No news.
        </c:if>
    </div>
</form>