<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">

  <form:form method="POST" action="next" commandName="answerForm" >
  <span id="trv"></span><script>TRT ()</script>

    <table width="100%">
      <tr><td width="100%" align="center"><span id="theTime" class="timeClass"></span></td></tr>
    </table>

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
    <tr>
      <td>
        <input type="checkbox" name="answer" id="-1" value="-1"> Don't know
      </td>
    </tr>
  </table>

    <input type="submit" name="Ok" value="Ok"/>

  </form:form>

</div>