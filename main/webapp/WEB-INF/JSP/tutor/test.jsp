<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
  <div id="maincontent_container">
    <div id="maincontent">
      <form method="get">
        <h1>Test list</h1>
        <table  align="center">
          <tr>
            <td>
              <li><sp><a href="/tutor/new/test">Add new test</a></sp></li>
            </td>
          </tr>
          <c:forEach var="test" items="${tests}">
            <tr>
              <td>
                <a href="/tutor/edit/test/id${test.id}">${test.name}</a>
              </td>
            </tr>
          </c:forEach>
        </table>

        <div id="pagination"> <!--NEDIS как вынести в отдельный метод -->
          <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
              <c:when test="${page == i.index}">
                <span>${i.index}</span>
              </c:when>
              <c:otherwise>
                <c:url value="/admin/listUsers" var="url">
                  <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <c:url value="/admin/listUsers" var="next">
            <c:param name="page" value="${page + 1}"/>
          </c:url>
          <c:if test="${page + 1 < maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">Next</a>
          </c:if>
        </div>

      </form>
    </div>
  </div>
</div>
