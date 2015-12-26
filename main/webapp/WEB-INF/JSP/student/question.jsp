<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div id="maincontent_container">
        <div id="maincontent">
            <div id="studentQuestion">

                <form:form method="POST" action="next" commandName="testPassForm">
                <span id="trv"></span>
                <script>TRT()</script>

                <div class="timer">
                    <span id="theTime" class="timeClass"></span>
                </div>

                <h1>${testPassForm.question.name}</h1>

                <div class="main_box">

                    <c:forEach var="answer" items="${testPassForm.answers}">
                        <div class="form_field">
                            <div class="check-box">
                                <input type="checkbox" name="answer" id="${answer.id}" value="${answer.id}">
                            </div>
                            <strong> ${answer.name}</strong>
                        </div>
                    </c:forEach>
                    <div class="form_field">
                        <div class="check-box">
                            <input type="checkbox" name="answer" id="-1" value="-1">
                        </div>
                        <strong>Don't know</strong>
                    </div>

                    <div id="submit_button">
                        <input type="submit" name="Ok" value="Ok"/>
                    </div>
                </div>
            </div>


            </form:form>

        </div>
    </div>
</div>

<content tag="customscripts">
<script>
$(document).ready(function(){
initTime(${testPassForm.timePerQuestion},0);
});
</script>
</content>