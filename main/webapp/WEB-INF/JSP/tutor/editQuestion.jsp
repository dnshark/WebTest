<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 09.12.2015
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
  <div id="maincontent_container">
    <div id="maincontent">
      <form:form method="POST" action="${context }/tutor/edit/question/ok" commandName="questionEditForm">
        <table align="center">
          <form:hidden path="testId" />
          <form:hidden path="questionId" />
          <tr>
            <td>
              Question:
            </td>
            <td>
              <form:input path="questionName" />
            </td>

          </tr>
          <c:forEach var="answer" items="${answers}">
            <tr>
              <td>
                <input type="checkbox" name="cbItemList" id="${answer.id}" value="${answer.id}"
                <c:if test="${answer.correct}"> checked </c:if>>
              </td>
              <td>
                <input type="text"  name="answerName" value="${answer.name}"/>
                <input type="hidden"  name="answerId" value="${answer.id}"/>
              </td>
              <td>
                <a href="/tutor/delete/answer?questionId=${questionEditForm.questionId}&answerId=${answer.id}">Delete </a>
              </td>
            </tr>
          </c:forEach>
          <tr>
            <c:if test="${mode == 'new'}">
              <td>
                <input type="submit" value="Add" onclick="form.action='/tutor/edit/question/add';">
              </td>
            </c:if>
            <c:if test="${mode == 'edit'}">
              <td>
                <a href="/tutor/new/answer/id${questionEditForm.questionId}">New answer</a>
              </td>
              <td>
                <input type="submit" value="Save question">
              </td>
              <td>
                <a href="/tutor/delete/question?questionId=${questionEditForm.questionId}">Delete question</a>
              </td>
            </c:if>
          </tr>
        </table>
      </form:form>
    </div>
  </div>
</div>
