<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="locale.jsp" %>

<div class="wrapper">

    <div class="registration_form">

        <div class="title">
            <c:out value="Registration"/>
        </div>


        <form action="controller" method="post">
            <div class="form_wrap">
                <div class="input_grp">
                    <div class="input_wrap">
                        <label for="name"><c:out value="Name"/></label> <input type="text" id="name" name="name">
                    </div>
                    <div class="input_wrap">
                        <label for="surname"><c:out value="Surname"/></label> <input type="text" id="surname" name="surname">
                    </div>
                    <div class="input_wrap">
                        <label for="birthday"><c:out value="Birthday"/></label>
                        <input type="date" id="birthday" name="birthday" value="2000-01-01">
                    </div>
                </div>
                <div class="input_wrap">
                    <label for="email"><c:out value="Email"/></label> <input type="text" id="email" name="email">
                </div>

                <div class="input_wrap">
                    <label for="login"><c:out value="Login"/></label> <input type="text" id="login" name="login">
                </div>
                <div class="input_wrap">
                    <label for="password"><c:out value="Password"/></label> <input type="password" id="password" name="password">
                </div>
                <div class="input_wrap">
                    <label for="repeatPassword"><c:out value="Repeat password"/></label>
                    <input type="password" id="repeatPassword" name="repeatPassword">
                </div>
                <div class="input_wrap">
                    <input type="hidden" name="command" value="do_registration"/>
                    <input type="submit" value="Confirm">
                </div>
            </div>
        </form>
    </div>
</div>
