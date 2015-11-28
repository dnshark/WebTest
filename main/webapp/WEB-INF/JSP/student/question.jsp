<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

  <h1>Questions!</h1>

  <h2>${question.name}</h2>
  <table>
    <c:forEach var="answer" items="${answers}">
      <tr>
        <td>
          <input type="checkbox" name="answer" id="${answer.id}" value="${answer.id}"> ${answer.name}
        </td>
      </tr>
    </c:forEach>
  </table>
  <p>
    <form action="id${question.getId()}" method="post">
    <input type="submit" name="Ok" value="Ok"/>
    </form>
  </p>

</div>