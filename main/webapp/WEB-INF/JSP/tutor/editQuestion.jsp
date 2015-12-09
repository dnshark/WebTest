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
<form:form  commandName="questionEditForm">
  <table align="center">
    <tr>
      <td>
        Question name
      </td>
      <td>
        <form:input path="question.name" />
      </td>
    </tr>
    <c:forEach items="${question.answers}" var="currentAnswer" varStatus="index">
      <form:form method="post" action = "edit" commandName="answer">
        <tr>
          <td><form:checkbox path="correct"/> value="${currentAnswer.correct}"</td>
          <td><form:input path="name"/> value="${currentAnswer.name}"</td>
        </tr>
      </form:form>
    </c:forEach>


    <c:forEach var="answer" items="${question.answers}">
      <tr>
     <!--   <td>  <input type="checkbox"  name="checks" value="${answer.id}" checked="answer.correct"/> </td>
        <td>  <input type="text"  name="answer.name" value="${answer.name}"/> </td> -->
        <td><form:checkbox path="answer.correct"/></td>
        <td><form:input path="answer.name"/></td>
      </tr>
    </c:forEach>
    <tr>
      <td>
        <a href="/tutor/editQuestion/new">New question</a>
      </td>
      <td>
        <a href="/tutor/editTest/test${question}">Save test</a>
      </td>
      <td>
        <a href="/tutor/deleteTest${question}">Delete</a>
      </td>
    </tr>
  </table>
</form:form>
</div>
