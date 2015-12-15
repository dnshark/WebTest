<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

  <h1>Result</h1>

  <table align="center" border="2">
    <tr>
      <td>
        Test name
      </td>
      <td>
        User correct answers
      </td>
      <td>
        All correct answers
      </td>
      <td>
        Date
      </td>
    </tr>
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

  <div id="pagination">
    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
      <c:choose>
        <c:when test="${page == i.index}">
          <span>${i.index}</span>
        </c:when>
        <c:otherwise>
          <c:url value="/allAccess/result" var="url">
            <c:param name="sizeoff" value="${i.index}"/>
          </c:url>
          <a href='<c:out value="${url}" />'>${i.index}</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:url value="/allAccess/result" var="next">
      <c:param name="sizeoff" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 < maxPages}">
      <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
  </div>
</div>
