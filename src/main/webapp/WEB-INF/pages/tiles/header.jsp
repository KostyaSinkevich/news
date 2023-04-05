<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="locale.jsp"%>

<div class="wrapper">
    <div class="newstitle">
        News management
    </div>
    <div class="local-link">
        <div align="right">
            <a href="controller?command=change_locale&locale=en"> ${en} </a> &nbsp;&nbsp;
            <a href="controller?command=change_locale&locale=ru"> ${ru} </a> <br/> <br/>
        </div>

        <c:if test="${not (sessionScope.user eq 'active')}">

            <div align="right">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="do_sign_in"/>
                    ${login}: <input type="text" name="login" value=""/><br/>
                    ${password}: <input type="password" name="password" value=""/><br/>

                    <c:if test="${not (sessionScope.authenticationError eq null)}">
                        <font color="red">
                            <c:out value="${sessionScope.authenticationError}"/>
                            <c:remove var="authenticationError"/>
                        </font>
                    </c:if>

                    <a href="controller?command=do_sign_in">${registration}</a> <input type="submit" value="${sign_in}"/><br/>
                </form>
            </div>

        </c:if>

        <c:if test="${sessionScope.user eq 'active'}">

            <div align="right">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="do_sign_out"/>
                    <input type="submit" value="${sign_out}"/><br/>
                </form>
            </div>
        </c:if>
    </div>

</div>
