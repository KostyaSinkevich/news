<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<fmt:message bundle="${loc}" key="local.greetings" var="greetings"/>
<fmt:message bundle="${loc}" key="local.newses" var="newses"/>
<fmt:message bundle="${loc}" key="local.news_list" var="news_list"/>
<fmt:message bundle="${loc}" key="local.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.view" var="view"/>
<fmt:message bundle="${loc}" key="local.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.sign_out" var="sign_out"/>
<fmt:message bundle="${loc}" key="local.add_news" var="add_news"/>