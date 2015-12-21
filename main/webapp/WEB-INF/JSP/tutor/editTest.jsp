<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <div id="maincontent_container">
        <div id="maincontent">

            <form:form action="/tutor/edit/test/ok" commandName="testForm">
                <h1>Edit test</h1>
                <form:hidden path="idTest"/>
                <table align="center">
                    <tr>
                        <td>Test name</td>
                        <td><form:input path="name"/></td>
                    </tr>
                    <tr>
                        <td>Time per question</td>
                        <td><form:input path="timePerQuestion"/></td>
                    </tr>
                    <tr>
                        <td>
                            Description
                        </td>
                        <td><form:input path="description"/></td>
                    </tr>

                    <c:forEach var="question" items="${testForm.testQuestions}">
                        <tr>
                            <td>
                                <a href="/tutor/edit/question?testId=${testForm.idTest}&questionId=${question.id}"> ${question.name}</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <div id="pagination">
                        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                            <c:choose>
                                <c:when test="${page == i.index}">
                                    <span>${i.index}</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/tutor/edit/test/id${testForm.idTest}" var="url">
                                        <c:param name="page" value="${i.index}"/>
                                    </c:url>
                                    <a href='<c:out value="${url}" />'>${i.index}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:url value="/tutor/edit/test/id${testForm.idTest}" var="next">
                            <c:param name="page" value="${page + 1}"/>
                        </c:url>
                        <c:if test="${page + 1 < maxPages}">
                            <a href='<c:out value="${next}" />' class="pn next">Next</a>
                        </c:if>
                    </div>

                    <c:if test="${mode == 'edit'}">
                        <tr>
                            <td>
                                <a href="/tutor/edit/question/new?testId=${testForm.idTest}">New question</a>
                            </td>
                            <td>
                                <input type="submit" value="Save test" onclick="form.action='/tutor/edit/test/ok';">
                            </td>
                            <td>
                                <a href="/tutor/delete/test/id${testForm.idTest}">Delete</a>
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${mode == 'new'}">
                        <tr>
                            <td>
                                <input type="submit" value="Add" onclick="form.action='/tutor/add/test';">
                            </td>
                        </tr>
                    </c:if>
                </table>
            </form:form>
        </div>
    </div>
</div>