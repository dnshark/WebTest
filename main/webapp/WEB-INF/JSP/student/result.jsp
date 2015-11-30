<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

  <h1>Result</h1>

  <table align="center">
    <c:forEach var="result" items="${results}">
      <tr>
        <td>
           ${result.testName}
        </td>
        <td>
            ${result.correctAnswer}
        </td>
        <td>
            ${result.allCount}
        </td>
        <td>
            ${result.created}
        </td>
      </tr>
    </c:forEach>
  </table>


</div>