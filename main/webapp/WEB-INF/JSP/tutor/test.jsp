<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
  <form action="questionNew" method="get">
  <h1>Test list</h1>
  <table align="center">
    <c:forEach var="test" items="${tests}">
      <tr>
        <td>
          <a href="/editquestion/id${test.id}">${test.name}</a>
        </td>
      </tr>
    </c:forEach>
    <tr>
      <td colspan="4" style="text-align:center;">
        <input type="submit"  name="button" value="add"/>
      </td>
    </tr>

  </table>
    </form>

</div>
