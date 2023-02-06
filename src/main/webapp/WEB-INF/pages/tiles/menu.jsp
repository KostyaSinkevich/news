<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="locale.jsp"%>


<div class="menu-wrapper">
    <div class="menu-title-wrapper">
        <div class="menu-title">
            ${newses}
        </div>
    </div>

    <div class="list-menu-invisible-wrapper">
        <div class="list-menu-wrapper" style="float: right;">
            <ul style="list-style-image: url(images/img.jpg); text-align: left;">
                <li style="padding-left: 15px;">

                    <a href="">${news_list}</a><br/>
                </li>

                <c:if test="${sessionScope.role eq 'admin'}">
                    <li style="padding-left: 15px;">

                        <a href="controller?command=go_to_add_news">${add_news}</a>

                        <br/>

                    </li>
                </c:if>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <!--  grey free space at the bottom of menu -->
    <div style="height: 25px;"></div>
</div>

