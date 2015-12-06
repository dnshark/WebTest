<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
    <h1>${test.name}</h1>
    <table align="center">
        <c:forEach var="question" items="${test.questions}">
            <tr>
                <td>${question.name} </td>
            </tr>
            <c:forEach var="answer" items="${question.answers}">
                <tr>
                    <td>
                        <input type="checkbox" name="answer" > ${answer.name}
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>

        <input type="submit" name="Ok" value="Download"/>
    </table>
</div>