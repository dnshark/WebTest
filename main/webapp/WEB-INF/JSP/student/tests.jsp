<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div id="maincontent_container">
		<div id="maincontent">
			<h1>Tests available!</h1>
			<div class="main_box">
				<c:forEach var="test" items="${tests}">

					<c:if test="${mode == 'online'}">
						<li><sp><a href="/test/start/id${test.id}">${test.name}</a></sp></li>
					</c:if>
					<c:if test="${mode == 'offline'}">
						<li><sp><a href="/offTest/id${test.id}" download="${test.name}.html">${test.name}</a></sp></li>
					</c:if>

				</c:forEach>

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
		</div>
	</div>
</div>
