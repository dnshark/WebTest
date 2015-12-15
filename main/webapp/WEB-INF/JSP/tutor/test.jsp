<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
  <form method="get">
  <h1>Test list</h1>
  <table align="center">
    <tr>
      <td>
        Tutor tests
      </td>
      <td>
        <a href="/tutor/new/test">Add new test</a>
      </td>
    </tr>
    <c:forEach var="test" items="${tests}">
      <tr>
        <td>
          <a href="/tutor/edit/test/id${test.id}?page=0&count=50">${test.name}</a>
        </td>
      </tr>
    </c:forEach>


  </table>
    </form>

</div>
