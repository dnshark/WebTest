<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

  <table align="center">
    <tr>
      <td>
        Test name
      </td>
      <td>
        <input type="text"  name="test.name" value="${test.name}" />
      </td>
    </tr>
    <tr>
      <td>
        Description
      </td>
      <td>
        <input type="text"  name="test.description" value="${test.description}"/>
      </td>
    </tr>
    <tr>
      <td>
        Time per question
      </td>
      <td>
        <input type="text"  name="test.timePerQuestion" value="${test.timePerQuestion}"/>
      </td>
    </tr>
    <c:forEach var="question" items="${test.questions}">
      <tr>
        <td> <a href="\editQuestion\id${question.id}"> ${question.name}</a> </td>
      </tr>
    </c:forEach>
    <tr>
      <td>
        <a href="/tutor/editQuestion/new">New question</a>
      </td>
      <td>
        <a href="/tutor/editTest/test${test}">Save test</a>
      </td>
      <td>
        <a href="/tutor/deleteTest${test}">Delete</a>
      </td>
    </tr>
  </table>

</div>