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
					<a href="/question/id${test.id}">${tests.name}</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
