<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
  <h1> Users list</h1>

  <table align="center">
    <c:forEach var="user" items="${users}">
      <tr>
        <td>
          <a href="/admin/user/id${user.id}">${user.fio}</a>
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
          <c:url value="/admin/list/users" var="url">
            <c:param name="page" value="${i.index}"/>
          </c:url>
          <a href='<c:out value="${url}" />'>${i.index}</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:url value="/admin/list/users" var="next">
      <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 < maxPages}">
      <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
  </div>
</div>
