<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 09.12.2015
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <div id="maincontent_container">
        <div id="maincontent">
            <div id="newAnswer">
            <form:form method="POST" action="${context }/tutor/new/answer/id{newAnswerForm.questionId}"
                       commandName="newAnswerForm">
                <form:hidden path="questionId"/>
                <div class="main_box">
                    <div class="form_field">
                    <strong>Answer:</strong>

                    <form:input path="name"/>
                    <form:checkbox path="correct" value="correct"/>
                    </div>

                    <div id="submit_button">
                            <input type="submit" value="Save answer"/>
                    </div>
                   </div>
            </form:form>
                </div>
        </div>
    </div>
</div>

