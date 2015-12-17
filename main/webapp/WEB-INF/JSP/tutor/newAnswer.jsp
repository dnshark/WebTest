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
  <form:form method="POST" action="${context }/tutor/new/answer/id{newAnswerForm.questionId}" commandName="newAnswerForm">
    <table>
      <tr>
        <td>
          Question:
          <form:hidden path="questionId" />
        </td>
        <td>
          <form:checkbox path="correct" value="correct"/>
        </td>
        <td>
          <form:input path="name" />
        </td>
      </tr>

      <tr>
        <td>
          <input type="submit" value="Save answer"/>
        </td>
      </tr>
    </table>
  </form:form>
</div>

