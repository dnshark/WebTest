<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
  <form:form method="POST" action="next" commandName="loginForm" >
  <h1>Questions!</h1>

  <h2>${question.name}</h2>

  <table align="center">
    <c:forEach var="answer" items="${answers}">
      <tr>
        <td>
          <input type="checkbox" name="answer" id="${answer.id}" value="${answer.id}"> ${answer.name}
        </td>
      </tr>
    </c:forEach>
  </table>

    <input type="submit" name="Ok" value="Ok"/>

  </form:form>

</div>