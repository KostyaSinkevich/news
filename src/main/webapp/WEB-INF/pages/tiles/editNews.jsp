<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="body-title">
    <a href="">News >> </a> Edit News
</div>

<form action="controller" method="post">
    <div class="add-table-margin">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text">News Title</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <input type="text" name="title" id="title" placeholder="Enter the title">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">News Date</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <input type="text" name="date" placeholder="Enter the date">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">Brief</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <textarea name="brief" placeholder="Enter the brief"></textarea>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="space_around_title_text">Content</td>
                <td class="space_around_view_text">
                    <div class="word-breaker">
                        <textarea name="content" placeholder="Enter the content"></textarea>
                    </div>
                </td>
            </tr>
        </table>
    </div>


    <div class="first-view-button">
        <input type="hidden" name="command" value="save_edited_news"/>
        <input type="hidden" name="id" value="${requestScope.news.idNews}"/>
        <input type="submit" value="Save"/>
    </div>
</form>

<div class="second-view-button">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="go_to_view_news"/>
        <input type="hidden" name="id" value="${requestScope.news.idNews}"/>
        <input type="submit" value="Cancel"/>
    </form>
</div>