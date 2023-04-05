<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<body>
<h1>Error occurred, please, try again</h1>

<c:if test="${not (sessionScope.validationError eq null)}">
    <c:forEach var="error" items="${sessionScope.validationError}">
        <font color="red">
            <c:out value="${error}"/>
        </font>
        <br/>
    </c:forEach>
</c:if>

<form action="controller" method="post">
    <input type="hidden" name="command" value="go_to_news_list"/>
    <input type="submit" value="Back"/>
</form>
</body>
</html>