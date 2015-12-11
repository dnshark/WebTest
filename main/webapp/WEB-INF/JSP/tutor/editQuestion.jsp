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
  <form:form method="POST" action="${context }/tutor/editQuestion/Ok" commandName="questionEditForm">
  <table align="center">
    <tr>
      <td>
        Question:
        <form:hidden path="questionId" />
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
          <a href="/tutor/deleteAnswer${answer.id}">Delete </a>
        </td>
      </tr>
    </c:forEach>
    <tr>
      <td>
        <input type="submit" value="Save question"/>
      </td>
      <td>
        <a href="/tutor/newAnswer${questionId}">New answer</a>
      </td>
      <td>
        <a href="/tutor/deleteQuestion${questionId}">Delete question</a>
      </td>
    </tr>
  </table>
    </form:form>
</div>
