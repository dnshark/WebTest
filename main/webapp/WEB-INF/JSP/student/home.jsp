<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="../templates/header.jsp" />
<div class="container">

	<h1>Tests available!</h1>
	<table>
		 <c:forEach var="tests" items="${tests}">
			<tr>
				<td><a href="/tests/id${tests.getId()}">${tests.getName()}</a></td>
			</tr>
		</c:forEach>
	</table>

</div>
<jsp:include page="../templates/footer.jsp" />
