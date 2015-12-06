<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">

	<h1>Tests available!</h1>
	<table align="center">
		 <c:forEach var="test" items="${tests}">
			<tr>
				<td>
					<c:if test="${mode == 'online'}">
					<a href="/question/id${test.id}">${test.name}</a>
					</c:if>
					<c:if test="${mode == 'offline'}">
					<a href="/offTest/id${test.id}" download="${test.name}.html">${test.name}</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
