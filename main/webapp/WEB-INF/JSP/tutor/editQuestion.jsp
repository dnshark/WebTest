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
            <div id="editTest">
                <form:form method="POST" action="${context }/tutor/edit/question/ok" commandName="questionEditForm">
                    <div class="main_box">
                        <form:hidden path="testId"/>
                        <form:hidden path="questionId"/>
                        <div class="question_field">
                            <strong>Question:</strong>
                            <form:input path="questionName"/>
                        </div>
                        <div class="question_small_field">
                            <strong>Time Question:</strong>
                            <form:input path="time"/>
                        </div>
                        <div class="question_small_field">
                            <strong>Question weight:</strong>
                            <form:input path="weight"/>
                        </div>
                        <div class="answer_fields">
                            <c:forEach var="answer" items="${answers}">
                                <div class="answer_field">
                                    <div class="check-box">
                                        <input type="checkbox" name="cbItemList" id="${answer.id}" value="${answer.id}"

                                        <c:if test="${answer.correct}"> checked </c:if>>
                                    </div>
                                    <div class="answerText">
                                        <input type="text" name="answerName" value="${answer.name}"/>
                                    </div>
                                    <input type="hidden" name="answerId" value="${answer.id}"/>
                                    <div class="delLink">
                                        <a href="/tutor/delete/answer?questionId=${questionEditForm.questionId}&answerId=${answer.id}">Delete </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="field_buttons">
                            <c:if test="${mode == 'new'}">
                                <div id="submit_button">
                                    <input type="submit" value="Add" onclick="form.action='/tutor/edit/question/add';">
                                </div>
                            </c:if>
                            <c:if test="${mode == 'edit'}">
                                <div class="left_button">
                                    <input type="button" value="New answer" onclick="location.href='/tutor/new/answer/id${questionEditForm.questionId}'">
                                </div>
                                <div class="center_button">
                                    <input type="submit" value="Save question">
                                </div>
                                <div class="right_button">
                                    <input type="button" value="Delete" onclick="location.href='/tutor/delete/question?questionId=${questionEditForm.questionId}'"/>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
