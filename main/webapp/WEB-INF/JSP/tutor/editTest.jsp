<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
  <form:form action="/tutor/addTest" commandName="testForm">
    <h1>New test</h1>
    <table align="center">
      <tr>
        <td>Test name</td>
        <td><form:input path="test.name"/> </td>
      </tr>
      <tr>
        <td>Test description</td>
        <td><form:input path="test.description"/> </td>
      </tr>
      <tr>
        <td>Time per question</td>
        <td><form:input path="test.timePerQuestion"/> </td>
      </tr>
      <c:forEach var="question" items="${test.questions}">
        <tr>
          <td>Question</td>
          <td><form:input path="${question.name}"/> </td>
        </tr>
        <c:forEach var="answer" items="${question.answers}">
          <tr>
            <td>Answer</td>
            <td>
              <input type="checkbox" name="answer" > ${answer.name}
            </td>
            <td>
              <form:input path="${answer.name}"/>
            </td>
          </tr>
        </c:forEach>
      </c:forEach>
      <tr>
        <td>
          <a href="/tutor/editTest">Add new test</a>
          <input type="submit" value="Delete"/>
        </td>
      </tr>
    </table>
  </form:form>

</div>