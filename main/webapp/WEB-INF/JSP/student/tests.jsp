<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

	<h1>Tests available!</h1>
	<table>
		 <c:forEach var="test" items="${tests}">
			<tr>
				<td>
					<c:if test="${mode == 'online'}">
					<a href="/test/start/id${test.id}">${test.name}</a>
					</c:if>
					<c:if test="${mode == 'offline'}">
					<a href="/offTest/id${test.id}" download="${test.name}.html">${test.name}</a>
					</c:if>
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
						<c:url value="/tests" var="url">
							<c:param name="page" value="${i.index}"/>
						</c:url>
						<a href='<c:out value="${url}" />'>${i.index}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:url value="/tests" var="next">
				<c:param name="page" value="${page + 1}"/>
			</c:url>
			<c:if test="${page + 1 < maxPages}">
				<a href='<c:out value="${next}" />' class="pn next">Next</a>
			</c:if>
		</div>

</div>
