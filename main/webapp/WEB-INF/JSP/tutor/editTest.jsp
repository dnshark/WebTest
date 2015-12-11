<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

  <form:form action="/tutor/editTest/Ok" commandName="testForm">
    <h1>Edit test</h1>
    <form:hidden path="idTest"/>
    <table align="center">
      <tr>
        <td>Test name</td>
        <td><form:input path="name"/> </td>
      </tr>
      <tr>
        <td>Time per question</td>
        <td><form:input path="timePerQuestion"/> </td>
      </tr>
      <tr>
        <td>
          Description
        </td>
        <td><form:input path="description"/> </td>
      </tr>

    <c:forEach var="question" items="${testForm.testQuestions}">
      <tr>
        <td> <a href="/tutor/editQuestion/id${question.id}"> ${question.name}</a> </td>
      </tr>
    </c:forEach>
    <tr>
      <td>
        <a href="/tutor/editQuestion/new?testId=${testForm.idTest}">New question</a>  <!--NEDIS как передать id -->
      </td>
      <td>
        <a href="/tutor/editTest/test${test}">Save test</a>
        <form:button name="save" value="Save test">Save test</form:button>
      </td>
      <td>
        <a href="/tutor/deleteTest${testId}">Delete</a>
      </td>
    </tr>
  </table>
  </form:form>

</div>